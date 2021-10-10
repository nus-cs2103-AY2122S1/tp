package seedu.programmer.logic.commands;

import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.programmer.testutil.TypicalPersons.getTypicalProgrammerError;

import org.junit.jupiter.api.Test;

import seedu.programmer.model.Model;
import seedu.programmer.model.ModelManager;
import seedu.programmer.model.UserPrefs;
import seedu.programmer.model.util.SampleDataUtil;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class FillCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.setProgrammerError(SampleDataUtil.fillSampleProgrammerError());

        assertCommandSuccess(new FillCommand(), model, FillCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalProgrammerError(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalProgrammerError(), new UserPrefs());

        assertCommandSuccess(new FillCommand(), model, FillCommand.MESSAGE_FAIL, expectedModel);
    }
}
