package seedu.track2gather.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.track2gather.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.track2gather.logic.commands.TShiftCommand;
import seedu.track2gather.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code TShiftCommand} object
 */
public class TShiftCommandParser implements Parser<TShiftCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code TShiftCommand}
     * and returns a {@code TShiftCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TShiftCommand parse(String args) throws ParseException {
        requireNonNull(args);

        try {
            int days = Integer.parseInt(args.trim());
            return new TShiftCommand(days);
        } catch (NumberFormatException nfe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TShiftCommand.MESSAGE_USAGE), nfe);
        }
    }
}
