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
	private Label outputLabel;
	private VerticalPanel masterPanel;

	/**
	 * Creates the title and master panel
	 */
	public Page() {
		pageTitleLabel = new Label();
		pageTitleLabel.addStyleDependentName("title");
		pageTitleLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		outputLabel = new Label();
		outputLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		masterPanel = new VerticalPanel();
		
		masterPanel.add(pageTitleLabel);
		initWidget(masterPanel);
	}
	
	/**
	 * Sets the page title
	 * @param string Page title
	 */
	protected void setPageTitle(String string) {
		pageTitleLabel.setText(string);
	}
	
	/**
	 * Adds a widget to the master panel
	 * @param widget Panel to add
	 */
	protected void addWidget(Widget widget) {
		masterPanel.add(widget);
	}
	
	/**
	 * Sets the widgets to be left aligned
	 */
	protected void isLeftAlign() {
		masterPanel.addStyleName("leftVerticalPanel");
	}
	
	protected void setSuccess(String success) {
		outputLabel.setText(success);
		outputLabel.addStyleDependentName("success");
		masterPanel.add(outputLabel);
	}
	
	protected void setError(String error) {
		outputLabel.setText(error);
		outputLabel.addStyleDependentName("error");
		masterPanel.add(outputLabel);
	}
	
	protected void removeLabel() {
		masterPanel.remove(outputLabel);
	}
	
}
