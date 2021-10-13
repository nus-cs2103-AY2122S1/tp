package seedu.address.logic.parser;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddToOrderCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EndAndTransactOrderCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RemoveFromOrderCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.StartOrderCommand;
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
        final String userGuide = "https://se-education.org/addressbook-level3/UserGuide.html";
        final String helpMessage = "Refer to the user guide: " + userGuide;

        switch (trimmedArgs) {
        case AddCommand.COMMAND_WORD:
            return new HelpCommand(AddCommand.MESSAGE_USAGE);

        case EditCommand.COMMAND_WORD:
            return new HelpCommand(EditCommand.MESSAGE_USAGE);

        case DeleteCommand.COMMAND_WORD:
            return new HelpCommand(DeleteCommand.MESSAGE_USAGE);

        case ClearCommand.COMMAND_WORD:
            return new HelpCommand(ClearCommand.MESSAGE_USAGE);

        case FindCommand.COMMAND_WORD:
            return new HelpCommand(FindCommand.MESSAGE_USAGE);

        case ListCommand.COMMAND_WORD:
            return new HelpCommand(ListCommand.MESSAGE_USAGE);

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
