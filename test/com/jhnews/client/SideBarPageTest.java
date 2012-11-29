package com.jhnews.client;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

public class SideBarPageTest extends GWTTestCase
{
  public String getModuleName()
  {  
	  return "com.jhnews.client.SideBarPage";
  }
  
  @Test
  public void testSideBarPageConstructor()
  { 
	  SideBarPage sbp = new SideBarPage();
	  assertTrue(sbp != null);
  }
}