package com.jhnews.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.jhnews.shared.Announcement;

public class TagsPanel extends Composite {
	
	private VerticalPanel masterPanel;
	private Grid gridPanel;
	private List<String> tags;
	private List<CheckBox> checkBoxes;
	
	private static final int COLUMNS = 3;
	private static final String CELL_FORMAT_WIDTH = "100px";
	
	public TagsPanel() {
		masterPanel = new VerticalPanel();
	    initWidget(masterPanel);
	}
	
	public void setTags(List<String> tags) {
		this.tags = tags;
		if (tags != null) {
			CheckBox checkBox;
			int rows = tags.size() / COLUMNS + 1;
			int columns = COLUMNS;
			checkBoxes = new ArrayList<CheckBox>(tags.size());		
			gridPanel = new Grid(rows, columns);
			masterPanel.clear();
			for (int i = 0; i < tags.size(); i++) {
				checkBox = new CheckBox(tags.get(i));
				gridPanel.setWidget(i / COLUMNS, i % COLUMNS, checkBox);
				gridPanel.getCellFormatter().setWidth(i / COLUMNS, i % COLUMNS, CELL_FORMAT_WIDTH);
			}
		}
	}
	
	public boolean boxIsChecked(int index) {
		return checkBoxes.get(index).getValue();
	}
	
	public void checkAnnouncementTags(Announcement announcement) {
		checkBoxes.get(1).setValue(announcement.isTag1());
		checkBoxes.get(2).setValue(announcement.isTag2());
		checkBoxes.get(3).setValue(announcement.isTag3());
		checkBoxes.get(4).setValue(announcement.isTag4());
		checkBoxes.get(5).setValue(announcement.isTag5());
	}
	
}
