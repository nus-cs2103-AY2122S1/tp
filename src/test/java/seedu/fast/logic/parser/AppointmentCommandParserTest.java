package seedu.fast.logic.parser;


import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_BOB;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_INPUT;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_TIME_AMY;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_TIME_BOB;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_TIME_FORMATTED;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_VENUE_AMY;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_VENUE_BOB;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT_TIME;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT_VENUE;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.fast.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.fast.commons.core.index.Index;
import seedu.fast.logic.commands.AppointmentCommand;
import seedu.fast.model.person.Appointment;

public class AppointmentCommandParserTest {
    private AppointmentCommandParser parser = new AppointmentCommandParser();

    @Test
    public void parse_indexSpecifiedWithoutAddition_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + VALID_APPOINTMENT_INPUT;
        AppointmentCommand expectedCommand = new AppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(VALID_APPOINTMENT_BOB, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_AMY));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_indexSpecifiedWithAddition_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + VALID_APPOINTMENT_INPUT
                + " " + PREFIX_APPOINTMENT_TIME + VALID_APPOINTMENT_TIME_BOB
                + " " + PREFIX_APPOINTMENT_VENUE + VALID_APPOINTMENT_VENUE_BOB;
        AppointmentCommand expectedCommand = new AppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(VALID_APPOINTMENT_BOB, VALID_APPOINTMENT_TIME_FORMATTED, VALID_APPOINTMENT_VENUE_BOB));
        System.out.println(userInput);
        System.out.println(expectedCommand);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_indexSpecifiedWithTime_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + VALID_APPOINTMENT_INPUT
                + " " + PREFIX_APPOINTMENT_TIME + VALID_APPOINTMENT_TIME_BOB;
        AppointmentCommand expectedCommand = new AppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(VALID_APPOINTMENT_BOB, VALID_APPOINTMENT_TIME_FORMATTED, VALID_APPOINTMENT_VENUE_AMY));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_indexSpecifiedWithVenue_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + VALID_APPOINTMENT_INPUT
                + " " + PREFIX_APPOINTMENT_VENUE + VALID_APPOINTMENT_VENUE_BOB;
        AppointmentCommand expectedCommand = new AppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(VALID_APPOINTMENT_BOB, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_BOB));
        System.out.println(userInput);
        System.out.println(expectedCommand);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_indexSpecifiedWithRepeatedFields_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + VALID_APPOINTMENT_INPUT
                + " " + PREFIX_APPOINTMENT_VENUE + VALID_APPOINTMENT_VENUE_BOB
                + " " + PREFIX_APPOINTMENT_VENUE + VALID_APPOINTMENT_VENUE_BOB
                + " " + PREFIX_APPOINTMENT_VENUE + VALID_APPOINTMENT_VENUE_BOB;
        AppointmentCommand expectedCommand = new AppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(VALID_APPOINTMENT_BOB, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_BOB));
        System.out.println(userInput);
        System.out.println(expectedCommand);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AppointmentCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, "", expectedMessage);

        // no index, with date
        assertParseFailure(parser, PREFIX_APPOINTMENT + VALID_APPOINTMENT_INPUT, expectedMessage);

        // with index, no date, no time, no venue
        assertParseFailure(parser, "" + INDEX_FIRST_PERSON.getOneBased(), expectedMessage);

        // with index, no date, with time
        assertParseFailure(parser, INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_APPOINTMENT_TIME
                + VALID_APPOINTMENT_TIME_BOB, expectedMessage);

        // with index, no date, with venue
        assertParseFailure(parser, INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_APPOINTMENT_VENUE
                + VALID_APPOINTMENT_VENUE_BOB, expectedMessage);

        // with index, no date, with time and venue
        assertParseFailure(parser, INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_APPOINTMENT_TIME
                + VALID_APPOINTMENT_TIME_BOB + " " + PREFIX_APPOINTMENT_VENUE + VALID_APPOINTMENT_VENUE_BOB,
                expectedMessage);
    }
}
