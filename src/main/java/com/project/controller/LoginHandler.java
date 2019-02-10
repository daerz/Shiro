package com.project.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.bean.UserBean;

@Controller
public class LoginHandler {
	@RequestMapping("/login")
	public String login(UserBean user){
		System.out.println(user);
		//1.获取subject门面对象
		Subject currentUser = SecurityUtils.getSubject();
		//开始进行认证（登录）操作，将用户名和密码传递给UsernamePasswordToken
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPwd());
            try {
            	//调用login进行认证
                currentUser.login(token);
                System.out.println("认证成功");
                return "html/success.html";
                //找不到用户回抛出异常
            } catch (UnknownAccountException uae) {
               System.out.println("用户名不存在");
               return "html/loginFail.html";
                //密码匹配错误异常
            } catch (IncorrectCredentialsException ice) {
                System.out.println("密码错误");
                //用户被锁异常
            } catch (LockedAccountException lae) {
                System.out.println("账户被锁定异常");
            }
            // 父异常。认证失败异常
            catch (AuthenticationException ae) {
            	System.out.println("异常不详：自己解决");
            }
           
        }
		return "html/fail.html";
	}
}
