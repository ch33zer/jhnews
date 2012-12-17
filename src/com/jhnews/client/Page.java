package com.jhnews.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Abstract class for any page view element
 * @author Group 8
 *
 */
public abstract class Page extends Composite {
	
	private Label pageTitleLabel;
	private VerticalPanel masterPanel;

	Page() {
		pageTitleLabel = new Label();
		pageTitleLabel.addStyleDependentName("title");
		pageTitleLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		masterPanel = new VerticalPanel();
		
		masterPanel.add(pageTitleLabel);
		initWidget(masterPanel);
	}
	
	protected void setPageTitle(String string) {
		pageTitleLabel.setText(string);
	}
	
	protected void addWidget(Widget widget) {
		masterPanel.add(widget);
	}
	
	protected void isLeftAlign() {
		masterPanel.addStyleName("leftVerticalPanel");
	}
	
}
