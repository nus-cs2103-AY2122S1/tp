package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.meeting.ClearMeetingCommand;
import seedu.address.logic.commands.meeting.DeleteMeetingCommand;
import seedu.address.logic.commands.meeting.MeetCommand;
import seedu.address.logic.commands.meeting.QuitMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.MeetingBuilder;
import seedu.address.testutil.MeetingUtil;

public class MeetingParserTest {

    private final MeetingParser parser = new MeetingParser();

    @Test
    public void parseCommand_meet() throws Exception {
        Meeting meeting = new MeetingBuilder().build();
        MeetCommand command = (MeetCommand) parser.parseCommand(MeetingUtil.getMeetCommand(meeting));
        assertEquals(new MeetCommand(meeting), command);
    }

    @Test
    public void parseCommand_clearMeeting() throws Exception {
        assertTrue(parser.parseCommand(ClearMeetingCommand.COMMAND_WORD) instanceof ClearMeetingCommand);
        assertTrue(parser.parseCommand(ClearMeetingCommand.COMMAND_WORD + " 2") instanceof ClearMeetingCommand);
    }

    @Test
    public void parseCommand_deleteMeeting() throws Exception {
        DeleteMeetingCommand command = (DeleteMeetingCommand) parser.parseCommand(
                DeleteMeetingCommand.COMMAND_WORD + " " + INDEX_FIRST_MEETING.getOneBased());
        assertEquals(new DeleteMeetingCommand(INDEX_FIRST_MEETING), command);
    }

    @Test
    public void parseCommand_undo() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD + " 2") instanceof UndoCommand);
    }

    @Test
    public void parseCommand_quitMeeting() throws Exception {
        assertTrue(parser.parseCommand(QuitMeetingCommand.COMMAND_WORD) instanceof QuitMeetingCommand);
        assertTrue(parser.parseCommand(QuitMeetingCommand.COMMAND_WORD + " 2") instanceof QuitMeetingCommand);
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), () ->
                        parser.parseCommand(""));
    }
}
