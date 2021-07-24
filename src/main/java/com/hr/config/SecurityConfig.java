package com.hr.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author yaoyunfei
 * @data 2021/7/17 16:07
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("admin");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/" ,"/index/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()//开始其他的配置
                .formLogin()//设置表单登录
                .loginPage("/")//设置登录页面
                .loginProcessingUrl("/login")//表单提交的url
                .usernameParameter("uname")//自定义用户名称-默认：username
                .passwordParameter("upwd")//自定义用户密码-默认：password
                .defaultSuccessUrl("/news/newsList")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")//默认就是
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .permitAll();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/images/**","/js/**");
    }
}
