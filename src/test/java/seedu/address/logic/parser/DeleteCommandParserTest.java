package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TASK_ID_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID_0;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteModuleCommand;
import seedu.address.logic.commands.DeleteStudentCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.StudentId;
import seedu.address.model.task.TaskId;

public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();
    private ModuleName moduleName = new ModuleName(MODULE_NAME_0);

    @Test
    public void parse_deleteModule_returnsDeleteModuleCommand() {
        String userInput = "module" + MODULE_NAME_DESC_0;
        DeleteModuleCommand expectedCommand = new DeleteModuleCommand(moduleName);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_deleteTask_returnsDeleteTaskCommand() {
        String userInput = "task" + MODULE_NAME_DESC_0 + TASK_ID_DESC_0;
        TaskId taskId = new TaskId(VALID_TASK_ID_0);
        DeleteTaskCommand expectedCommand = new DeleteTaskCommand(taskId, moduleName);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_deleteStudent_returnsDeleteStudentCommand() {
        String userInput = "student" + MODULE_NAME_DESC_0 + STUDENT_ID_DESC_AMY;
        StudentId studentId = new StudentId(VALID_STUDENT_ID_AMY);
        DeleteStudentCommand expectedCommand = new DeleteStudentCommand(studentId, moduleName);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidCommand_throwsParseException() {
        // any user input that is not module, task or student
        String userInput = "(*#@";
        assertParseFailure(parser, userInput, MESSAGE_UNKNOWN_COMMAND);
    }
}
