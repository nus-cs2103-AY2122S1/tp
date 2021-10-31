package seedu.plannermd.logic.parser.apptcommandparser;

import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plannermd.commons.core.Messages.MESSAGE_UNKNOWN_FLAG;
import static seedu.plannermd.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.plannermd.logic.commands.apptcommand.AppointmentCommand;
import seedu.plannermd.logic.parser.exceptions.ParseException;

class AppointmentCommandParserTest {

    private final AppointmentCommandParser parser = new AppointmentCommandParser();

    //Appointment command integration tests
    @Test
    public void parseAppointmentCommand_addAppointmentCommand() throws Exception {
        //TODO
    }

    @Test
    public void parseAppointmentCommand_deleteAppointmentCommand() throws Exception {
        //TODO
    }

    @Test
    public void parseAppointmentCommand_editAppointmentCommand() throws Exception {
        //TODO
    }

    @Test
    public void parseAppointmentCommand_filterAppointmentCommand() throws Exception {
        //TODO
    }

    @Test
    public void parseAppointmentCommand_filterUpcomingAppointmentCommand() throws Exception {
        //TODO
    }

    @Test
    public void parseAppointmentCommand_listAppointmentCommand() throws Exception {
        //TODO
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AppointmentCommand.MESSAGE_USAGE), () -> parser.parseAppointmentCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_FLAG, (
        )-> parser.parseAppointmentCommand("unknownCommand"));
    }
}
