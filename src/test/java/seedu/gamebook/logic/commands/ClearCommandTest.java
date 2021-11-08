package seedu.gamebook.logic.commands;

import static seedu.gamebook.logic.commands.ClearCommand.COMMAND_CONFIRMATION;
import static seedu.gamebook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.gamebook.testutil.TypicalGameEntries.getTypicalGameBook;

import org.junit.jupiter.api.Test;

import seedu.gamebook.model.GameBook;
import seedu.gamebook.model.Model;
import seedu.gamebook.model.ModelManager;
import seedu.gamebook.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyGameBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(COMMAND_CONFIRMATION), model,
            ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyGameBook_success() {
        Model model = new ModelManager(getTypicalGameBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalGameBook(), new UserPrefs());
        expectedModel.setGameBook(new GameBook());

        assertCommandSuccess(new ClearCommand(COMMAND_CONFIRMATION),
            model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
