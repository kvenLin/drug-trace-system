package com.uchain.drugtracesystem.service;

import com.uchain.drugtracesystem.form.fabric.ChaincodeForm;
import com.uchain.drugtracesystem.form.fabric.ChaincodeUpdateForm;
import com.uchain.drugtracesystem.form.fabric.OrdererForm;
import com.uchain.drugtracesystem.form.fabric.PeerForm;
import com.uchain.drugtracesystem.model.VO.ChaincodeVO;
import com.uchain.drugtracesystem.model.domain.Chaincode;
import com.uchain.drugtracesystem.model.domain.Orderer;
import com.uchain.drugtracesystem.model.domain.Peer;
import com.uchain.drugtracesystem.result.Result;

import java.util.List;

/**
 * @Author: clf
 * @Date: 19-2-17
 * @Description:
 */
public interface FabricConfigService {
    Result addPeer(PeerForm peerForm);
    void deletePeer(Long peerId);
    List<Peer> allPeers();
    Result updatePeer(Peer peer);
    Result addOrderer(OrdererForm ordererForm);
    void deleteOrderer(Long ordererId);
    List<Orderer> allOrderers();
    Result updateOrderer(Orderer orderer);
    Result addChaincode(ChaincodeForm chaincodeForm);
    Chaincode selectByChaincodeName(String chaincodeName);
    void deleteChaincode(Long chaincodeId);
    Result updateChaincode(ChaincodeUpdateForm chaincodeUpdateForm);
    List<ChaincodeVO> allChaincodes();
}
