package com.jhnews.client;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

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