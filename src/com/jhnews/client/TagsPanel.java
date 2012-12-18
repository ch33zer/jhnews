package com.jhnews.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.jhnews.shared.Announcement;
import com.jhnews.shared.Tags;
import com.jhnews.shared.User;
import com.jhnews.shared.UserTags;

/**
 * A panel that contains a set of subject tags
 * 
 * @author Group 8
 */
public class TagsPanel extends Composite {
	
	private VerticalPanel masterPanel;
	private Grid gridPanel;
	private List<CheckBox> checkBoxes;
	private List<Tags> tags;
	
	private UnrestrictedServiceAsync unrestrictedService = GWT.create(UnrestrictedService.class);
	
	private static final int COLUMNS = 5;
	private static final String CELL_FORMAT_WIDTH = "100px";
	
	/**
	 * This is the default constructor for the tags panel class.
	 */
	public TagsPanel() {
		if (unrestrictedService == null) {
			unrestrictedService = GWT.create(UnrestrictedService.class);
		}
		masterPanel = new VerticalPanel();
		fillTags();
	    initWidget(masterPanel);
	}
	
	/**
	 * Sets the list of tags in the panel.
	 * @param tags the list of tags in the panel
	 */
	public void setTags(List<Tags> tags) {
		if (tags != null) {
			this.tags = tags;
			CheckBox checkBox;
			int rows = tags.size() / COLUMNS + 1;
			int columns = COLUMNS;
			checkBoxes = new ArrayList<CheckBox>(tags.size());
			gridPanel = new Grid(rows, columns);
			masterPanel.clear();
			for (int i = 0; i < tags.size(); i++) {
				checkBox = new CheckBox(tags.get(i).getName());
				checkBoxes.add(checkBox);
				gridPanel.setWidget(i / COLUMNS, i % COLUMNS, checkBox);
				gridPanel.getCellFormatter().setWidth(i / COLUMNS, i % COLUMNS, CELL_FORMAT_WIDTH);
			}
			masterPanel.add(gridPanel);
		}
	}
	
	/**
	 * Checks if a certain checkbox is checked
	 * @param index the index of the checkbox to check
	 * @return true if the checkbox is checked, false otherwise
	 */
	public boolean boxIsChecked(int index) {
		return checkBoxes.get(index).getValue();
	}
	
	/**
	 * Sets the checkboxes according to an announcement's set of tags.
	 * @param announcement the announcement to check checkboxes according to
	 */
	public void checkAnnouncementTags(Announcement announcement) {
		int index = -1;
		if ((index = tags.indexOf(announcement.getTag1())) != -1) {
			checkBoxes.get(index).setValue(true);
		}
		index = -1;
		if ((index = tags.indexOf(announcement.getTag2())) != -1) {
			checkBoxes.get(index).setValue(true);
		}
		index = -1;
		if ((index = tags.indexOf(announcement.getTag3())) != -1) {
			checkBoxes.get(index).setValue(true);
		}
	}
	
	public void setTagsInAnnouncement(Announcement announcement) {
		int counter = 0;
		int index = 0;
		for (CheckBox checkbox : checkBoxes) {
			if (checkbox.getValue() && counter < 3) {
				switch (counter) {
				case 0:
					announcement.setTag1(tags.get(index));
					break;
				case 1:
					announcement.setTag2(tags.get(index));
					break;
				case 2:
					announcement.setTag3(tags.get(index));
					break;
				}
			}
			index++;
		}
	}
	
	public void checkUserTags(User user) {
		List<UserTags> userTags = user.getTags();
		for (UserTags userTag : userTags) {
			for (CheckBox checkBox : checkBoxes) {
				if (userTag.getTags().toString().equals(checkBox.getText())) {
					checkBox.setValue(true);
					break;
				}
			}
		}
	}
	
	public void setTagsInUser(User user) {
		int counter = 0;
		int index = 0;
		for (CheckBox checkBox : checkBoxes) {
			if (checkBox.getValue()) {
				
			}
			index++;
		}
	}
	
	/**
	 * Sets the tags according to a set of default sample tags.
	 * 
	 */
	public void fillTags() {
		if (unrestrictedService != null) {
			unrestrictedService.getAllActiveTags(new AsyncCallback<List<Tags>>() {
				
				@Override
				public void onSuccess(List<Tags> result) {
					setTags(result);
				}
				
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}
			});
		}
	}
	
}