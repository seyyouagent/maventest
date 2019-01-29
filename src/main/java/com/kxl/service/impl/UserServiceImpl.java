package com.kxl.service.impl;

import com.kxl.Dto.LoginDto;
import com.kxl.bo.RoleBo;
import com.kxl.bo.UserBo;
import com.kxl.mapper.RoleMapper;
import com.kxl.mapper.UserMapper;
import com.kxl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/1/25.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<UserBo> selectByUname(LoginDto loginDto) {
        return userMapper.selectByUname(loginDto);
    }

    public void login(UserBo user, HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LoginDto loginDto = new LoginDto();
        loginDto.setUname(s);
        List<UserBo> users = userMapper.selectByUname(loginDto);

        List<RoleBo> roles = roleMapper.selectByUser(users.get(0));
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (RoleBo role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        if(users.size() == 0)
            throw new UsernameNotFoundException("there's no user founded!");
        else
            return new User(users.get(0).getUserName(),users.get(0).getPassword(),authorities);

    }
}
