package com.jhnews.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.jhnews.shared.Announcement;

/**
 * A panel that contains a set of subject tags
 * 
 * @author Group 8
 */
public class TagsPanel extends Composite {
	
	private VerticalPanel masterPanel;
	private Grid gridPanel;
	private List<CheckBox> checkBoxes;
	
	private static final int COLUMNS = 3;
	private static final String CELL_FORMAT_WIDTH = "100px";
	
	/**
	 * This is the default constructor for the tags panel class.
	 */
	public TagsPanel() {
		masterPanel = new VerticalPanel();
		fillSampleInfo();
	    initWidget(masterPanel);
	}
	
	/**
	 * Sets the list of tags in the panel.
	 * @param tags the list of tags in the panel
	 */
	public void setTags(List<String> tags) {
		if (tags != null) {
			CheckBox checkBox;
			int rows = tags.size() / COLUMNS + 1;
			int columns = COLUMNS;
			checkBoxes = new ArrayList<CheckBox>(tags.size());		
			gridPanel = new Grid(rows, columns);
			masterPanel.clear();
			for (int i = 0; i < tags.size(); i++) {
				checkBox = new CheckBox(tags.get(i));
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
	 */
	public boolean boxIsChecked(int index) {
		return checkBoxes.get(index).getValue();
	}
	
	/**
	 * Sets the checkboxes according to an announcement's set of tags.
	 * @param announcement the announcement to check checkboxes according to
	 */
	public void checkAnnouncementTags(Announcement announcement) {
		checkBoxes.get(0).setValue(announcement.isTag1());
		checkBoxes.get(1).setValue(announcement.isTag2());
		checkBoxes.get(2).setValue(announcement.isTag3());
		checkBoxes.get(3).setValue(announcement.isTag4());
		checkBoxes.get(4).setValue(announcement.isTag5());
	}
	
	/**
	 * Sets the tags according to a default sample set of tags.
	 * 
	 */
	public void fillSampleInfo() {
		List<String> tags = new ArrayList<String>();
		tags.add("Food");
		tags.add("Free");
		tags.add("DBlaise");
		tags.add("Cheezer");
		tags.add("Greek life");
		setTags(tags);
	}
	
}
