package seedu.plannermd.logic.parser.apptcommandparser;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_START;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.stream.Stream;

import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.apptcommand.AddAppointmentCommand;
import seedu.plannermd.logic.parser.ArgumentMultimap;
import seedu.plannermd.logic.parser.ArgumentTokenizer;
import seedu.plannermd.logic.parser.Parser;
import seedu.plannermd.logic.parser.ParserUtil;
import seedu.plannermd.logic.parser.Prefix;
import seedu.plannermd.logic.parser.exceptions.ParseException;
import seedu.plannermd.model.appointment.AppointmentDate;
import seedu.plannermd.model.appointment.Duration;
import seedu.plannermd.model.appointment.Session;
import seedu.plannermd.model.person.Remark;

public class AddAppointmentCommandParser implements Parser<AddAppointmentCommand> {
    private final DateTimeFormatter fmt = new DateTimeFormatterBuilder()
            .appendPattern("d/M/uuuu")
            .appendPattern(" HH:mm")
            .toFormatter().withResolverStyle(ResolverStyle.STRICT);

    /**
     * Parses the given {@code String} of arguments in the context of the AddAppointmentCommand
     * and returns a AddAppointmentCommand object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format
     */
    public AddAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PATIENT, PREFIX_DOCTOR, PREFIX_START,
                PREFIX_DURATION, PREFIX_REMARK);
        if (!arePrefixesPresent(argMultimap, PREFIX_PATIENT, PREFIX_DOCTOR, PREFIX_START)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAppointmentCommand.MESSAGE_USAGE));
        }

        AddAppointmentCommand.AddAppointmentDescriptor addAppointmentDescriptor =
                new AddAppointmentCommand.AddAppointmentDescriptor();
        Remark remark = Remark.getEmptyRemark();
        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            remark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get());
        }

        addAppointmentDescriptor.setRemark(remark);
        String trimmedParsedDateTime = argMultimap.getValue(PREFIX_START).get().trim();
        String time = getTimeFromDateTime(trimmedParsedDateTime);
        String date = getDateFromDateTime(trimmedParsedDateTime);
        AppointmentDate appointmentDate = new AppointmentDate(date);
        addAppointmentDescriptor.setAppointmentDate(appointmentDate);

        Duration duration = Duration.getDefaultDuration();
        if (argMultimap.getValue(PREFIX_DURATION).isPresent()) {
            duration = ParserUtil.parseDuration(argMultimap.getValue(PREFIX_DURATION).get());
        }

        Session session = new Session(time, duration);
        if (session.isEndWithinSameDay()) {
            addAppointmentDescriptor.setSession(session);
        } else {
            throw new ParseException(Session.MESSAGE_END_WITHIN_SAME_DAY);
        }

        Index patientIndex;
        Index doctorIndex;
        try {
            patientIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PATIENT).get());
            doctorIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_DOCTOR).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAppointmentCommand.MESSAGE_USAGE), pe);
        }

        return new AddAppointmentCommand(patientIndex, doctorIndex, addAppointmentDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given {@code ArgumentMultimap}.
     *
     * @return boolean
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private String getTimeFromDateTime(String dateTime) throws ParseException {
        requireNonNull(dateTime);
        try {
            LocalTime inputTime = LocalDateTime.parse(dateTime, fmt).toLocalTime();
            return inputTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            throw new ParseException(AddAppointmentCommand.MESSAGE_WRONG_DATE_TIME);
        }
    }

    private String getDateFromDateTime(String dateTime) throws ParseException {
        requireNonNull(dateTime);
        try {
            LocalDate inputDate = LocalDateTime.parse(dateTime, fmt).toLocalDate();
            return inputDate.format(DateTimeFormatter.ofPattern("d/M/yyyy"));
        } catch (DateTimeParseException e) {
            throw new ParseException(AddAppointmentCommand.MESSAGE_WRONG_DATE_TIME);
        }
    }
}
