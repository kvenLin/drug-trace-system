package com.uchain.drugtracesystem.controller;

import com.uchain.drugtracesystem.form.fabric.ChaincodeForm;
import com.uchain.drugtracesystem.form.fabric.ChaincodeUpdateForm;
import com.uchain.drugtracesystem.form.fabric.OrdererForm;
import com.uchain.drugtracesystem.form.fabric.PeerForm;
import com.uchain.drugtracesystem.model.domain.Orderer;
import com.uchain.drugtracesystem.model.domain.Peer;
import com.uchain.drugtracesystem.result.Result;
import com.uchain.drugtracesystem.service.FabricConfigService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: clf
 * @Date: 19-2-17
 * @Description:
 */
@Api(tags = "区块链信息配置模块")
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/fabric")
public class FabricController {
    @Autowired
    private FabricConfigService fabricConfigService;

    @PostMapping("/addPeer")
    public Object addPeer(@Valid PeerForm peerForm){
        return fabricConfigService.addPeer(peerForm);
    }

    @PostMapping("updatePeer")
    public Object updatePeer(Peer peer){
        return fabricConfigService.updatePeer(peer);
    }

    @PostMapping("/deletePeer")
    public Object deletePeer(Long peerId){
        fabricConfigService.deletePeer(peerId);
        return Result.success();
    }

    @GetMapping("/allPeers")
    public Object allPeers(){
        return Result.success(fabricConfigService.allPeers());
    }

    @PostMapping("/addOrderer")
    public Object addOrderer(@Valid OrdererForm ordererForm){
        return fabricConfigService.addOrderer(ordererForm);
    }

    @PostMapping("deleteOrderer")
    public Object deleteOrderer(Long ordererId){
        fabricConfigService.deleteOrderer(ordererId);
        return Result.success();
    }

    @PostMapping("updateOrderer")
    public Object updateOrderer(Orderer orderer){
        return fabricConfigService.updateOrderer(orderer);
    }

    @GetMapping("/allOrderers")
    public Object allOrderers(){
        return Result.success(fabricConfigService.allOrderers());
    }

    @PostMapping("/addChaincode")
    public Object addChaincode(@Valid ChaincodeForm chaincodeForm){
        return fabricConfigService.addChaincode(chaincodeForm);
    }

    @PostMapping("/deleteChaincode")
    public Object deleteChaincode(Long chaincodeId){
        fabricConfigService.deleteChaincode(chaincodeId);
        return Result.success();
    }

    @PostMapping("/updateChaincode")
    public Object updateChaincode(@Valid ChaincodeUpdateForm chaincodeUpdateForm){
        return fabricConfigService.updateChaincode(chaincodeUpdateForm);
    }

    @GetMapping("/allChaincodes")
    public Object allChaincodes(){
        return Result.success(fabricConfigService.allChaincodes());
    }
}
