package com.jhnews.client;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;

/**
 * This class is the JUnit test for HeaderPage.
 * @author Group 8
 */
public class HeaderPageTest extends GWTTestCase
{
  public String getModuleName()
  {  
	  return "com.jhnews.client.HeaderPage";
  }
  
  @Test
  public void testHeaderPageConstructor()
  { 
	  HeaderPanel hp = new HeaderPanel();
	  assertTrue(hp != null);
  }
  
  @Test
  public void testOnLogin()
  { 
	HorizontalPanel testPanel = new HorizontalPanel();
	HeaderPanel hp = new HeaderPanel();
	hp.onLogin();
	  
	testPanel.add(new Label("Hello, " + LoginManager.getInstance().getUsername()));
	testPanel.add(new Label("|"));
	testPanel.add(new Hyperlink("Preferences", "PREFERENCES"));
	testPanel.add(new Label("|"));
	testPanel.add(new Anchor("Log out"));
	assertTrue(hp.getMasterPanel().equals(testPanel));
  }

  @Test
  public void testOnLogout()
  { 
	HorizontalPanel testPanel = new HorizontalPanel();
	HeaderPanel hp = new HeaderPanel();
	hp.onLogout();
	
	testPanel.clear();
	testPanel.add(new Hyperlink("Register", "REGISTER"));
	testPanel.add(new Label("|"));
	testPanel.add(new Hyperlink("Log in", "LOGIN"));
	assertTrue(hp.getMasterPanel().equals(testPanel));
  }
  
  @Test
  public void testGetMasterPanel()
  {
	HeaderPanel hp = new HeaderPanel();
	assertTrue(hp.getMasterPanel() != null);	
  }
}