package seedu.address.ui;

import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import static java.util.Objects.requireNonNull;

public class ResultPopup extends UiPart<Region> {

    private static final String FXML = "ResultPopup.fxml";

    private Stage primaryStage;

    @FXML
    private Popup popup;

    @FXML
    private Label result;

    public ResultPopup(Stage primaryStage) {
        super(FXML);
        this.primaryStage = primaryStage;
        this.popup = new Popup();
        popup.getContent().add(result);
    }

    public void show() {
        popup.show(primaryStage);
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> popup.hide());
        delay.play();
    }

    public void setFeedbackToUser(String feedbackToUser, boolean isError) {
        requireNonNull(feedbackToUser);
        ObservableList<String> styleClass = result.getStyleClass();
        if (isError && !styleClass.contains("command-error")) {
            styleClass.add("command-error");
        }
        if(!isError){
            styleClass.remove("command-error");
        }
        result.setText(feedbackToUser);
        show();
    }


}
