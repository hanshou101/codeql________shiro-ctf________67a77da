package com.summersec.shiroctf.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @ClassName: shiroconfig
 * @Description: TODO
 * @Author: Summer
 * @Date: 2020/12/23 13:46
 * @Version: v1.0.0
 * @Description: shiro 配置类
 **/
@Configuration
public class shiroconfig {



    @Bean
    MyRealm myRealm() {
        return new MyRealm();
    }

    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm());
        return manager;
    }

    /**
     * anon：匿名用户可访问
     * authc：认证用户可访问
     * user：使用rememberMe可访问
     * perms：对应权限可访问
     * role：对应角色权限可访问
     */
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(this.securityManager());
        bean.setLoginUrl("/login");
        Map<String, String> map = new LinkedHashMap();
        map.put("/doLogin", "anon");//* anon：匿名用户可访问
        map.put("/index/*", "authc"); //authc：认证用户可访问
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

}
