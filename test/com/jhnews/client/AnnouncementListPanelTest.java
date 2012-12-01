package com.jhnews.client;

import java.util.List;

import org.junit.Test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.TextArea;
import com.jhnews.shared.Announcement;

public class AnnouncementListPanelTest extends GWTTestCase
{
  public String getModuleName()
  {  
	  return "com.jhnews.client.AnnouncementListPanel";
  }
  
  @Test
  public void testAnnouncementListPanelConstructor()
  { 
	  AnnouncementListPanel alp = new AnnouncementListPanel();
	  assertTrue(alp != null);
  }
  
  @Test
  public void testOnValueChange()
  {
	  final AnnouncementListPanel alp = new AnnouncementListPanel();
	  
	  TextArea textArea = new TextArea();
	  textArea.addValueChangeHandler(new ValueChangeHandler<String>() {

	        @Override
	        public void onValueChange(ValueChangeEvent<String> event) {
	        	alp.onValueChange(event);
	        	assertTrue(true);
	        }
	    });
	  textArea.setText("1");
  }
  
  @Test
  public void testSetAnnouncementList()
  {
	  UnrestrictedServiceAsync service = GWT.create(UnrestrictedService.class);
	  final AnnouncementListPanel alp = new AnnouncementListPanel();
		if (service == null) {
			service = GWT.create(UnrestrictedService.class);
		}
		service.getTodaysAnnouncements(new AsyncCallback<List<Announcement>>() {
			
			@Override
			public void onSuccess(List<Announcement> result) {
				alp.setAnnouncementList(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				alp.setAnnouncementList(null);
			}
		});
	
	  assertTrue(alp.getAnnouncements() != null);
  }
  
  @Test
  public void testGetAnnouncements()
  {
	  UnrestrictedServiceAsync service = GWT.create(UnrestrictedService.class);
	  AnnouncementListPanel alp = new AnnouncementListPanel();
	  final AnnouncementListPanel alp2 = new AnnouncementListPanel();
		if (service == null) {
			service = GWT.create(UnrestrictedService.class);
		}
		service.getTodaysAnnouncements(new AsyncCallback<List<Announcement>>() {
			
			@Override
			public void onSuccess(List<Announcement> result) {
				alp2.setAnnouncementList(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				alp2.setAnnouncementList(null);
			}
		});
	  assertTrue(alp2.getAnnouncements() != null);
  }
}