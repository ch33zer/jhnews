package com.jhnews.client;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;
import com.jhnews.shared.Announcement;

public class AnnouncementPageTest extends GWTTestCase
{
  public String getModuleName()
  {  
	  return "com.jhnews.client.AnnouncementPage";
  }
  
  @Test
  public void testAnnouncementPageConstructor()
  { 
	  AnnouncementPage ap = new AnnouncementPage(new Announcement());
	  assertTrue(ap != null);
  }
}