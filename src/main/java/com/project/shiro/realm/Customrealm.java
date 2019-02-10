package com.project.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.project.bean.UserBean;

public class Customrealm extends AuthorizingRealm{

	/**
	 * 用于授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 用于认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取到数据库的数据(调用UserService获取数据库数据)
		
		/**
		 * 获取前台传来的用户名，用于判断用户是否存在
		 * 调用service方法传入loginName，判断数据库是否存在该用户
		 * 若不存在，返回空(shiro验证则是catch用户名不存在)
		 * 存在则把数据库内容传入SimpleAuthenticationInfo()验证
		 */
		String loginName = (String)token.getPrincipal();
		UserBean user = new UserBean();
		user.setName("tiger");
		user.setPwd("123");
		if(!loginName.equals(user.getName()))
			return null;
		
		AuthenticationInfo info = new SimpleAuthenticationInfo(user.getName(), user.getPwd(), getName());
		return info;
	}

}
