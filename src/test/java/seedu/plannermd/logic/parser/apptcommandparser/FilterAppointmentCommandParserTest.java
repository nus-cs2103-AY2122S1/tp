package seedu.plannermd.logic.parser.apptcommandparser;

import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plannermd.logic.commands.CommandTestUtil.FILTER_VALID_END_DATE;
import static seedu.plannermd.logic.commands.CommandTestUtil.FILTER_VALID_START_DATE;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_STRING_END_DATE;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_STRING_START_DATE;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_END;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_START;
import static seedu.plannermd.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.plannermd.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.plannermd.logic.parser.apptcommandparser.FilterAppointmentCommandParser.END_DATE_BEFORE_START_DATE_MESSAGE;
import static seedu.plannermd.logic.parser.apptcommandparser.FilterAppointmentCommandParser.NO_ARGUMENTS_MESSAGE;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.plannermd.logic.commands.apptcommand.AppointmentFilters;
import seedu.plannermd.logic.commands.apptcommand.FilterAppointmentCommand;
import seedu.plannermd.model.appointment.AppointmentDate;
import seedu.plannermd.testutil.appointment.AppointmentFiltersBuilder;

public class FilterAppointmentCommandParserTest {

    private final FilterAppointmentCommandParser parser = new FilterAppointmentCommandParser();

