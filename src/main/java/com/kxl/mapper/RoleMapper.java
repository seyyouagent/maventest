package com.kxl.mapper;

import com.kxl.bo.RoleBo;
import com.kxl.bo.UserBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2019/1/28.
 */
@Mapper
public interface RoleMapper {

    @Select("select a.role_id AS roleId ,role_name AS roleName , version from role a left join user_role b on a.role_id = b.role_id where b.user_id = #{id}")
    List<RoleBo> selectByUser(UserBo user);
}
