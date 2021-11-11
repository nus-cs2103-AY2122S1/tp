package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindOrderCommand;
import seedu.address.logic.commands.Keyword;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.OrderContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindOrderCommand object
 */
public class FindOrderCommandParser implements Parser<FindOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindOrderCommand
     * and returns a FindOrderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindOrderCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");
        for (String keyword : nameKeywords) {
            if (!Keyword.isValidKeyword(keyword)) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE));
            }
        }

        return new FindOrderCommand(new OrderContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
