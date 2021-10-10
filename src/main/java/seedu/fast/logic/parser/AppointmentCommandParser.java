package seedu.fast.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT_TIME;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.fast.commons.core.index.Index;
import seedu.fast.commons.exceptions.IllegalValueException;
import seedu.fast.logic.commands.AppointmentCommand;
import seedu.fast.logic.parser.exceptions.ParseException;
import seedu.fast.model.person.Appointment;

public class AppointmentCommandParser implements Parser {

    /**
     * Parses the given {@code String} of arguments in the context of the AppointmentCommand
     * and returns an AppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_APPOINTMENT, PREFIX_APPOINTMENT_TIME);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AppointmentCommand.MESSAGE_USAGE), ive);
        }

        String retrievedDate = argMultimap.getValue(PREFIX_APPOINTMENT).orElse(Appointment.NO_APPOINTMENT);
        String parsedDate = parseDateString(retrievedDate);

        String retrievedTime = argMultimap.getValue(PREFIX_APPOINTMENT_TIME).orElse(Appointment.NO_TIME);
        String parsedTime = parseTimeString(retrievedTime);

        return new AppointmentCommand(index, new Appointment(parsedDate, parsedTime));
    }

    /**
     * Checks if the retrieved date from user input is valid or if it is a delete appointment command.
     *
     * A valid date input is of the format yyyy-mm-dd.
     * `mm` is a 2-digit number in the range 01-12, which represents a calendar month.
     * `dd` is a 2-digit number in the range of 01-31, depending on the number of days in the calendar month.
     *
     * If the retrieved date is valid, returns the date in `dd MMM yyyy` format.
     * If the retrieved date is a delete appointment command, returns `No Appointment Scheduled Yet`.
     * Otherwise, it means that the user did not enter the correct input. A ParseException will be thrown.
     *
     * @param date Date String retrieved from user input
     * @return A String representing the date in the specified format if it is valid (for add/update),
     * or 'No Appointment Scheduled Yet' (for delete)
     * @throws ParseException Thrown when the date retrieved is invalid
     */
    private String parseDateString(String date) throws ParseException {
        if (!date.equals(AppointmentCommand.APPOINTMENT_DELETE_COMMAND)) {
            try {
                // converts the date to the specified format
                date = LocalDate.parse(date).format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
            } catch (DateTimeParseException dtpe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AppointmentCommand.MESSAGE_USAGE), dtpe);
            }
        } else {
            date = Appointment.NO_APPOINTMENT;
        }
        return date;
    }

    /**
     * Checks if the retrieved time from user input is valid.
     *
     * A valid time input is of the format hh:mm (in 24-hour format).
     * `hh` is a 2-digit number in the range 00-23, which represents the hour in the 24-hour format.
     * `mm` is a 2-digit number in the range of 00-59, which represents the minute in the 24-hour format.
     *
     * If the retrieved time is valid, returns the time in `HHmm` format.
     * Otherwise, it means that the user did not enter the correct input. A ParseException will be thrown.
     *
     * @param time Time String retrieved from user input
     * @return A String representing the time in the specified format if it is valid.
     * @throws ParseException Thrown when the date retrieved is invalid
     */
    private String parseTimeString(String time) throws ParseException {
        String validationPattern = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";

        if (!time.equals(Appointment.NO_TIME)) {
            if (!time.matches(validationPattern)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AppointmentCommand.MESSAGE_USAGE));
            }

            try {
                // converts the time to the specified format
                time = LocalTime.parse(time).format(DateTimeFormatter.ofPattern("HHmm"));
            } catch (DateTimeParseException dtpe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AppointmentCommand.MESSAGE_USAGE), dtpe);
            }
        }

        return time;
    }
}
