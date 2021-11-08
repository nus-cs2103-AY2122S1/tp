package seedu.address.logic.candidate;

import static seedu.address.logic.candidate.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.general.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.CommandResult;
import seedu.address.logic.general.ExitCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT,
                CommandResult.CommandType.EXIT);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
