package com.uchain.drugtracesystem.security;

import com.uchain.drugtracesystem.model.domain.User;
import com.uchain.drugtracesystem.service.RoleService;
import com.uchain.drugtracesystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //从数据库查询username,如果不存在则抛出异常
        User user = userService.selectByUsername(username);
        if (user==null) {
            log.error("用户 {} 不存在", username);
            throw new UsernameNotFoundException(String.format(" user not exist with stuId ='%s'.", username));
        } else {
            //若存在则返回userDetails对象
            List<String> roles = roleService.getUserRoleValues(user.getId());
            return new JwtUser(username, passwordEncoder.encode(user.getPassword()), roles);
        }
    }
}
