package seedu.unify.logic.commands;

import static seedu.unify.logic.commands.CommandTestUtil.VALID_DATE_ASSIGNMENT;
import static seedu.unify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.unify.testutil.TypicalTasks.getTypicalUniFy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.unify.model.Model;
import seedu.unify.model.ModelManager;
import seedu.unify.model.UserPrefs;
import seedu.unify.model.task.Date;

public class ShowCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalUniFy(), new UserPrefs());
        expectedModel = new ModelManager(model.getUniFy(), new UserPrefs());
    }

    @Test
    public void execute_show_success() {
        Date date = new Date(VALID_DATE_ASSIGNMENT);
        ShowCommand showCommand = new ShowCommand(date, 1);

        String expectedMessage = String.format(ShowCommand.MESSAGE_SUCCESS_1, date.getWeek());

        assertCommandSuccess(showCommand, model, expectedMessage, expectedModel);
    }


}
