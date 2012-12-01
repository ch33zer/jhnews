package com.jhnews.client;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * This class is the JUnit test for HomePage.
 * @author Group 8
 */
public class HomePageTest extends GWTTestCase
{
  public String getModuleName()
  {  
	  return "com.jhnews.client.HomePage";
  }
  
  @Test
  public void testHomePageConstructor()
  { 
	  HomePage hp = new HomePage();
	  assertTrue(hp != null);
  }
}