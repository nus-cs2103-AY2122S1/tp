package seedu.plannermd.logic.parser;

import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.plannermd.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.plannermd.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.plannermd.logic.parser.FilterUpcomingAppointmentCommandParser.NO_ARGUMENTS_MESSAGE;

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
        String userInput = PREFIX_PATIENT + "Alice " + PREFIX_DOCTOR + "John";
        AppointmentFilters filters = new AppointmentFiltersBuilder().withUpcoming().withPatientKeywords("Alice")
                .withDoctorKeywords("John").build();
        FilterUpcomingAppointmentCommand expectedCommand = new FilterUpcomingAppointmentCommand(filters);
        assertParseSuccess(parser, userInput, expectedCommand);
        // Whitespace before and after user input
        userInput = "   " + PREFIX_PATIENT + "Alice " + PREFIX_DOCTOR + "John  ";
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // Patient name - 1 word
        String userInput = PREFIX_PATIENT + "John";
        AppointmentFilters filters = new AppointmentFiltersBuilder().withUpcoming()
                .withPatientKeywords("John").build();
        FilterUpcomingAppointmentCommand expectedCommand = new FilterUpcomingAppointmentCommand(filters);
        assertParseSuccess(parser, userInput, expectedCommand);
        userInput = PREFIX_PATIENT + "  John"; // White spaces around name
        assertParseSuccess(parser, userInput, expectedCommand);

        // Patient name - multiple words
        userInput = PREFIX_PATIENT + "John Doe";
        filters = new AppointmentFiltersBuilder().withUpcoming().withPatientKeywords("John", "Doe").build();
        expectedCommand = new FilterUpcomingAppointmentCommand(filters);
        assertParseSuccess(parser, userInput, expectedCommand);
        userInput = PREFIX_PATIENT + "John   Doe"; // More than 1 white space
        assertParseSuccess(parser, userInput, expectedCommand);


        // Doctor name - 1 word
        userInput = PREFIX_DOCTOR + "John";
        filters = new AppointmentFiltersBuilder().withUpcoming().withDoctorKeywords("John").build();
        expectedCommand = new FilterUpcomingAppointmentCommand(filters);
        assertParseSuccess(parser, userInput, expectedCommand);
        userInput = PREFIX_DOCTOR + "  John"; // White spaces around name
        assertParseSuccess(parser, userInput, expectedCommand);

        // Doctor name - multiple words
        userInput = PREFIX_DOCTOR + "John Doe";
        filters = new AppointmentFiltersBuilder().withUpcoming().withDoctorKeywords("John", "Doe").build();
        expectedCommand = new FilterUpcomingAppointmentCommand(filters);
        assertParseSuccess(parser, userInput, expectedCommand);
        userInput = PREFIX_DOCTOR + "John   Doe"; // More than 1 white space
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_NoFieldSpecified_success() {
        // Should show all appointments
        AppointmentFilters filters = new AppointmentFiltersBuilder().withUpcoming().build();
        FilterUpcomingAppointmentCommand expectedCommand = new FilterUpcomingAppointmentCommand(filters);
        assertParseSuccess(parser, "", expectedCommand);
    }
}
