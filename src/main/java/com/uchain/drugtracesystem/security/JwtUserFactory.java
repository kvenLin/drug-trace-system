package com.uchain.drugtracesystem.security;

import com.uchain.drugtracesystem.model.domain.User;
import com.uchain.drugtracesystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: clf
 * @Date: 19-2-10
 * @Description:
 */
@Component
public class JwtUserFactory {
    @Autowired
    private RoleService roleService;

    public JwtUser createUser(User user) {
        return new JwtUser(
                user.getUsername(),
                user.getPassword(),
                roleService.getUserRoleValues(user.getId())
        );
    }
}
