package seedu.fast.logic.parser;


import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT_TIME;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT_VENUE;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_DELETE_APPOINTMENT;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.fast.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.fast.commons.core.index.Index;
import seedu.fast.logic.commands.AppointmentCommand;
import seedu.fast.model.person.Appointment;

public class AppointmentCommandParserTest {
    private AppointmentCommandParser parser = new AppointmentCommandParser();

    private final String validDateInput = "2021-10-10";
    private final String formattedDate = "10 Oct 2021";
    private final String noAppointment = "No Appointment Scheduled Yet";

    private final String validTimeInput = "20:00";
    private final String formattedTime = "2000";
    private final String noAppointmentTime = "";

    private final String appointmentVenue = "Clementi Mall";
    private final String noAppointmentVenue = "";

    @Test
    public void parse_indexSpecifiedWithoutAddition_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + validDateInput;
        AppointmentCommand expectedCommand = new AppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(formattedDate, noAppointmentTime, noAppointmentVenue));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_indexSpecifiedWithAddition_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + validDateInput
                + " " + PREFIX_APPOINTMENT_TIME + validTimeInput
                + " " + PREFIX_APPOINTMENT_VENUE + appointmentVenue;
        AppointmentCommand expectedCommand = new AppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(formattedDate, formattedTime, appointmentVenue));
        System.out.println(userInput);
        System.out.println(expectedCommand);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_indexSpecifiedWithTime_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + validDateInput
                + " " + PREFIX_APPOINTMENT_TIME + validTimeInput;
        AppointmentCommand expectedCommand = new AppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(formattedDate, formattedTime, noAppointmentVenue));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_indexSpecifiedWithVenue_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + validDateInput
                + " " + PREFIX_APPOINTMENT_VENUE + appointmentVenue;
        AppointmentCommand expectedCommand = new AppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(formattedDate, noAppointmentTime, appointmentVenue));
        System.out.println(userInput);
        System.out.println(expectedCommand);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AppointmentCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, AppointmentCommand.COMMAND_WORD, expectedMessage);

        // no index, with date
        assertParseFailure(parser, AppointmentCommand.COMMAND_WORD + " " + PREFIX_APPOINTMENT
                + validDateInput, expectedMessage);

        // with index, no date, no time, no venue
        assertParseFailure(parser, AppointmentCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased(),
                expectedMessage);

        // with index, no date, with time
        assertParseFailure(parser, AppointmentCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased()
                + " " + PREFIX_APPOINTMENT_TIME + validTimeInput, expectedMessage);

        // with index, no date, with venue
        assertParseFailure(parser, AppointmentCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased()
                + " " + PREFIX_APPOINTMENT_VENUE + appointmentVenue, expectedMessage);

        // with index, no date, with time and venue
        assertParseFailure(parser, AppointmentCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased()
                + " " + PREFIX_APPOINTMENT_TIME + validTimeInput
                + " " + PREFIX_APPOINTMENT_VENUE + appointmentVenue, expectedMessage);
    }

    @Test
    public void parse_invalidDateFormat_failure() {
        Index targetIndex = INDEX_FIRST_PERSON;

        // wrong order
        String invalidDateInput = "10-10-2021";
        String userInput1 = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + invalidDateInput;
        String expectedMessage1 = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AppointmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput1, expectedMessage1);

        // using text
        String invalidTextInput = "10-Oct-2021";
        String userInput2 = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + invalidTextInput;
        String expectedMessage2 = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AppointmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput2, expectedMessage2);

        // without dash
        String invalidNoDashInput = "10102021";
        String userInput3 = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + invalidNoDashInput;
        String expectedMessage3 = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AppointmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput3, expectedMessage3);
    }

    @Test
    public void parse_appointmentMonthOutOfBound_failure() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String invalidMonthInput = "2021-20-01";

        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + invalidMonthInput;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AppointmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_appointmentDayOutOfBound_failure() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String invalidDayInput = "2021-10-45";

        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + invalidDayInput;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AppointmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_invalidTimeFormat_failure() {
        Index targetIndex = INDEX_FIRST_PERSON;

        // using 12-hour string-style format (i.e 8pm)
        String invalidTimeInput = "8pm";
        String userInput1 = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + validDateInput
                + " " + PREFIX_APPOINTMENT_TIME + invalidTimeInput;
        String expectedMessage1 = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AppointmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput1, expectedMessage1);

        // without semi-colon
        String userInput2 = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + validDateInput
                + " " + PREFIX_APPOINTMENT_TIME + formattedTime;
        String expectedMessage2 = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AppointmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput2, expectedMessage2);

        // specify hour only
        String invalidSingleTimeInput = "8";
        String userInput3 = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + validDateInput
                + " " + PREFIX_APPOINTMENT_TIME + invalidSingleTimeInput;
        String expectedMessage3 = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AppointmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput3, expectedMessage3);
    }

    @Test
    public void parse_appointmentHourOutOfBound_failure() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String invalidHourInput = "26:00";

        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + validDateInput
                + " " + PREFIX_APPOINTMENT_TIME + invalidHourInput;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AppointmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_appointmentMinuteOutOfBound_failure() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String invalidMinuteInput = "08:80";

        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + validDateInput
                + " " + PREFIX_APPOINTMENT_TIME + invalidMinuteInput;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AppointmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }
}
