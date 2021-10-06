package seedu.fast.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT;

import java.time.LocalDate;
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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_APPOINTMENT);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AppointmentCommand.MESSAGE_USAGE), ive);
        }

        String date = argMultimap.getValue(PREFIX_APPOINTMENT).orElse(Appointment.NO_APPOINTMENT);
        String parsedDate = parseDateString(date);

        return new AppointmentCommand(index, new Appointment(parsedDate));
    }

    /**
     * Checks if the retrieved date from user input is valid or if it is a delete appointment command.
     * If date is valid, returns the formatted date in the specified format (dd MMM yyyy).
     * If it is a delete appointment command, returns 'No Appointment Scheduled Yet'.
     *
     * @param date Date String retrieved from user input
     * @return A String representing the date in the specified format if it is valid (for add/update),
     * or 'No Appointment Scheduled Yet' (for delete)
     * @throws ParseException Thrown when the date retrieved is invalid (i.e. invalid month or day)
     */
    private String parseDateString(String date) throws ParseException {
        if (!date.equals(AppointmentCommand.DELETE_COMMAND)) {
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
}
