package seedu.programmer.logic.commands;

import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.programmer.testutil.TypicalStudents.getTypicalProgrammerError;

import org.junit.jupiter.api.Test;

import seedu.programmer.model.Model;
import seedu.programmer.model.ModelManager;
import seedu.programmer.model.ProgrammerError;
import seedu.programmer.model.UserPrefs;

public class PurgeCommandTest {

    @Test
    public void execute_emptyProgrammerError_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new PurgeCommand(), model, PurgeCommand.MESSAGE_FAIL, expectedModel);
    }


    @Test
    public void execute_nonEmptyProgrammerError_success() {
        Model model = new ModelManager(getTypicalProgrammerError(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalProgrammerError(), new UserPrefs());
        expectedModel.setProgrammerError(new ProgrammerError());

        assertCommandSuccess(new PurgeCommand(), model, PurgeCommand.MESSAGE_SUCCESS, expectedModel);
    }


}
