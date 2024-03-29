package com.jhnews.client;

import java.util.List;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.Cookies;
import com.jhnews.shared.Session;
import com.jhnews.shared.User;

/**
 * This class is the JUnit test for LoginManager.
 * @author Group 8
 */
public class LoginManagerTest extends GWTTestCase
{
  public String getModuleName()
  {  
	  return "com.jhnews.client.LoginManager";
  }
  
  @Test
  public void testLoginManagerConstructor()
  {
	  assertTrue(LoginManager.getInstance() != null);
  }
  
  @Test
  public void testGetInstance()
  {
	  assertTrue(LoginManager.getInstance() != null);
  }
  
  @Test
  public void testGetSessionID()
  {
	  assertTrue(LoginManager.getInstance().getSessionID().equals(Cookies.getCookie("sid")));
  }
  
  @Test
  public void testGetUsername()
  {
	  assertTrue(LoginManager.getInstance().getUsername().equals(Cookies.getCookie("username")));
  }
  
  @Test
  public void testAddLoginListener()
  {
	  HeaderPanel temp = new HeaderPanel();
	  LoginManager.getInstance().addLoginListener(temp);
	  List<LoginListener> ll = LoginManager.getInstance().getLoginListener();
	  assertTrue(ll.get(ll.size()-1).equals(temp));
  }
  
  @Test
  public void testGetLoginListener()
  {
	  Boolean result = false;
	  List<LoginListener> ll = LoginManager.getInstance().getLoginListener();
	  if (ll instanceof List<?>)
	  {
		  if (ll.get(0) instanceof LoginListener || ll.get(0) == null)
		  {
			  result = true;
		  }
	  }
	  assertTrue(result);
  }
  
  @Test
  public void testRemoveLoginListener()
  {
	  LoginManager lmanager = LoginManager.getInstance();
	  List<LoginListener> llist = LoginManager.getInstance().getLoginListener();
	  HeaderPanel tempHP = new HeaderPanel();
	  
	  lmanager.addLoginListener(tempHP);
	  int tempInt = LoginManager.getInstance().getLoginListener().size();
	  lmanager.removeLoginListener(tempHP);
	  assertTrue(tempInt == lmanager.getLoginListener().size());
  }
  
  @Test
  public void testCreateSessionCookie()
  {
	  Session session = new Session();
	  User user = new User();
	  user.setUsername("Tester");
	  session.setSessionID("sessionID");
	  session.setUser(user);
	  LoginManager.getInstance().createSessionCookie(session);
  }
  
  @Test
  public void testLogIn()
  {
	  Session session = new Session();
	  User user = new User();
	  user.setUsername("Tester");
	  session.setSessionID("sessionID");
	  session.setUser(user);
	  
	  //LoginManager.getInstance().logIn(user);
	  
  }
  
  @Test
  public void testLogOut()
  {
	  
  }
  
  @Test
  public void testRegister()
  {
	  
  }
  
  @Test
  public void testIsAdmin()
  {
	  
  }
}