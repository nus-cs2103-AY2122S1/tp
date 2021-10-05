package seedu.fast.logic.parser;


import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.fast.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.fast.commons.core.index.Index;
import seedu.fast.logic.commands.AppointmentCommand;
import seedu.fast.model.person.Appointment;

public class AppointmentCommandParserTest {
    private AppointmentCommandParser parser = new AppointmentCommandParser();
    private final String appt = "2021-10-10";
    private final String invalidFormatAppt = "10-10-2021";
    private final String invalidApptMonth = "2021-20-01";
    private final String invalidApptDay = "2021-10-45";
    private final String formattedAppt = "10 Oct 2021";
    private final String noApptSceduled = "No Appointment Scheduled Yet";
    private final String deleteCommand = "delete";

    @Test
    public void parse_indexSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + appt;
        AppointmentCommand expectedCommand = new AppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(formattedAppt));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AppointmentCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, AppointmentCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, AppointmentCommand.COMMAND_WORD + " " + appt, expectedMessage);
    }

    @Test
    public void parse_invalidDateFormat_failure() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + invalidFormatAppt;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AppointmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_appointmentMonthOutOfBound_failure() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + invalidApptMonth;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AppointmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_appointmentDayOutOfBound_failure() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + invalidApptDay;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AppointmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_appointmentDelete_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + deleteCommand;
        AppointmentCommand expectedCommand = new AppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(noApptSceduled));
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
