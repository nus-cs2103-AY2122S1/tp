package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SetDefaultShiftTimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;


public class SetDefaultShiftTimeCommandParser {
    public static final ParseException DEFAULT_ERROR = new ParseException(
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetDefaultShiftTimeCommand.HELP_MESSAGE));

    /**
     * Parses the given {@code String} of arguments in the context of the SetDefaultShiftTimeCommand
     * and returns a SetDefaultShiftTimeCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetDefaultShiftTimeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] strTimings = args.trim().split(" ");
        if (strTimings.length != 4) {
            throw DEFAULT_ERROR;
        }

        LocalTime[] newTimings;
        try {
            newTimings = ParserUtil.parseTimingsArr(strTimings);
        } catch (DateTimeParseException e) {
            throw DEFAULT_ERROR;
        }

        return new SetDefaultShiftTimeCommand(newTimings);
    }
}
