package seedu.smartnus.logic.commands;

import static seedu.smartnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartnus.logic.commands.CommandTestUtil.showQuestionAtIndex;
import static seedu.smartnus.testutil.TypicalIndexes.INDEX_FIRST_QUESTION;
import static seedu.smartnus.testutil.TypicalQuestions.getTypicalSmartNus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartnus.model.Model;
import seedu.smartnus.model.ModelManager;
import seedu.smartnus.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSmartNus(), new UserPrefs());
        //        expectedModel = new ModelManager(model.getSmartNus(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalSmartNus(), new UserPrefs());
    }


    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand("qn"), model, ListCommand.MESSAGE_SUCCESS_QUESTIONS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showQuestionAtIndex(model, INDEX_FIRST_QUESTION);
        assertCommandSuccess(new ListCommand("qn"), model, ListCommand.MESSAGE_SUCCESS_QUESTIONS, expectedModel);
    }
}
