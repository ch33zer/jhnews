package com.jhnews.client;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

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