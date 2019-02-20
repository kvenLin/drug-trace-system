package com.uchain.drugtracesystem.dao;

import com.uchain.drugtracesystem.model.VO.ChaincodeVO;
import com.uchain.drugtracesystem.model.domain.Chaincode;
import java.util.List;

public interface ChaincodeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Chaincode record);

    Chaincode selectByPrimaryKey(Long id);

    List<ChaincodeVO> selectAll();

    int updateByPrimaryKey(Chaincode record);

    Chaincode selectByChaincodeName(String chaincodeName);
}