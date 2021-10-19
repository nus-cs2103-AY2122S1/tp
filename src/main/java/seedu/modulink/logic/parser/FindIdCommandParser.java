package seedu.modulink.logic.parser;

import static seedu.modulink.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.modulink.logic.commands.FindIdCommand;
import seedu.modulink.logic.parser.exceptions.ParseException;
import seedu.modulink.model.person.StudentIdContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindIdCommand object
 */
public class FindIdCommandParser implements Parser<FindIdCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindIdCommand
     * and returns a FindIdCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindIdCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindIdCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");


        return new FindIdCommand(new StudentIdContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
