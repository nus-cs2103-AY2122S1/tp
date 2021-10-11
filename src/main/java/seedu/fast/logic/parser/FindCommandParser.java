package seedu.fast.logic.parser;

import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.fast.logic.commands.FindCommand;
import seedu.fast.logic.parser.exceptions.ParseException;
import seedu.fast.model.person.NameContainsKeywordsPredicate;
import seedu.fast.model.person.PriorityPredicate;
import seedu.fast.model.tag.PriorityTag;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        if (trimmedArgs.startsWith(PriorityTag.PRIORITY_TAG_PREFIX)) {
            trimmedArgs = trimmedArgs.substring(
                    PriorityTag.PRIORITY_TAG_PREFIX.length());
            String[] tags = trimmedArgs.split("\\s+");
            for (String tag:tags) {
                if (isNotPriority(tag)) {
                    throw new ParseException(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
                }
            }
            return new FindCommand(new PriorityPredicate(Arrays.asList(tags)));
        } else {
            String[] nameKeywords = trimmedArgs.split("\\s+");

            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }
    }

    private static boolean isNotPriority(String tag) {
        switch(tag) {
        case PriorityTag.LowPriority.TERM:
        case PriorityTag.MediumPriority.TERM:
        case PriorityTag.HighPriority.TERM:
            return false;
        default:
            return true;
        }
    }

}
