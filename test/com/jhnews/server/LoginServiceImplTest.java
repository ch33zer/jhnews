package com.jhnews.server;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;
import com.jhnews.shared.NotLoggedInException;
import com.jhnews.shared.RegistrationFailedException;
import com.jhnews.shared.Session;
import com.jhnews.shared.UserExistsException;

public class LoginServiceImplTest extends GWTTestCase
{
  public String getModuleName()
  {  
	  return "com.jhnews.server.LoginServiceImpl";
  }
  
  @Test
  public void testLoginServiceImplConstructor()
  { 
	  LoginServiceImpl login = new LoginServiceImpl();
	  assertTrue(true);//unsure how to test the constructor
  }
  
  @Test
  public void testIsLoggedIn() throws NotLoggedInException
  {
	  LoginServiceImpl login = new LoginServiceImpl();
	  assertTrue(login.isLoggedIn("dog") == true);
  }	
  
  public void testRegister() throws RegistrationFailedException, NotLoggedInException, UserExistsException
  {
	  LoginServiceImpl login = new LoginServiceImpl();
	  Session temp = login.register("user", "pw");
	  assertTrue(login.isLoggedIn(temp.getSessionID()));
  }	
}