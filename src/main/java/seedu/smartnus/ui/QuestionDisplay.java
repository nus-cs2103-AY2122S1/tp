package seedu.smartnus.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.smartnus.model.question.Question;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class QuestionDisplay extends UiPart<Region> {

    private static final String FXML = "QuestionDisplay.fxml";

    @FXML
    private TextArea questionDisplay;

    public QuestionDisplay() {
        super(FXML);
    }

    public void displayQuestion(Question question) {
        questionDisplay.setText(question.getName().fullName);
    }

}
