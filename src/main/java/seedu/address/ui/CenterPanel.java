package seedu.address.ui;

import com.calendarfx.model.Calendar;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * The Center Panel of the App that can switch between {@code Person Panel} and {@code Calendar Panel}.
 * Center Panel subscribes to Events meant for Person Panel and Calendar Panel
 * in order to handle the switching between the displays.
 */
public class CenterPanel extends UiPart<Region> {

    private static final String FXML = "CenterPanel.fxml";

    private SchedulePanel schedulePanel;

    private PersonListPanel personListPanel;

    private TagListPanel tagListPanel;

    @FXML
    private StackPane centerPanelPlaceholder;

    /**
     * Constructor for a CenterPanel.
     *
     * @param calendar The calendar in the CenterPanel.
     * @param personList The ObservableList of persons.
     */
    public CenterPanel(Calendar calendar, ObservableList<Person> personList, ObservableList<Tag> tagList) {
        super(FXML);
        personListPanel = new PersonListPanel(personList);
        schedulePanel = new SchedulePanel(calendar);
        tagListPanel = new TagListPanel(tagList);
        displayPersonListPanel();
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    public SchedulePanel getSchedulePanel() {
        return schedulePanel;
    }

    /**
     * Brings PersonListPanel to top of the stack's child list.
     */
    public void displayPersonListPanel() {
        if (!centerPanelPlaceholder.getChildren().contains(personListPanel.getRoot())) {
            centerPanelPlaceholder.getChildren().setAll(personListPanel.getRoot());
        }
    }

    /**
     * Brings SchedulePanel to top of the stack's child list.
     */
    public void displaySchedulePanel() {
        if (!centerPanelPlaceholder.getChildren().contains(schedulePanel.getRoot())) {
            centerPanelPlaceholder.getChildren().setAll(schedulePanel.getRoot());
        }
    }

    /**
     * Brings TagListPanel to top of the stack's child list.
     */
    public void displayTagListPanel() {
        if (!centerPanelPlaceholder.getChildren().contains(tagListPanel.getRoot())) {
            centerPanelPlaceholder.getChildren().setAll(tagListPanel.getRoot());
        }
    }
}
