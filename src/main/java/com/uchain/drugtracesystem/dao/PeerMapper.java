package com.uchain.drugtracesystem.dao;

import com.uchain.drugtracesystem.model.domain.Peer;
import java.util.List;

public interface PeerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Peer record);

    Peer selectByPrimaryKey(Long id);

    List<Peer> selectAll();

    int updateByPrimaryKey(Peer record);
}