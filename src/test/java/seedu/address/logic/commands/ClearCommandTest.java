package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalApplications.getTypicalInternship;

import org.junit.jupiter.api.Test;

import seedu.address.model.Internship;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ClearCommand.
 */
public class ClearCommandTest {

    @Test
    public void execute_emptyInternship_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyInternship_success() {
        Model model = new ModelManager(getTypicalInternship(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalInternship(), new UserPrefs());
        expectedModel.setInternship(new Internship());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
