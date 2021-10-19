package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim().replace(FindCommand.COMMAND_WORD, "");
        checkTrimmedArgs(trimmedArgs);

        if (trimmedArgs.contains(CliSyntax.PREFIX_DASH_NAME.getPrefix())) {
            String[] nameKeywords = trimmedArgs.replace(CliSyntax.PREFIX_DASH_NAME.getPrefix(), "")
                    .trim().split("\\s+");
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));

        } else if (trimmedArgs.contains(CliSyntax.PREFIX_DASH_INDEX.getPrefix())) {
            String indexString = trimmedArgs.replace("-i", "").trim();

            try {
                // TODO can consider creating an exception when the index is not in range
                int index = Integer.parseInt(indexString);
                return new FindCommand(index - 1); // +1 so that it starts from 1 instead of 0

            } catch (NumberFormatException e) {
                throw new ParseException(
                        // "Invalid number, check index");
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }

        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    private void checkTrimmedArgs(String trimmedArgs) throws ParseException {
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    //"empty string found");
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }
}
