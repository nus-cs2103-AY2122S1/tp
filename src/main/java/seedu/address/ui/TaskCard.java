package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class TaskCard extends UiPart<Region> {

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">
     * The issue on SE-EDU AB-level4</a>
     */

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label taskId;
    @FXML
    private Label taskName;
    @FXML
    private Label taskDeadline;
    @FXML
    private Label isComplete;

    /**
     * Creates a {@code TaskCard} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super((task.isComplete()) ? "TaskCardComplete.fxml" : "TaskCardIncomplete.fxml");
        this.task = task;
        id.setText(displayedIndex + ". ");
        taskId.setText(task.getTaskId().value);
        taskName.setText(task.getTaskName().taskName);
        taskName.setWrapText(true);
        taskDeadline.setText(task.getTaskDeadline().toString());
        isComplete.setText(task.isComplete() ? "Completed" : "Incomplete");
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
        TaskCard card = (TaskCard) other;

        return id.getText().equals(card.id.getText()) && task.equals(card.task);
    }
}
