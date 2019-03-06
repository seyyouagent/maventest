package com.kxl.mapper;


import com.kxl.Dto.UserDto;
import com.kxl.bo.UserBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by Administrator on 2019/1/25.
 */
@Mapper
public interface UserMapper {

    /**
     * 根据name查询用户
     * @param loginDto
     * @return
     */
    @Select("SELECT id, user_name AS userName, account, password, salt, locked FROM sys_user where user_name=#{uname} ")
    List<UserBo> selectByUname(UserDto loginDto);

    /**
     * 修改密码
     * @param userDto
     * @return
     */
    @Update("update sys_user set password=#{password} where user_name=#{uname}")
    UserBo updateUser(UserDto userDto);
}
