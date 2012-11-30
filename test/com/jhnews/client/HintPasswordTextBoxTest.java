package com.jhnews.client;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

public class HintPasswordTextBoxTest extends GWTTestCase
{
  public String getModuleName()
  {  
	  return "com.jhnews.client.HintPassWordText";
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
  public void testOnBlur()
  {
  }
}