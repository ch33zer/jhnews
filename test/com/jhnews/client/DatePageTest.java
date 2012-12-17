package com.jhnews.client;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * This class is the JUnit test for DatePage.
 * @author Group 8
 */
public class DatePageTest extends GWTTestCase
{
  public String getModuleName()
  {  
	  return "com.jhnews.client.DatePage";
  }
  
  @Test
  public void testDatePageConstructor()
  { 
	  DatePanel dp = new DatePanel();
	  assertTrue(dp != null);
  }
}