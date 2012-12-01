package com.jhnews.client;

import org.junit.Test;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.TextBox;

public class HintPasswordTextBoxTest extends GWTTestCase
{
  public String getModuleName()
  {  
	  return "com.jhnews.client.HintPasswordTextBox";
  }
  
  @Test
  public void testOnFocus()
  {
	  final HintPasswordTextBox hptb = new HintPasswordTextBox("hint");
	  
	  TextBox textbox = new TextBox();
	  textbox.setFocus(false);
	  
	  textbox.addFocusHandler(new FocusHandler() {

			@Override
			public void onFocus(FocusEvent event) {
				hptb.onFocus(event);
				assertTrue(hptb.hasFocus());
			}
	    });
	  textbox.setFocus(true);
  }
  
  @Test
  public void testHintPassWordTextConstructor()
  { 
	  HintPasswordTextBox hptb = new HintPasswordTextBox();
	  assertTrue(hptb != null);
  }
  
  @Test
  public void testHintPassWordTextConstructor2()
  { 
	  HintPasswordTextBox hptb = new HintPasswordTextBox("new hint");
	  assertTrue(hptb != null);
  }
  
  @Test
  public void testSetHint()
  {
	  HintPasswordTextBox hptb = new HintPasswordTextBox();
	  String oldHint = hptb.getHint();
	  hptb.setHint("new hint");
	  String newHint = hptb.getHint();
	  assertFalse(oldHint.equals(newHint));
  }
  
  @Test
  public void testGetHint()
  {
	  HintPasswordTextBox hptb = new HintPasswordTextBox("hint");
	  assertTrue(hptb.getHint().equals("hint"));
  }
  
  @Test
  public void testHasFocus()
  {
	  HintPasswordTextBox hptb = new HintPasswordTextBox("hint");
	  hptb.setFocus(true);
	  assertTrue(hptb.hasFocus());
  }
  
  @Test
  public void testEnableHint()
  {
	  HintPasswordTextBox hptb = new HintPasswordTextBox("hint");
	  hptb.setHint("this is the hint");
	  hptb.enableHint();
	  assertTrue(hptb.getText().equals("this is the hint"));
  }
  
  @Test
  public void testOnBlur()
  {
	  final HintPasswordTextBox hptb = new HintPasswordTextBox("hint");	  
	  TextBox textbox = new TextBox();
	  
	  textbox.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				hptb.onBlur(event);
				assertTrue(true);
			}
	    });
	  textbox.setFocus(false);
  }
}