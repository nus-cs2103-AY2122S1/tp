package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.address.model.task.Task;

/**
 * A UI component that displays information of a {@code Task}.
 */
public class TaskCard extends Card {

    private static final String FXML = "TaskCard.fxml";

    private static final String IS_DONE_INDICATOR_DONE_TEXT = "Done";
    private static final String IS_DONE_INDICATOR_NOT_DONE_TEXT = "Not done";
    private static final String IS_DONE_INDICATOR_DONE_BACKGROUND_STYLE = "-fx-background-color: #33691e;";
    private static final String IS_DONE_INDICATOR_NOT_DONE_BACKGROUND_STYLE = "-fx-background-color: #b00020;";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label description;
    @FXML
    private Label isDoneIndicator;

    /**
     * Creates a {@code TaskCard} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        description.setText(task.getDescription().toString());
        if (task.getDoneTask()) {
            isDoneIndicator.setText(IS_DONE_INDICATOR_DONE_TEXT);
            isDoneIndicator.setStyle(getIsDoneIndicatorDoneStyle());
        } else {
            isDoneIndicator.setText(IS_DONE_INDICATOR_NOT_DONE_TEXT);
            isDoneIndicator.setStyle(getIsDoneIndicatorNotDoneStyle());
        }
    }

    private String getIsDoneIndicatorDoneStyle() {
        return isDoneIndicator.getStyle() + IS_DONE_INDICATOR_DONE_BACKGROUND_STYLE;
    }

    private String getIsDoneIndicatorNotDoneStyle() {
        return isDoneIndicator.getStyle() + IS_DONE_INDICATOR_NOT_DONE_BACKGROUND_STYLE;
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
