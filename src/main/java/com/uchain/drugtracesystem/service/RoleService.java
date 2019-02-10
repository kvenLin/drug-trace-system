package com.uchain.drugtracesystem.service;

import java.util.List;

/**
 * @Author: clf
 * @Date: 19-2-10
 * @Description:
 */
public interface RoleService {
    /**
     * 查询用户的所有角色信息
     * @param userId
     * @return
     */
    List<String> selectRoleByUserId(Long userId);
}
