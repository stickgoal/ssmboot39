package com.woniuxy.cq.ssmboot39.dao;

import com.woniuxy.cq.ssmboot39.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

public interface AuthMapper {

    @Select("select * from perm_user where username=#{username}")
    User findByUsername(String username);

    @Select("select r.role_value from perm_user_role ur LEFT JOIN perm_role r ON ur.role_id=r.role_id where ur.user_id=#{userId}")
    Set<String> findRolesById(int userId);

    @Select("select p.perm_value from perm_role_perm rp  " +
            " left join perm_perm p on rp.perm_id=p.perm_id  " +
            " left join perm_user_role ur on rp.role_id=ur.role_id " +
            " where ur.user_id=#{userId}")
    Set<String> findPermissionsById(int userId);
}
