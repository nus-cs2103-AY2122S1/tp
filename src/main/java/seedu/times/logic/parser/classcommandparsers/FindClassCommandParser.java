package seedu.times.logic.parser.classcommandparsers;

import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.times.logic.commands.classcommands.FindClassCommand;
import seedu.times.logic.parser.Parser;
import seedu.times.logic.parser.exceptions.ParseException;
import seedu.times.model.tuitionclass.predicates.ClassTimingContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindClassCommand object
 */
public class FindClassCommandParser implements Parser<FindClassCommand> {

    private static final String VALIDATION_REGEX_DAY = "(?i)(MON|TUE|WED|THU|FRI|SAT|SUN){1}";
    private static final String VALIDATION_REGEX_TIME = "([01][0-9]|2[0-3]):[03]0-(([01][0-9]|2[0-3])"
            + ":[03]0|23:59){1}";
    private static final String FIND_REGEX = "\\s+";

    /**
     * Returns true if all keywords contained in {@code classTimingKeywords} is a three-letter abbreviation
     * for day of the week or in a valid time format, HH:MM-HH:MM.
     *
     * @param classTimingKeywords Array of string keywords.
     */
    private static boolean isAllValidKeyword(String[] classTimingKeywords) {
        return Arrays.stream(classTimingKeywords).allMatch(keyword -> {
            return keyword.matches(VALIDATION_REGEX_TIME) || keyword.matches(VALIDATION_REGEX_DAY);
        });
    }

    @Override
    public FindClassCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClassCommand.MESSAGE_USAGE));
        }

        String[] classTimingKeywords = trimmedArgs.split(FIND_REGEX);

        if (!isAllValidKeyword(classTimingKeywords)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClassCommand.MESSAGE_USAGE));
        }

        return new FindClassCommand(new ClassTimingContainsKeywordsPredicate(Arrays.asList(classTimingKeywords)));
    }
}
