package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.person.NextMeeting;
import seedu.address.model.person.Person;

/**
 * An UI that acts as the side bar of the application, housing panels for easy viewing.
 */
public class SideBar extends UiPart<Region> {

    private static final String FXML = "SideBar.fxml";

    private PersonViewPanel personViewPanel;

    private MeetingsListPanel meetingsListPanel;

    @FXML
    private StackPane personViewPanelPlaceholder;

    @FXML
    private StackPane nextMeetingListPanelPlaceholder;

    /**
     * Creates a {@code SideBar} with panels initiated.
     */
    public SideBar(ObservableList<Person> personToView, ObservableList<NextMeeting> nextMeetings) {
        super(FXML);

        personViewPanel = new PersonViewPanel(personToView);
        personViewPanelPlaceholder.getChildren().add(personViewPanel.getRoot());

        meetingsListPanel = new MeetingsListPanel(nextMeetings);
        nextMeetingListPanelPlaceholder.getChildren().add(meetingsListPanel.getRoot());
    }

}
