package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindSupplierCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.supplier.SupplierClassContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindSupplierCommand object
 */
public class FindSupplierCommandParser implements Parser<FindSupplierCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindSupplierCommand
     * and returns a FindSupplierCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindSupplierCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindSupplierCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindSupplierCommand(new SupplierClassContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
