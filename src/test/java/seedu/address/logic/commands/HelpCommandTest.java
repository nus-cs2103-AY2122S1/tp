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
    public void execute_noCommand_success() {
        HelpCommand helpCommand = new HelpCommand(HelpCommand.EMPTY);
        CommandResult expectedCommandResult = new CommandResult(HelpCommand.HELP_MESSAGE, false, false);
        assertCommandSuccess(helpCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_moreHelp_success() {
        HelpCommand helpCommand = new HelpCommand(HelpCommand.MORE);
        CommandResult expectedCommandResult = new CommandResult(HelpCommand.SHOWING_HELP_MESSAGE,
                true, false);
        assertCommandSuccess(helpCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_addCommand_success() {
        HelpCommand helpCommand = new HelpCommand(AddCommand.COMMAND_WORD);
        CommandResult expectedCommandResult = new CommandResult(AddCommand.MESSAGE_USAGE, false, false);
        assertCommandSuccess(helpCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_editCommand_success() {
        HelpCommand helpCommand = new HelpCommand(EditCommand.COMMAND_WORD);
        CommandResult expectedCommandResult = new CommandResult(EditCommand.MESSAGE_USAGE, false, false);
        assertCommandSuccess(helpCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_untagCommand_success() {
        HelpCommand helpCommand = new HelpCommand(UntagCommand.COMMAND_WORD);
        CommandResult expectedCommandResult = new CommandResult(UntagCommand.MESSAGE_USAGE, false, false);
        assertCommandSuccess(helpCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_tagCommand_success() {
        HelpCommand helpCommand = new HelpCommand(TagCommand.COMMAND_WORD);
        CommandResult expectedCommandResult = new CommandResult(TagCommand.MESSAGE_USAGE, false, false);
        assertCommandSuccess(helpCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_deleteCommand_success() {
        HelpCommand helpCommand = new HelpCommand(DeleteCommand.COMMAND_WORD);
        CommandResult expectedCommandResult = new CommandResult(DeleteCommand.MESSAGE_USAGE,
                false, false);
        assertCommandSuccess(helpCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_deletemCommand_success() {
        HelpCommand helpCommand = new HelpCommand(DeleteMultipleCommand.COMMAND_WORD);
        CommandResult expectedCommandResult = new CommandResult(DeleteMultipleCommand.MESSAGE_USAGE,
                false, false);
        assertCommandSuccess(helpCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_clearCommand_success() {
        HelpCommand helpCommand = new HelpCommand(ClearCommand.COMMAND_WORD);
        CommandResult expectedCommandResult = new CommandResult(ClearCommand.MESSAGE_USAGE, false, false);
        assertCommandSuccess(helpCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_findCommand_success() {
        HelpCommand helpCommand = new HelpCommand(FindCommand.COMMAND_WORD);
        CommandResult expectedCommandResult = new CommandResult(FindCommand.MESSAGE_USAGE, false, false);
        assertCommandSuccess(helpCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_findAnyCommand_success() {
        HelpCommand helpCommand = new HelpCommand(FindAnyCommand.COMMAND_WORD);
        CommandResult expectedCommandResult = new CommandResult(FindAnyCommand.MESSAGE_USAGE, false, false);
        assertCommandSuccess(helpCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listCommand_success() {
        HelpCommand helpCommand = new HelpCommand(ListCommand.COMMAND_WORD);
        CommandResult expectedCommandResult = new CommandResult(ListCommand.MESSAGE_USAGE, false, false);
        assertCommandSuccess(helpCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_exitCommand_success() {
        HelpCommand helpCommand = new HelpCommand(ExitCommand.COMMAND_WORD);
        CommandResult expectedCommandResult = new CommandResult(ExitCommand.MESSAGE_USAGE, false, false);
        assertCommandSuccess(helpCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpCommand_success() {
        HelpCommand helpCommand = new HelpCommand(HelpCommand.COMMAND_WORD);
        CommandResult expectedCommandResult = new CommandResult(HelpCommand.MESSAGE_USAGE, false, false);
        assertCommandSuccess(helpCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_pinCommand_success() {
        HelpCommand helpCommand = new HelpCommand(PinCommand.COMMAND_WORD);
        CommandResult expectedCommandResult = new CommandResult(PinCommand.MESSAGE_USAGE,
                false, false);
        assertCommandSuccess(helpCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_unpinCommand_success() {
        HelpCommand helpCommand = new HelpCommand(UnpinCommand.COMMAND_WORD);
        CommandResult expectedCommandResult = new CommandResult(UnpinCommand.MESSAGE_USAGE,
                false, false);
        assertCommandSuccess(helpCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_mailingListCommand_success() {
        HelpCommand helpCommand = new HelpCommand(MailingListCommand.COMMAND_WORD);
        CommandResult expectedCommandResult = new CommandResult(MailingListCommand.MESSAGE_USAGE,
                false, false);
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
