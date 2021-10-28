package seedu.address.logic.parser;

import seedu.address.logic.commands.*;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HelpCommand object
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns a HelpCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public HelpCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        final String userGuide = "https://github.com/AY2122S1-CS2103-F10-2/tp/blob/master/docs/UserGuide.md";
        final String helpMessage = "\nRefer to the user guide: " + userGuide;

        switch (trimmedArgs) {
        case AddCommand.COMMAND_WORD:
            return new HelpCommand(AddCommand.MESSAGE_USAGE);

        case EditCommand.COMMAND_WORD:
            return new HelpCommand(EditCommand.MESSAGE_USAGE);

        case RemoveCommand.COMMAND_WORD:
            return new HelpCommand(RemoveCommand.MESSAGE_USAGE);

        case DeleteCommand.COMMAND_WORD:
            return new HelpCommand(DeleteCommand.MESSAGE_USAGE);

        case ClearCommand.COMMAND_WORD:
            return new HelpCommand(ClearCommand.MESSAGE_USAGE);

        case FindCommand.COMMAND_WORD:
            return new HelpCommand(FindCommand.MESSAGE_USAGE);

        case ListInventoryCommand.COMMAND_WORD:
            String messageUsage = ListInventoryCommand.MESSAGE_USAGE + "\n"
                    + ListTransactionCommand.MESSAGE_USAGE;
            return new HelpCommand(messageUsage);

        case SortCommand.COMMAND_WORD:
            return new HelpCommand(SortCommand.MESSAGE_USAGE);

        case ExitCommand.COMMAND_WORD:
            return new HelpCommand(ExitCommand.MESSAGE_USAGE);

        case StartOrderCommand.COMMAND_WORD:
            return new HelpCommand(StartOrderCommand.MESSAGE_USAGE);

        case AddToOrderCommand.COMMAND_WORD:
            return new HelpCommand(AddToOrderCommand.MESSAGE_USAGE);

        case RemoveFromOrderCommand.COMMAND_WORD:
            return new HelpCommand(RemoveFromOrderCommand.MESSAGE_USAGE);

        case EndAndTransactOrderCommand.COMMAND_WORD:
            return new HelpCommand(EndAndTransactOrderCommand.MESSAGE_USAGE);

        default:
            return new HelpCommand(helpMessage);
        }
    }
}
