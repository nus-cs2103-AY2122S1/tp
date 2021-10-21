package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.task.EventTask;
import seedu.address.model.task.Task;

public class EventTaskCard extends UiPart<Region> {

    private static final String FXML = "EventTaskListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Task task;

    @javafx.fxml.FXML
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
     * Creates a {@code TaskCode} with the given {@code Task} and index to display.
     */
    public EventTaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". [E]");
        name.setText(task.getName().toString());
        EventTask deadlineTask = (EventTask) task;
        status.setText(task.getStatusString());
        description.setText(task.getDescription());

        taskDate.setText(deadlineTask.getTaskDate().toString());

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
        EventTaskCard card = (EventTaskCard) other;
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }
}
