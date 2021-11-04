package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TUTORIALCLASS;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddClassCommand;
import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.commands.AddStudentToGroupCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteClassCommand;
import seedu.address.logic.commands.DeleteStudentCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindStudentCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListStudentCommand;
import seedu.address.logic.commands.ViewClassCommand;
import seedu.address.logic.commands.ViewStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialclass.TutorialClass;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.StudentUtil;
import seedu.address.testutil.TutorialClassBuilder;
import seedu.address.testutil.TutorialClassUtil;
import seedu.address.testutil.TutorialGroupBuilder;
import seedu.address.testutil.TutorialGroupUtil;

public class ClassmateParserTest {

    private final ClassmateParser parser = new ClassmateParser();

    @Test
    public void parseCommand_addStudent() throws Exception {
        Student student = new StudentBuilder().build();
        AddStudentCommand command = (AddStudentCommand) parser.parseCommand(StudentUtil.getAddCommand(student));
        assertEquals(new AddStudentCommand(student), command);

    }

    @Test
    public void parseCommand_addTutorialClass() throws Exception {
        TutorialClass tutorialClass = new TutorialClassBuilder().build();
        AddClassCommand command = (AddClassCommand) parser.parseCommand(
                TutorialClassUtil.getAddClassCommand(tutorialClass));
        assertEquals(new AddClassCommand(tutorialClass), command);

    }

    @Test
    public void parseCommand_addTutorialGroup() throws Exception {
        TutorialGroup tutorialGroup = new TutorialGroupBuilder().build();
        AddGroupCommand command = (AddGroupCommand) parser.parseCommand(
                TutorialGroupUtil.getAddGroupCommand(tutorialGroup)
        );
        assertEquals(new AddGroupCommand(tutorialGroup), command);
    }

    @Test
    public void parseCommand_addStudentToGroup() throws Exception {
        TutorialGroup tutorialGroup = new TutorialGroupBuilder().build();
        AddStudentToGroupCommand command = (AddStudentToGroupCommand) parser.parseCommand(
                TutorialGroupUtil.getAddStudentToGroupCommand(tutorialGroup, INDEX_FIRST_STUDENT)
        );
        assertEquals(new AddStudentToGroupCommand(INDEX_FIRST_STUDENT, tutorialGroup), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteStudent() throws Exception {
        DeleteStudentCommand command = (DeleteStudentCommand) parser.parseCommand(
                DeleteStudentCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased());
        assertEquals(new DeleteStudentCommand(INDEX_FIRST_STUDENT), command);
    }

    @Test
    public void parseCommand_deletetutorialCLass() throws Exception {
        DeleteClassCommand command = (DeleteClassCommand) parser.parseCommand(
                DeleteClassCommand.COMMAND_WORD + " " + INDEX_FIRST_TUTORIALCLASS.getOneBased());
        assertEquals(new DeleteClassCommand(INDEX_FIRST_TUTORIALCLASS), command);
    }

    @Test
    public void parseCommand_viewStudent() throws Exception {
        ViewStudentCommand command = (ViewStudentCommand) parser.parseCommand(
                ViewStudentCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased());
        assertEquals(new ViewStudentCommand(INDEX_FIRST_STUDENT), command);
    }

    @Test
    public void parseCommand_viewTutorialClass() throws Exception {
        ViewClassCommand command = (ViewClassCommand) parser.parseCommand(
                ViewClassCommand.COMMAND_WORD + " " + INDEX_FIRST_TUTORIALCLASS.getOneBased());
        assertEquals(new ViewClassCommand(INDEX_FIRST_TUTORIALCLASS), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Student student = new StudentBuilder().build();
        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(student).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_STUDENT.getOneBased() + " " + StudentUtil.getEditStudentDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_STUDENT, descriptor), command);
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
                FindStudentCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindStudentCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }


    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listStudent() throws Exception {
        assertTrue(parser.parseCommand(ListStudentCommand.COMMAND_WORD) instanceof ListStudentCommand);
        assertTrue(parser.parseCommand(ListStudentCommand.COMMAND_WORD + " 3") instanceof ListStudentCommand);
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
