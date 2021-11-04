package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SystemCommandTest {

    @Test
    public void execute_addCommand_success() {
        CommandResult expectedCommandResult = new CommandResult(AddCommand.COMMAND_EXAMPLE, false, false);
        assertSystemCommandSuccess(AddCommand.COMMAND_WORD, expectedCommandResult);
    }

    @Test
    public void execute_deleteCommand_success() {
        CommandResult expectedCommandResult = new CommandResult(DeleteCommand.COMMAND_EXAMPLE, false, false);
        assertSystemCommandSuccess(DeleteCommand.COMMAND_WORD, expectedCommandResult);
    }

    @Test
    public void execute_deletemCommand_success() {
        CommandResult expectedCommandResult = new CommandResult(DeleteMultipleCommand.COMMAND_EXAMPLE, false, false);
        assertSystemCommandSuccess(DeleteMultipleCommand.COMMAND_WORD, expectedCommandResult);
    }

    @Test
    public void execute_editCommand_success() {
        CommandResult expectedCommandResult = new CommandResult(EditCommand.COMMAND_EXAMPLE, false, false);
        assertSystemCommandSuccess(EditCommand.COMMAND_WORD, expectedCommandResult);
    }

    @Test
    public void execute_findAnyCommand_success() {
        CommandResult expectedCommandResult = new CommandResult(FindAnyCommand.COMMAND_EXAMPLE, false, false);
        assertSystemCommandSuccess(FindAnyCommand.COMMAND_WORD, expectedCommandResult);
    }

    @Test
    public void execute_findCommand_success() {
        CommandResult expectedCommandResult = new CommandResult(FindCommand.COMMAND_EXAMPLE, false, false);
        assertSystemCommandSuccess(FindCommand.COMMAND_WORD, expectedCommandResult);
    }

    @Test
    public void execute_pinCommand_success() {
        CommandResult expectedCommandResult = new CommandResult(PinCommand.COMMAND_EXAMPLE, false, false);
        assertSystemCommandSuccess(PinCommand.COMMAND_WORD, expectedCommandResult);
    }

    @Test
    public void execute_tagCommand_success() {
        CommandResult expectedCommandResult = new CommandResult(TagCommand.COMMAND_EXAMPLE, false, false);
        assertSystemCommandSuccess(TagCommand.COMMAND_WORD, expectedCommandResult);
    }

    @Test
    public void execute_unpinCommand_success() {
        CommandResult expectedCommandResult = new CommandResult(UnpinCommand.COMMAND_EXAMPLE, false, false);
        assertSystemCommandSuccess(UnpinCommand.COMMAND_WORD, expectedCommandResult);
    }

    @Test
    public void execute_untagCommand_success() {
        CommandResult expectedCommandResult = new CommandResult(UntagCommand.COMMAND_EXAMPLE, false, false);
        assertSystemCommandSuccess(UntagCommand.COMMAND_WORD, expectedCommandResult);
    }

    @Test
    public void execute_listCommand_success() {
        CommandResult expectedCommandResult = new CommandResult(ListCommand.COMMAND_EXAMPLE, false, false);
        assertSystemCommandSuccess(ListCommand.COMMAND_WORD, expectedCommandResult);
    }

    @Test
    public void execute_mailingListCommand_success() {
        CommandResult expectedCommandResult = new CommandResult(MailingListCommand.COMMAND_EXAMPLE, false, false);
        assertSystemCommandSuccess(MailingListCommand.COMMAND_WORD, expectedCommandResult);
    }

    @Test
    public void execute_clearCommand_success() {
        CommandResult expectedCommandResult = new CommandResult(ClearCommand.COMMAND_EXAMPLE, false, false);
        assertSystemCommandSuccess(ClearCommand.COMMAND_WORD, expectedCommandResult);
    }

    @Test
    public void execute_helpCommand_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpCommand.HELP_MESSAGE, false, false);
        assertSystemCommandSuccess(HelpCommand.COMMAND_WORD, expectedCommandResult);
    }

    @Test
    public void execute_exitCommand_success() {
        CommandResult expectedCommandResult = new CommandResult(ExitCommand.COMMAND_EXAMPLE, false, false);
        assertSystemCommandSuccess(ExitCommand.COMMAND_WORD, expectedCommandResult);
    }

    @Test
    public void execute_invalidCommand() {
        CommandResult expectedCommandResult = new CommandResult("", false, false);
        assertSystemCommandSuccess("", expectedCommandResult);
    }

    public static void assertSystemCommandSuccess(String commandWord, CommandResult expectedCommandResult) {
        CommandResult result = SystemCommand.execute(commandWord);
        assertEquals(expectedCommandResult, result);
    }

}
