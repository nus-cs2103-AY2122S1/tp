package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;

public class PersonGridPanel extends UiPart<Region> {
    private static final String FXML = "PersonGridPanel.fxml";
    private static final String NO_EXISTING_LESSONS_MESSAGE = "This student has no lessons!";

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

    /**
     * Initialise panels content.
     * @param student Student to view.
     * @param lessons Lessons of the student.
     */
    public void fillListPanels(Person student, ObservableList<Lesson> lessons) {
        lessonListPanel = lessons.isEmpty()
                ? new LessonListPanel(lessons, student, NO_EXISTING_LESSONS_MESSAGE)
                : new LessonListPanel(lessons, student);
    }

    /**
     * Set the children of the panels.
     */
    public void setListPanels() {
        personListPanelPlaceholder.getChildren().setAll(personListPanel.getRoot());
        lessonListPanelPlaceholder.getChildren().setAll(lessonListPanel.getRoot());
    }
}
