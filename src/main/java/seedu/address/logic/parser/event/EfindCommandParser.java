package seedu.address.logic.parser.event;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.event.EfindCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.NameContainsKeywordsPredicate;
import seedu.address.model.module.event.Event;

/**
 * Parses input arguments and creates a new EfindCommand object
 */
public class EfindCommandParser implements Parser<EfindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EfindCommand
     * and returns a EfindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EfindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EfindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new EfindCommand(new NameContainsKeywordsPredicate<Event>(Arrays.asList(nameKeywords)));
    }

}
