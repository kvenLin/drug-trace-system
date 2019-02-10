package com.uchain.drugtracesystem.service.impl;

import com.uchain.drugtracesystem.enums.CodeMsg;
import com.uchain.drugtracesystem.model.domain.User;
import com.uchain.drugtracesystem.result.Result;
import com.uchain.drugtracesystem.security.JwtTokenUtil;
import com.uchain.drugtracesystem.security.JwtUserFactory;
import com.uchain.drugtracesystem.service.AuthService;
import com.uchain.drugtracesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @Author: clf
 * @Date: 19-2-10
 * @Description:
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUserFactory jwtUserFactory;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Override
    public Result login(String username, String password) {
        User user = userService.selectByUsername(username);
        if (user != null) {
            return Result.error(CodeMsg.USER_NOT_EXIST);
        }
        if (password.equals(user.getPassword())){
            UserDetails userDetails = jwtUserFactory.createUser(user);
            final String token = jwtTokenUtil.generateToken(userDetails);
            return Result.success(token);
        }
        return Result.error(CodeMsg.AUTHENTICATION_ERROR);
    }
}
