package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String MORE = "more";
    public static final String EMPTY = "";
    public static final String COMMAND_DESCRIPTION = "Shows program usage instructions.\n";
    public static final String COMMAND_EXAMPLE = "Parameters: "
            + "[COMMAND] \n"
            + "Example: " + COMMAND_WORD + "; "
            + COMMAND_WORD + " " + AddCommand.COMMAND_WORD + "; "
            + COMMAND_WORD + " " + MORE;

    public static final String SPACE = "            ";

    public static final String HELP_MESSAGE = "Usage: help <command>\n"
            + "\n"
            + "Commands:\n"
            + AddCommand.COMMAND_WORD + SPACE + AddCommand.COMMAND_DESCRIPTION
            + EditCommand.COMMAND_WORD + SPACE + EditCommand.COMMAND_DESCRIPTION
            + TagCommand.COMMAND_WORD + SPACE + TagCommand.COMMAND_DESCRIPTION
            + UntagCommand.COMMAND_WORD + SPACE + UntagCommand.COMMAND_DESCRIPTION
            + ListCommand.COMMAND_WORD + SPACE + ListCommand.COMMAND_DESCRIPTION
            + FindCommand.COMMAND_WORD + SPACE + FindCommand.COMMAND_DESCRIPTION
            + FindAnyCommand.COMMAND_WORD + SPACE + FindAnyCommand.COMMAND_DESCRIPTION
            + PinCommand.COMMAND_WORD + SPACE + PinCommand.COMMAND_DESCRIPTION
            + UnpinCommand.COMMAND_WORD + SPACE + UnpinCommand.COMMAND_DESCRIPTION
            + MailingListCommand.COMMAND_WORD + SPACE + MailingListCommand.COMMAND_DESCRIPTION
            + DeleteCommand.COMMAND_WORD + SPACE + DeleteCommand.COMMAND_DESCRIPTION
            + DeleteMultipleCommand.COMMAND_WORD + SPACE + DeleteMultipleCommand.COMMAND_DESCRIPTION
            + ClearCommand.COMMAND_WORD + SPACE + ClearCommand.COMMAND_DESCRIPTION
            + ExitCommand.COMMAND_WORD + SPACE + ExitCommand.COMMAND_DESCRIPTION
            + "For more information, enter <help more>.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": " + COMMAND_DESCRIPTION + COMMAND_EXAMPLE;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    private final String commandWord;

    /**
     * Creates a {@code HelpCommand} which returns help for the command provided.
     *
     * @param commandWord the command for which help is requested.
     */
    public HelpCommand(String commandWord) {
        this.commandWord = commandWord;
    }

    /**
     * Executes the {@code HelpCommand} which provides help messages to user.
     *
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} regarding the status of the {@code HelpCommand}.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {

        switch (commandWord) {

        case EMPTY:
            return new CommandResult(HELP_MESSAGE, false, false);

        case MORE:
            return new CommandResult(SHOWING_HELP_MESSAGE, true, false);

        case AddCommand.COMMAND_WORD:
            return new CommandResult(AddCommand.MESSAGE_USAGE, false, false);

        case EditCommand.COMMAND_WORD:
            return new CommandResult(EditCommand.MESSAGE_USAGE, false, false);

        case UntagCommand.COMMAND_WORD:
            return new CommandResult(UntagCommand.MESSAGE_USAGE, false, false);

        case TagCommand.COMMAND_WORD:
            return new CommandResult(TagCommand.MESSAGE_USAGE, false, false);

        case DeleteCommand.COMMAND_WORD:
            return new CommandResult(DeleteCommand.MESSAGE_USAGE, false, false);

        case DeleteMultipleCommand.COMMAND_WORD:
            return new CommandResult(DeleteMultipleCommand.MESSAGE_USAGE, false, false);

        case ClearCommand.COMMAND_WORD:
            return new CommandResult(ClearCommand.MESSAGE_USAGE, false, false);

        case FindCommand.COMMAND_WORD:
            return new CommandResult(FindCommand.MESSAGE_USAGE, false, false);

        case FindAnyCommand.COMMAND_WORD:
            return new CommandResult(FindAnyCommand.MESSAGE_USAGE, false, false);

        case ListCommand.COMMAND_WORD:
            return new CommandResult(ListCommand.MESSAGE_USAGE, false, false);

        case ExitCommand.COMMAND_WORD:
            return new CommandResult(ExitCommand.MESSAGE_USAGE, false, false);

        case HelpCommand.COMMAND_WORD:
            return new CommandResult(HelpCommand.MESSAGE_USAGE, false, false);

        case PinCommand.COMMAND_WORD:
            return new CommandResult(PinCommand.MESSAGE_USAGE, false, false);

        case UnpinCommand.COMMAND_WORD:
            return new CommandResult(UnpinCommand.MESSAGE_USAGE, false, false);

        case MailingListCommand.COMMAND_WORD:
            return new CommandResult(MailingListCommand.MESSAGE_USAGE, false, false);

        default:
            String message = MESSAGE_UNKNOWN_COMMAND + ": " + commandWord + "\n" + HELP_MESSAGE;
            throw new CommandException(message);
        }
    }

    /**
     * Checks if {@code other} is equal to {@code this}.
     *
     * @param other the object to check if it is equal to {@code this}.
     * @return {@code boolean} indicating if it is equal.
     */
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HelpCommand)) {
            return false;
        }

        // state check
        HelpCommand e = (HelpCommand) other;
        return commandWord.equals(e.commandWord);
    }
}
