package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SwitchTabCommand.SWITCHED_TAB_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class SwitchTabCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_switchTab_success() {
        CommandResult expectedCommandResult = new CommandResult(SWITCHED_TAB_MESSAGE, false, false, true);
        assertCommandSuccess(new SwitchTabCommand(), model, expectedCommandResult, expectedModel);
    }
}
