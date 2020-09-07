package com.woniuxy.cq.ssmboot39.component;

import com.woniuxy.cq.ssmboot39.common.RandomUtil;
import com.woniuxy.cq.ssmboot39.dao.AuthMapper;
import com.woniuxy.cq.ssmboot39.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private AuthMapper authMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        User user = (User) principals.getPrimaryPrincipal();

        Set<String> rolesSet = authMapper.findRolesById(user.getUserId());

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(rolesSet);

        Set<String> permissions = authMapper.findPermissionsById(user.getUserId());
        simpleAuthorizationInfo.setStringPermissions(permissions);

        log.info(user.getUsername()+"拥有的角色："+rolesSet+" 拥有的权限："+permissions);



        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("进入验证：{}",token.getPrincipal());
        User user = authMapper.findByUsername((String)token.getPrincipal());
        if(user==null){
            throw new UnknownAccountException("账户不存在");
        }
        //从数据库中取出盐（模拟使用随机生成）
        String salt = "abcd";
        System.out.println("加盐计算");
        return new SimpleAuthenticationInfo(user,user.getPassword(), ByteSource.Util.bytes(salt),getName());
    }
}
