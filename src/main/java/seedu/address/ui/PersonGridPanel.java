package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;

public class PersonGridPanel extends UiPart<Region> {
    private static final String FXML = "PersonGridPanel.fxml";
    private static final Logger logger = LogsCenter.getLogger(CenterPanel.class);

    private PersonListPanel personListPanel;
    private LessonListPanel lessonListPanel;

    @FXML
    private StackPane personListPanelPlaceholder;
    @FXML
    private StackPane lessonListPanelPlaceholder;

    /**
     * Constructor for a CenterPanel.
     *
     * @param personList The ObservableList of persons.
     */
    public PersonGridPanel(ObservableList<Person> personList, ObservableList<Lesson> lessonList) {
        super(FXML);
        personListPanel = new PersonListPanel(personList);
        lessonListPanel = new LessonListPanel(lessonList);
    }

    public ListView<Person> getPersonListView() {
        return personListPanel.getPersonListView();
    }

    /**
     * Initialise panels content.
     *
     * @param student Student to view.
     * @param lessons Lessons of the student.
     */
    public void fillListPanels(Person student, ObservableList<Lesson> lessons) {
        lessonListPanel = new LessonListPanel(lessons, student);
    }

    /**
     * Initialise empty panels content.
     *
     * @param lessons Lessons of the student.
     */
    public void fillListPanels(ObservableList<Lesson> lessons) {
        logger.info("Showing the student list without lessons.");
        lessonListPanel = new LessonListPanel(lessons);
    }

    /**
     * Set the children of the panels.
     */
    public void setListPanels() {
        personListPanelPlaceholder.getChildren().setAll(personListPanel.getRoot());
        lessonListPanelPlaceholder.getChildren().setAll(lessonListPanel.getRoot());
    }
}
