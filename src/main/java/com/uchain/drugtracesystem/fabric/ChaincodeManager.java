package com.uchain.drugtracesystem.fabric;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.uchain.drugtracesystem.fabric.bean.Chaincode;
import com.uchain.drugtracesystem.fabric.bean.Orderers;
import com.uchain.drugtracesystem.fabric.bean.Peers;
import com.uchain.drugtracesystem.result.Result;
import org.apache.log4j.Logger;
import org.hyperledger.fabric.sdk.*;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric.sdk.exception.TransactionException;
import org.hyperledger.fabric.sdk.security.CryptoSuite;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 只能合约操作总控制器
 */
public class ChaincodeManager {

    private static Logger log = Logger.getLogger(ChaincodeManager.class);

    private FabricConfig config;
    private Orderers orderers;
    private Peers peers;
    private Chaincode chaincode;

    private HFClient client;
    private FabricOrg fabricOrg;
    private Channel channel;
    private ChaincodeID chaincodeID;

    public ChaincodeManager(FabricConfig fabricConfig)
            throws CryptoException, InvalidArgumentException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, IOException, TransactionException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.config = fabricConfig;

        orderers = this.config.getOrderers();
        peers = this.config.getPeers();
        chaincode = this.config.getChaincode();

        //生成一个Hyperledger Fabric的客户端
        client = HFClient.createNewInstance();
        log.debug("Create instance of HFClient");
        client.setCryptoSuite(CryptoSuite.Factory.getCryptoSuite());
        log.debug("Set Crypto Suite of HFClient");

        //获取FaricOrg组织信息
        fabricOrg = getFabricOrg();
        channel = getChannel();
        chaincodeID = getChaincodeID();

