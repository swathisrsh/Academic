package com.spring.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.spring.controller.LoginBean;
import com.spring.controller.RegistrationBean;


public class loginDaoImpl implements loginDao{
	private JdbcTemplate jdbcTemplate;
	
	public loginDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public loginDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void save(RegistrationBean registrationBean) {
		// insert
        String sql;
        sql = "insert into userdata(userName,email,passwd,imageName, imageContent)values(?,?,?,?,?)";
        jdbcTemplate.update(sql, registrationBean.getUserName(),registrationBean.getEmail(),registrationBean.getPassword(),registrationBean.getImageSource(),registrationBean.getImageData());
	}

	@Override
	public LoginBean get(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginBean getUser(String email) {
		String sql = "select * from userdata where email ='"+email+"'";
		LoginBean bean = jdbcTemplate.queryForObject(sql, new UserRowMapper());
		return bean;
	}

	@Override
	public int updatelockCount(LoginBean lbean) {
		String sql = "update userdata set loginAttempt = "+lbean.getLoginAttempt()+" where email ='"+lbean.getEmail()+"'";
		return jdbcTemplate.update(sql);
		
	}

}
