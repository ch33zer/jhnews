package com.jhnews.client;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * This class is the JUnit test for RegistrationPage.
 * @author Group 8
 */
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