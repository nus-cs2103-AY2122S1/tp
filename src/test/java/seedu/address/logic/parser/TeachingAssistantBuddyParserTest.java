package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DEADLINE_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DEADLINE_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.TASK_ID_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.TELE_HANDLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELE_HANDLE_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_1;
import static seedu.address.testutil.TypicalStudents.AMY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteModuleCommand;
import seedu.address.logic.commands.DeleteStudentCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.EditModuleCommand;
import seedu.address.logic.commands.EditStudentCommand;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindStudentCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HomeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.Email;
import seedu.address.model.module.student.Name;
import seedu.address.model.module.student.Student;
import seedu.address.model.module.student.StudentId;
import seedu.address.model.module.student.TeleHandle;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskId;
import seedu.address.model.task.TaskName;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.TaskBuilder;

public class TeachingAssistantBuddyParserTest {

    private final TeachingAssistantBuddyParser parser = new TeachingAssistantBuddyParser();

    @Test
    public void parseCommand_findStudent() throws Exception {
        ModuleName moduleName = new ModuleName(MODULE_NAME_0);
        StudentId studentId = new StudentId(VALID_STUDENT_ID_AMY);
        FindStudentCommand command = (FindStudentCommand) parser.parseCommand(FindStudentCommand.COMMAND_WORD
                + MODULE_NAME_DESC_0 + STUDENT_ID_DESC_AMY);
        assertEquals(new FindStudentCommand(moduleName, studentId), command);
    }

    @Test
    public void parseCommand_addStudent() throws Exception {
        ModuleName moduleName = new ModuleName(MODULE_NAME_0);
        Student student = AMY;
        AddStudentCommand command = (AddStudentCommand) parser.parseCommand(AddStudentCommand.COMMAND_WORD
                + MODULE_NAME_DESC_0 + STUDENT_ID_DESC_AMY + NAME_DESC_AMY + TELE_HANDLE_DESC_AMY + EMAIL_DESC_AMY);
        assertEquals(new AddStudentCommand(student, moduleName), command);
    }

    @Test
    public void parseCommand_addModule() throws Exception {
        Module module = new ModuleBuilder().withName(MODULE_NAME_0).build();
        AddModuleCommand command = (AddModuleCommand) parser.parseCommand(AddModuleCommand.COMMAND_WORD
                + MODULE_NAME_DESC_0);
        assertEquals(new AddModuleCommand(module), command);
    }

    @Test
    public void parseCommand_addTask() throws Exception {
        ModuleName moduleName = new ModuleName(MODULE_NAME_0);
        Task task = new TaskBuilder().withName(VALID_TASK_NAME_1).withId(VALID_TASK_ID_1)
                .withDeadline(VALID_TASK_DEADLINE_1).build();
        AddTaskCommand command = (AddTaskCommand) parser.parseCommand(AddTaskCommand.COMMAND_WORD
                + MODULE_NAME_DESC_0 + TASK_ID_DESC_1 + TASK_NAME_DESC_1 + TASK_DEADLINE_DESC_1);
        assertEquals(new AddTaskCommand(moduleName, task), command);
    }

    @Test
    public void parseCommand_deleteStudent() throws Exception {
        ModuleName moduleName = new ModuleName(MODULE_NAME_0);
        StudentId studentId = new StudentId(VALID_STUDENT_ID_AMY);
        DeleteStudentCommand command = (DeleteStudentCommand) parser.parseCommand(DeleteStudentCommand.COMMAND_WORD
                + MODULE_NAME_DESC_0 + STUDENT_ID_DESC_AMY);
        assertEquals(new DeleteStudentCommand(studentId, moduleName), command);
    }

    @Test
    public void parseCommand_deleteModule() throws Exception {
        DeleteModuleCommand command = (DeleteModuleCommand) parser.parseCommand(DeleteModuleCommand.COMMAND_WORD
                + MODULE_NAME_DESC_0);
        assertEquals(new DeleteModuleCommand(new ModuleName(MODULE_NAME_0)), command);
    }

    @Test
    public void parseCommand_deleteTask() throws Exception {
        ModuleName moduleName = new ModuleName(MODULE_NAME_0);
        TaskId taskId = new TaskId(VALID_TASK_ID_1);
        DeleteTaskCommand command = (DeleteTaskCommand) parser.parseCommand(DeleteTaskCommand.COMMAND_WORD
                + MODULE_NAME_DESC_0 + TASK_ID_DESC_1);
        assertEquals(new DeleteTaskCommand(taskId, moduleName), command);
    }

    @Test
    public void parseCommand_editStudent() throws Exception {
        ModuleName moduleName = new ModuleName(MODULE_NAME_0);
        EditStudentCommand.EditStudentDescriptor editedStudent = new EditStudentCommand.EditStudentDescriptor();
        editedStudent.setStudentId(new StudentId(VALID_STUDENT_ID_AMY));
        editedStudent.setName(new Name(VALID_NAME_AMY));
        editedStudent.setTeleHandle(new TeleHandle(VALID_TELE_HANDLE_AMY));
        editedStudent.setEmail(new Email(VALID_EMAIL_AMY));
        EditStudentCommand command = (EditStudentCommand) parser.parseCommand(EditStudentCommand.COMMAND_WORD
                + MODULE_NAME_DESC_0 + STUDENT_ID_DESC_AMY + NAME_DESC_AMY + TELE_HANDLE_DESC_AMY + EMAIL_DESC_AMY);
        assertEquals(new EditStudentCommand(moduleName, editedStudent), command);
    }

    @Test
    public void parseCommand_editModule() throws Exception {
        EditModuleCommand.EditModuleDescriptor editedModule = new EditModuleCommand.EditModuleDescriptor();
        editedModule.setModuleName(new ModuleName(MODULE_NAME_1));
        EditModuleCommand command = (EditModuleCommand) parser.parseCommand(EditModuleCommand.COMMAND_WORD
                + MODULE_NAME_DESC_0 + " mn/" + MODULE_NAME_1);
        assertEquals(new EditModuleCommand(new ModuleName(MODULE_NAME_0), editedModule), command);
    }

    @Test
    public void parseCommand_editTask() throws Exception {
        ModuleName moduleName = new ModuleName(MODULE_NAME_0);
        EditTaskCommand.EditTaskDescriptor editedTask = new EditTaskCommand.EditTaskDescriptor();
        editedTask.setTaskName(new TaskName(VALID_TASK_NAME_0));
        editedTask.setTaskId(new TaskId(VALID_TASK_ID_1));
        editedTask.setTaskDeadline(new TaskDeadline(VALID_TASK_DEADLINE_0));
        Task task = new TaskBuilder().withName(VALID_TASK_NAME_1).withId(VALID_TASK_ID_1)
                .withDeadline(VALID_TASK_DEADLINE_1).build();
        EditTaskCommand command = (EditTaskCommand) parser.parseCommand(EditTaskCommand.COMMAND_WORD
                + MODULE_NAME_DESC_0 + TASK_ID_DESC_1 + TASK_NAME_DESC_0 + TASK_DEADLINE_DESC_0);
        assertEquals(new EditTaskCommand(moduleName, editedTask), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
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
    public void parseCommand_home() throws Exception {
        assertTrue(parser.parseCommand(HomeCommand.COMMAND_WORD) instanceof HomeCommand);
        assertTrue(parser.parseCommand(HomeCommand.COMMAND_WORD + " 3") instanceof HomeCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                HelpCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser
                .parseCommand("unknownCommand"));
    }
}
