package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandResult.DisplayType.WEEK;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.WeekCommand.SHOWING_WEEK_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class WeekCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_week_success() {
        CommandResult expectedCommandResult =
                new CommandResult(SHOWING_WEEK_MESSAGE, WEEK);
        assertCommandSuccess(new WeekCommand(), model, expectedCommandResult, expectedModel);
    }
}
