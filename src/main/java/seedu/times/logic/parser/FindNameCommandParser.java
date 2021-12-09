package seedu.times.logic.parser;

import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.times.logic.parser.ParserUtil.FIND_REGEX_WITH_COMMA_DELIMITER;
import static seedu.times.logic.parser.ParserUtil.getSearchTermList;

import seedu.times.logic.commands.FindNameCommand;
import seedu.times.logic.parser.exceptions.ParseException;
import seedu.times.model.person.predicates.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindNameCommand object
 */
public class FindNameCommandParser implements Parser<FindNameCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindNameCommand
     * and returns a FindNameCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public FindNameCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindNameCommand.MESSAGE_USAGE));
        }

        return new FindNameCommand(new NameContainsKeywordsPredicate(getSearchTermList(trimmedArgs,
                FIND_REGEX_WITH_COMMA_DELIMITER)));
    }
}