        client.setUserContext(fabricOrg.getPeerAdmin()); // 也许是1.0.0测试版的bug，只有节点管理员可以调用链码
    }

    /**
     * 获取FaricOrg组织信息
     */
    private FabricOrg getFabricOrg() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, IOException {

        // java.io.tmpdir : C:\Users\yangyi47\AppData\Local\Temp\
        File storeFile = new File(System.getProperty("java.io.tmpdir") + "/HFCSampletest.properties");
        //根据文件创建FabricStore对象
        FabricStore fabricStore = new FabricStore(storeFile);

        // Get Org1 from configuration
        FabricOrg fabricOrg = new FabricOrg(peers, orderers, fabricStore, config.getCryptoConfigPath());
        log.debug("Get FabricOrg");
        return fabricOrg;
    }

    private Channel getChannel()
            throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, IOException, CryptoException, InvalidArgumentException, TransactionException {
        client.setUserContext(fabricOrg.getPeerAdmin());
        return getChannel(fabricOrg, client);
    }

    private Channel getChannel(FabricOrg fabricOrg, HFClient client)
            throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, IOException, CryptoException, InvalidArgumentException, TransactionException {
        Channel channel = client.newChannel(chaincode.getChannelName());
        log.debug("Get Chain " + chaincode.getChannelName());

//        channel.setTransactionWaitTime(chaincode.getInvokeWatiTime());
//        channel.setDeployWaitTime(chaincode.getDeployWatiTime());

        for (int i = 0; i < peers.get().size(); i++) {
            File peerCert = Paths.get(config.getCryptoConfigPath(), "/peerOrganizations", peers.getOrgDomainName(), "peers", peers.get().get(i).getPeerName(), "tls/server.crt")
                    .toFile();
            if (!peerCert.exists()) {
                throw new RuntimeException(
                        String.format("Missing cert file for: %s. Could not find at location: %s", peers.get().get(i).getPeerName(), peerCert.getAbsolutePath()));
            }
            Properties peerProperties = new Properties();
            peerProperties.setProperty("pemFile", peerCert.getAbsolutePath());
            // ret.setProperty("trustServerCertificate", "true"); //testing
            // environment only NOT FOR PRODUCTION!
            peerProperties.setProperty("hostnameOverride", peers.getOrgDomainName());
            peerProperties.setProperty("sslProvider", "openSSL");
            peerProperties.setProperty("negotiationType", "TLS");
            // 在grpc的NettyChannelBuilder上设置特定选项
            peerProperties.put("grpc.ManagedChannelBuilderOption.maxInboundMessageSize", 9000000);
            channel.addPeer(client.newPeer(peers.get().get(i).getPeerName(), fabricOrg.getPeerLocation(peers.get().get(i).getPeerName()), peerProperties));
            if (peers.get().get(i).isAddEventHub()) {
                channel.addEventHub(
                        client.newEventHub(peers.get().get(i).getPeerEventHubName(), fabricOrg.getEventHubLocation(peers.get().get(i).getPeerEventHubName()), peerProperties));
            }
        }

        for (int i = 0; i < orderers.get().size(); i++) {
            File ordererCert = Paths.get(config.getCryptoConfigPath(), "/ordererOrganizations", orderers.getOrdererDomainName(), "orderers", orderers.get().get(i).getOrdererName(),
                    "tls/server.crt").toFile();
            if (!ordererCert.exists()) {
                throw new RuntimeException(
                        String.format("Missing cert file for: %s. Could not find at location: %s", orderers.get().get(i).getOrdererName(), ordererCert.getAbsolutePath()));
            }
            Properties ordererProperties = new Properties();
            ordererProperties.setProperty("pemFile", ordererCert.getAbsolutePath());
            ordererProperties.setProperty("hostnameOverride", orderers.getOrdererDomainName());
            ordererProperties.setProperty("sslProvider", "openSSL");
            ordererProperties.setProperty("negotiationType", "TLS");
            ordererProperties.put("grpc.ManagedChannelBuilderOption.maxInboundMessageSize", 9000000);
            ordererProperties.setProperty("ordererWaitTimeMilliSecs", "300000");
            channel.addOrderer(
                    client.newOrderer(orderers.get().get(i).getOrdererName(), fabricOrg.getOrdererLocation(orderers.get().get(i).getOrdererName()), ordererProperties));
        }

        log.debug("channel.isInitialized() = " + channel.isInitialized());
        if (!channel.isInitialized()) {
            channel.initialize();
        }
        if (config.isRegisterEvent()) {
            channel.registerBlockListener(new BlockListener() {

                @Override
                public void received(BlockEvent event) {
                    // TODO
                    log.debug("========================Event事件监听开始========================");
                    try {
                        log.debug("event.getChannelId() = " + event.getChannelId());
                        log.debug("event.getEvent().getChaincodeEvent().getPayload().toStringUtf8() = " + event.getEvent().getChaincodeEvent().getPayload().toStringUtf8());
                        log.debug("event.getBlock().getData().getDataList().size() = " + event.getBlock().getData().getDataList().size());
                        ByteString byteString = event.getBlock().getData().getData(0);
                        String result = byteString.toStringUtf8();
                        log.debug("byteString.toStringUtf8() = " + result);

                        String r1[] = result.split("END CERTIFICATE");
                        String rr = r1[2];
                        log.debug("rr = " + rr);
                    } catch (InvalidProtocolBufferException e) {
                        // TODO
                        e.printStackTrace();
                    }
                    log.debug("========================Event事件监听结束========================");
                }
            });
        }
        return channel;
    }

    private ChaincodeID getChaincodeID() {
        return ChaincodeID.newBuilder().setName(chaincode.getChaincodeName()).setVersion(chaincode.getChaincodeVersion()).setPath(chaincode.getChaincodePath()).build();
    }

    /**
     * 执行智能合约
     *
     * @param fcn
     *            方法名
     * @param args
     *            参数数组
     * @return
     * @throws InvalidArgumentException
     * @throws ProposalException
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     * @throws IOException
     * @throws TransactionException
     * @throws CryptoException
     * @throws InvalidKeySpecException
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     */
    public Map<String, String> invoke(String fcn, String[] args)
            throws InvalidArgumentException, ProposalException, InterruptedException, ExecutionException, TimeoutException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, CryptoException, TransactionException, IOException {
        Map<String, String> resultMap = new HashMap<>();

        Collection<ProposalResponse> successful = new LinkedList<>();
        Collection<ProposalResponse> failed = new LinkedList<>();

        /// Send transaction proposal to all peers
        TransactionProposalRequest transactionProposalRequest = client.newTransactionProposalRequest();
        transactionProposalRequest.setChaincodeID(chaincodeID);
        transactionProposalRequest.setFcn(fcn);
        transactionProposalRequest.setArgs(args);

        Map<String, byte[]> tm2 = new HashMap<>();
        tm2.put("HyperLedgerFabric", "TransactionProposalRequest:JavaSDK".getBytes(UTF_8));
        tm2.put("method", "TransactionProposalRequest".getBytes(UTF_8));
        tm2.put("result", ":)".getBytes(UTF_8));
        transactionProposalRequest.setTransientMap(tm2);

        Collection<ProposalResponse> transactionPropResp = channel.sendTransactionProposal(transactionProposalRequest, channel.getPeers());
        for (ProposalResponse response : transactionPropResp) {
            if (response.getStatus() == ProposalResponse.Status.SUCCESS) {
                successful.add(response);
            } else {
                System.out.println("返回信息有错误:"+response.toString());
                failed.add(response);
            }
        }

//        Collection<Set<ProposalResponse>> proposalConsistencySets = SDKUtils.getProposalConsistencySets(transactionPropResp);
//        if (proposalConsistencySets.size() != 1) {
//            log.error("Expected only one set of consistent proposal responses but got " + proposalConsistencySets.size());
//        }

        if (failed.size() > 0) {
            ProposalResponse firstTransactionProposalResponse = failed.iterator().next();
            log.error("Not enough endorsers for inspect:" + failed.size() + " endorser error: " + firstTransactionProposalResponse.getMessage() + ". Was verified: "
                    + firstTransactionProposalResponse.isVerified());
            resultMap.put("code", "error");
            resultMap.put("data", firstTransactionProposalResponse.getMessage());
            return resultMap;
        } else {
            log.info("Successfully received transaction proposal responses.");
            ProposalResponse resp = transactionPropResp.iterator().next();
            byte[] x = resp.getChaincodeActionResponsePayload();
            String resultAsString = null;
            if (x != null) {
                resultAsString = new String(x, "UTF-8");
            }
            log.info("resultAsString = " + resultAsString);
            channel.sendTransaction(successful);
            resultMap.put("code", "success");
            resultMap.put("data", resultAsString);
            return resultMap;
        }

//        channel.sendTransaction(successful).thenApply(transactionEvent -> {
//            if (transactionEvent.isValid()) {
//                log.info("Successfully send transaction proposal to orderer. Transaction ID: " + transactionEvent.getTransactionID());
//            } else {
//                log.info("Failed to send transaction proposal to orderer");
//            }
//            // chain.shutdown(true);
//            return transactionEvent.getTransactionID();
//        }).get(chaincode.getInvokeWatiTime(), TimeUnit.SECONDS);
    }

    /**
     * 查询智能合约
     *
     * @param fcn
     *            方法名
     * @param args
     *            参数数组
     * @return
     * @throws InvalidArgumentException
     * @throws ProposalException
     * @throws IOException
     * @throws TransactionException
     * @throws CryptoException
     * @throws InvalidKeySpecException
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     */
    public Result query(String fcn, String[] args) throws InvalidArgumentException, ProposalException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, CryptoException, TransactionException, IOException {
        Result<String> result = new Result();
        String payload = "";
        QueryByChaincodeRequest queryByChaincodeRequest = client.newQueryProposalRequest();
        queryByChaincodeRequest.setArgs(args);
        queryByChaincodeRequest.setFcn(fcn);
        queryByChaincodeRequest.setChaincodeID(chaincodeID);

        Map<String, byte[]> tm2 = new HashMap<>();
        tm2.put("HyperLedgerFabric", "QueryByChaincodeRequest:JavaSDK".getBytes(UTF_8));
        tm2.put("method", "QueryByChaincodeRequest".getBytes(UTF_8));
        queryByChaincodeRequest.setTransientMap(tm2);

        Collection<ProposalResponse> queryProposals = channel.queryByChaincode(queryByChaincodeRequest, channel.getPeers());
        for (ProposalResponse proposalResponse : queryProposals) {
            if (!proposalResponse.isVerified() || proposalResponse.getStatus() != ProposalResponse.Status.SUCCESS) {
                log.debug("Failed query proposal from peer " + proposalResponse.getPeer().getName() + " status: " + proposalResponse.getStatus() + ". Messages: "
                        + proposalResponse.getMessage() + ". Was verified : " + proposalResponse.isVerified());
                result.setMsg("error");
                result.setData("Failed query proposal from peer " + proposalResponse.getPeer().getName() + " status: " + proposalResponse.getStatus() + ". Messages: "
                        + proposalResponse.getMessage() + ". Was verified : " + proposalResponse.isVerified());
            } else {
                payload = proposalResponse.getProposalResponse().getResponse().getPayload().toStringUtf8();
                log.debug("Query payload from peer: " + proposalResponse.getPeer().getName());
                log.debug("" + payload);
                return result.success(payload);
            }
        }
        return result;
    }

}