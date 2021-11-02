package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {
    private static final String DUMMY_MESSAGE = "Dummy help message";

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(DUMMY_MESSAGE, false);
        assertCommandSuccess(new HelpCommand(DUMMY_MESSAGE), model, expectedCommandResult, expectedModel);
    }
}
