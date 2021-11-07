package seedu.academydirectory.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.academydirectory.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.academydirectory.testutil.Assert.assertThrows;
import static seedu.academydirectory.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.logic.commands.AddCommand;
import seedu.academydirectory.logic.commands.AttendanceCommand;
import seedu.academydirectory.logic.commands.ClearCommand;
import seedu.academydirectory.logic.commands.DeleteCommand;
import seedu.academydirectory.logic.commands.EditCommand;
import seedu.academydirectory.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.academydirectory.logic.commands.ExitCommand;
import seedu.academydirectory.logic.commands.FilterCommand;
import seedu.academydirectory.logic.commands.GetCommand;
import seedu.academydirectory.logic.commands.GradeCommand;
import seedu.academydirectory.logic.commands.HelpCommand;
import seedu.academydirectory.logic.commands.HistoryCommand;
import seedu.academydirectory.logic.commands.ListCommand;
import seedu.academydirectory.logic.commands.ParticipationCommand;
import seedu.academydirectory.logic.commands.RedoCommand;
import seedu.academydirectory.logic.commands.ShowCommand;
import seedu.academydirectory.logic.commands.TagCommand;
import seedu.academydirectory.logic.commands.UndoCommand;
import seedu.academydirectory.logic.commands.ViewCommand;
import seedu.academydirectory.logic.commands.VisualizeCommand;
import seedu.academydirectory.logic.parser.exceptions.ParseException;
import seedu.academydirectory.model.student.InformationContainsKeywordsPredicate;
import seedu.academydirectory.model.student.Student;
import seedu.academydirectory.testutil.EditStudentDescriptorBuilder;
import seedu.academydirectory.testutil.StudentBuilder;
import seedu.academydirectory.testutil.StudentUtil;

public class AcademyDirectoryParserTest {

    private final AcademyDirectoryParser parser = new AcademyDirectoryParser();

    @Test
    public void parseCommand_add() throws Exception {
        Student student = new StudentBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(StudentUtil.getAddCommand(student));
        assertEquals(new AddCommand(student), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_STUDENT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Student student = new StudentBuilder().build();
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(student).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_STUDENT.getOneBased() + " " + StudentUtil.getEditStudentDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_STUDENT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_filter() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FilterCommand command = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FilterCommand(new InformationContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
    }

    @Test
    public void parseCommand_get() throws Exception {
        assertTrue(parser.parseCommand(GetCommand.COMMAND_WORD + " " + PREFIX_EMAIL)
                instanceof GetCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " grade") instanceof HelpCommand);
        assertThrows(ParseException.class, () -> parser.parseCommand(HelpCommand.COMMAND_WORD + " me"));
    }

    @Test
    public void parseCommand_view() throws Exception {
        assertTrue(parser.parseCommand(ViewCommand.COMMAND_WORD + " 2") instanceof ViewCommand);
    }

    @Test
    public void parseCommand_visualize() throws Exception {
        assertTrue(parser.parseCommand(VisualizeCommand.COMMAND_WORD) instanceof VisualizeCommand);
        assertThrows(ParseException.class, () -> parser.parseCommand("visualize 2"));
    }

    @Test
    public void parseCommand_undo() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
        assertThrows(ParseException.class, () -> parser.parseCommand("undo 2"));
    }

    @Test
    public void parseCommand_redo() throws Exception {
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD) instanceof RedoCommand);
        assertThrows(ParseException.class, () -> parser.parseCommand("redo 2"));
    }

    @Test
    public void parseCommand_history() throws Exception {
        assertTrue(parser.parseCommand(HistoryCommand.COMMAND_WORD) instanceof HistoryCommand);
        assertThrows(ParseException.class, () -> parser.parseCommand("history 2"));
    }

    @Test
    public void parseCommand_attendance() throws Exception {
        assertTrue(parser.parseCommand(AttendanceCommand.COMMAND_WORD + " 1 ses/1 att/1")
                instanceof AttendanceCommand);
        assertThrows(ParseException.class, () -> parser.parseCommand(
                AttendanceCommand.COMMAND_WORD + "1 att/attended"));
    }

    @Test
    public void parseCommand_grade() throws Exception {
        assertTrue(parser.parseCommand(GradeCommand.COMMAND_WORD + " 1 as/RA1 g/15") instanceof GradeCommand);
        assertThrows(ParseException.class, () -> parser.parseCommand(GradeCommand.COMMAND_WORD + "1"));
    }

    @Test
    public void parseCommand_participation() throws Exception {
        assertTrue(parser.parseCommand(ParticipationCommand.COMMAND_WORD + " 4 ses/9 add/1")
                instanceof ParticipationCommand);
        assertThrows(ParseException.class, () -> parser.parseCommand(ParticipationCommand.COMMAND_WORD + "5"));
    }

    @Test
    public void parseCommand_show() throws Exception {
        assertTrue(parser.parseCommand(ShowCommand.COMMAND_WORD + " RA1") instanceof ShowCommand);
        assertThrows(ParseException.class, () -> parser.parseCommand(ShowCommand.COMMAND_WORD + " RA3"));
    }

    @Test
    public void parseCommand_tag() throws Exception {
        assertTrue(parser.parseCommand(TagCommand.COMMAND_WORD + " 1 t/tag") instanceof TagCommand);
        assertThrows(ParseException.class, () -> parser.parseCommand(TagCommand.COMMAND_WORD + " something"));
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
