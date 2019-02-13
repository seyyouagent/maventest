package com.kxl.filter;

import com.kxl.bo.UserBo;
import com.kxl.service.UserService;
import com.kxl.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;

/**
 * Created by Administrator on 2019/2/13.
 */
@EnableWebMvc
@Configuration
public class AuthInterceptorAdapter extends HandlerInterceptorAdapter {

    Logger logger = LoggerFactory.getLogger(AuthInterceptorAdapter.class);

    @Value("${jwt.header}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {

            try {
                // 返回服务器ip
                response.setHeader("serviceIp", InetAddress.getLocalHost().getHostAddress());
            } catch (Exception e) {
                response.setHeader("serviceIp", "服务器配置异常，无法获取服务器IP");
            }
            if (request.getRequestURI().endsWith("login") ||
                    request.getRequestURI().endsWith(".css") ||
                    request.getRequestURI().endsWith(".js")) {
                return true;
            }

        } else {
            return true;
        }

        String authHeader = request.getHeader(tokenHeader);
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            String authToken = authHeader.substring(tokenHead.length());
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            if (username != null) {
                UserBo userBo = userService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(authToken)) {
                    if (jwtTokenUtil.validateToken(authToken, userBo)) {
                        return true;
                    } else {
                        jwtTokenUtil.del(authToken);
                        return false;
                    }
                }
            }
        }
        return false;
    }
}
