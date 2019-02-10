package com.uchain.drugtracesystem.dao;

import com.uchain.drugtracesystem.model.domain.Product;
import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    Product selectByPrimaryKey(Long id);

    List<Product> selectAll();

    int updateByPrimaryKey(Product record);
}