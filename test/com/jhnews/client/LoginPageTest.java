package com.jhnews.client;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

public class LoginPageTest extends GWTTestCase
{
  public String getModuleName()
  {  
	  return "com.jhnews.client.LoginPage";
  }
  
  @Test
  public void testLoginPageConstructor()
  { 
	  LoginPage lp = new LoginPage();
	  assertTrue(lp != null);
  }
}