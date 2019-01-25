package com.kxl.service.impl;

import com.kxl.Dto.LoginDto;
import com.kxl.bo.UserBo;
import com.kxl.mapper.UserMapper;
import com.kxl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2019/1/25.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserBo> selectByUname(LoginDto loginDto) {
        return userMapper.selectByUname(loginDto);
    }

    public void login(UserBo user, HttpServletRequest request, HttpServletResponse response) {

    }
}
