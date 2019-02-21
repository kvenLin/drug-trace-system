package com.uchain.drugtracesystem.service.impl;

import com.uchain.drugtracesystem.dao.UserMapper;
import com.uchain.drugtracesystem.enums.CodeMsg;
import com.uchain.drugtracesystem.fabric.ChaincodeManager;
import com.uchain.drugtracesystem.model.domain.User;
import com.uchain.drugtracesystem.model.view.RegisterRequest;
import com.uchain.drugtracesystem.result.Result;
import com.uchain.drugtracesystem.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: clf
 * @Date: 19-2-10
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean insert(User user) {
        return userMapper.insert(user) == 1;
    }

    @Override
    public boolean update(User user) {
        return userMapper.updateByPrimaryKey(user) == 1;
    }

    @Override
    public void delete(Long id) {
        userMapper.deleteByPrimaryKey(id);
        //TODO,对链上数据进行维护
    }

    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    @Transactional
    public Result register(RegisterRequest request) {
        if (userMapper.selectByUsername(request.getUsername()) == null){
            User user = new User();
            BeanUtils.copyProperties(request, user);
            user.setBalance(0L);
            user.setPoint(0);
            user.setCreateTime(new Date());
            if (insert(user)){
                //TODO,用户信息进行上链处理
                return Result.success();
            }
        }
        return Result.error(CodeMsg.USER_ALREADY_EXIST);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        if (authentication != null && !"anonymousUser".equals(name)){
            return selectByUsername(name);
        }
        return null;
    }
}
