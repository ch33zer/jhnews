package com.jhnews.client;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

public class SubmissionPageTest extends GWTTestCase
{
  public String getModuleName()
  {  
	  return "com.jhnews.client.SubmissionPage";
  }
  
  @Test
  public void testSubmissionPageConstructor()
  { 
	  SubmissionPage sp = new SubmissionPage();
	  assertTrue(sp != null);
  }
}