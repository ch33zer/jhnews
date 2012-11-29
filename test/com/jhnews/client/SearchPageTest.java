package com.jhnews.client;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

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