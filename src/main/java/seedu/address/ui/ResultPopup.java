package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

public class ResultPopup extends UiPart<Region> {

    private static final String FXML = "ResultPopup.fxml";

    private static final int POPUP_DISPLAY_DURATION_SECONDS = 6;

    private Stage primaryStage;

    @FXML
    private Popup popup;

    @FXML
    private Label result;

    /**
     * Creates a result popup.
     * @param primaryStage stage to show popup on
     */
    public ResultPopup(Stage primaryStage) {
        super(FXML);
        this.primaryStage = primaryStage;
        this.popup = new Popup();
        popup.getContent().add(result);
    }

    /**
     * Displays popup on primary stage for POPUP_DISPLAY_DURATION_SECONDS.
     */
    public void show() {
        Window window = primaryStage.getScene().getWindow();

        result.setPrefSize(window.getWidth() * 0.75, window.getHeight() * 0.25);

        double x = window.getX() + window.getWidth() * 0.125;
        double y = window.getY() + window.getHeight() * 0.25;
        popup.show(primaryStage, x, y);

        PauseTransition delay = new PauseTransition(Duration.seconds(POPUP_DISPLAY_DURATION_SECONDS));
        delay.setOnFinished(event -> popup.hide());
        delay.play();
    }

    public void setFeedbackToUser(String feedbackToUser, boolean isError) {
        requireNonNull(feedbackToUser);
        ObservableList<String> styleClass = result.getStyleClass();
        if (isError && !styleClass.contains("command-error")) {
            styleClass.add("command-error");
        }
        if (!isError) {
            styleClass.remove("command-error");
        }
        result.setText(feedbackToUser);
        show();
    }


}
