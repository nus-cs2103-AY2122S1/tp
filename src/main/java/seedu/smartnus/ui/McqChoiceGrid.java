package seedu.smartnus.ui;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Question;


/**
 * An UI component that displays information of a {@code Question}.
 */
public class McqChoiceGrid extends ChoiceGrid {

    private static final String FXML = "McqChoiceGrid.fxml";

    @FXML
    private GridPane mcqGrid;
    @FXML
    private Button optionA;
    @FXML
    private Button optionB;
    @FXML
    private Button optionC;
    @FXML
    private Button optionD;

    /**
     * A UI component that displays the choices of a {@code MultipleChoiceQuestion}
     */
    public McqChoiceGrid(Question question, Choice selectedChoice) {
        super(FXML);
        ArrayList<Choice> choices = question.getOrderedChoices();
        optionA.setText("A. " + choices.get(0).getTitle());
        optionB.setText("B. " + choices.get(1).getTitle());
        optionC.setText("C. " + choices.get(2).getTitle());
        optionD.setText("D. " + choices.get(3).getTitle());

        boolean isQuestionAnswered = selectedChoice != null;

        if (isQuestionAnswered) {
            showCorrectOption(choices, question.getCorrectChoice());
            if (!selectedChoice.equals(question.getCorrectChoice())) {
                showIncorrectOption(choices, selectedChoice);
            }
        }

        // TODO: Remove function below if we're enabling click to select choice.
        disableButtons();
    }

    @Override
    protected void showCorrectOption(ArrayList<Choice> choices, Choice correctChoice) {
        int selectedIndex = choices.indexOf(correctChoice);

        switch (selectedIndex) {
        case 0:
            optionA.getStyleClass().add("quiz-choice-correct");
            break;
        case 1:
            optionB.getStyleClass().add("quiz-choice-correct");
            break;
        case 2:
            optionC.getStyleClass().add("quiz-choice-correct");
            break;
        case 3:
            optionD.getStyleClass().add("quiz-choice-correct");
            break;
        default:
            break;
        }
    }

    @Override
    protected void showIncorrectOption(ArrayList<Choice> choices, Choice selectedChoice) {
        int selectedIndex = choices.indexOf(selectedChoice);

        switch (selectedIndex) {
        case 0:
            optionA.getStyleClass().add("quiz-choice-incorrect");
            break;
        case 1:
            optionB.getStyleClass().add("quiz-choice-incorrect");
            break;
        case 2:
            optionC.getStyleClass().add("quiz-choice-incorrect");
            break;
        case 3:
            optionD.getStyleClass().add("quiz-choice-incorrect");
            break;
        default:
            break;
        }
    }

    @Override
    protected void disableButtons() {
        optionA.setDisable(true);
        optionB.setDisable(true);
        optionC.setDisable(true);
        optionD.setDisable(true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof McqChoiceGrid)) {
            return false;
        }

        // state check
        McqChoiceGrid card = (McqChoiceGrid) other;
        return optionA.getText().equals(card.optionA.getText())
                && optionB.getText().equals(card.optionB.getText())
                && optionC.getText().equals(card.optionC.getText())
                && optionD.getText().equals(card.optionD.getText());
    }
}
