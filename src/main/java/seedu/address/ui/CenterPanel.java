package seedu.address.ui;

import com.calendarfx.model.Calendar;

import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;

/**
 * The Center Panel of the App that can switch between {@code Person Panel} and {@code Calendar Panel}.
 * Center Panel subscribes to Events meant for Person Panel and Calendar Panel
 * in order to handle the switching between the displays.
 */
public class CenterPanel extends UiPart<Region> {

    private static final String FXML = "CenterPanel.fxml";

    private SchedulePanel schedulePanel;

    private PersonListPanel personListPanel;

    private PersonGridPanel personGridPanel;

    @FXML
    private StackPane centerPanelPlaceholder;

    /**
     * Constructor for a CenterPanel.
     *
     * @param calendar The calendar in the CenterPanel.
     * @param personList The ObservableList of persons.
     */
    public CenterPanel(Calendar calendar, ObservableList<Person> personList, ObservableList<Lesson> lessonList) {
        super(FXML);
        personListPanel = new PersonListPanel(personList);
        schedulePanel = new SchedulePanel(calendar);
        personGridPanel = new PersonGridPanel(personList, lessonList);
        displayPersonGridPanel();
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    public SchedulePanel getSchedulePanel() {
        return schedulePanel;
    }

    /**
     * Bring PersonListPanel to top of the stack's child list.
     */
    public void displayPersonListPanel() {
        if (!centerPanelPlaceholder.getChildren().contains(personListPanel.getRoot())) {
            centerPanelPlaceholder.getChildren().setAll(personListPanel.getRoot());
        }
    }

    public void displayPersonGridPanel() {
        if (!centerPanelPlaceholder.getChildren().contains(personGridPanel.getRoot())) {
            personGridPanel.setListPanels();
            centerPanelPlaceholder.getChildren().setAll(personGridPanel.getRoot());
        }
    }


    public void displayPersonGridPanel(Person student, ObservableList<Lesson> lessons) {
        if (!centerPanelPlaceholder.getChildren().contains(personGridPanel.getRoot())) {
            personGridPanel.fillListPanels(student, lessons);
            personGridPanel.setListPanels();
            centerPanelPlaceholder.getChildren().setAll(personGridPanel.getRoot());
        }
    }

    /**
     * Bring SchedulePanel to top of the stack's child list.
     */
    public void displaySchedulePanel() {
        if (!centerPanelPlaceholder.getChildren().contains(schedulePanel.getRoot())) {
            centerPanelPlaceholder.getChildren().setAll(schedulePanel.getRoot());
        }
    }
}
