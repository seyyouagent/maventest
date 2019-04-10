package com.kxl.service;

import com.kxl.Dto.UserDto;
import com.kxl.bo.UserBo;
import com.kxl.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/1/25.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserBo loadUserByUsername(String username) {
        UserDto loginDto = new UserDto();
        loginDto.setUname(username);
        List<UserBo> users = userMapper.selectByUname(loginDto);

        return users.get(0);
    }

    /**
     * 修改密码
     * @param userDto
     */
    public void updatePassword(UserDto userDto){
        userMapper.updateUser(userDto);
    }
}
