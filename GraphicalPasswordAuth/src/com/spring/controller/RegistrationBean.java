package com.spring.controller;

import java.sql.Blob;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("session")
public class RegistrationBean {

  private String userName;
  private String password;
  private String email;
  private String imageSource;
  private Blob imageData;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

public String getImageSource() {
	return imageSource;
}

public void setImageSource(String imageSource) {
	this.imageSource = imageSource;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public Blob getImageData() {
	return imageData;
}

public void setImageData(Blob imageData) {
	this.imageData = imageData;
}

}