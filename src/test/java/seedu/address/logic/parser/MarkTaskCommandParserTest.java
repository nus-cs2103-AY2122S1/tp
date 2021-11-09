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

import seedu.address.logic.commands.MarkTaskDoneCommand;
import seedu.address.logic.commands.MarkTaskUndoneCommand;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.StudentId;
import seedu.address.model.task.TaskId;

class MarkTaskCommandParserTest {
    private final MarkTaskCommandParser parser = new MarkTaskCommandParser();
    private final ModuleName moduleName = new ModuleName(MODULE_NAME_0);
    private final StudentId studentId = new StudentId(VALID_STUDENT_ID_AMY);
    private final TaskId taskId = new TaskId(VALID_TASK_ID_0);

    @Test
    public void parse_markDone_returnsMarkTaskDoneCommand() {
        String userInput = "done" + MODULE_NAME_DESC_0 + STUDENT_ID_DESC_AMY + TASK_ID_DESC_0;
        MarkTaskDoneCommand expectedCommand = new MarkTaskDoneCommand(moduleName, studentId, taskId);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_markUndone_returnsMarkTaskUndoneCommand() {
        String userInput = "undone" + MODULE_NAME_DESC_0 + STUDENT_ID_DESC_AMY + TASK_ID_DESC_0;
        MarkTaskUndoneCommand expectedCommand = new MarkTaskUndoneCommand(moduleName, studentId, taskId);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidCommand_throwsParseException() {
        String invalidUserInput = "invalid";
        assertParseFailure(parser, invalidUserInput, MESSAGE_UNKNOWN_COMMAND);
    }

}
