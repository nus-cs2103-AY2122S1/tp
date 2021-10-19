package seedu.plannermd.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_PATIENT;

import java.util.Arrays;
import java.util.List;

import seedu.plannermd.logic.commands.apptcommand.AppointmentFilters;
import seedu.plannermd.logic.commands.apptcommand.FilterUpcomingAppointmentCommand;
import seedu.plannermd.logic.parser.exceptions.ParseException;
import seedu.plannermd.model.appointment.AppointmentContainsDoctorPredicate;
import seedu.plannermd.model.appointment.AppointmentContainsPatientPredicate;

public class FilterUpcomingAppointmentCommandParser implements
        Parser<FilterUpcomingAppointmentCommand> {

    private String messageToUser = "Filtering all upcoming appointments according to these arguments:\n";

    /**
     * Parses the given {@code String} of arguments in the context of the FilterUpcomingAppointmentCommand
     * and returns a FilterUpcomingAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterUpcomingAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DOCTOR, PREFIX_PATIENT);

        AppointmentFilters filters = AppointmentFilters.upcomingAppointmentsFilter();
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
        return new FilterUpcomingAppointmentCommand(filters, messageToUser);
    }

    private List<String> stringToList(String string) throws ParseException {
        requireNonNull(string);
        if (string.isEmpty()) {
            throw new ParseException("Empty arguments");
        }
        String[] nameKeywords = string.trim().split("\\s+");
        return Arrays.asList(nameKeywords);
    }
}
