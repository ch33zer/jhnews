package com.jhnews.client;

import org.junit.Test;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.TextBox;

/**
 * This class is the JUnit test for HintTextBox.
 * @author Group 8
 */
public class HintTextBoxTest extends GWTTestCase
{
  public String getModuleName()
  {  
	  return "com.jhnews.client.HintTextBox";
  }
  
  @Test
  public void testHintTextBoxConstructor()
  { 
	  HintTextBox htb = new HintTextBox();
	  assertTrue(htb != null);
  }
  
  @Test
  public void testHintTextBoxConstructor2()
  {
	  HintTextBox htb = new HintTextBox("a hint");
	  assertTrue(htb != null);
  }
  
  @Test
  public void testSetHint()
  {
	  HintTextBox htb = new HintTextBox("original hint");
	  htb.setHint("new hint");
	  assertTrue(htb.getText().equals("new hint"));
  }
  
  @Test
  public void testEnableHint()
  {
	  HintTextBox htb = new HintTextBox("original hint");
	  htb.enableHint();
	  assertTrue(htb.getText().equals("original hint"));
  }
  
  @Test
  public void testOnBlur()
  {
	  final HintTextBox htb = new HintTextBox("original hint");
	  
	  TextBox textbox = new TextBox();
	  
	  textbox.addBlurHandler(new BlurHandler() {
			@Override
			public void onBlur(BlurEvent event) {
				htb.onBlur(event);
				assertTrue(htb.getText().equals("original hint"));
			}
	    });
	  textbox.setFocus(false);
  }
  
  @Test
  public void testOnFocus()
  {
	  final HintPasswordTextBox htb = new HintPasswordTextBox("hint");
	  
	  TextBox textbox = new TextBox();
	  textbox.setFocus(false);
	  
	  textbox.addFocusHandler(new FocusHandler() {

			@Override
			public void onFocus(FocusEvent event) {
				htb.onFocus(event);
				assertTrue(htb.getText() != null);
			}
	    });
	  textbox.setFocus(true);
  }
}