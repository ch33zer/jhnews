package com.jhnews.client;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Extends the PasswordTextBox class to allow for a hint before user input
 * @author Group 8
 *
 */
public class HintPasswordTextBox extends HintTextBox implements BlurHandler, FocusHandler {
	private String hint;
	private HandlerRegistration blurHandler;
	private HandlerRegistration focusHandler;

	/**
	 * Default constructor with no hint
	 */
	public HintPasswordTextBox() {
		super();
		this.setStylePrimaryName("gwt-TextBox");
	}

	/**
	 * Constructor with hint
	 * @param hint Hint shown when there is not text in the box
	 */
	public HintPasswordTextBox(String hint) {
		super();
		this.setStylePrimaryName("gwt-TextBox");
		setHint(hint);
	}

	/**
	 * Sets the hint shown 
	 * @param hint Hint shown when there is not text in the box
	 */
	public void setHint(String hint) {
		this.hint = hint;
		if ((hint != null) && (hint != "")) {
			blurHandler = addBlurHandler(this);
			focusHandler = addFocusHandler(this);
			enableHint();
		} else {
			blurHandler.removeHandler();
			focusHandler.removeHandler();
		}
	}
	
	/**
	 * Gets and returns the hint shown 
	 * @return the hint
	 */
	public String getHint()
	{
		return hint;
	}

	/**
	 * Calls the hint to be shown when the widget loses focus
	 */
	@Override
	public void onBlur(BlurEvent event) {
		enableHint();
	}

	/**
	 * Checks if the box is empty and shows a hint if it is
	 */
	public void enableHint() {
		String text = getText();
		if ((text.length() == 0) || (text.equalsIgnoreCase(hint))) {
			// Show watermark
			setText(hint);
			addStyleDependentName("hint");
			getElement().setAttribute("type", "text");
		}
	}

	/**
	 * Returns to non-hint style when the objects gains focus
	 */
	@Override
	public void onFocus(FocusEvent event) {
		removeStyleDependentName("hint");
		getElement().setAttribute("type", "password");
		if (getText().equalsIgnoreCase(hint)) {
			// Hide watermark
			setText("");
		}
	}
}
