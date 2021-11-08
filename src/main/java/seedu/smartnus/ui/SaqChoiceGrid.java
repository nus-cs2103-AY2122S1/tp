package seedu.smartnus.ui;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.ShortAnswerQuestion;


/**
 * A UI component that displays information of a {@code Question}.
 */
public class SaqChoiceGrid extends ChoiceGrid {

    private static final String FXML = "SaqChoiceGrid.fxml";

    @FXML
    private GridPane saqGrid;
    @FXML
    private Button answerInstructions;

    /**
     * A UI component that displays the choices of a {@code MultipleChoiceQuestion}
     */
    public SaqChoiceGrid(ShortAnswerQuestion question, Choice selectedChoice) {
        super(FXML);
        boolean isQuestionAnswered = selectedChoice != null;
        answerInstructions.setWrapText(true);
        if (isQuestionAnswered) {
            showAnswers(question, selectedChoice);
        } else {
            showSaqInstructions();
        }

        disableButtons();
    }

    protected void showAnswers(ShortAnswerQuestion question, Choice selectedChoice) {
        StringBuilder builder = new StringBuilder();
        if (question.isCorrectAnswer(selectedChoice)) {
            answerInstructions.getStyleClass().add("quiz-choice-correct");
            builder.append("Correct! ");
        } else {
            answerInstructions.getStyleClass().add("quiz-choice-incorrect");
            builder.append("Incorrect! ");
        }
        builder.append("Your answer: ").append(selectedChoice.getTitle()).append("\n");
        builder.append("Answer: ").append(question.getCorrectChoiceTitle());
        builder.append(" (Keywords that must be present: ").append(question.getKeywordsFormattedString()).append(")");
        answerInstructions.setText(builder.toString());
    }

    protected void showSaqInstructions() {
        String saqMessage = "This is a short-answer question without options to select."
                + "Please input your answer starting with ans/ "
                + "(e.g. ans/This is my answer).";
        answerInstructions.setText(saqMessage);
    }

    // not used as SAQ do not have any options to display
    @Override
    protected void showCorrectOption(ArrayList<Choice> choices, Choice correctChoice) {
    }

    // not used as SAQ do not have any options to display
    @Override
    protected void showIncorrectOption(ArrayList<Choice> choices, Choice selectedChoice) {
    }

    @Override
    protected void disableButtons() {
        answerInstructions.setDisable(true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SaqChoiceGrid)) {
            return false;
        }

        // state check
        SaqChoiceGrid card = (SaqChoiceGrid) other;
        return answerInstructions.getText().equals(card.answerInstructions.getText());
    }
}