    @Test
    public void parse_invalidInput_failure() {
        assertParseFailure(parser, "Hello world " + PREFIX_PATIENT + "ds",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterAppointmentCommand.MESSAGE_USAGE));
    }

    @Test public void parse_noPrefixArguments_failure() {
        // Empty patient argument
        assertParseFailure(parser, PREFIX_PATIENT.getPrefix(), NO_ARGUMENTS_MESSAGE);
        assertParseFailure(parser, PREFIX_PATIENT + " ", NO_ARGUMENTS_MESSAGE);

        // Empty doctor argument
        assertParseFailure(parser, PREFIX_DOCTOR.getPrefix(), NO_ARGUMENTS_MESSAGE);
        assertParseFailure(parser, PREFIX_DOCTOR + " ", NO_ARGUMENTS_MESSAGE);

        // Empty startDate argument
        assertParseFailure(parser, PREFIX_START.getPrefix(), NO_ARGUMENTS_MESSAGE);
        assertParseFailure(parser, PREFIX_START + " ", NO_ARGUMENTS_MESSAGE);

        // Empty endDate argument
        assertParseFailure(parser, PREFIX_END.getPrefix(), NO_ARGUMENTS_MESSAGE);
        assertParseFailure(parser, PREFIX_END + " ", NO_ARGUMENTS_MESSAGE);

        // Some valid arguments with some empty arguments
        assertParseFailure(parser, PREFIX_PATIENT + "Amy " + PREFIX_START, NO_ARGUMENTS_MESSAGE);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        AppointmentFilters filters = new AppointmentFiltersBuilder().withPatientKeywords("Alice")
                .withDoctorKeywords("John").withStartDate(FILTER_VALID_START_DATE)
                .withEndDate(FILTER_VALID_END_DATE).build();
        FilterAppointmentCommand expectedCommand = new FilterAppointmentCommand(filters);
        assertParseSuccess(parser, filters.getFilterDetails(), expectedCommand);
        // Whitespace before and after user input
        String userInput = "   " + PREFIX_PATIENT + "Alice " + PREFIX_DOCTOR + "John " + PREFIX_START
                + VALID_STRING_START_DATE + " " + PREFIX_END + VALID_STRING_END_DATE + " ";
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        // Patient name and start date
        AppointmentFilters filters = new AppointmentFiltersBuilder().withPatientKeywords("Alice", "Doe")
                .withStartDate(FILTER_VALID_START_DATE).build();
        FilterAppointmentCommand expectedCommand = new FilterAppointmentCommand(filters);
        assertParseSuccess(parser, filters.getFilterDetails(), expectedCommand);

        // Start date and end date
        filters = new AppointmentFiltersBuilder().withStartDate(FILTER_VALID_START_DATE)
                .withEndDate(FILTER_VALID_END_DATE).build();
        expectedCommand = new FilterAppointmentCommand(filters);
        assertParseSuccess(parser, filters.getFilterDetails(), expectedCommand);

        // Same start date and end date -> should filter all appointments on that day
        filters = new AppointmentFiltersBuilder().withStartDate(FILTER_VALID_START_DATE)
                .withEndDate(FILTER_VALID_START_DATE).build();
        expectedCommand = new FilterAppointmentCommand(filters);
        assertParseSuccess(parser, filters.getFilterDetails(), expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // Patient name - 1 word
        AppointmentFilters filters = new AppointmentFiltersBuilder().withPatientKeywords("John").build();
        FilterAppointmentCommand expectedCommand = new FilterAppointmentCommand(filters);
        assertParseSuccess(parser, filters.getFilterDetails(), expectedCommand);
        String userInput = PREFIX_PATIENT + "  John"; // White spaces around name
        assertParseSuccess(parser, userInput, expectedCommand);


        // Patient name - multiple words
        filters = new AppointmentFiltersBuilder().withPatientKeywords("John", "Doe").build();
        expectedCommand = new FilterAppointmentCommand(filters);
        assertParseSuccess(parser, filters.getFilterDetails(), expectedCommand);
        userInput = PREFIX_PATIENT + "John   Doe"; // More than 1 white space
        assertParseSuccess(parser, userInput, expectedCommand);


        // Doctor name - 1 word
        filters = new AppointmentFiltersBuilder().withDoctorKeywords("John").build();
        expectedCommand = new FilterAppointmentCommand(filters);
        assertParseSuccess(parser, filters.getFilterDetails(), expectedCommand);
        userInput = PREFIX_DOCTOR + "  John"; // White spaces around name
        assertParseSuccess(parser, userInput, expectedCommand);

        // Doctor name - multiple words
        filters = new AppointmentFiltersBuilder().withDoctorKeywords("John", "Doe").build();
        expectedCommand = new FilterAppointmentCommand(filters);
        assertParseSuccess(parser, filters.getFilterDetails(), expectedCommand);
        userInput = PREFIX_DOCTOR + "John   Doe"; // More than 1 white space
        assertParseSuccess(parser, userInput, expectedCommand);

        // With valid start date
        filters = new AppointmentFiltersBuilder().withStartDate(FILTER_VALID_START_DATE).build();
        expectedCommand = new FilterAppointmentCommand(filters);
        assertParseSuccess(parser, filters.getFilterDetails(), expectedCommand);
        userInput = PREFIX_START + " " + VALID_STRING_START_DATE; // Whitespace before startDate
        assertParseSuccess(parser, userInput, expectedCommand);
        userInput = PREFIX_START + "2/8/2021"; // Single digit date and month
        filters = new AppointmentFiltersBuilder()
                .withStartDate(LocalDate.of(2021, 8, 2)).build();
        expectedCommand = new FilterAppointmentCommand(filters);
        assertParseSuccess(parser, userInput, expectedCommand);

        // With valid end date
        filters = new AppointmentFiltersBuilder().withEndDate(FILTER_VALID_END_DATE).build();
        expectedCommand = new FilterAppointmentCommand(filters);
        assertParseSuccess(parser, filters.getFilterDetails(), expectedCommand);
        userInput = PREFIX_END + " " + VALID_STRING_END_DATE + "  "; // Whitespace before endDate
        assertParseSuccess(parser, userInput, expectedCommand);
        userInput = PREFIX_END + "2/8/2021"; // Single digit date and month
        filters = new AppointmentFiltersBuilder()
                .withEndDate(LocalDate.of(2021, 8, 2)).build();
        expectedCommand = new FilterAppointmentCommand(filters);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_noFieldSpecified_success() {
        // Should show all appointments
        AppointmentFilters filters = new AppointmentFiltersBuilder().build();
        FilterAppointmentCommand expectedCommand = new FilterAppointmentCommand(filters);
        assertParseSuccess(parser, "", expectedCommand);
    }

    @Test
    public void parse_invalidDates_failure() {
        // Invalid start date format
        assertParseFailure(parser, PREFIX_START + "31/06/2021", AppointmentDate.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, PREFIX_START + "6/20/2021", AppointmentDate.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, PREFIX_START + "16/02", AppointmentDate.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, PREFIX_START + "06/20/21", AppointmentDate.MESSAGE_CONSTRAINTS); //2 digit year

        // Invalid end date format
        assertParseFailure(parser, PREFIX_END + "31/06/2021", AppointmentDate.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, PREFIX_END + "6/20/2021", AppointmentDate.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, PREFIX_END + "16/02", AppointmentDate.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, PREFIX_END + "06/20/21", AppointmentDate.MESSAGE_CONSTRAINTS); //2 digit year

        // End date before start date
        assertParseFailure(parser, PREFIX_START + "12/12/2021 " + PREFIX_END + "01/02/2021",
                END_DATE_BEFORE_START_DATE_MESSAGE);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = PREFIX_PATIENT + "Amy " + PREFIX_DOCTOR + "Bob " + PREFIX_START + "19/10/2021 "
                + PREFIX_END + "20/10/2021 " + PREFIX_PATIENT + "Alice " + PREFIX_DOCTOR + "John "
                + PREFIX_START + VALID_STRING_START_DATE + " " + PREFIX_END + VALID_STRING_END_DATE;
        AppointmentFilters filters = new AppointmentFiltersBuilder().withPatientKeywords("Alice")
                .withDoctorKeywords("John").withStartDate(FILTER_VALID_START_DATE)
                .withEndDate(FILTER_VALID_END_DATE).build();
        FilterAppointmentCommand expectedCommand = new FilterAppointmentCommand(filters);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // No other valid values specified
        String userInput = PREFIX_START + "19/15/2021 " + PREFIX_START + VALID_STRING_START_DATE;
        AppointmentFilters filters = new AppointmentFiltersBuilder().withStartDate(FILTER_VALID_START_DATE).build();
        FilterAppointmentCommand expectedCommand = new FilterAppointmentCommand(filters);
        assertParseSuccess(parser, userInput, expectedCommand);

        // Other valid fields specified
        userInput = PREFIX_PATIENT + "Johnny " + PREFIX_END + "19/15/2022 " + PREFIX_END + VALID_STRING_END_DATE;
        filters = new AppointmentFiltersBuilder().withPatientKeywords("Johnny")
                .withEndDate(FILTER_VALID_END_DATE).build();
        expectedCommand = new FilterAppointmentCommand(filters);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
