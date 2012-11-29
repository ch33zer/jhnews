package com.jhnews.client;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;
import com.jhnews.shared.Announcement;

public class HintPasswordTextBoxTest extends GWTTestCase
{
  public String getModuleName()
  {  
	  return "com.jhnews.client.HintPassWordText";
  }
  
  @Test
  public void testHintPassWordTextConstructor()
  { 
	  HintPasswordTextBox ap = new HintPasswordTextBox();
	  assertTrue(ap != null);
  }
}