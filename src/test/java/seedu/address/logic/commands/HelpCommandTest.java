package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertExecuteSuccess;
import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {
    private final Model model = new ModelManager();
    private final Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false, null, false);
        assertExecuteSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
