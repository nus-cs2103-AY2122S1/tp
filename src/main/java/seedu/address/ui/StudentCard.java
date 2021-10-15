package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.module.student.Student;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">
     * The issue on TeachingAssistantBuddy level 4</a>
     */

    public final Student student;

    // Independent Ui parts residing in this Ui container
    private TaskListPanel taskListPanel;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label teleHandle;
    @FXML
    private Label studentId;
    @FXML
    private Label email;
    @FXML
    private ListView<Task> taskListView;
    @FXML
    private StackPane taskListPanelPlaceholder;

    /**
     * Creates a {@code StudentCard} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        name.setText(student.getName().fullName);
        teleHandle.setText(student.getTeleHandle().value);
        studentId.setText(student.getStudentId().value);
        email.setText(student.getEmail().value);
    }

    /**
     * Fills up all the placeholders of this StudentCard.
     */
    void fillInnerParts() {
        ObservableList<Task> taskList = student.getTaskList().asModifiableObservableList();
        taskListPanel = new TaskListPanel(student.getTaskList().asModifiableObservableList());
        taskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StringBuilder)) {
            return false;
        }

        // state check
        StudentCard card = (StudentCard) other;

        return id.getText().equals(card.id.getText()) && student.equals(card.student);
    }
}
