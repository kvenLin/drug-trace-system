package com.uchain.drugtracesystem.dao;

import com.uchain.drugtracesystem.model.domain.Drug;
import java.util.List;

public interface DrugMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Drug record);

    Drug selectByPrimaryKey(Long id);

    List<Drug> selectAll();

    int updateByPrimaryKey(Drug record);
}