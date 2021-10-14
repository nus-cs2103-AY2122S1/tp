package seedu.fast.logic.parser;

import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_BOB;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_INPUT;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_TIME_AMY;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_TIME_BOB;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_TIME_FORMATTED;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_VENUE_BOB;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT_TIME;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT_VENUE;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.fast.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.fast.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.fast.commons.core.index.Index;
import seedu.fast.logic.commands.EditAppointmentCommand;
import seedu.fast.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.fast.testutil.EditAppointmentDescriptorBuilder;

public class EditAppointmentCommandParserTest {
    private EditAppointmentCommandParser parser = new EditAppointmentCommandParser();

    @Test
    public void parse_indexSpecifiedWithDateOnly_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + VALID_APPOINTMENT_INPUT;
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withDate(VALID_APPOINTMENT_BOB).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(INDEX_SECOND_PERSON,
                descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_indexSpecifiedWithTwoFields_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT_TIME + VALID_APPOINTMENT_TIME_BOB + " "
                + PREFIX_APPOINTMENT_VENUE + VALID_APPOINTMENT_VENUE_BOB;
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withTime(VALID_APPOINTMENT_TIME_FORMATTED).withVenue(VALID_APPOINTMENT_VENUE_BOB).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(INDEX_SECOND_PERSON,
                descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_indexSpecifiedWithAllFields_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + VALID_APPOINTMENT_INPUT + " "
                + PREFIX_APPOINTMENT_TIME + VALID_APPOINTMENT_TIME_BOB + " "
                + PREFIX_APPOINTMENT_VENUE + VALID_APPOINTMENT_VENUE_BOB;
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder().withDate(VALID_APPOINTMENT_BOB)
                .withTime(VALID_APPOINTMENT_TIME_FORMATTED).withVenue(VALID_APPOINTMENT_VENUE_BOB).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(INDEX_SECOND_PERSON,
                descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_indexSpecifiedWithRepeatedFields_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + VALID_APPOINTMENT_INPUT
                + " " + PREFIX_APPOINTMENT_TIME + VALID_APPOINTMENT_TIME_BOB + " "
                + PREFIX_APPOINTMENT_TIME + VALID_APPOINTMENT_TIME_BOB + " "
                + PREFIX_APPOINTMENT_TIME + VALID_APPOINTMENT_TIME_BOB + " "
                + PREFIX_APPOINTMENT_VENUE + VALID_APPOINTMENT_VENUE_BOB;
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withDate(VALID_APPOINTMENT_BOB).withTime(VALID_APPOINTMENT_TIME_FORMATTED)
                .withVenue(VALID_APPOINTMENT_VENUE_BOB).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(INDEX_SECOND_PERSON,
                descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_indexSpecifiedWithSpacesFields_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + VALID_APPOINTMENT_INPUT
                + " " + PREFIX_APPOINTMENT_TIME + " " + " "
                + PREFIX_APPOINTMENT_VENUE + VALID_APPOINTMENT_VENUE_BOB;
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withDate(VALID_APPOINTMENT_BOB).withTime(VALID_APPOINTMENT_TIME_AMY)
                .withVenue(VALID_APPOINTMENT_VENUE_BOB).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(INDEX_SECOND_PERSON,
                descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditAppointmentCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, EditAppointmentCommand.COMMAND_WORD, expectedMessage);

        // no index, with date
        assertParseFailure(parser, EditAppointmentCommand.COMMAND_WORD + " " + PREFIX_APPOINTMENT
                + VALID_APPOINTMENT_INPUT, expectedMessage);

        // with index, no date, no time, no venue
        assertParseFailure(parser, EditAppointmentCommand.COMMAND_WORD + " "
                        + INDEX_FIRST_PERSON.getOneBased(), expectedMessage);

        // with index, with date prefix, blank date
        assertParseFailure(parser, EditAppointmentCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_APPOINTMENT + " ", expectedMessage);

    }
}
