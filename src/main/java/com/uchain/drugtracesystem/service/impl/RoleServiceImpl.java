package com.uchain.drugtracesystem.service.impl;

import com.uchain.drugtracesystem.dao.RoleMapper;
import com.uchain.drugtracesystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: clf
 * @Date: 19-2-10
 * @Description:
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<String> getUserRoleValues(Long userId) {
        return roleMapper.selectRoleValuesByUserId(userId);
    }
}
