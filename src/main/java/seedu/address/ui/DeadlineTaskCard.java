package seedu.address.ui;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.task.DeadlineTask;
import seedu.address.model.task.Task;

public class DeadlineTaskCard extends UiPart<Region> {

    private static final String FXML = "DeadlineTaskListCard.fxml";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private static final String DEADLINE_LABEL_STYLE = "taskType-deadline";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label taskDate;
    @FXML
    private Label status;
    @FXML
    private FlowPane tags;
    @FXML
    private Label description;

    /**
     * Creates a {@code TaskCode} with the given {@code Student} and index to display.
     */
    public DeadlineTaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        name.setText(task.getName().toString());
        DeadlineTask deadlineTask = (DeadlineTask) task;
        status.setText("Status: " + task.getStatusString());
        description.setText(task.getDescription());

        Label priorityLabel = new Label("Priority: " + task.getPriorityAsString());
        Label taskType = new Label("Deadline");
        tags.getChildren().addAll(taskType, priorityLabel);

        String priority = task.getPriorityAsString();
        if (priority.contains("HIGH")) {
            priorityLabel.getStyleClass().add("priorityLabel-high");
        } else if (priority.contains("MED")) {
            priorityLabel.getStyleClass().add("priorityLabel-med");
        } else {
            priorityLabel.getStyleClass().add("priorityLabel-low");
        }
        taskType.getStyleClass().add(DEADLINE_LABEL_STYLE);

        taskDate.setText("Date: " + deadlineTask.getDeadline().getDeadline().format(formatter));

        task.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeadlineTaskCard)) {
            return false;
        }

        // state check
        DeadlineTaskCard card = (DeadlineTaskCard) other;
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }
}
