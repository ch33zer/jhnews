package com.jhnews.client;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

public class RegistrationPageTest extends GWTTestCase
{
  public String getModuleName()
  {  
	  return "com.jhnews.client.RegistrationPage";
  }
  
  @Test
  public void testRegistrationPageConstructor()
  { 
	  RegistrationPage rp = new RegistrationPage();
	  assertTrue(rp != null);
  }
}