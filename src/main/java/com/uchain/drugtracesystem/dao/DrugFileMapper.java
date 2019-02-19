package com.uchain.drugtracesystem.dao;

import com.uchain.drugtracesystem.model.domain.DrugFile;
import java.util.List;

public interface DrugFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DrugFile record);

    DrugFile selectByPrimaryKey(Long id);

    List<DrugFile> selectAll();

    int updateByPrimaryKey(DrugFile record);
}