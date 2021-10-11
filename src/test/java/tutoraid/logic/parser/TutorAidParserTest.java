package tutoraid.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutoraid.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tutoraid.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static tutoraid.testutil.Assert.assertThrows;
import static tutoraid.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import tutoraid.logic.commands.AddStudentCommand;
import tutoraid.logic.commands.ClearCommand;
import tutoraid.logic.commands.DeleteStudentCommand;
import tutoraid.logic.commands.ExitCommand;
import tutoraid.logic.commands.HelpCommand;
import tutoraid.logic.commands.ListCommand;
import tutoraid.logic.commands.PaidCommand;
import tutoraid.logic.commands.UnpaidCommand;
import tutoraid.logic.parser.exceptions.ParseException;
import tutoraid.model.student.Student;
import tutoraid.testutil.StudentBuilder;
import tutoraid.testutil.StudentUtil;

public class TutorAidParserTest {

    private final TutorAidParser parser = new TutorAidParser();

    @Test
    public void parseCommand_add() throws Exception {
        Student student = new StudentBuilder().build();
        AddStudentCommand command = (AddStudentCommand) parser.parseCommand(StudentUtil.getAddCommand(student));
        assertEquals(new AddStudentCommand(student), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteStudentCommand command = (DeleteStudentCommand) parser.parseCommand(
                DeleteStudentCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased());
        assertEquals(new DeleteStudentCommand(INDEX_FIRST_STUDENT), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_paid() throws Exception {
        PaidCommand command = (PaidCommand) parser.parseCommand(
                PaidCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased());
        assertEquals(new PaidCommand(INDEX_FIRST_STUDENT), command);
    }

    @Test
    public void parseCommand_unpaid() throws Exception {
        UnpaidCommand command = (UnpaidCommand) parser.parseCommand(
                UnpaidCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased());
        assertEquals(new UnpaidCommand(INDEX_FIRST_STUDENT), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
