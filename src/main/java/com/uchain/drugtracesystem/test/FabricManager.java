package com.uchain.drugtracesystem.test;

import com.uchain.drugtracesystem.fabric.ChaincodeManager;
import com.uchain.drugtracesystem.fabric.FabricConfig;
import com.uchain.drugtracesystem.fabric.bean.Chaincode;
import com.uchain.drugtracesystem.fabric.bean.Orderers;
import com.uchain.drugtracesystem.fabric.bean.Peers;
import com.uchain.drugtracesystem.result.Result;
import org.apache.log4j.Logger;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.TransactionException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

public class FabricManager {

    private static Logger log = Logger.getLogger(FabricManager.class);
    private ChaincodeManager manager;

    private static FabricManager instance = null;

    private final static String channelName = "mychannel";

    /**
     * 获取一个FabricManager的实例
     */
    public static FabricManager obtain()
            throws CryptoException, InvalidArgumentException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, TransactionException, IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (null == instance) {
            synchronized (FabricManager.class) {
                if (null == instance) {
                    instance = new FabricManager();
                }
            }
        }
        return instance;
    }

    //生成实例
    private FabricManager()
            throws CryptoException, InvalidArgumentException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, TransactionException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        //获取实例时调用getconfig()进行加载配置信息,调用ChaincodeManager构造方法生成ChaincodeManager实例对象
        manager = new ChaincodeManager(getConfig());
    }

    /**
     * 获取节点服务器管理器
     *
     * @return 节点服务器管理器
     */
    public ChaincodeManager getManager() {
        return manager;
    }

    /**
     * 根据节点作用类型获取节点服务器配置
     * 该方法主要进行加载配置文件,(如果区块链相关的配置信息写在配置文件中);
     * 初始化数据库连接信息;
     * 再加载证书和通道相关文件的路径。
     * @param //type 服务器作用类型（1、执行；2、查询）
     * @return 节点服务器配置
     */
    private FabricConfig getConfig() {
        FabricConfig config = new FabricConfig();
        config.setOrderers(getOrderers());
        config.setPeers(getPeers());
        config.setChaincode(getChaincode(channelName, "drugTrace"
                , "github.com/hyperledger/fabric/imocc/chaincode/drugTrace/", "1.0.0"));
        //加载通道配置文件
        config.setChannelArtifactsPath(getChannleArtifactsPath());
        //加载证书的相关配置文件
        config.setCryptoConfigPath(getCryptoConfigPath());
        return config;
    }

    /**
     *加载排序节点相关配置信息
     * @return
     */
    private Orderers getOrderers() {
        Orderers orderer = new Orderers();
        //设置根域名和排序服务器对象
        orderer.setOrdererDomainName("imocc.com");
        //设置orderer节点名称和所在地址
        orderer.addOrderer("orderer.imocc.com", "grpc://127.0.0.1:7050");
        //orderer.addOrderer("orderer1.example.com", "grpc://127.0.0.1:8050");
//        orderer.addOrderer("orderer2.example.com", "grpc://x.x.x.xxx:7050");
        return orderer;
    }

    /**
     * 获取节点服务器集
     * 加载节点服务相关配置信息
     * @return 节点服务器集
     */
    private Peers getPeers() {
        Peers peers = new Peers();
        //设置组织名
        peers.setOrgName("Org1");
        //设置组织的MSPID
        peers.setOrgMSPID("Org1MSP");
        //设置根域名
        peers.setOrgDomainName("org1.imocc.com");
        //节点服务器对象（包含节点名称，节点事件监听名称，节点访问地址，节点事件监听访问地址，CA访问地址）
        peers.addPeer("peer0.org1.imocc.com", "peer1.org1.imocc.com",
                "grpc://127.0.0.1:27051", "grpc://127.0.0.1:27053", "http://127.0.0.1:7051");
        return peers;
    }

    /**
     * 获取智能合约
     *  通过getChaincode()方法加载chaincode相关信息,
     *  需要定义Chaincode对象,
     *  （包含归属的channel名称，chaincode名称，安装路径，版本号）,
     *  设置执行智能合约操作等待时间，执行智能合约实例等待时间
     * @param channelName      频道名称
     * @param chaincodeName    智能合约名称
     * @param chaincodePath    智能合约路径
     * @param chaincodeVersion 智能合约版本
     * @return 智能合约
     */
    private Chaincode getChaincode(String channelName, String chaincodeName, String chaincodePath, String chaincodeVersion) {
        Chaincode chaincode = new Chaincode();
        chaincode.setChannelName(channelName);
        chaincode.setChaincodeName(chaincodeName);
        chaincode.setChaincodePath(chaincodePath);
        chaincode.setChaincodeVersion(chaincodeVersion);
        //
        chaincode.setInvokeWaitTime(100000);
        chaincode.setDeployWaitTime(120000);
        return chaincode;
    }

    /**
     * 获取channel-artifacts配置路径
     *
     * @return /WEB-INF/classes/fabric/channel-artifacts/
     */
    private String getChannleArtifactsPath() {
        String directorys = FabricManager.class.getClassLoader().getResource("fabric").getFile();
        log.debug("directorys = " + directorys);
        File directory = new File(directorys);
        log.debug("directory = " + directory.getPath());

        return directory.getPath() + "/channel-artifacts/";
    }

    /**
     * 获取crypto-config配置路径
     *
     * @return /WEB-INF/classes/fabric/crypto-config/
     */
    private String getCryptoConfigPath() {
        String directorys = FabricManager.class.getClassLoader().getResource("fabric").getFile();
        log.debug("directorys = " + directorys);
        File directory = new File(directorys);
        log.debug("directory = " + directory.getPath());

        return directory.getPath() + "/crypto-config/";
    }

    public static void main(String[] args) {
        try {
            //获取manager的实例
            ChaincodeManager manager = FabricManager.obtain().getManager();
//
             String[] str = {"1"};
             Result query = manager.query("query", str);
             System.out.println(query);
//
//          String[] str = new String[]{"201631063225","123456789","123456"};
//          Map<String, String> result = manager.invoke("changePwd",str);
//          System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}