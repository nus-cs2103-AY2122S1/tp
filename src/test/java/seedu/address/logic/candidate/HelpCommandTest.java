package seedu.address.logic.candidate;

import static seedu.address.logic.candidate.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.general.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.CommandResult;
import seedu.address.logic.general.HelpCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(
                SHOWING_HELP_MESSAGE, CommandResult.CommandType.HELP);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
