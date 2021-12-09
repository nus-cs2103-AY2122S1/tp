package seedu.times.logic.parser;

import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.times.logic.parser.ParserUtil.FIND_REGEX_WITH_COMMA_DELIMITER;
import static seedu.times.logic.parser.ParserUtil.getSearchTermList;

import seedu.times.logic.commands.FindTagCommand;
import seedu.times.logic.parser.exceptions.ParseException;
import seedu.times.model.person.predicates.TagsContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new {@code FindTagCommand} object
 */
public class FindTagCommandParser implements Parser<FindTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTagCommand
     * and returns a FindTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public FindTagCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTagCommand.MESSAGE_USAGE));
        }

        return new FindTagCommand(new TagsContainsKeywordsPredicate(getSearchTermList(trimmedArgs,
                FIND_REGEX_WITH_COMMA_DELIMITER)));
    }
}
