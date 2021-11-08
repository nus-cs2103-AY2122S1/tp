package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A UI for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    /**
     * Uses FXML to identify ResultDisplay.
     */
    private static final String FXML = "ResultDisplay.fxml";

    /**
     * Stands for components to be used in FXML.
     */
    @FXML
    private TextArea resultDisplay;

    /**
     * Constructs a {@code ResultDisplay}.
     */
    public ResultDisplay() {
        super(FXML);
    }

    /**
     * Sets feedback to User with given message to display {@code feedbackToUser}.
     *
     * @param feedbackToUser message to display
     */
    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        resultDisplay.setText(feedbackToUser);
    }

}
