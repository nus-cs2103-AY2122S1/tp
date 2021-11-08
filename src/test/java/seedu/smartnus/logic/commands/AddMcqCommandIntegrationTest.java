package seedu.smartnus.logic.commands;

import static seedu.smartnus.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.smartnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartnus.testutil.TypicalSmartNus.getTypicalSmartNus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.commands.questions.AddMcqCommand;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.ModelManager;
import seedu.smartnus.model.UserPrefs;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.testutil.QuestionBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddMcqCommand}.
 */
public class AddMcqCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSmartNus(), new UserPrefs());
    }

    @Test
    public void execute_newQuestion_success() {
        Question validQuestion = new QuestionBuilder().build();

        Model expectedModel = new ModelManager(model.getSmartNus(), new UserPrefs());
        expectedModel.addQuestion(validQuestion);

        assertCommandSuccess(new AddMcqCommand(validQuestion), model,
                String.format(AddMcqCommand.MESSAGE_SUCCESS, validQuestion), expectedModel);
    }

    @Test
    public void execute_duplicateQuestion_throwsCommandException() {
        Question questionInList = model.getSmartNus().getQuestionList().get(0);
        assertCommandFailure(new AddMcqCommand(questionInList), model, AddMcqCommand.MESSAGE_DUPLICATE_QUESTION);
    }

}
