package seedu.address.logic.commands;

import static seedu.address.logic.commands.CmdCommand.SHOWING_CMD_MESSAGE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class CmdCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_CMD_MESSAGE, true,
                false, false);
        assertCommandSuccess(new CmdCommand(), model, expectedCommandResult, expectedModel);
    }
}
