package socialite.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static socialite.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import socialite.model.Model;
import socialite.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult =
                new CommandResult(HelpCommand.SHOWING_HELP_MESSAGE, true, false, false);
        assertCommandSuccess(new HelpCommand(HelpCommand.GENERIC_RESPONSE), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_help_add() {
        CommandResult expectedCommandResult =
                new CommandResult(AddCommand.MESSAGE_USAGE, false, false, false);
        assertCommandSuccess(new HelpCommand(AddCommand.MESSAGE_USAGE), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_help_delete() {
        CommandResult expectedCommandResult =
                new CommandResult(DeleteCommand.MESSAGE_USAGE, false, false, false);
        assertCommandSuccess(new HelpCommand(DeleteCommand.MESSAGE_USAGE), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_help_edit() {
        CommandResult expectedCommandResult =
                new CommandResult(EditCommand.MESSAGE_USAGE, false, false, false);
        assertCommandSuccess(new HelpCommand(EditCommand.MESSAGE_USAGE), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_help_find() {
        CommandResult expectedCommandResult =
                new CommandResult(FindCommand.MESSAGE_USAGE, false, false, false);
        assertCommandSuccess(new HelpCommand(FindCommand.MESSAGE_USAGE), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_help_remark() {
        CommandResult expectedCommandResult =
                new CommandResult(RemarkCommand.MESSAGE_USAGE, false, false, false);
        assertCommandSuccess(new HelpCommand(RemarkCommand.MESSAGE_USAGE), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        HelpCommand helpFirstCommand = new HelpCommand(AddCommand.MESSAGE_USAGE);
        HelpCommand helpSecondCommand = new HelpCommand(HelpCommand.GENERIC_RESPONSE);

        // same object -> returns true
        assertTrue(helpFirstCommand.equals(helpFirstCommand));

        // same values -> returns true
        HelpCommand helpFirstCommandCopy = new HelpCommand(AddCommand.MESSAGE_USAGE);
        assertTrue(helpFirstCommand.equals(helpFirstCommandCopy));

        // different Help Commands -> returns false
        assertFalse(helpFirstCommand.equals(helpSecondCommand));
    }

}
