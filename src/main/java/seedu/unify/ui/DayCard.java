package seedu.unify.ui;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.unify.model.task.Task;


/**
 * An UI component that displays information of a {@code Task} in the specified day.
 */
public class DayCard extends UiPart<Region> {

    private static final String FXML = "DayCard.fxml";

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
    private Label name;
    @FXML
    private Label date;
    @FXML
    private Label id;

    /**
     * Creates a {@code TaskCode} with the given {@code Task} and index to display.
     */
    public DayCard(Task task, int size) {
        super(FXML);
        this.task = task;
        name.setText(task.getName().taskName);
        date.setText(task.getTime().toString());
        if (task.getDate().localDate.equals(LocalDate.now())) {
            if (size < 3) {
                cardPane.getStyleClass().add("dayCardLight");
            } else if (size < 5) {
                cardPane.getStyleClass().add("dayCardMed");
            } else {
                cardPane.getStyleClass().add("dayCardHeavy");
            }
        } else {
            cardPane.getStyleClass().add("dayCard");
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DayCard)) {
            return false;
        }

        // state check
        DayCard card = (DayCard) other;
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }
}
