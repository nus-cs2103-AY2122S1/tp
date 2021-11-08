package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.commands.ClearAllCommand;
import seedu.address.logic.commands.ClearGroupsCommand;
import seedu.address.logic.commands.ClearStudentsCommand;
import seedu.address.logic.commands.ClearTasksCommand;
import seedu.address.logic.commands.DeleteStudentCommand;
import seedu.address.logic.commands.EditStudentCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindGroupCommand;
import seedu.address.logic.commands.FindStudentCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListGroupCommand;
import seedu.address.logic.commands.ListStudentCommand;
import seedu.address.logic.commands.ListTaskCommand;
import seedu.address.logic.commands.MarkStudentAttCommand;
import seedu.address.logic.commands.MarkStudentPartCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.GroupContainsKeywordsPredicate;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Participation;
import seedu.address.model.student.Student;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.StudentUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_listStudent() throws Exception {
        assertTrue(parser.parseCommand(ListStudentCommand.COMMAND_WORD) instanceof ListStudentCommand);
        assertTrue(parser.parseCommand(ListStudentCommand.COMMAND_WORD + " 3") instanceof ListStudentCommand);
    }

    @Test
    public void parseCommand_listGroup() throws Exception {
        assertTrue(parser.parseCommand(ListGroupCommand.COMMAND_WORD) instanceof ListGroupCommand);
        assertTrue(parser.parseCommand(ListGroupCommand.COMMAND_WORD + " 3") instanceof ListGroupCommand);
    }

    @Test
    public void parseCommand_listTask() throws Exception {
        assertTrue(parser.parseCommand(ListTaskCommand.COMMAND_WORD) instanceof ListTaskCommand);
        assertTrue(parser.parseCommand(ListTaskCommand.COMMAND_WORD + " 3") instanceof ListTaskCommand);
    }

    @Test
    public void parseCommand_addStudent() throws Exception {
        Student student = new StudentBuilder().build();
        AddStudentCommand command = (AddStudentCommand) parser.parseCommand(StudentUtil.getAddCommand(student));
        assertEquals(new AddStudentCommand(student), command);
    }

    @Test
    public void parseCommand_clearAll() throws Exception {
        assertTrue(parser.parseCommand(ClearAllCommand.COMMAND_WORD) instanceof ClearAllCommand);
        assertTrue(parser.parseCommand(ClearAllCommand.COMMAND_WORD + " 3") instanceof ClearAllCommand);
    }

    @Test
    public void parseCommand_clearStudents() throws Exception {
        assertTrue(parser.parseCommand(ClearStudentsCommand.COMMAND_WORD) instanceof ClearStudentsCommand);
        assertTrue(parser.parseCommand(ClearStudentsCommand.COMMAND_WORD + " 3") instanceof ClearStudentsCommand);
    }

    @Test
    public void parseCommand_clearGroups() throws Exception {
        assertTrue(parser.parseCommand(ClearGroupsCommand.COMMAND_WORD) instanceof ClearGroupsCommand);
        assertTrue(parser.parseCommand(ClearGroupsCommand.COMMAND_WORD + " 3") instanceof ClearGroupsCommand);
    }

    @Test
    public void parseCommand_clearTasks() throws Exception {
        assertTrue(parser.parseCommand(ClearTasksCommand.COMMAND_WORD) instanceof ClearTasksCommand);
        assertTrue(parser.parseCommand(ClearTasksCommand.COMMAND_WORD + " 3") instanceof ClearTasksCommand);
    }

    @Test
    public void parseCommand_deleteStudent() throws Exception {
        DeleteStudentCommand command = (DeleteStudentCommand) parser.parseCommand(
                DeleteStudentCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased());
        assertEquals(new DeleteStudentCommand(INDEX_FIRST_STUDENT), command);
    }

    @Test
    public void parseCommand_editStudent() throws Exception {
        Student student = new StudentBuilder().build();
        EditStudentCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(student).build();
        EditStudentCommand command = (EditStudentCommand) parser.parseCommand(EditStudentCommand.COMMAND_WORD + " "
                + INDEX_FIRST_STUDENT.getOneBased() + " " + StudentUtil.getEditStudentDescriptorDetails(descriptor));
        EditStudentCommand temp = new EditStudentCommand(INDEX_FIRST_STUDENT, descriptor);
        assertEquals(temp, command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findStudent() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindStudentCommand command = (FindStudentCommand) parser.parseCommand(
                FindStudentCommand.COMMAND_WORD + " " + String.join(" ", keywords));
        assertEquals(new FindStudentCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findGroup() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindGroupCommand command = (FindGroupCommand) parser.parseCommand(
                FindGroupCommand.COMMAND_WORD + " " + String.join(" ", keywords));
        assertEquals(new FindGroupCommand(new GroupContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }


    @Test
    public void parseCommand_markStudentAttendance() throws Exception {
        int validWeek = Attendance.FIRST_WEEK_OF_SEM;
        MarkStudentAttCommand command = (MarkStudentAttCommand) parser.parseCommand(
                MarkStudentAttCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased() + " "
                        + PREFIX_WEEK + validWeek);
        assertEquals(new MarkStudentAttCommand(
                Collections.singletonList(INDEX_FIRST_STUDENT), validWeek), command);
    }

    @Test
    public void parseCommand_markStudentParticipation() throws Exception {
        int validWeek = Participation.FIRST_WEEK_OF_SEM;
        MarkStudentPartCommand command = (MarkStudentPartCommand) parser.parseCommand(
                MarkStudentPartCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased() + " "
                        + PREFIX_WEEK + validWeek);
        assertEquals(new MarkStudentPartCommand(
                Collections.singletonList(INDEX_FIRST_STUDENT), validWeek), command);
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
