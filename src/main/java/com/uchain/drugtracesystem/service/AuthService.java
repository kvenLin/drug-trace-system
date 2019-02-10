package com.uchain.drugtracesystem.service;

import com.uchain.drugtracesystem.result.Result;

/**
 * @Author: clf
 * @Date: 19-2-10
 * @Description:
 */
public interface AuthService {
    Result login(String username, String password);
}
