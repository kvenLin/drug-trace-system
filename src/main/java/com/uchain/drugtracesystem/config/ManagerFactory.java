package com.uchain.drugtracesystem.config;

import com.uchain.drugtracesystem.fabric.ChaincodeManager;
import com.uchain.drugtracesystem.fabric.FabricConfig;
import com.uchain.drugtracesystem.fabric.bean.Orderers;
import com.uchain.drugtracesystem.fabric.bean.Peers;
import com.uchain.drugtracesystem.model.domain.Chaincode;
import com.uchain.drugtracesystem.model.domain.Orderer;
import com.uchain.drugtracesystem.model.domain.Peer;
import com.uchain.drugtracesystem.service.FabricConfigService;
import com.uchain.drugtracesystem.test.FabricManagerTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @Author: clf
 * @Date: 19-2-20
 * @Description:
 */
@Service
@Slf4j
public class ManagerFactory {
    @Autowired
    private FabricConfigService fabricConfigService;
    @Autowired
    private ManagerConfig managerConfig;

    @Bean(name = "registerManager")
    public ChaincodeManager registerManager() throws Exception {
        return configManager("register");
    }

    @Bean(name = "drugTraceManager")
    public ChaincodeManager drugTraceManager() throws Exception {
        return configManager("drugTrace");
    }

    public ChaincodeManager configManager(String chaincodeName) throws Exception {
        List<Orderer> orderers = fabricConfigService.allOrderers();
        List<Peer> peers = fabricConfigService.allPeers();
        Chaincode chaincode = fabricConfigService.selectByChaincodeName(chaincodeName);
        return new ChaincodeManager(getConfig(chaincode, peers, orderers));
    }

    /**
     * 根据节点作用类型获取节点服务器配置
     * 该方法主要进行加载配置文件,(如果区块链相关的配置信息写在配置文件中);
     * 初始化数据库连接信息;
     * 再加载证书和通道相关文件的路径。
     * @param //type 服务器作用类型（1、执行；2、查询）
     * @return 节点服务器配置
     */
    private FabricConfig getConfig(Chaincode chaincode, List<Peer> peers, List<Orderer> orderers) {
        FabricConfig config = new FabricConfig();
        config.setOrderers(getFabricOrderers(orderers));
        config.setPeers(getFabricPeers(peers));
        config.setChaincode(getFabricChaincode(
                managerConfig.getChannelName(),
                chaincode.getChaincodeName(),
                chaincode.getChaincodePath(),
                chaincode.getChaincodeVersion()));
        //加载通道配置文件
        config.setChannelArtifactsPath(getChannelArtifactsPath());
        //加载证书的相关配置文件
        config.setCryptoConfigPath(getCryptoConfigPath());
        return config;
    }

    /**
     *加载排序节点相关配置信息
     * @return
     */
    private Orderers getFabricOrderers(List<Orderer> orderersData) {
        Orderers orderers = new Orderers();
        //设置根域名和排序服务器对象
        orderers.setOrdererDomainName(managerConfig.getOrdererDomainName());
        //设置orderer节点名称和所在地址
        orderersData.forEach(orderer -> {
            orderers.addOrderer(orderer.getOrdererName(), orderer.getOrdererLocation());
        });
        return orderers;
    }

    /**
     * 获取节点服务器集
     * 加载节点服务相关配置信息
     * @return 节点服务器集
     */
    private Peers getFabricPeers(List<Peer> peersData) {
        Peers peers = new Peers();
        //设置组织名
        peers.setOrgName(managerConfig.getOrgName());
        //设置组织的MSPID
        peers.setOrgMSPID(managerConfig.getOrgMspId());
        //设置根域名
        peers.setOrgDomainName(managerConfig.getOrgDomainName());
        //节点服务器对象（包含节点名称，节点事件监听名称，节点访问地址，节点事件监听访问地址，CA访问地址）
        peersData.forEach(peer -> {
            peers.addPeer(
                    peer.getPeerName(),
                    peer.getPeerEventHubName(),
                    peer.getPeerLocation(),
                    peer.getPeerEventHubLocation(),
                    peer.getCaLocation());
        });
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
    private com.uchain.drugtracesystem.fabric.bean.Chaincode getFabricChaincode(String channelName, String chaincodeName, String chaincodePath, String chaincodeVersion) {
        com.uchain.drugtracesystem.fabric.bean.Chaincode chaincode = new com.uchain.drugtracesystem.fabric.bean.Chaincode();
        chaincode.setChannelName(channelName);
        chaincode.setChaincodeName(chaincodeName);
        chaincode.setChaincodePath(chaincodePath);
        chaincode.setChaincodeVersion(chaincodeVersion);
        chaincode.setInvokeWaitTime(100000);
        chaincode.setDeployWaitTime(120000);
        return chaincode;
    }

    /**
     * 获取channel-artifacts配置路径
     *
     * @return /WEB-INF/classes/fabric/channel-artifacts/
     */
    private String getChannelArtifactsPath() {
        String directorys = FabricManagerTest.class.getClassLoader().getResource("fabric").getFile();
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
        String directorys = FabricManagerTest.class.getClassLoader().getResource("fabric").getFile();
        log.debug("directorys = " + directorys);
        File directory = new File(directorys);
        log.debug("directory = " + directory.getPath());
        return directory.getPath() + "/crypto-config/";
    }
}
