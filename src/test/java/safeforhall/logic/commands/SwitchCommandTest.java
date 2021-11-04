package safeforhall.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static safeforhall.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import safeforhall.model.Model;
import safeforhall.model.ModelManager;

public class SwitchCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SwitchCommand.SWITCH_SUCCESS_MESSAGE,
                false, false, true);
        boolean result = expectedCommandResult.isSwitchTab();
        assertEquals(result, true);
        assertCommandSuccess(new SwitchCommand(), model, expectedCommandResult, expectedModel);
    }
}
