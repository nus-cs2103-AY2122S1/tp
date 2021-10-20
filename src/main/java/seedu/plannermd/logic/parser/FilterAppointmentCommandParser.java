package seedu.plannermd.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_END;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_START;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.List;

import seedu.plannermd.logic.commands.apptcommand.AppointmentFilters;
import seedu.plannermd.logic.commands.apptcommand.FilterAppointmentCommand;
import seedu.plannermd.logic.parser.exceptions.ParseException;
import seedu.plannermd.model.appointment.AppointmentContainsDoctorPredicate;
import seedu.plannermd.model.appointment.AppointmentContainsPatientPredicate;
import seedu.plannermd.model.appointment.AppointmentIsAfterPredicate;
import seedu.plannermd.model.appointment.AppointmentIsBeforePredicate;

public class FilterAppointmentCommandParser implements Parser<FilterAppointmentCommand> {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

    private static final String DATE_CONSTRAINTS = "Dates should be of the format DD/MM/YYYY "
            + "and adhere to the following constraints:\n"
            + "1. Day must be between 1-31 (0 in front of single digit is optional)\n"
            + "2. Month must be between 1-12 (0 in front of single digit is optional)\n"
            + "3. Year must be 4 characters.";

    public static final String INVALID_DATE_MESSAGE = "Invalid date provided.\n" + DATE_CONSTRAINTS;
    public static final String END_DATE_BEFORE_START_DATE_MESSAGE = "End date cannot be before start date.";
    public static final String NO_ARGUMENTS_MESSAGE = "No arguments provided.\n"
            + FilterAppointmentCommand.MESSAGE_USAGE;
    private static final String UNUSED_PREAMBLE = "0 ";

    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Parses the given {@code String} of arguments in the context of the FilterAllAppointmentCommand
     * and returns a FilterAllAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(UNUSED_PREAMBLE + args,
                PREFIX_DOCTOR, PREFIX_PATIENT, PREFIX_START, PREFIX_END);

        AppointmentFilters filters = AppointmentFilters.allAppointmentsFilter();

        boolean hasStartFilter = false;
        boolean hasEndFilter = false;

        if (!argumentMultimap.getPreamble().equals("0")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterAppointmentCommand.MESSAGE_USAGE));
        }
        if (argumentMultimap.getValue(PREFIX_DOCTOR).isPresent()) {
            String doctorKeywords = argumentMultimap.getValue(PREFIX_DOCTOR).get();
            filters.setHasDoctor(new AppointmentContainsDoctorPredicate(stringToList(doctorKeywords)));
        }
        if (argumentMultimap.getValue(PREFIX_PATIENT).isPresent()) {
            String patientKeywords = argumentMultimap.getValue(PREFIX_PATIENT).get();
            filters.setHasPatient(new AppointmentContainsPatientPredicate(stringToList(patientKeywords)));
        }
        if (argumentMultimap.getValue(PREFIX_START).isPresent()) {
            startDate = stringToDate(argumentMultimap.getValue(PREFIX_START).get());
            filters.setStartAfter(new AppointmentIsAfterPredicate(startDate));
            hasStartFilter = true;
        }
        if (argumentMultimap.getValue(PREFIX_END).isPresent()) {
            endDate = stringToDate(argumentMultimap.getValue(PREFIX_END).get());
            filters.setStartBefore(new AppointmentIsBeforePredicate(endDate));
            hasEndFilter = true;
        }
        if (hasStartFilter && hasEndFilter && !verifyStartDateBeforeEndDate()) {
            throw new ParseException(END_DATE_BEFORE_START_DATE_MESSAGE);
        }
        return new FilterAppointmentCommand(filters);
    }

    private List<String> stringToList(String string) throws ParseException {
        requireNonNull(string);
        if (string.trim().isEmpty()) {
            throw new ParseException(NO_ARGUMENTS_MESSAGE);
        }
        String[] nameKeywords = string.trim().split("\\s+");
        return Arrays.asList(nameKeywords);
    }

    private LocalDate stringToDate(String string) throws ParseException {
        requireNonNull(string);
        if (string.trim().isEmpty()) {
            throw new ParseException(NO_ARGUMENTS_MESSAGE);
        }

        try {
            return LocalDate.parse(string.trim(), DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ParseException(INVALID_DATE_MESSAGE);

        }
    }

    private boolean verifyStartDateBeforeEndDate() {
        return !startDate.isAfter(endDate);
    }
}
