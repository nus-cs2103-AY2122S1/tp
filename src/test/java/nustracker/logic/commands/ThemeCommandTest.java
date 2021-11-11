package nustracker.logic.commands;

import org.junit.jupiter.api.Test;

import nustracker.model.Model;
import nustracker.model.ModelManager;

class ThemeCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    void execute_theme_success() {
        CommandResult expectedCommandResult = new CommandResult(ThemeCommand.SHOWING_THEME_MESSAGE,
                false, false, false, true, false, false, false);
        CommandTestUtil.assertCommandSuccess(new ThemeCommand(), model, expectedCommandResult, expectedModel);
    }

}
