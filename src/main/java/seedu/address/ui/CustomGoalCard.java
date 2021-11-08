package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.customGoal.CustomGoal;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class CustomGoalCard extends UiPart<Region> {

    private static final String FXML = "CustomGoalCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final CustomGoal customGoal;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label description;
    @FXML
    private Label progress;
    @FXML
    private Label goal;
    @FXML
    private Label dateEnd;
    @FXML
    private Label timeEnd;
    @FXML
    private ProgressBar progressBar;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public CustomGoalCard(CustomGoal customGoal, int displayedIndex) {
        super(FXML);
        this.customGoal = customGoal;
        id.setText(displayedIndex + ". ");
        description.setText(customGoal.getDescription());
        progress.setText(customGoal.getProgressValue());
        if (customGoal.isComplete()) {
            progress.setStyle("-fx-text-fill: CHARTREUSE");
        }
        if (customGoal.isOverdue()) {
            dateEnd.setStyle("-fx-text-fill: RED");
            timeEnd.setStyle("-fx-text-fill: RED");
        }
        goal.setText(customGoal.getGoalValue());
        dateEnd.setText(customGoal.getEndDateValue());
        timeEnd.setText(customGoal.getEndTimeValue());
        progressBar.setProgress(((float) customGoal.getProgress() / (float) customGoal.getGoal()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CustomGoalCard)) {
            return false;
        }

        // state check
        CustomGoalCard card = (CustomGoalCard) other;
        return id.getText().equals(card.id.getText())
                && customGoal.equals(card.customGoal);
    }
}
