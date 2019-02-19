package com.uchain.drugtracesystem.dao;

import com.uchain.drugtracesystem.model.domain.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectByUsername(String username);
}