package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SplitCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code SplitCommand} object.
 */
public class SplitCommandParser implements Parser<SplitCommand> {

    public static final String VALIDATION_REGEX = "[1-7]"; // numbers from 1 to 7

    @Override
    public SplitCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (!trimmedArgs.matches(VALIDATION_REGEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SplitCommand.MESSAGE_USAGE));
        }
        int dayNumber = Integer.parseInt(trimmedArgs);
        return new SplitCommand(dayNumber);
    }
}
