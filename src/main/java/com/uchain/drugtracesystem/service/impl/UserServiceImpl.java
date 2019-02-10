package com.uchain.drugtracesystem.service.impl;

import com.uchain.drugtracesystem.model.domain.User;
import com.uchain.drugtracesystem.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author: clf
 * @Date: 19-2-10
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User selectByUsername(String username) {
        return null;
    }
}
