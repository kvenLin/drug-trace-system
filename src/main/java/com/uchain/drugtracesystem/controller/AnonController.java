package com.uchain.drugtracesystem.controller;

import com.uchain.drugtracesystem.model.view.JwtAuthenticationRequest;
import com.uchain.drugtracesystem.model.view.RegisterRequest;
import com.uchain.drugtracesystem.service.AuthService;
import com.uchain.drugtracesystem.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author: clf
 * @Date: 19-2-10
 * @Description:
 */
@Api(tags = "匿名访问接口")
@Slf4j
@CrossOrigin
@RequestMapping
@RestController
public class AnonController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @ApiOperation("登录接口")
    @PostMapping(value = "/login", name = "登录接口")
    public Object login(@Valid JwtAuthenticationRequest request){
        return authService.login(request.getUsername(), request.getPassword());
    }

    @PostMapping("/register")
    @ApiOperation("注册接口")
    public Object register(@Valid RegisterRequest request){
        return userService.register(request);
    }


}

