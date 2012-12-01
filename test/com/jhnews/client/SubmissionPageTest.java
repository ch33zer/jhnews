package com.jhnews.client;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * This class is the JUnit test for SubmissionPage.
 * @author Group 8
 */
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