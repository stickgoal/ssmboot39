package com.woniuxy.cq.ssmboot39.config;

import com.woniuxy.cq.ssmboot39.common.EncryptUtil;
import com.woniuxy.cq.ssmboot39.component.ShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

    @Bean
    public Realm realm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        //设置到realm中
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealm;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        //用户输入的密码    数据库中的正确密码
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //设置算法名字
        hashedCredentialsMatcher.setHashAlgorithmName(EncryptUtil.ALGORITHM_NAME);
        //设置hash迭代次数
        hashedCredentialsMatcher.setHashIterations(EncryptUtil.HASH_ITERATIONS);
        return hashedCredentialsMatcher;
    }

    /**
     * shiro 过滤器链配置
     *
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition sfcd = new DefaultShiroFilterChainDefinition();
        //  xxx路径  -> 某个过滤器
        //public enum DefaultFilter

        //不拦截 anon
        //需要登录  user
        //登出     logout

        // 不拦截
        sfcd.addPathDefinition("/css/**", "anon");
        sfcd.addPathDefinition("/js/**", "anon");
        sfcd.addPathDefinition("/images/**", "anon");
        sfcd.addPathDefinition("/html/**", "anon");
        sfcd.addPathDefinition("/fonts/**", "anon");
        sfcd.addPathDefinition("/layui/**", "anon");
        sfcd.addPathDefinition("/", "anon");
        sfcd.addPathDefinition("/login.html", "anon");
        sfcd.addPathDefinition("/login", "anon");
        sfcd.addPathDefinition("/reg.html", "anon");
        sfcd.addPathDefinition("/index.html", "anon");
        //登出
        sfcd.addPathDefinition("/logout", "logout");
        //拦截
//        sfcd.addPathDefinition("/**", "user");
        sfcd.addPathDefinition("/**", "anon");

        return sfcd;
    }

    @Bean
    public CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }


    //解决权限注解与RequestMapping冲突
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        /**
         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
         * 在@Controller注解的类的方法中加入@RequiresRole等shiro注解，会导致该方法无法映射请求，导致返回404。
         * 加入这项配置能解决这个bug
         */
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }
}
