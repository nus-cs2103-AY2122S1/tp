package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DEADLINE_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.TASK_ID_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.TELE_HANDLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_0;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModules.MODULE_1;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;
import static seedu.address.testutil.TypicalStudents.AMY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.Student;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();
    private ModuleName moduleName = new ModuleName(MODULE_NAME_0);

    @Test
    public void parse_addModule_returnsAddModuleCommand() {
        String userInput = "module" + MODULE_NAME_DESC_0;
        AddModuleCommand expectedCommand = new AddModuleCommand(MODULE_1);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_addTask_returnsAddTaskCommand() {
        String userInput = "task" + MODULE_NAME_DESC_0 + TASK_ID_DESC_0 + TASK_NAME_DESC_0 + TASK_DEADLINE_DESC_0;
        Task expectedTask = new TaskBuilder()
                .withModule(MODULE_NAME_0)
                .withId(VALID_TASK_ID_0)
                .withName(VALID_TASK_NAME_0)
                .withDeadline(VALID_TASK_DEADLINE_0)
                .build();
        AddTaskCommand expectedCommand = new AddTaskCommand(moduleName, expectedTask);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_addStudent_returnsAddStudentCommand() {
        String userInput = "student" + MODULE_NAME_DESC_0 + STUDENT_ID_DESC_AMY + NAME_DESC_AMY
                + TELE_HANDLE_DESC_AMY + EMAIL_DESC_AMY;
        Student expectedStudent = AMY;
        AddStudentCommand expectedCommand = new AddStudentCommand(expectedStudent, moduleName);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidCommand_throwsParseException() {
        // any user input that is not module, task or student
        String userInput = "(*#@";
        assertParseFailure(parser, userInput, MESSAGE_UNKNOWN_COMMAND);
    }
}
