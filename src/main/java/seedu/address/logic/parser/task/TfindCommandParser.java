package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.task.TfindCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.NameContainsKeywordsPredicate;
import seedu.address.model.module.task.Task;

/**
 * Parses input arguments and creates a new TfindCommand object
 */
public class TfindCommandParser implements Parser<TfindCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TfindCommand
     * and returns a TfindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TfindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TfindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new TfindCommand(new NameContainsKeywordsPredicate<Task>(Arrays.asList(nameKeywords)));
    }
}
