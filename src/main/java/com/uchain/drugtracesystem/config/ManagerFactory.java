package com.uchain.drugtracesystem.config;

import com.alibaba.fastjson.JSONObject;
import com.uchain.drugtracesystem.fabric.ChaincodeManager;
import com.uchain.drugtracesystem.fabric.FabricConfig;
import com.uchain.drugtracesystem.fabric.bean.Orderers;
import com.uchain.drugtracesystem.fabric.bean.Peers;
import com.uchain.drugtracesystem.model.domain.Chaincode;
import com.uchain.drugtracesystem.model.domain.Orderer;
import com.uchain.drugtracesystem.model.domain.Peer;
import com.uchain.drugtracesystem.result.Result;
import com.uchain.drugtracesystem.service.FabricConfigService;
import com.uchain.drugtracesystem.test.FabricManagerTest;
import com.uchain.drugtracesystem.util.FabricMethod;
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
        //TODO,完成manager的bean的注册
        return configManager("registerManager");
    }
    public ChaincodeManager configManager(String chaincodeName) {
        List<Orderer> orderers = fabricConfigService.allOrderers();
        List<Peer> peers = fabricConfigService.allPeers();
        Chaincode chaincode = fabricConfigService.selectByChaincodeName(chaincodeName);
        //TODO
        return null;
    }
}
