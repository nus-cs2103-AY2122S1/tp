package seedu.fast.logic.parser;

import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.fast.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.fast.commons.core.index.Index;
import seedu.fast.logic.commands.DeleteAppointmentCommand;
import seedu.fast.model.person.Appointment;

public class DeleteAppointmentCommandParserTest {
    private DeleteAppointmentCommandParser parser = new DeleteAppointmentCommandParser();

    private final String validDateInput = "2021-10-10";
    private final String noAppointment = "No Appointment Scheduled Yet";
    private final String noAppointmentTime = "";
    private final String noAppointmentVenue = "";

    @Test
    public void parse_indexSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = String.valueOf(targetIndex.getOneBased());
        DeleteAppointmentCommand expectedCommand = new DeleteAppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(noAppointment, noAppointmentTime, noAppointmentVenue));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteAppointmentCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, DeleteAppointmentCommand.COMMAND_WORD, expectedMessage);
    }

    @Test
    public void parse_additionalField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteAppointmentCommand.MESSAGE_USAGE);

        assertParseFailure(parser, DeleteAppointmentCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_APPOINTMENT + validDateInput, expectedMessage);
    }
}
