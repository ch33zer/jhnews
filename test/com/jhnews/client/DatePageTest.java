package com.jhnews.client;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

public class DatePageTest extends GWTTestCase
{
  public String getModuleName()
  {  
	  return "com.jhnews.client.DatePage";
  }
  
  @Test
  public void testDatePageConstructor()
  { 
	  DatePage dp = new DatePage();
	  assertTrue(dp != null);
  }
}