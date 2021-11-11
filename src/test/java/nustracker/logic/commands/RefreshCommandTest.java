package nustracker.logic.commands;

import org.junit.jupiter.api.Test;

import nustracker.model.Model;
import nustracker.model.ModelManager;


public class RefreshCommandTest {

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_refresh_success() {
        CommandResult expectedCommandResult = new CommandResult(RefreshCommand.SHOWING_REFRESH_MESSAGE,
                false, false, false, false, true, false, false);
        CommandTestUtil.assertCommandSuccess(new RefreshCommand(), model, expectedCommandResult, expectedModel);
    }
}
