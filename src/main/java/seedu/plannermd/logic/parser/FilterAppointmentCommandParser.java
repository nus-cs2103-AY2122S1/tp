package seedu.plannermd.logic.parser;

import static java.util.Objects.requireNonNull;
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

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy")
            .withResolverStyle(ResolverStyle.SMART);

    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy")
            .withResolverStyle(ResolverStyle.SMART);

    private boolean hasStartFilter = false;
    private boolean hasEndFilter = false;
    private LocalDate startDate;
    private LocalDate endDate;
    private String messageToUser = "Filtering all appointments according to these arguments:\n";

    /**
     * Parses the given {@code String} of arguments in the context of the FilterAllAppointmentCommand
     * and returns a FilterAllAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DOCTOR,
                PREFIX_PATIENT, PREFIX_START, PREFIX_END);

        AppointmentFilters filters = AppointmentFilters.allAppointmentsFilter();
        if (argumentMultimap.getValue(PREFIX_DOCTOR).isPresent()) {
            String doctorKeywords = argumentMultimap.getValue(PREFIX_DOCTOR).get();
            filters.setHasDoctor(new AppointmentContainsDoctorPredicate(stringToList(doctorKeywords)));
            messageToUser += "Doctor: " + doctorKeywords + " ";
        }
        if (argumentMultimap.getValue(PREFIX_PATIENT).isPresent()) {
            String patientKeywords = argumentMultimap.getValue(PREFIX_PATIENT).get();
            filters.setHasPatient(new AppointmentContainsPatientPredicate(stringToList(patientKeywords)));
            messageToUser += "Patient: " + patientKeywords + " ";
        }
        if (argumentMultimap.getValue(PREFIX_START).isPresent()) {
            startDate = stringToDate(argumentMultimap.getValue(PREFIX_START).get());
            filters.setStartAfter(new AppointmentIsAfterPredicate(startDate));
            hasStartFilter = true;
            messageToUser += "Starting from: " + startDate.format(DISPLAY_FORMATTER) + " ";
        }
        if (argumentMultimap.getValue(PREFIX_END).isPresent()) {
            endDate = stringToDate(argumentMultimap.getValue(PREFIX_END).get());
            filters.setStartBefore(new AppointmentIsBeforePredicate(endDate));
            hasEndFilter = true;
            messageToUser += "Ending at: " + endDate.format(DISPLAY_FORMATTER) + " ";
        }
        if (hasStartFilter && hasEndFilter && !verifyStartDateBeforeEndDate()) {
            throw new ParseException("End date cannot be before start date");
        }
        return new FilterAppointmentCommand(filters, messageToUser);
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
