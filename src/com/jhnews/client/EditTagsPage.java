package com.jhnews.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * Allows the admin to add new tags or remove existing tags
 * @author nirrattner
 *
 */
public class EditTagsPage extends AdminPage {
	
	private HintTextBox addTagBox;
	private TagsPanel tagsPanel;
	private Button addButton;
	private Button deleteButton;
	
	/**
	 * Sets the title and left align
	 */
	public EditTagsPage() {
		setPageTitle("Edit Tags");
		isLeftAlign();
	}

	/**
	 * Creates the widgets for the page
	 */
	@Override
	protected void createRestrictedContent() {
		 HorizontalPanel addPanel = new HorizontalPanel();
		
		tagsPanel = new TagsPanel();
		addTagBox = new HintTextBox("Tag name");
		addButton = new Button("Add tag", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
			}		
		});
		deleteButton = new Button("Delete tags", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
			}		
		});
		
		addPanel.add(addTagBox);
		addPanel.add(addButton);
		addWidget(addPanel);
		addWidget(tagsPanel);
		addWidget(deleteButton);
	}
	
}
