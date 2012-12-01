package com.jhnews.client;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * This class is the JUnit test for SearchPage.
 * @author Group 8
 */
public class SearchPageTest extends GWTTestCase
{
  public String getModuleName()
  {  
	  return "com.jhnews.client.SearchPage";
  }
  
  @Test
  public void testSearchPageConstructor()
  { 
	  SearchPage sp = new SearchPage();
	  assertTrue(sp != null);
  }
}