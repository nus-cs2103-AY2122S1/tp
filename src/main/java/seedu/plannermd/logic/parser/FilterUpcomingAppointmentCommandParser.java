package seedu.plannermd.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_PATIENT;

import java.util.Arrays;
import java.util.List;

import seedu.plannermd.logic.commands.filterappointmentcommand.AppointmentFilters;
import seedu.plannermd.logic.commands.filterappointmentcommand.FilterUpcomingAppointmentCommand;
import seedu.plannermd.logic.parser.exceptions.ParseException;
import seedu.plannermd.model.appointment.AppointmentContainsDoctorPredicate;
import seedu.plannermd.model.appointment.AppointmentContainsPatientPredicate;

public class FilterUpcomingAppointmentCommandParser implements
        Parser<FilterUpcomingAppointmentCommand> {

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
            List<String> doctorKeywords = stringToList(argumentMultimap.getValue(PREFIX_DOCTOR).get());
            filters.setHasDoctor(new AppointmentContainsDoctorPredicate(doctorKeywords));
        }
        if (argumentMultimap.getValue(PREFIX_PATIENT).isPresent()) {
            List<String> patientKeywords = stringToList(argumentMultimap.getValue(PREFIX_PATIENT).get());
            filters.setHasPatient(new AppointmentContainsPatientPredicate(patientKeywords));
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
}
