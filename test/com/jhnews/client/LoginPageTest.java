package com.jhnews.client;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * This class is the JUnit test for LoginPage.
 * @author Group 8
 */
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