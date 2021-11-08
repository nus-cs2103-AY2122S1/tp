package seedu.fast.logic.parser;

import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_PRIORITY_TAG;

import java.util.Arrays;

import seedu.fast.logic.commands.FindCommand;
import seedu.fast.logic.parser.exceptions.ParseException;
import seedu.fast.model.person.NameContainsQueriesPredicate;
import seedu.fast.model.person.PriorityPredicate;
import seedu.fast.model.person.RemarkContainsKeywordPredicate;
import seedu.fast.model.person.TagMatchesKeywordPredicate;
import seedu.fast.model.tag.PriorityTag;
import seedu.fast.model.tag.Tag;

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
        if (trimmedArgs.startsWith(PREFIX_PRIORITY_TAG.getPrefix())) {
            String tokenizedArgs = trimmedArgs.substring(
                    PREFIX_PRIORITY_TAG.getPrefix().length());
            String[] tags = tokenizedArgs.split("\\s+");
            // splits trimmedArgs according to whitespaces
            checkIfPriorityTag(tags);
            return new FindCommand(new PriorityPredicate(Arrays.asList(tags)));

        } else if (trimmedArgs.startsWith(FindCommand.TAG_PREFIX)) {
            String tokenizedArgs = trimmedArgs.substring(
                    FindCommand.TAG_PREFIX.length());
            String[] tags = tokenizedArgs.split("\\s+");
            // splits trimmedArgs according to whitespaces
            checkForBlanks(tags);
            return new FindCommand(new TagMatchesKeywordPredicate(Arrays.asList(tags)));

        } else if (trimmedArgs.startsWith(FindCommand.REMARK_PREFIX)) {
            String tokenizedArgs = trimmedArgs.substring(
                    FindCommand.REMARK_PREFIX.length());
            String[] queries = tokenizedArgs.split("\\s+");
            // splits trimmedArgs according to whitespaces
            checkForBlanks(queries);
            return new FindCommand(new RemarkContainsKeywordPredicate(Arrays.asList(queries)));

        } else {
            String[] nameKeywords = trimmedArgs.split("\\s+");
            // splits trimmedArgs according to whitespaces
            return new FindCommand(new NameContainsQueriesPredicate(Arrays.asList(nameKeywords)));
        }
    }

    private void checkIfPriorityTag(String[] tags) throws ParseException {
        for (String tag : tags) {
            if (!isPriority(tag)) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, Tag.MESSAGE_CONSTRAINTS));
            }
        }
    }

    private void checkForBlanks(String[] tags) throws ParseException {
        for (String tag : tags) {
            if (isBlank(tag)) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
        }
    }

    private static boolean isPriority(String tag) {
        return PriorityTag.LowPriority.TERM.equals(tag)
                || PriorityTag.MediumPriority.TERM.equals(tag)
                || PriorityTag.HighPriority.TERM.equals(tag);
    }

    private static boolean isBlank(String string) {
        return string.trim().equals("");
    }

}
