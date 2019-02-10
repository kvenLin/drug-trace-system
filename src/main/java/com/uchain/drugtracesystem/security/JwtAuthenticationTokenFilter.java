package com.uchain.drugtracesystem.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**定义的tokenHeader的名称*/
    private String tokenHeader = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if ("OPTIONS".equals(request.getMethod())){
            log.info("浏览器的预请求的处理..");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type,Accept,Authorization,token,Cookie");
            return;
        }else {
            String requestURI = request.getRequestURI();
            log.info("requestURI:{}",requestURI);
            String authToken = request.getHeader(this.tokenHeader);

            String username = jwtTokenUtil.getUsernameFromToken(authToken);

            log.info("checking authentication for user " + username);

            //当token中的username不为空是进行验证token是否是有效的token
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                log.info("token中的username不为空,Context中的authentication为空时,进行token的验证..");
                //从数据库得到带有密码的完整user信息
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                log.info("加载userdetails:{}",userDetails.getUsername());
                //校验token
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    log.info("authenticated user " + username + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

            chain.doFilter(request, response);
        }


    }
}