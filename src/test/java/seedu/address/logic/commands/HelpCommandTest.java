package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {

    private final String invalidCommand = "abc";

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_validCommand_success() {
        HelpCommand helpCommand = new HelpCommand(UntagCommand.COMMAND_WORD);
        CommandResult expectedCommandResult = new CommandResult(UntagCommand.MESSAGE_USAGE, false, false);
        assertCommandSuccess(helpCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_noCommand_success() {
        HelpCommand helpCommand = new HelpCommand(HelpCommand.EMPTY);
        CommandResult expectedCommandResult = new CommandResult(HelpCommand.HELP_MESSAGE, false, false);
        assertCommandSuccess(helpCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_moreHelp_success() {
        HelpCommand helpCommand = new HelpCommand(HelpCommand.MORE);
        CommandResult expectedCommandResult = new CommandResult(HelpCommand.SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(helpCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidCommand_failure() {
        HelpCommand helpCommand = new HelpCommand(invalidCommand);
        String message = MESSAGE_UNKNOWN_COMMAND + ": " + invalidCommand + "\n" + HelpCommand.HELP_MESSAGE;
        assertCommandFailure(helpCommand, model, message);
    }

    @Test
    public void execute_invalidCommandExtended_failure() {
        String commandPhrase = invalidCommand + NAME_DESC_AMY;
        HelpCommand helpCommand = new HelpCommand(commandPhrase);
        String message = MESSAGE_UNKNOWN_COMMAND + ": " + commandPhrase + "\n" + HelpCommand.HELP_MESSAGE;
        assertCommandFailure(helpCommand, model, message);
    }
}
