package seedu.plannermd.logic.parser.apptcommandparser;

import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_UPCOMING_APPOINTMENT_DATE_FIELD;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_END;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_START;
import static seedu.plannermd.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.plannermd.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.plannermd.logic.parser.apptcommandparser.FilterUpcomingAppointmentCommandParser.NO_ARGUMENTS_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.plannermd.logic.commands.apptcommand.AppointmentFilters;
import seedu.plannermd.logic.commands.apptcommand.FilterUpcomingAppointmentCommand;
import seedu.plannermd.testutil.appointment.AppointmentFiltersBuilder;

public class FilterUpcomingAppointmentCommandParserTest {

    private final FilterUpcomingAppointmentCommandParser parser = new FilterUpcomingAppointmentCommandParser();

    @Test
    public void parse_invalidInput_failure() {
        assertParseFailure(parser, "Hello world " + PREFIX_PATIENT + "ds",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterUpcomingAppointmentCommand.MESSAGE_USAGE));
    }

    @Test public void parse_noPrefixArguments_failure() {
        // Empty patient argument
        assertParseFailure(parser, PREFIX_PATIENT.getPrefix(), NO_ARGUMENTS_MESSAGE);
        assertParseFailure(parser, PREFIX_PATIENT + " ", NO_ARGUMENTS_MESSAGE);

        // Empty doctor argument
        assertParseFailure(parser, PREFIX_DOCTOR.getPrefix(), NO_ARGUMENTS_MESSAGE);
        assertParseFailure(parser, PREFIX_DOCTOR + " ", NO_ARGUMENTS_MESSAGE);

        // Some valid arguments with some empty arguments
        assertParseFailure(parser, PREFIX_PATIENT + "Amy " + PREFIX_DOCTOR, NO_ARGUMENTS_MESSAGE);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        AppointmentFilters filters = new AppointmentFiltersBuilder().withUpcoming().withPatientKeywords("Alice")
                .withDoctorKeywords("John").build();
        FilterUpcomingAppointmentCommand expectedCommand = new FilterUpcomingAppointmentCommand(filters);
        assertParseSuccess(parser, filters.getUpcomingFilterDetails(), expectedCommand);
        // Whitespace before and after user input
        String userInput = "   " + PREFIX_PATIENT + "Alice " + PREFIX_DOCTOR + "John   ";
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // Patient name - 1 word
        AppointmentFilters filters = new AppointmentFiltersBuilder().withUpcoming()
                .withPatientKeywords("John").build();
        FilterUpcomingAppointmentCommand expectedCommand = new FilterUpcomingAppointmentCommand(filters);
        assertParseSuccess(parser, filters.getUpcomingFilterDetails(), expectedCommand);
        String userInput = PREFIX_PATIENT + "  John"; // White spaces around name
        assertParseSuccess(parser, userInput, expectedCommand);

        // Patient name - multiple words
        filters = new AppointmentFiltersBuilder().withUpcoming().withPatientKeywords("John", "Doe").build();
        expectedCommand = new FilterUpcomingAppointmentCommand(filters);
        assertParseSuccess(parser, filters.getUpcomingFilterDetails(), expectedCommand);
        userInput = PREFIX_PATIENT + "John   Doe"; // More than 1 white space
        assertParseSuccess(parser, userInput, expectedCommand);


        // Doctor name - 1 word
        filters = new AppointmentFiltersBuilder().withUpcoming().withDoctorKeywords("John").build();
        expectedCommand = new FilterUpcomingAppointmentCommand(filters);
        assertParseSuccess(parser, filters.getUpcomingFilterDetails(), expectedCommand);
        userInput = PREFIX_DOCTOR + "  John"; // White spaces around name
        assertParseSuccess(parser, userInput, expectedCommand);

        // Doctor name - multiple words
        filters = new AppointmentFiltersBuilder().withUpcoming().withDoctorKeywords("John", "Doe").build();
        expectedCommand = new FilterUpcomingAppointmentCommand(filters);
        assertParseSuccess(parser, filters.getUpcomingFilterDetails(), expectedCommand);
        userInput = PREFIX_DOCTOR + "John   Doe"; // More than 1 white space
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_dateParametersSpecified_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_UPCOMING_APPOINTMENT_DATE_FIELD,
                FilterUpcomingAppointmentCommand.MESSAGE_USAGE);
        // Start date provided
        assertParseFailure(parser, PREFIX_PATIENT + " John " + PREFIX_START + " 30/11/2021", expectedMessage);
        // End date provided
        assertParseFailure(parser, PREFIX_DOCTOR + " Alex " + PREFIX_END + " 20/12/2021", expectedMessage);
        // Start and end date provided
        assertParseFailure(parser, PREFIX_PATIENT + " John " + PREFIX_DOCTOR + " Alex " + PREFIX_START
                + " 30/11/2021 " + PREFIX_END + "20/12/2021", expectedMessage);
    }

    @Test
    public void parse_noFieldSpecified_success() {
        // Should show all appointments
        AppointmentFilters filters = new AppointmentFiltersBuilder().withUpcoming().build();
        FilterUpcomingAppointmentCommand expectedCommand = new FilterUpcomingAppointmentCommand(filters);
        assertParseSuccess(parser, "", expectedCommand);
    }
}
