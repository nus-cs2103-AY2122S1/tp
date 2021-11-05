package seedu.plannermd.logic.parser.apptcommandparser;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_END;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_START;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import seedu.plannermd.logic.commands.apptcommand.AppointmentFilters;
import seedu.plannermd.logic.commands.apptcommand.FilterAppointmentCommand;
import seedu.plannermd.logic.parser.ArgumentMultimap;
import seedu.plannermd.logic.parser.ArgumentTokenizer;
import seedu.plannermd.logic.parser.Parser;
import seedu.plannermd.logic.parser.ParserUtil;
import seedu.plannermd.logic.parser.exceptions.ParseException;
import seedu.plannermd.model.appointment.AppointmentContainsDoctorPredicate;
import seedu.plannermd.model.appointment.AppointmentContainsPatientPredicate;
import seedu.plannermd.model.appointment.AppointmentIsAfterPredicate;
import seedu.plannermd.model.appointment.AppointmentIsBeforePredicate;

/**
 * Parses input arguments and creates a new FilterAppointmentCommand object.
 */
public class FilterAppointmentCommandParser implements Parser<FilterAppointmentCommand> {

    public static final String END_DATE_BEFORE_START_DATE_MESSAGE = "End date cannot be before start date.";
    public static final String NO_ARGUMENTS_MESSAGE = "Specified parameters cannot be empty.\n"
            + FilterAppointmentCommand.MESSAGE_USAGE;

    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Parses the given {@code String} of arguments in the context of the FilterAppointmentCommand
     * and returns a FilterAppointmentCommand object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format
     */
    public FilterAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(" " + args,
                PREFIX_DOCTOR, PREFIX_PATIENT, PREFIX_START, PREFIX_END);

        AppointmentFilters filters = AppointmentFilters.allAppointmentsFilter();

        boolean hasStartFilter = false;
        boolean hasEndFilter = false;

        if (!argumentMultimap.getPreamble().isEmpty()) {
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
            startDate = ParserUtil.stringToDate(argumentMultimap.getValue(PREFIX_START).get());
            filters.setStartAfter(new AppointmentIsAfterPredicate(startDate));
            hasStartFilter = true;
        }
        if (argumentMultimap.getValue(PREFIX_END).isPresent()) {
            endDate = ParserUtil.stringToDate(argumentMultimap.getValue(PREFIX_END).get());
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
        String[] nameKeywords = string.trim().split("\\s+"); // Splits the string based on 1 or more whitespace
        return Arrays.asList(nameKeywords);
    }

    private boolean verifyStartDateBeforeEndDate() {
        return !startDate.isAfter(endDate);
    }
}
