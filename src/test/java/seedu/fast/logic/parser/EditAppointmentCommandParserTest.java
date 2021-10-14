package seedu.fast.logic.parser;

import seedu.fast.commons.core.index.Index;
import seedu.fast.logic.commands.EditAppointmentCommand;

import org.junit.jupiter.api.Test;

import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT_TIME;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT_VENUE;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.fast.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.fast.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import seedu.fast.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.fast.testutil.EditAppointmentDescriptorBuilder;

public class EditAppointmentCommandParserTest {
    private EditAppointmentCommandParser parser = new EditAppointmentCommandParser();

    private final String validDateInput = "2021-10-10";
    private final String formattedDate = "10 Oct 2021";
    private final String noAppointment = "No Appointment Scheduled Yet";

    private final String validTimeInput = "20:00";
    private final String formattedTime = "2000";
    private final String noAppointmentTime = "";

    private final String appointmentVenue = "Clementi Mall";
    private final String noAppointmentVenue = "";

    @Test
    public void parse_indexSpecifiedWithDateOnly_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + validDateInput;
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder().withDate(formattedDate).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(INDEX_SECOND_PERSON,
                descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_indexSpecifiedWithTwoFields_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT_TIME + validTimeInput + " "
                + PREFIX_APPOINTMENT_VENUE + appointmentVenue;
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder().withTime(formattedTime).
                withVenue(appointmentVenue).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(INDEX_SECOND_PERSON,
                descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_indexSpecifiedWithAllFields_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + validDateInput + " "
                + PREFIX_APPOINTMENT_TIME + validTimeInput + " "
                + PREFIX_APPOINTMENT_VENUE + appointmentVenue;
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder().withDate(formattedDate)
                .withTime(formattedTime).withVenue(appointmentVenue).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(INDEX_SECOND_PERSON,
                descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_indexSpecifiedWithRepeatedFields_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT + validDateInput + " "
                + PREFIX_APPOINTMENT_TIME + validTimeInput + " "
                + PREFIX_APPOINTMENT_TIME + validTimeInput + " "
                + PREFIX_APPOINTMENT_TIME + validTimeInput + " "
                + PREFIX_APPOINTMENT_VENUE + appointmentVenue;
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder().withDate(formattedDate)
                .withTime(formattedTime).withVenue(appointmentVenue).build();
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
                + validDateInput, expectedMessage);

        // with index, no date, no time, no venue
        assertParseFailure(parser, EditAppointmentCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased(),
                expectedMessage);

    }
}
