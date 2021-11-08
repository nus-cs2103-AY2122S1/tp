package seedu.programmer.logic.commands;

import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.programmer.logic.commands.DashboardCommand.SHOWING_DASHBOARD_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.programmer.model.Model;
import seedu.programmer.model.ModelManager;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code DashboardCommand}.
 */
public class DashboardCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_dashboard_success() {
        DashboardCommandResult expectedCommandResult = new DashboardCommandResult(SHOWING_DASHBOARD_MESSAGE);
        assertCommandSuccess(new DashboardCommand(), model, expectedCommandResult, expectedModel);
    }
}
