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
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.apptcommand.EditAppointmentCommand;
import seedu.plannermd.logic.parser.ArgumentMultimap;
import seedu.plannermd.logic.parser.ArgumentTokenizer;
import seedu.plannermd.logic.parser.Parser;
import seedu.plannermd.logic.parser.ParserUtil;
import seedu.plannermd.logic.parser.exceptions.ParseException;
import seedu.plannermd.model.appointment.AppointmentDate;

public class EditAppointmentCommandParser implements Parser<EditAppointmentCommand> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/uuuu HH:mm")
            .withResolverStyle(ResolverStyle.STRICT);

    /**
     * Parses the given {@code String} of arguments in the context of the
     * EditAppointmentCommand and returns an EditAppointmentCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public EditAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PATIENT, PREFIX_DOCTOR, PREFIX_START,
                PREFIX_DURATION, PREFIX_REMARK);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAppointmentCommand.MESSAGE_USAGE), pe);
        }

        EditAppointmentCommand.EditAppointmentDescriptor editAppointmentDescriptor =
                new EditAppointmentCommand.EditAppointmentDescriptor();
        if (argMultimap.getValue(PREFIX_PATIENT).isPresent()) {
            editAppointmentDescriptor.setPatientIndex(
                    ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PATIENT).get()));
        }
        if (argMultimap.getValue(PREFIX_DOCTOR).isPresent()) {
            editAppointmentDescriptor.setDoctorIndex(ParserUtil.parseIndex(argMultimap.getValue(PREFIX_DOCTOR).get()));
        }
        if (argMultimap.getValue(PREFIX_START).isPresent()) {
            String dateTime = argMultimap.getValue(PREFIX_START).get().trim();
            String date = getDateFromDateTime(dateTime);
            String time = getTimeFromDateTime(dateTime);
            AppointmentDate appointmentDate = new AppointmentDate(date);
            editAppointmentDescriptor.setAppointmentDate(appointmentDate);
            editAppointmentDescriptor.setStartTime(time);
        }
        if (argMultimap.getValue(PREFIX_DURATION).isPresent()) {
            editAppointmentDescriptor.setDuration(
                    ParserUtil.parseDuration(argMultimap.getValue(PREFIX_DURATION).get()));
        }
        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            editAppointmentDescriptor.setRemark(ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get()));
        }

        if (!editAppointmentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditAppointmentCommand.MESSAGE_NOT_EDITED);
        }

        return new EditAppointmentCommand(index, editAppointmentDescriptor);
    }

    private String getTimeFromDateTime(String dateTime) throws ParseException {
        requireNonNull(dateTime);
        try {
            LocalTime inputTime = LocalDateTime.parse(dateTime, formatter).toLocalTime();
            return inputTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            throw new ParseException(EditAppointmentCommand.MESSAGE_INVALID_START);
        }
    }

    private String getDateFromDateTime(String dateTime) throws ParseException {
        requireNonNull(dateTime);
        try {
            LocalDate inputDate = LocalDateTime.parse(dateTime, formatter).toLocalDate();
            return inputDate.format(DateTimeFormatter.ofPattern("d/M/yyyy"));
        } catch (DateTimeParseException e) {
            throw new ParseException(EditAppointmentCommand.MESSAGE_INVALID_START);
        }
    }
}
