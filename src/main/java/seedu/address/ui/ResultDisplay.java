package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";

    private static final String WARNING_STYLE_CLASS = "warning";
    private static final String ERROR_STYLE_CLASS = "error";

    @FXML
    private TextArea resultDisplay;

    public ResultDisplay() {
        super(FXML);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        resultDisplay.setText(feedbackToUser);
    }

    public void setColorBasedOnResultType(boolean isWarning, boolean isError) {
        ObservableList<String> styles = resultDisplay.getStyleClass();

        if (!isWarning) {
            if (styles.contains(WARNING_STYLE_CLASS)) {
                styles.remove(WARNING_STYLE_CLASS);
            }
        } else {
            if (!styles.contains(WARNING_STYLE_CLASS)) {
                styles.add(WARNING_STYLE_CLASS);
            }
        }

        if (!isError) {
            if (styles.contains(ERROR_STYLE_CLASS)) {
                styles.remove(ERROR_STYLE_CLASS);
            }
        } else {
            if (!styles.contains(ERROR_STYLE_CLASS)) {
                styles.add(ERROR_STYLE_CLASS);
            }
        }
    }

}
