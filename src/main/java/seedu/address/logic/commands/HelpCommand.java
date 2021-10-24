package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String HELP_MESSAGE = "Usage: help <command>\n"
            + "\n"
            + "Commands:\n"
            + "add            Adds a person to the address book.\n"
            + "edit           Edits an existing person in the address book.\n"
            + "tag            Adds tag to an existing person in the address book.\n"
            + "untag          Removes an existing tag from an existing person in the address book.\n"
            + "list           Shows a list of all persons in the address book.\n"
            + "find           Finds all persons whose names contain ALL of the specified keywords (case-insensitive).\n"
            + "findOr         Finds all persons whose names contain ANY of the specified keywords (case-insensitive).\n"
            + "findTag        Finds persons whose contact contain any of the given tags (case insensitive).\n"
            + "findTagC       Finds persons whose contact contain any of the given tags (case sensitive).\n"
            + "delete         Deletes the specified person from the address book.\n"
            + "deletem        Deletes the people within q range from the address book.\n"
            + "clear          Clears all entries from the address book.\n"
            + "exit           Exits the program.\n"
            + "For more information, enter <help more>.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    private static final String EMPTY = "";

    private static final String MORE = "more";

    private final String commandWord;

    public HelpCommand(String commandWord) {
        this.commandWord = commandWord;
    }

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

        case FindOrCommand.COMMAND_WORD:
            return new CommandResult(FindOrCommand.MESSAGE_USAGE, false, false);

        case ListCommand.COMMAND_WORD:
            return new CommandResult(ListCommand.MESSAGE_USAGE, false, false);

        case ExitCommand.COMMAND_WORD:
            return new CommandResult(ExitCommand.MESSAGE_USAGE, false, false);

        case HelpCommand.COMMAND_WORD:
            return new CommandResult(HelpCommand.MESSAGE_USAGE, false, false);

        case FindTagCaseInsensitiveCommand.COMMAND_WORD:
            return new CommandResult(FindTagCaseInsensitiveCommand.MESSAGE_USAGE, false, false);

        case FindTagCaseSensitiveCommand.COMMAND_WORD:
            return new CommandResult(FindTagCaseSensitiveCommand.MESSAGE_USAGE, false, false);

        default:
            String message = MESSAGE_UNKNOWN_COMMAND + ": " + commandWord + "\n" + HELP_MESSAGE;
            throw new CommandException(message);
        }
    }
}
