package com.uchain.drugtracesystem.dao;

import com.uchain.drugtracesystem.model.domain.Role;
import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    List<String> selectRoleValuesByUserId(Long userId);
}