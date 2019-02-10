package com.uchain.drugtracesystem.service;

import com.uchain.drugtracesystem.model.domain.User;

/**
 * @Author: clf
 * @Date: 19-2-10
 * @Description:
 */
public interface UserService {
    /**
     * 根据用户名进行查询
     * @param username
     * @return
     */
    User selectByUsername(String username);

}
