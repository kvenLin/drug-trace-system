package com.uchain.drugtracesystem.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JwtUser implements UserDetails {

    private String username;
    private String password;
    private List<String> roles;

    public JwtUser(String username, String password, List<String> roles) {
            this.username = username;
            this.password = password;
            this.roles = roles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        roles.forEach(role -> {
            auths.add(new SimpleGrantedAuthority(role));
        });
        return auths;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 默认有效账户
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 默认账户没有被锁
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 默认凭证有效
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 默认账户可用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }


}
