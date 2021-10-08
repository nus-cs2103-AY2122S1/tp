package seedu.siasa.logic.commands;

import static seedu.siasa.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.siasa.testutil.TypicalSiasa.getTypicalSiasa;

import org.junit.jupiter.api.Test;

import seedu.siasa.model.Model;
import seedu.siasa.model.ModelManager;
import seedu.siasa.model.Siasa;
import seedu.siasa.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalSiasa(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalSiasa(), new UserPrefs());
        expectedModel.setSiasa(new Siasa());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
