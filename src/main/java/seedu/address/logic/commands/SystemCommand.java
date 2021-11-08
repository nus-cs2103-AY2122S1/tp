package seedu.address.logic.commands;

/** Displays help message when user is typing. */
public class SystemCommand {

    /**
     * Executes help message when user is typing.
     *
     * @param userInput input of user.
     * @return feedback message of the operation result for display.
     */
    public static CommandResult execute(String userInput) {
        String[] parsedInput = userInput.split(" ");
        String commandWord = parsedInput[0];
        switch (commandWord) {
        case (AddCommand.COMMAND_WORD):
            return new CommandResult(AddCommand.COMMAND_EXAMPLE, false, false);
        case (DeleteCommand.COMMAND_WORD):
            return new CommandResult(DeleteCommand.COMMAND_EXAMPLE, false, false);
        case (DeleteMultipleCommand.COMMAND_WORD):
            return new CommandResult(DeleteMultipleCommand.COMMAND_EXAMPLE, false, false);
        case (EditCommand.COMMAND_WORD):
            return new CommandResult(EditCommand.COMMAND_EXAMPLE, false, false);
        case (FindAnyCommand.COMMAND_WORD):
            return new CommandResult(FindAnyCommand.COMMAND_EXAMPLE, false, false);
        case (FindCommand.COMMAND_WORD):
            return new CommandResult(FindCommand.COMMAND_EXAMPLE, false, false);
        case (PinCommand.COMMAND_WORD):
            return new CommandResult(PinCommand.COMMAND_EXAMPLE, false, false);
        case (TagCommand.COMMAND_WORD):
            return new CommandResult(TagCommand.COMMAND_EXAMPLE, false, false);
        case (UnpinCommand.COMMAND_WORD):
            return new CommandResult(UnpinCommand.COMMAND_EXAMPLE, false, false);
        case (UntagCommand.COMMAND_WORD):
            return new CommandResult(UntagCommand.COMMAND_EXAMPLE, false, false);
        case (ListCommand.COMMAND_WORD):
            return new CommandResult(ListCommand.COMMAND_EXAMPLE, false, false);
        case (MailingListCommand.COMMAND_WORD):
            return new CommandResult(MailingListCommand.COMMAND_EXAMPLE, false, false);
        case (ClearCommand.COMMAND_WORD):
            return new CommandResult(ClearCommand.COMMAND_EXAMPLE, false, false);
        case (HelpCommand.COMMAND_WORD):
            return new CommandResult(HelpCommand.HELP_MESSAGE, false, false);
        case (ExitCommand.COMMAND_WORD):
            return new CommandResult(ExitCommand.COMMAND_EXAMPLE, false, false);
        default:
            return new CommandResult("", false, false);
        }
    }
}
