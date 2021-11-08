package seedu.smartnus.ui;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.TrueFalseQuestion;


/**
 * An UI component that displays information of a {@code Question}.
 */
public class TfqChoiceGrid extends ChoiceGrid {

    private static final String FXML = "TfqChoiceGrid.fxml";

    @FXML
    private GridPane tfqGrid;
    @FXML
    private Button optionTrue;
    @FXML
    private Button optionFalse;

    /**
     * A UI component that displays the choices of a {@code TrueFalseQuestion}
     */
    public TfqChoiceGrid(Question question, Choice selectedChoice) {
        super(FXML);

        assert question instanceof TrueFalseQuestion;

        ArrayList<Choice> choices = question.getOrderedChoices();
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

    protected void showCorrectOption(ArrayList<Choice> choices, Choice correctChoice) {
        int selectedIndex = choices.indexOf(correctChoice);

        switch (selectedIndex) {
        case 0:
            optionTrue.getStyleClass().add("quiz-choice-correct");
            break;
        case 1:
            optionFalse.getStyleClass().add("quiz-choice-correct");
            break;
        default:
            break;
        }
    }

    protected void showIncorrectOption(ArrayList<Choice> choices, Choice selectedChoice) {
        int selectedIndex = choices.indexOf(selectedChoice);

        switch (selectedIndex) {
        case 0:
            optionTrue.getStyleClass().add("quiz-choice-incorrect");
            break;
        case 1:
            optionFalse.getStyleClass().add("quiz-choice-incorrect");
            break;
        default:
            break;
        }
    }

    protected void disableButtons() {
        optionTrue.setDisable(true);
        optionFalse.setDisable(true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TfqChoiceGrid)) {
            return false;
        }

        // state check
        TfqChoiceGrid card = (TfqChoiceGrid) other;
        return card == other;
    }
}
