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
	private UnrestrictedServiceAsync unrestrictedService = GWT
			.create(UnrestrictedService.class);
	
	private RestrictedServiceAsync restrictedService = GWT
			.create(RestrictedService.class);

	
	private VerticalPanel masterPanel;
	private TextBox titleTextBox;
	private TextBox briefDescTextBox;
	private TextBox fullDescTextBox;
	private TextBox locationTextBox;
	private CheckBox freshmanCheckBox;
	private CheckBox sophomoreCheckBox;
	private CheckBox juniorCheckBox;
	private CheckBox seniorCheckBox;
	private CheckBox graduateCheckBox;
	private CheckBox facultyCheckBox;
	private DatePicker dateTimePicker;
	private TagsPanel tagsPanel;


	/**
	 * This is the default constructor for this SubmissionPage class.
	 */
	public SubmissionPage() {
		if (unrestrictedService == null) {
			unrestrictedService = GWT.create(UnrestrictedService.class);
		}
		if (restrictedService == null) {
			restrictedService = GWT.create(RestrictedService.class);
		}
		masterPanel = new VerticalPanel();
		LoginManager.getInstance().isLoggedOn(
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
		titleTextBox = new TextBox();
		briefDescTextBox = new TextBox();
		fullDescTextBox = new TextBox();
		locationTextBox = new TextBox();
		Label pageTitleLabel = new Label("Submit Announcement");
		pageTitleLabel.addStyleDependentName("title");
		Label audienceLabel = new Label("Audience: ");
		Label titleLabel = new Label("Title: ");
		Label briefDescLabel = new Label("Brief Description: ");
		Label fullDescLabel = new Label("Full Description: ");
		Label dateTimeLabel = new Label("Date/Time: ");
		Label locationLabel = new Label("Location: ");
		Label tagsLabel = new Label("Tags: ");
		freshmanCheckBox = new CheckBox("[Freshmen]");
		sophomoreCheckBox = new CheckBox("[Sophomore]");
		juniorCheckBox = new CheckBox("[Junior]");
		seniorCheckBox = new CheckBox("[Senior]");
		graduateCheckBox = new CheckBox("[Graduate]");
		facultyCheckBox = new CheckBox("[Faculty]");
		dateTimePicker = new DatePicker();// TODO add selector,
															// view, and model
		tagsPanel = new TagsPanel();
		final Label errorLabel = new Label(
				"Invalid Announcement. Please fill in all fields.");
		errorLabel.addStyleDependentName("error");
		errorLabel.setVisible(false);
		Button submitButton = new Button("Submit");

		// Set ClickHandlers for interactive widgets
		submitButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (titleTextBox.getText().length() != 0
						&& briefDescTextBox.getText().length() != 0
						&& fullDescTextBox.getText().length() != 0
						&& dateTimePicker.getValue() != null
						&& locationTextBox.getText().length() != 0) {
					errorLabel.setVisible(false);
					// Build the announcement to be submitted
					Announcement currentSubmission = new Announcement();
					currentSubmission.setSubmitter(currentUser);
					currentSubmission.setTitle(titleTextBox.getText());
					currentSubmission.setLocation(locationTextBox.getText());
					currentSubmission.setBriefDescription(briefDescTextBox
							.getText());
					currentSubmission.setLongDescription(fullDescTextBox.getText());
					currentSubmission.setEventDate(dateTimePicker.getValue());

					// Set announcements in the submission according to checked
					// announcement CheckBoxs

						currentSubmission.setToFreshman(freshmanCheckBox
								.getValue());
						currentSubmission.setToSophomore(sophomoreCheckBox
								.getValue());
						currentSubmission.setToJunior(juniorCheckBox
								.getValue());
						currentSubmission.setToSenior(seniorCheckBox
								.getValue());
						currentSubmission.setToGraduate(graduateCheckBox
								.getValue());
						currentSubmission.setToFaculty(facultyCheckBox
								.getValue());


					// Set tags in the submission according to checked tag
					// CheckBoxs
					currentSubmission.setTag1(tagsPanel.boxIsChecked(0));
					currentSubmission.setTag2(tagsPanel.boxIsChecked(1));
					currentSubmission.setTag3(tagsPanel.boxIsChecked(2));
					currentSubmission.setTag4(tagsPanel.boxIsChecked(3));
					currentSubmission.setTag5(tagsPanel.boxIsChecked(4));
					
					currentSubmission.setHasEventTime(false);
					/*ArrayList<String> checkedTagCBValues = new ArrayList<String>();
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
					currentSubmission.setTags(checkedTagCBValues);*/
					restrictedService.putAnnouncement(LoginManager.getInstance().getSessionID(), currentSubmission,
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
		titleTextBox.setMaxLength(70);
		titleTextBox.setVisibleLength(80);
		briefDescTextBox.setMaxLength(255);
		briefDescTextBox.setVisibleLength(80 - 12);
		fullDescTextBox.setMaxLength(500);
		fullDescTextBox.setVisibleLength(80);
		locationTextBox.setMaxLength(77);
		locationTextBox.setVisibleLength(80 - 3);

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
		// VerticalPanel innerVertTagsPanel = new VerticalPanel();

		// Setup Panels for each category
		innerHoriAudiencePanel1.add(freshmanCheckBox);
		innerHoriAudiencePanel1.add(sophomoreCheckBox);
		innerHoriAudiencePanel1.add(juniorCheckBox);
		innerHoriAudiencePanel2.add(seniorCheckBox);
		innerHoriAudiencePanel2.add(graduateCheckBox);
		innerHoriAudiencePanel2.add(facultyCheckBox);
		innerVertAudiencePanel.add(innerHoriAudiencePanel1);
		innerVertAudiencePanel.add(innerHoriAudiencePanel2);
		audiencePanel.add(audienceLabel);
		audiencePanel.add(innerVertAudiencePanel);
		titlePanel.add(titleLabel);
		titlePanel.add(titleTextBox);
		briefDescPanel.add(briefDescLabel);
		briefDescPanel.add(briefDescTextBox);
		fullDescPanel.add(fullDescLabel);
		fullDescPanel.add(fullDescTextBox);
		dateTimePanel.add(dateTimeLabel);
		dateTimePanel.add(dateTimePicker);
		locationPanel.add(locationLabel);
		locationPanel.add(locationTextBox);

		// Add all category Panels to the masterPanel
		masterPanel.add(pageTitleLabel);
		masterPanel.add(errorLabel);
		masterPanel.add(audiencePanel);
		masterPanel.add(titlePanel);
		masterPanel.add(briefDescPanel);
		masterPanel.add(fullDescPanel);
		masterPanel.add(dateTimePanel);
		masterPanel.add(locationPanel);
		masterPanel.add(tagsLabel);
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