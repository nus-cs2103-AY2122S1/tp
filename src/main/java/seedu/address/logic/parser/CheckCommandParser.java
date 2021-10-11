package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserUtil.DATE_FORMATTER;
import static seedu.address.logic.parser.ParserUtil.TIME_FORMATTER;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import seedu.address.logic.commands.CheckCommand;
import seedu.address.logic.parser.enums.EnumTypeOfCheck;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.reservation.ListContainsReservationPredicate;

/**
 * Parses input arguments and creates a new CheckCommand object
 */
public class CheckCommandParser implements Parser<CheckCommand> {

    public static final String ALLOWED_TIME_FORMAT = "HHmm";

    /**
     * Used to convert LocalDate to LocalDateTime when user inputs only date to query
     */
    public static final LocalTime DEFAULT_TIME = LocalTime.parse("00:00");

    private LocalDate date;
    private LocalTime time;
    private EnumTypeOfCheck typeOfCheck;

    /**
     * Parses the given {@code String} of arguments in the context of the CheckCommand
     * and returns a CheckCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public CheckCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        }

        String[] splitTrimmedArgs = trimmedArgs.split("\\s+");
        if (splitTrimmedArgs.length > 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        }

        parseArgs(splitTrimmedArgs);
        return new CheckCommand(new ListContainsReservationPredicate(date, time, typeOfCheck));
    }

    private void parseArgs(String[] splitTrimmedArgs) throws ParseException {
        if (splitTrimmedArgs.length > 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        } else if (splitTrimmedArgs.length == 2) {
            parseDateTime(splitTrimmedArgs[0], splitTrimmedArgs[1]);
        } else { // Input arguments array is length 1
            String dateOrTimeString = splitTrimmedArgs[0];
            if (dateOrTimeString.length() == ALLOWED_TIME_FORMAT.length()) {
                parseTime(dateOrTimeString);
            } else {
                parseDate(dateOrTimeString);
            }
        }
    }

    private void parseDate(String dateString) throws ParseException {
        assert (dateString.length() != ALLOWED_TIME_FORMAT.length());
        try {
            date = LocalDate.parse(dateString, DATE_FORMATTER);
            time = DEFAULT_TIME;
            typeOfCheck = EnumTypeOfCheck.Date;
        } catch (DateTimeParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        }
    }

    private void parseTime(String timeString) throws ParseException {
        assert (timeString.length() == ALLOWED_TIME_FORMAT.length());
        try {
            date = LocalDate.now();
            time = LocalTime.parse(timeString, TIME_FORMATTER);
            typeOfCheck = EnumTypeOfCheck.Time;
        } catch (DateTimeParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        }
    }

    private void parseDateTime(String dateString, String timeString) throws ParseException {
        try {
            date = LocalDate.parse(dateString, DATE_FORMATTER);
            time = LocalTime.parse(timeString, TIME_FORMATTER);
            typeOfCheck = EnumTypeOfCheck.DateTime;
        } catch (DateTimeParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        }
    }
}


