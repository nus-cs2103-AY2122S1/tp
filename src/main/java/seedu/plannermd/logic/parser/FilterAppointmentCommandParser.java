package seedu.plannermd.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_END;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_START;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.List;

import seedu.plannermd.logic.commands.filterappointmentcommand.AppointmentFilters;
import seedu.plannermd.logic.commands.filterappointmentcommand.FilterAllAppointmentCommand;
import seedu.plannermd.logic.parser.exceptions.ParseException;
import seedu.plannermd.model.appointment.AppointmentContainsDoctorPredicate;
import seedu.plannermd.model.appointment.AppointmentContainsPatientPredicate;
import seedu.plannermd.model.appointment.AppointmentIsAfterPredicate;
import seedu.plannermd.model.appointment.AppointmentIsBeforePredicate;

public class FilterAppointmentCommandParser implements Parser<FilterAllAppointmentCommand> {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy")
            .withResolverStyle(ResolverStyle.SMART);

    private boolean hasStartFilter = false;
    private boolean hasEndFilter = false;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Parses the given {@code String} of arguments in the context of the FilterAllAppointmentCommand
     * and returns a FilterAllAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterAllAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DOCTOR,
                PREFIX_PATIENT, PREFIX_START, PREFIX_END);

        AppointmentFilters filters = AppointmentFilters.allAppointmentsFilter();
        if (argumentMultimap.getValue(PREFIX_DOCTOR).isPresent()) {
            List<String> doctorKeywords = stringToList(argumentMultimap.getValue(PREFIX_DOCTOR).get());
            filters.setHasDoctor(new AppointmentContainsDoctorPredicate(doctorKeywords));
        }
        if (argumentMultimap.getValue(PREFIX_PATIENT).isPresent()) {
            List<String> patientKeywords = stringToList(argumentMultimap.getValue(PREFIX_PATIENT).get());
            filters.setHasPatient(new AppointmentContainsPatientPredicate(patientKeywords));
        }
        if (argumentMultimap.getValue(PREFIX_START).isPresent()) {
            startDate = stringToDate(argumentMultimap.getValue(PREFIX_START).get()).atStartOfDay();
            filters.setStartAfter(new AppointmentIsAfterPredicate(startDate));
            hasStartFilter = true;
        }
        if (argumentMultimap.getValue(PREFIX_END).isPresent()) {
            endDate = stringToDate(argumentMultimap.getValue(PREFIX_END).get()).atTime( 23, 59, 59);
            filters.setStartBefore(new AppointmentIsBeforePredicate(endDate));
            hasEndFilter = true;
        }
        if (hasStartFilter && hasEndFilter && !verifyStartDateBeforeEndDate()) {
            throw new ParseException("End date cannot be before start date");
        }
        return null;
    }

    private List<String> stringToList(String string) throws ParseException {
        requireNonNull(string);
        if (string.isEmpty()) {
            throw new ParseException("Empty arguments");
        }
        String[] nameKeywords = string.trim().split("\\s+");
        return Arrays.asList(nameKeywords);
    }

    private LocalDate stringToDate(String string) throws ParseException {
        try {
            return LocalDate.parse(string.trim(), DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ParseException("Invalid date provided");

        }
    }

    private boolean verifyStartDateBeforeEndDate() {
        return startDate.isBefore(endDate);
    }
}
