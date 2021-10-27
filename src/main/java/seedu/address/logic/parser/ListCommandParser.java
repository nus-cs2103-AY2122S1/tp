package seedu.address.logic.parser;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.display.DisplayMode;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        switch (trimmedArgs) {
        case ListCommand.ORDER_KEYWORD:
            return new ListCommand(DisplayMode.DISPLAY_OPEN_ORDER);

        case ListCommand.TRANSACTIONS_KEYWORD:
            return new ListCommand(DisplayMode.DISPLAY_TRANSACTIONS);

        case "":
            return new ListCommand(DisplayMode.DISPLAY_INVENTORY);

        default:
            throw new ParseException(ListCommand.MESSAGE_USAGE);

        }
    }
}
