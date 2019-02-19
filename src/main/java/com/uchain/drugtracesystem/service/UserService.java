package com.uchain.drugtracesystem.service;

import com.uchain.drugtracesystem.model.domain.User;
import com.uchain.drugtracesystem.model.view.RegisterRequest;
import com.uchain.drugtracesystem.result.Result;

/**
 * @Author: clf
 * @Date: 19-2-10
 * @Description:
 */
public interface UserService {
    /**
     * 添加用户
     * @param user
     * @return
     */
    boolean insert(User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    boolean update(User user);

    /**
     * 删除用户
     * @param id
     */
    void delete(Long id);

    /**
     * 根据用户名进行查询
     * @param username
     * @return
     */
    User selectByUsername(String username);

    /**
     * 用户注册
     * @param request
     * @return
     */
    Result register(RegisterRequest request);

    /**
     * 获取当前的用户信息
     * @return
     */
    User getCurrentUser();
}
