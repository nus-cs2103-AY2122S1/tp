package seedu.programmer.logic.commands;

import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.programmer.testutil.TypicalStudents.getTypicalProgrammerError;

import org.junit.jupiter.api.Test;

import seedu.programmer.model.Model;
import seedu.programmer.model.ModelManager;
import seedu.programmer.model.UserPrefs;
import seedu.programmer.model.util.SampleDataUtil;

/**
 * Contains integration tests (interaction with the Model) for FillCommand.
 */
public class FillCommandTest {

    @Test
    public void execute_emptyProgrammerError_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.setProgrammerError(SampleDataUtil.getSampleProgrammerError());

        assertCommandSuccess(new FillCommand(), model, FillCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyProgrammerError_success() {
        Model model = new ModelManager(getTypicalProgrammerError(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalProgrammerError(), new UserPrefs());

        assertCommandSuccess(new FillCommand(), model, FillCommand.MESSAGE_FAIL, expectedModel);
    }
}
