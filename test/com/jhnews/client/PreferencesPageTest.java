package com.jhnews.client;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * This class is the JUnit test for PreferencesPage.
 * @author Group 8
 */
public class PreferencesPageTest extends GWTTestCase
{
  public String getModuleName()
  {  
	  return "com.jhnews.client.PreferencesPage";
  }
  
  @Test
  public void testPreferencesPageConstructor()
  { 
	  PreferencesPage pp = new PreferencesPage();
	  assertTrue(pp != null);
  }
}