package com.jhnews.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.jhnews.shared.Tags;

/**
 * Allows the admin to add new tags or remove existing tags
 * @author Group 8
 *
 */
public class EditTagsPage extends AdminPage {
	
	private HintTextBox addTagBox;
	private TagsPanel tagsPanel;
	private Button addButton;
	private Button deleteButton;
	
	private RestrictedServiceAsync restrictedService = GWT.create(RestrictedService.class);
	
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
				if (addTagBox.getText() != null) {
					restrictedService.addTag(LoginManager.getInstance().getSessionID(), addTagBox.getText(), new AsyncCallback<Void>() {
						@Override
						public void onFailure(Throwable caught) {
							setError("Failed to add tags");
						}

						@Override
						public void onSuccess(Void result) {
							tagsPanel.fillTags();							
						}
					});
				}
			}		
		});
		deleteButton = new Button("Delete tags", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				List<Tags> tags = tagsPanel.getCheckedTags();
				for (Tags tag : tags) {
					restrictedService.removeTag(LoginManager.getInstance().getSessionID(), tag, new AsyncCallback<Void>() {
						@Override
						public void onFailure(Throwable caught) {
							setError("Failed to delete tags");
						}

						@Override
						public void onSuccess(Void result) {
							tagsPanel.fillTags();							
						}
					});
				}
			}		
		});
		
		addPanel.add(addTagBox);
		addPanel.add(addButton);
		addWidget(addPanel);
		addWidget(tagsPanel);
		addWidget(deleteButton);
	}
	
}
