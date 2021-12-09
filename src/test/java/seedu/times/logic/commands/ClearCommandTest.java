package seedu.times.logic.commands;

import static seedu.times.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.times.testutil.TypicalTimestable.getTypicalTimesTable;

import org.junit.jupiter.api.Test;

import seedu.times.model.Model;
import seedu.times.model.ModelManager;
import seedu.times.model.TimesTable;
import seedu.times.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyTimesTable_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyTimesTable_success() {
        Model model = new ModelManager(getTypicalTimesTable(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTimesTable(), new UserPrefs());
        expectedModel.setTimesTable(new TimesTable());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
