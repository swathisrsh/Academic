package com.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.controller.LoginBean;

public class UserRowMapper implements RowMapper<LoginBean>{

	@Override
	public LoginBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		LoginBean userLoginBean = new LoginBean();
		userLoginBean.setEmail(rs.getString("email"));
		userLoginBean.setUserName(rs.getString("userName"));
		userLoginBean.setImageData(rs.getBytes("imageContent"));
		userLoginBean.setImageSource(rs.getString("imageName"));
		userLoginBean.setLoginAttempt(rs.getInt("loginAttempt"));
		userLoginBean.setPassword(rs.getString("passwd"));
		return userLoginBean;
	}

}
