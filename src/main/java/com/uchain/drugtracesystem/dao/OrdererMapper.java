package com.uchain.drugtracesystem.dao;

import com.uchain.drugtracesystem.model.domain.Orderer;
import java.util.List;

public interface OrdererMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Orderer record);

    Orderer selectByPrimaryKey(Long id);

    List<Orderer> selectAll();

    int updateByPrimaryKey(Orderer record);
}