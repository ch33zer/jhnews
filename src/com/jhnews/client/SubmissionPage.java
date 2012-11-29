package com.jhnews.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.jhnews.shared.Announcement;
import com.jhnews.shared.User;

/**
 * This class is the new announcement submission page.
 * 
 * @author Group 8
 */
public class SubmissionPage extends Page {
	private AnnouncementFetcherAsync service = GWT
			.create(AnnouncementFetcher.class);

	/**
	 * This is the default constructor for this SubmissionPage class.
	 */
	public SubmissionPage() {
		if (service == null) {
			service = GWT.create(AnnouncementFetcher.class);
		}
		final VerticalPanel masterPanel = new VerticalPanel();
		LoginManager.getInstance().isLoggedOn(
				LoginManager.getInstance().getSessionID(),
				new LoginManagerCallback<Boolean>() {

					@Override
					public void onSuccess(Boolean result) {
						masterPanel.clear();
						generateLoggedInPanel(masterPanel);
					}

					@Override
					public void onFail() {
						masterPanel.clear();
						masterPanel.add(new Label("You must be logged in to submit an announcement"));
					}
				});
		initWidget(masterPanel);
	}

	private void generateLoggedInPanel(VerticalPanel masterPanel) {
		// Variables
		final User currentUser = new User();// TODO change currentUser to the
											// real user once implemented
		final TextBox titleBox = new TextBox();
		final TextBox briefDescBox = new TextBox();
		final TextBox fullDescBox = new TextBox();
		final TextBox locationBox = new TextBox();
		Label pageTitleLabel = new Label("Submit Announcement");
		pageTitleLabel.addStyleDependentName("title");
		Label audienceLabel = new Label("Audience: ");
		Label titleLabel = new Label("Title: ");
		Label briefDescLabel = new Label("Brief Description: ");
		Label fullDescLabel = new Label("Full Description: ");
		Label dateTimeLabel = new Label("Date/Time: ");
		Label locationLabel = new Label("Location: ");
		Label tagsLabel = new Label("Tags: ");
		final CheckBox freshmanAudienceCB = new CheckBox("[Freshmen]");
		final CheckBox sophomoreAudienceCB = new CheckBox("[Sophomore]");
		final CheckBox juniorAudienceCB = new CheckBox("[Junior]");
		final CheckBox seniorAudienceCB = new CheckBox("[Senior]");
		final CheckBox graduateAudienceCB = new CheckBox("[Graduate]");
		final CheckBox facultyAudienceCB = new CheckBox("[Faculty]");
		final DatePicker dateTimePicker = new DatePicker();// TODO add selector,
															// view, and model
		final Label errorLabel = new Label(
				"Invalid Announcement. Please fill in all fields.");
		errorLabel.addStyleDependentName("error");
		errorLabel.setVisible(false);
		Button submitButton = new Button("Submit");

		// Converts all of currentUser's desired tags into CheckBoxes
		//TODO Hookup user tags
		//ArrayList<String> allUserTags = (ArrayList<String>) currentUser.getTags();
		final ArrayList<CheckBox> allTagCBs = new ArrayList<CheckBox>();
		for (int i = 0; i < 9; i++) {
			allTagCBs.add(new CheckBox("Tag " + i));
		}

		// Set ClickHandlers for interactive widgets
		submitButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (titleBox.getText().length() != 0
						&& briefDescBox.getText().length() != 0
						&& fullDescBox.getText().length() != 0
						&& dateTimePicker.getValue() != null
						&& locationBox.getText().length() != 0) {
					errorLabel.setVisible(false);
					// Build the announcement to be submitted
					Announcement currentSubmission = new Announcement();
					currentSubmission.setSubmitter(currentUser);
					currentSubmission.setTitle(titleBox.getText());
					currentSubmission.setLocation(locationBox.getText());
					currentSubmission.setBriefDescription(briefDescBox
							.getText());
					currentSubmission.setLongDescription(fullDescBox.getText());
					currentSubmission.setEventDate(dateTimePicker.getValue());

					// Set announcements in the submission according to checked
					// announcement CheckBoxs
					Boolean[] checkedAnnouncementCBValues = new Boolean[6];
					for (int i = 0; i < 6; i++)// for all tag CheckBoxes
					{
						checkedAnnouncementCBValues[0] = freshmanAudienceCB
								.getValue();
						checkedAnnouncementCBValues[1] = sophomoreAudienceCB
								.getValue();
						checkedAnnouncementCBValues[2] = juniorAudienceCB
								.getValue();
						checkedAnnouncementCBValues[3] = seniorAudienceCB
								.getValue();
						checkedAnnouncementCBValues[4] = graduateAudienceCB
								.getValue();
						checkedAnnouncementCBValues[5] = facultyAudienceCB
								.getValue();
						currentSubmission
								.setAudiences(checkedAnnouncementCBValues);
					}

					// Set tags in the submission according to checked tag
					// CheckBoxs
					ArrayList<String> checkedTagCBValues = new ArrayList<String>();
					for (int i = 0; i < allTagCBs.size(); i++)// for all tag
																// CheckBoxes
					{
						if (allTagCBs.get(i).getValue())
							;// if CB is checked
						checkedTagCBValues.add(allTagCBs.get(i).getText());// store
																			// CB's
																			// tag
																			// in
																			// checkedCBValues
					}
					currentSubmission.setTags(checkedTagCBValues);
					service.putAnnouncement(currentSubmission,
							new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable caught) {
									errorLabel.setVisible(true);
								}

								@Override
								public void onSuccess(Void result) {
									History.newItem("HOME");
								}
							});
				} else {
					errorLabel.setVisible(true);
				}
			}
		});

		// Set widget details
		pageTitleLabel
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		titleBox.setMaxLength(70);
		titleBox.setVisibleLength(80);
		briefDescBox.setMaxLength(255);
		briefDescBox.setVisibleLength(80 - 12);
		fullDescBox.setMaxLength(500);
		fullDescBox.setVisibleLength(80);
		locationBox.setMaxLength(77);
		locationBox.setVisibleLength(80 - 3);

		// Create Panels
		masterPanel.addStyleName("leftVerticalPanel");
		HorizontalPanel audiencePanel = new HorizontalPanel();
		VerticalPanel innerVertAudiencePanel = new VerticalPanel();
		HorizontalPanel innerHoriAudiencePanel1 = new HorizontalPanel();
		HorizontalPanel innerHoriAudiencePanel2 = new HorizontalPanel();
		HorizontalPanel titlePanel = new HorizontalPanel();
		HorizontalPanel briefDescPanel = new HorizontalPanel();
		HorizontalPanel fullDescPanel = new HorizontalPanel();
		HorizontalPanel dateTimePanel = new HorizontalPanel();
		HorizontalPanel locationPanel = new HorizontalPanel();
		HorizontalPanel tagsPanel = new HorizontalPanel();
		// VerticalPanel innerVertTagsPanel = new VerticalPanel();

		// Setup Panels for each category
		innerHoriAudiencePanel1.add(freshmanAudienceCB);
		innerHoriAudiencePanel1.add(sophomoreAudienceCB);
		innerHoriAudiencePanel1.add(juniorAudienceCB);
		innerHoriAudiencePanel2.add(seniorAudienceCB);
		innerHoriAudiencePanel2.add(graduateAudienceCB);
		innerHoriAudiencePanel2.add(facultyAudienceCB);
		innerVertAudiencePanel.add(innerHoriAudiencePanel1);
		innerVertAudiencePanel.add(innerHoriAudiencePanel2);
		audiencePanel.add(audienceLabel);
		audiencePanel.add(innerVertAudiencePanel);
		titlePanel.add(titleLabel);
		titlePanel.add(titleBox);
		briefDescPanel.add(briefDescLabel);
		briefDescPanel.add(briefDescBox);
		fullDescPanel.add(fullDescLabel);
		fullDescPanel.add(fullDescBox);
		dateTimePanel.add(dateTimeLabel);
		dateTimePanel.add(dateTimePicker);
		locationPanel.add(locationLabel);
		locationPanel.add(locationBox);
		tagsPanel.add(tagsLabel);
		//TODO Re add
		//tagsPanel.add(generateInnerVertTagsPanel(allUserTags));

		// Add all category Panels to the masterPanel
		masterPanel.add(pageTitleLabel);
		masterPanel.add(errorLabel);
		masterPanel.add(audiencePanel);
		masterPanel.add(titlePanel);
		masterPanel.add(briefDescPanel);
		masterPanel.add(fullDescPanel);
		masterPanel.add(dateTimePanel);
		masterPanel.add(locationPanel);
		masterPanel.add(tagsPanel);
		masterPanel.add(submitButton);
	}

	// Code below splits user's tags into groups of 3 and creates their
	// CheckBoxes
	private VerticalPanel generateInnerVertTagsPanel(
			ArrayList<String> inAllUserTags) {
		VerticalPanel innerVertTagsPanel = new VerticalPanel();
		ArrayList<HorizontalPanel> threeTagPanels = new ArrayList<HorizontalPanel>();
		HorizontalPanel tempHoriPanel = new HorizontalPanel();
		int counter = 0;
		int counter2 = inAllUserTags.size();
		while (counter2 > 0) {
			if (counter2 >= 3) {
				tempHoriPanel.add(new CheckBox(inAllUserTags.get(counter)));
				tempHoriPanel.add(new CheckBox(inAllUserTags.get(counter + 1)));
				tempHoriPanel.add(new CheckBox(inAllUserTags.get(counter + 2)));
				threeTagPanels.add(tempHoriPanel);
				counter += 3;
				counter2 -= 3;
			} else if (counter2 == 2) {
				tempHoriPanel.add(new CheckBox(inAllUserTags.get(counter)));
				tempHoriPanel.add(new CheckBox(inAllUserTags.get(counter + 1)));
				threeTagPanels.add(tempHoriPanel);
				counter += 2;
				counter2 -= 2;
			} else if (counter2 == 1) {
				tempHoriPanel.add(new CheckBox(inAllUserTags.get(counter)));
				threeTagPanels.add(tempHoriPanel);
				counter += 1;
				counter2 -= 1;
			}
		}
		for (int i = 0; i < threeTagPanels.size(); i++) {
			innerVertTagsPanel.add(threeTagPanels.get(i));
		}
		return innerVertTagsPanel;
	}
}