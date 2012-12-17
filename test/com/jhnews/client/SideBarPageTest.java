package com.jhnews.client;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * This class is the JUnit test for SideBarPage.
 * @author Group 8
 */
public class SideBarPageTest extends GWTTestCase
{
  public String getModuleName()
  {  
	  return "com.jhnews.client.SideBarPage";
  }
  
  @Test
  public void testSideBarPageConstructor()
  { 
	  SideBarPanel sbp = new SideBarPanel();
	  assertTrue(sbp != null);
  }
}