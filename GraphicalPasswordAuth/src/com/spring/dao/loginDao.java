package com.spring.dao;

import com.spring.controller.LoginBean;
import com.spring.controller.RegistrationBean;

public interface loginDao {
	public void save(RegistrationBean registrationBean);
    
    public LoginBean get(String email);

	public LoginBean getUser(String email);

	public int updatelockCount(LoginBean lbean);

}
