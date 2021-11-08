package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListInventoryCommand;
import seedu.address.logic.commands.ListTransactionCommand;
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

        // Listing Inventory or Order
        if (trimmedArgs.equals("")) {
            // Display all items
            return new ListInventoryCommand(DisplayMode.DISPLAY_INVENTORY);
        } else if (trimmedArgs.equals(ListInventoryCommand.ORDER_KEYWORD)) {
            //Display current order
            return new ListInventoryCommand(DisplayMode.DISPLAY_OPEN_ORDER);
        }

        // Listing Transactions
        if (trimmedArgs.startsWith(ListTransactionCommand.TRANSACTIONS_KEYWORD)) {
            String id = trimmedArgs
                    .substring(ListTransactionCommand.TRANSACTIONS_KEYWORD.length())
                    .trim();

            return new ListTransactionCommand(id);
        }

        // Unrecognised keyword
        String messageUsage = ListInventoryCommand.MESSAGE_USAGE + "\n"
                + ListTransactionCommand.MESSAGE_USAGE;

        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, messageUsage)
        );
    }
}
