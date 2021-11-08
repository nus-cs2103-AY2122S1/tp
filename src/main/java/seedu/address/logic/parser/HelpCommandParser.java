package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DoneCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ReminderCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.ViewTaskListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input argument and creates a new HelpCommand object
 */
public class HelpCommandParser {

    /**
     * Parses the given {@code String} of argument in the context of the HelpCommand
     * and returns a HelpCommand object for execution.
     * @param args String argument
     * @return HelpCommand
     * @throws ParseException if the user input does not conform to the expected format
     */
    public HelpCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] argsArray = trimmedArgs.split(" ");
        if (trimmedArgs.isEmpty()) {
            return new HelpCommand();
        } else if (argsArray.length > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        } else {
            switch(argsArray[0]) {

            case AddCommand.COMMAND_WORD:

            case EditCommand.COMMAND_WORD:

            case DeleteCommand.COMMAND_WORD:

            case ClearCommand.COMMAND_WORD:

            case FindCommand.COMMAND_WORD:

            case SortCommand.COMMAND_WORD:

            case ListCommand.COMMAND_WORD:

            case ExitCommand.COMMAND_WORD:

            case HelpCommand.COMMAND_WORD:

            case ViewTaskListCommand.COMMAND_WORD:

            case DoneCommand.COMMAND_WORD:

            case UndoCommand.COMMAND_WORD:

            case ReminderCommand.COMMAND_WORD:

                return new HelpCommand(argsArray[0]);

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        }
    }
}
