package com.jhnews.client;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;

public class HeaderPageTest extends GWTTestCase
{
  public String getModuleName()
  {  
	  return "com.jhnews.client.HeaderPage";
  }
  
  @Test
  public void testHeaderPageConstructor()
  { 
	  HeaderPage hp = new HeaderPage();
	  assertTrue(hp != null);
  }
  
  public void testOnLogin()
  { 
	HorizontalPanel testPanel = new HorizontalPanel();
	HeaderPage hp = new HeaderPage();
	hp.onLogin();
	  
	testPanel.add(new Label("Hello, " + LoginManager.getInstance().getUsername()));
	testPanel.add(new Label("|"));
	testPanel.add(new Hyperlink("Preferences", "PREFERENCES"));
	testPanel.add(new Label("|"));
	testPanel.add(new Anchor("Log out"));
	assertTrue(hp.getMasterPanel().equals(testPanel));
  }

  public void testOnLogout()
  { 
	HorizontalPanel testPanel = new HorizontalPanel();
	HeaderPage hp = new HeaderPage();
	hp.onLogout();
	
	testPanel.clear();
	testPanel.add(new Hyperlink("Register", "REGISTER"));
	testPanel.add(new Label("|"));
	testPanel.add(new Hyperlink("Log in", "LOGIN"));
	assertTrue(hp.getMasterPanel().equals(testPanel));
  }
  
  public void testGetMasterPanel()
  {
	HeaderPage hp = new HeaderPage();
	assertTrue(hp.getMasterPanel() != null);	
  }
}