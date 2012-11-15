package com.jhnews.server;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;
import com.jhnews.server.AnnouncementFetcherImpl;

public class AnnouncementFetcherImplTest extends GWTTestCase
{
  public String getModuleName()
  {  
	  return "com.jhnews.server.AnnouncementFetcherImpl";
  }
  
  @Test
  public void testAnnouncementFetcherConstructor()
  { 
	  AnnouncementFetcherImpl fetcher = new AnnouncementFetcherImpl();
	  assertTrue(fetcher != null);//makes sure the constructor worked
  }
  
  @Test
  public void testGetTodaysAnnouncements()
  {
	  AnnouncementFetcherImpl fetcher = new AnnouncementFetcherImpl();
	  assertTrue(fetcher.getTodaysAnnouncements().isEmpty() == false);//makes sure getTodaysAnnouncements returns an non-empty list
  }	
}