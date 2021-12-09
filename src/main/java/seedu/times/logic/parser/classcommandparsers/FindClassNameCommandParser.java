package seedu.times.logic.parser.classcommandparsers;

import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.times.logic.parser.ParserUtil.FIND_REGEX_WITH_COMMA_DELIMITER;
import static seedu.times.logic.parser.ParserUtil.getSearchTermList;

import seedu.times.logic.commands.classcommands.FindClassNameCommand;
import seedu.times.logic.parser.Parser;
import seedu.times.logic.parser.exceptions.ParseException;
import seedu.times.model.tuitionclass.predicates.ClassNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindClassNameCommand object
 */
public class FindClassNameCommandParser implements Parser<FindClassNameCommand> {

    @Override
    public FindClassNameCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClassNameCommand.MESSAGE_USAGE));
        }

        return new FindClassNameCommand(new ClassNameContainsKeywordsPredicate(getSearchTermList(trimmedArgs,
                FIND_REGEX_WITH_COMMA_DELIMITER)));
    }
}
