package com.jhnews.client;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Extends the TextBox class to allow for a hint before user input
 * @author Group 8
 *
 */
public class HintTextBox extends TextBox implements BlurHandler, FocusHandler {
	private String hint;
	private HandlerRegistration blurHandler;
	private HandlerRegistration focusHandler;

	/**
	 * Default constructor with no hint
	 */
	public HintTextBox() {
		super();
		this.setStylePrimaryName("gwt-TextBox");
	}

	/**
	 * Constructor with hint
	 * @param hint Hint shown when there is not text in the box
	 */
	public HintTextBox(String hint) {
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
			// Remove handlers
			blurHandler.removeHandler();
			focusHandler.removeHandler();
		}
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
			setText(hint);
			addStyleDependentName("hint");
		}
	}

	/**
	 * Returns to non-hint style when the objects gains focus
	 */
	@Override
	public void onFocus(FocusEvent event) {
		removeStyleDependentName("hint");
		if (getText().equalsIgnoreCase(hint)) {
			setText("");
		}
	}
}
