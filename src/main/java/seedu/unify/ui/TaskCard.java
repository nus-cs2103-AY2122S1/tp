package seedu.unify.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.unify.model.task.Task;

/**
 * A UI component that displays information of a {@code Task}.
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     */

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label time;
    @FXML
    private Label date;
    @FXML
    private Label state;
    @FXML
    private Label priority;
    @FXML
    private FlowPane tags;


    /**
     * Creates a {@code TaskCode} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        name.setText(task.getName().taskName);
        time.setText(task.getTime().value);
        date.setText(task.getDate().value);
        task.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagTaskName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagTaskName)));

        String stateStyleClass = "state-" + task.getState().toString();
        state.getStyleClass().add(stateStyleClass);
        state.setText(task.getState().toString());

        String priorityStyleClass = "priority-" + task.getPriority().toString();
        priority.getStyleClass().add(priorityStyleClass);
        priority.setText(task.getPriority().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskCard)) {
            return false;
        }

        // state check
        TaskCard card = (TaskCard) other;
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }
}
