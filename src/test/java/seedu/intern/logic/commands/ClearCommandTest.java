package seedu.intern.logic.commands;

import static seedu.intern.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intern.testutil.TypicalApplicants.getTypicalInternWatcher;

import org.junit.jupiter.api.Test;

import seedu.intern.model.InternWatcher;
import seedu.intern.model.Model;
import seedu.intern.model.ModelManager;
import seedu.intern.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyInternWatcher_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyInternWatcher_success() {
        Model model = new ModelManager(getTypicalInternWatcher(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalInternWatcher(), new UserPrefs());
        expectedModel.setInternWatcher(new InternWatcher());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
