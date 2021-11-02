package seedu.fast.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT_TIME;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT_VENUE;
import static seedu.fast.logic.parser.ParserUtil.parseDateString;
import static seedu.fast.logic.parser.ParserUtil.parseTimeString;

import seedu.fast.commons.core.index.Index;
import seedu.fast.commons.exceptions.IllegalValueException;
import seedu.fast.logic.commands.AppointmentCommand;
import seedu.fast.logic.parser.exceptions.ParseException;
import seedu.fast.model.person.Appointment;

public class AppointmentCommandParser implements Parser<AppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AppointmentCommand
     * and returns an AppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_APPOINTMENT, PREFIX_APPOINTMENT_TIME, PREFIX_APPOINTMENT_VENUE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AppointmentCommand.MESSAGE_USAGE), ive);
        }

        String parsedDate = getParsedDate(argMultimap);
        String parsedTime = getParsedTime(argMultimap);
        String parsedVenue = getParsedVenue(argMultimap);

        return new AppointmentCommand(index, new Appointment(parsedDate, parsedTime, parsedVenue));
    }

    private String checkInputDate(String retrievedDate) throws ParseException {
        if (retrievedDate.equalsIgnoreCase(Appointment.NO_APPOINTMENT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AppointmentCommand.MESSAGE_USAGE));
        }

        return retrievedDate;
    }

    private String getParsedDate(ArgumentMultimap argMultimap) throws ParseException {
        String retrievedDate = checkInputDate(argMultimap.getValue(PREFIX_APPOINTMENT)
                .orElse(Appointment.NO_APPOINTMENT));
        String parsedDate = parseDateString(retrievedDate);
        return parsedDate;
    }

    private String getParsedTime(ArgumentMultimap argMultimap) throws ParseException {
        String retrievedTime = argMultimap.getValue(PREFIX_APPOINTMENT_TIME).orElse(Appointment.NO_TIME);
        String parsedTime = parseTimeString(retrievedTime);
        return parsedTime;
    }

    private String checkVenueLength(String venue) throws ParseException {
        if (!Appointment.isValidVenueFormat(venue)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    Appointment.INVALID_VENUE_INPUT));
        }

        return venue;
    }

    private String getParsedVenue(ArgumentMultimap argMultimap) throws ParseException {
        String retrievedVenue = argMultimap.getValue(PREFIX_APPOINTMENT_VENUE).orElse(Appointment.NO_VENUE);
        String parsedVenue = checkVenueLength(retrievedVenue);
        return parsedVenue;
    }
}
