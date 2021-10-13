package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.util.Duration;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";

    @FXML
    private TextArea resultDisplay;

    public ResultDisplay() {
        super(FXML);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);

        Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(500));
            }

            @Override
            protected void interpolate(double frac) {
                // cast to float so Math round to int
                int n = Math.round(feedbackToUser.length() * (float) frac);
                resultDisplay.setText(feedbackToUser.substring(0, n));
            }
        };

        animation.play();
    }

}
