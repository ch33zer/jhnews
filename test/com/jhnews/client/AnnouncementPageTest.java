package com.jhnews.client;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;
import com.jhnews.shared.Announcement;

/**
 * This class is the JUnit test for AnnouncementPage.
 * @author Group 8
 */
public class AnnouncementPageTest extends GWTTestCase
{
  public String getModuleName()
  {  
	  return "com.jhnews.client.AnnouncementPage";
  }
  

@Test
  public void testAnnouncementPageConstructor()
  { 
	  AnnouncementPanel ap = new AnnouncementPanel(new Announcement());
	  assertTrue(ap != null);
  }
}