package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandSummaryCommand.SHOWING_COMMAND_SUMMARY_MESSAGE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class CommandSummaryTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_COMMAND_SUMMARY_MESSAGE, false, true, false);
        assertCommandSuccess(new CommandSummaryCommand(), model, expectedCommandResult, expectedModel);
    }
}
