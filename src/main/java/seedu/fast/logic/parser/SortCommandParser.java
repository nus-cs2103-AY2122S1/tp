package seedu.fast.logic.parser;

import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.fast.commons.util.SortByAppointment;
import seedu.fast.commons.util.SortByName;
import seedu.fast.logic.commands.FindCommand;
import seedu.fast.logic.commands.SortCommand;
import seedu.fast.logic.parser.exceptions.ParseException;
import seedu.fast.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        switch (trimmedArgs) {

        case SortByName.KEYWORD:
            return new SortCommand(new SortByName());

        case SortByAppointment.KEYWORD:
            return new SortCommand(new SortByAppointment());

        default:
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }


    }

}
