package seedu.plannermd.logic.commands;

import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.plannermd.logic.commands.ToggleCommand.MESSAGE_TOGGLE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.plannermd.model.Model;
import seedu.plannermd.model.ModelManager;

class ToggleCommandTest {

    private static final String MESSAGE_TOGGLE_SUCCESS_PATIENTS = String.format(MESSAGE_TOGGLE_SUCCESS, "patients");
    private static final String MESSAGE_TOGGLE_SUCCESS_DOCTOR = String.format(MESSAGE_TOGGLE_SUCCESS, "doctors");

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_toggleTwice_success() {
        CommandResult expectedCommandResult =
                new CommandResult(MESSAGE_TOGGLE_SUCCESS_DOCTOR, false, true, false);
        expectedModel.toggleState();
        assertCommandSuccess(new ToggleCommand(), model, expectedCommandResult, expectedModel);

        expectedCommandResult =
                new CommandResult(MESSAGE_TOGGLE_SUCCESS_PATIENTS, false, true, false);
        expectedModel.toggleState();
        assertCommandSuccess(new ToggleCommand(), model, expectedCommandResult, expectedModel);
    }
}
