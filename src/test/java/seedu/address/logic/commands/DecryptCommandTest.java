package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class DecryptCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_decrypt_success() {
        CommandResult expectedCommandResult = new CommandResult(DecryptCommand.MESSAGE_SUCCESS, false, false, null);
        assertCommandSuccess(new DecryptCommand(), model, expectedCommandResult, expectedModel);
    }
}
