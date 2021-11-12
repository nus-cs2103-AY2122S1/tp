package seedu.teachbook.logic.commands;

import static seedu.teachbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.teachbook.testutil.TypicalStudents.getTypicalTeachBook;

import org.junit.jupiter.api.Test;

import seedu.teachbook.model.Model;
import seedu.teachbook.model.ModelManager;
import seedu.teachbook.model.TeachBook;
import seedu.teachbook.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyTeachBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        // clear empty teachbook will not make a new commit
        CommandResult expectedCommandResult = new CommandResult(ClearCommand.MESSAGE_SUCCESS, false, false, true, true);

        assertCommandSuccess(new ClearCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_nonEmptyTeachBook_success() {
        Model model = new ModelManager(getTypicalTeachBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTeachBook(), new UserPrefs());
        expectedModel.setTeachBook(new TeachBook());
        expectedModel.commitTeachBook();
        CommandResult expectedCommandResult = new CommandResult(ClearCommand.MESSAGE_SUCCESS, false, false, true, true);

        assertCommandSuccess(new ClearCommand(), model, expectedCommandResult, expectedModel);
    }

}
