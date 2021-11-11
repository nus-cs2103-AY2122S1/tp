package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindClassCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutorialclass.ClassCodeContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindClassCommand object
 */
public class FindClassCommandParser implements Parser<FindClassCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindClassCommand
     * and returns a FindClassCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindClassCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClassCommand.MESSAGE_USAGE));
        }

        String[] classCodeKeywords = trimmedArgs.split("\\s+");

        return new FindClassCommand(new ClassCodeContainsKeywordsPredicate(Arrays.asList(classCodeKeywords)));
    }

}
