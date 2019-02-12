//package com.kxl.filter;
//
//import com.kxl.bo.UserBo;
//import com.kxl.service.UserService;
//import com.kxl.util.JwtTokenUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.logging.Logger;
//
///**
// * Created by Administrator on 2019/1/28.
// */
//@Component
//public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
//
//    @Value("${jwt.header}")
//    private String tokenHeader;
//    @Value("${jwt.tokenHead}")
//    private String tokenHead;
//
//    private UserService userService;
//    private JwtTokenUtil jwtTokenUtil;
//
//    @Autowired
//    public JwtAuthenticationTokenFilter(UserService userService, JwtTokenUtil jwtTokenUtil) {
//        this.userService = userService;
//        this.jwtTokenUtil = jwtTokenUtil;
//    }
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        String authHeader = request.getHeader(tokenHeader);
//        if (authHeader != null && authHeader.startsWith(tokenHead)) {
//            String authToken = authHeader.substring(tokenHead.length());
//            String username = jwtTokenUtil.getUsernameFromToken(authToken);
//            if (username != null) {
//                UserBo userBo = userService.loadUserByUsername(username);
//                if(jwtTokenUtil.validateToken(authToken)) {
//                    if (jwtTokenUtil.validateToken(authToken, userBo)) {
////                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
////                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////                        SecurityContextHolder.getContext().setAuthentication(authentication);
//                    } else {
//                        jwtTokenUtil.del(authToken);
//                    }
//                }
//            }
//        }
//        filterChain.doFilter(request, response);
//
//    }
//}
