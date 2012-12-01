package com.jhnews.client;

import org.junit.Test;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.jhnews.shared.Announcement;

/**
 * This class is the JUnit test for PageManger.
 * @author Group 8
 */
public class PageManagerTest extends GWTTestCase
{
  public String getModuleName()
  {  
	  return "com.jhnews.client.PageManager";
  }
  
  @Test
  public void testPageManagerConstructor()
  { 
	  assertTrue(PageManager.getInstance() != null);
  }
  
  @Test
  public void testPrepareStartPage()
  {
	  PageManager.getInstance().prepareStartPage();
	  assertTrue(History.getToken().length() > 0);
  }
  
  @Test
  public void testGetInstance()
  {
	  assertTrue(PageManager.getInstance() != null);
  }
  
  @Test
  public void testSetBody()
  {
	  PageManager.getInstance().setBody(PagesEnum.PREFERENCES);
	  assertTrue(RootPanel.get("body").equals(new PreferencesPage()));
  }
  
  @Test
  public void testGenerateAnnouncementPage()
  {
	  Announcement temp = new Announcement();
	  PageManager.getInstance().generateAnnouncementPage(temp);
	  assertTrue(RootPanel.get("body").equals(temp));
  }
  
  @Test
  public void testOnValueChange()
  {
	  TextArea textArea = new TextArea();
	  textArea.addValueChangeHandler(new ValueChangeHandler<String>() {

	        @Override
	        public void onValueChange(ValueChangeEvent<String> event) {
	        	PageManager.getInstance().onValueChange(event);
	        	assertTrue(RootPanel.get("body").equals(new PreferencesPage()));
	        }
	    });
	  textArea.setText("PREFERENCES");
  }
}