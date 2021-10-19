package seedu.fast.logic.parser;

import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.fast.logic.commands.FindCommand;
import seedu.fast.logic.parser.exceptions.ParseException;
import seedu.fast.model.person.NameContainsKeywordsPredicate;
import seedu.fast.model.person.PriorityPredicate;
import seedu.fast.model.person.RemarkContainsKeyWordsPredicate;
import seedu.fast.model.person.TagContainsKeyWordsPredicate;
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
            String tokenizedArgs = trimmedArgs.substring(
                    PriorityTag.PRIORITY_TAG_PREFIX.length());
            String[] tags = tokenizedArgs.split("\\s+");
            // splits trimmedArgs according to whitespaces
            for (String tag : tags) {
                if (!isPriority(tag)) {
                    throw new ParseException(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
                }
            }
            return new FindCommand(new PriorityPredicate(Arrays.asList(tags)));

        } else if (trimmedArgs.startsWith(FindCommand.TAG_PREFIX)) {
            String tokenizedArgs = trimmedArgs.substring(
                    FindCommand.TAG_PREFIX.length());
            String[] tags = tokenizedArgs.split("\\s+");
            // splits trimmedArgs according to whitespaces
            checkForBlanks(tags);
            return new FindCommand(new TagContainsKeyWordsPredicate(Arrays.asList(tags)));

        } else if (trimmedArgs.startsWith(FindCommand.REMARK_PREFIX)) {
            String tokenizedArgs = trimmedArgs.substring(
                    FindCommand.REMARK_PREFIX.length());
            String[] queries = tokenizedArgs.split("\\s+");
            // splits trimmedArgs according to whitespaces
            checkForBlanks(queries);
            return new FindCommand(new RemarkContainsKeyWordsPredicate(Arrays.asList(queries)));

        } else {
            String[] nameKeywords = trimmedArgs.split("\\s+");
            // splits trimmedArgs according to whitespaces
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
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
