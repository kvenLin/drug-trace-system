package com.uchain.drugtracesystem.service.impl;

import com.uchain.drugtracesystem.dao.ChaincodeMapper;
import com.uchain.drugtracesystem.dao.OrdererMapper;
import com.uchain.drugtracesystem.dao.PeerMapper;
import com.uchain.drugtracesystem.enums.CodeMsg;
import com.uchain.drugtracesystem.form.fabric.ChaincodeForm;
import com.uchain.drugtracesystem.form.fabric.ChaincodeUpdateForm;
import com.uchain.drugtracesystem.form.fabric.OrdererForm;
import com.uchain.drugtracesystem.form.fabric.PeerForm;
import com.uchain.drugtracesystem.model.VO.ChaincodeVO;
import com.uchain.drugtracesystem.model.domain.Chaincode;
import com.uchain.drugtracesystem.model.domain.Orderer;
import com.uchain.drugtracesystem.model.domain.Peer;
import com.uchain.drugtracesystem.model.domain.User;
import com.uchain.drugtracesystem.result.Result;
import com.uchain.drugtracesystem.service.FabricConfigService;
import com.uchain.drugtracesystem.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: clf
 * @Date: 19-2-17
 * @Description:
 */
@Service
public class FabricConfigServiceImpl implements FabricConfigService {
    @Autowired
    private ChaincodeMapper chaincodeMapper;
    @Autowired
    private OrdererMapper ordererMapper;
    @Autowired
    private PeerMapper peerMapper;
    @Autowired
    private UserService userService;

    @Override
    public Result addPeer(PeerForm peerForm) {
        Peer peer = new Peer();
        BeanUtils.copyProperties(peerForm, peer);
        if (peerMapper.insert(peer) == 1) {
            return Result.success();
        }
        return Result.error(CodeMsg.ADD_ERROR);
    }

    @Override
    public void deletePeer(Long peerId) {
        peerMapper.deleteByPrimaryKey(peerId);
    }

    @Override
    public List<Peer> allPeers() {
        return peerMapper.selectAll();
    }

    @Override
    public Result updatePeer(Peer peer) {
        if (peerMapper.selectByPrimaryKey(peer.getId()) == null){
            return Result.error(CodeMsg.PEER_NOT_EXIST);
        }
        if (peerMapper.updateByPrimaryKey(peer) == 1) {
            return Result.success();
        }
        return Result.error(CodeMsg.UPDATE_ERROR);
    }

    @Override
    public Result addOrderer(OrdererForm ordererForm) {
        Orderer orderer = new Orderer();
        BeanUtils.copyProperties(ordererForm, orderer);
        if (ordererMapper.insert(orderer) == 1){
            return Result.success();
        }
        return Result.error(CodeMsg.ADD_ERROR);
    }

    @Override
    public void deleteOrderer(Long ordererId) {
        ordererMapper.deleteByPrimaryKey(ordererId);
    }

    @Override
    public List<Orderer> allOrderers() {
        return ordererMapper.selectAll();
    }

    @Override
    public Result updateOrderer(Orderer orderer) {
        if (ordererMapper.selectByPrimaryKey(orderer.getId()) == null){
            return Result.error(CodeMsg.ORDERER_NOT_EXIST);
        }
        if (ordererMapper.updateByPrimaryKey(orderer) == 1){
            return Result.success();
        }
        return Result.error(CodeMsg.UPDATE_ERROR);
    }

    @Override
    public Result addChaincode(ChaincodeForm chaincodeForm) {
        Chaincode chaincode = selectByChaincodeName(chaincodeForm.getChaincodeName());
        if (chaincode != null){
            return Result.error(CodeMsg.CHAINCODE_ALREADY_EXIST);
        }
        chaincode = new Chaincode();
        BeanUtils.copyProperties(chaincodeForm, chaincode);
        User user = userService.getCurrentUser();
        chaincode.setCreatedBy(user.getId());
        if (chaincodeMapper.insert(chaincode) == 1){
            return Result.success();
        }
        return Result.error(CodeMsg.ADD_ERROR);
    }

    @Override
    public Chaincode selectByChaincodeName(String chaincodeName) {
        return chaincodeMapper.selectByChaincodeName(chaincodeName);
    }

    @Override
    public void deleteChaincode(Long chaincodeId) {
        chaincodeMapper.deleteByPrimaryKey(chaincodeId);
    }

    @Override
    public Result updateChaincode(ChaincodeUpdateForm chaincodeUpdateForm) {
        Chaincode chaincode = chaincodeMapper.selectByPrimaryKey(chaincodeUpdateForm.getId());
        if (chaincode == null){
            return Result.error(CodeMsg.CHAINCODE_NOT_EXIST);
        }
        BeanUtils.copyProperties(chaincodeUpdateForm, chaincode);
        if (chaincodeMapper.updateByPrimaryKey(chaincode) == 1){
            return Result.success();
        }
        return Result.error(CodeMsg.UPDATE_ERROR);
    }

    @Override
    public List<ChaincodeVO> allChaincodes() {
        return chaincodeMapper.selectAll();
    }
}
