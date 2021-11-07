package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STUDENT_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TASK_ID_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.TASK_ID_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID_0;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MarkTaskUndoneCommand;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.StudentId;
import seedu.address.model.task.TaskId;

class MarkTaskUndoneCommandParserTest {
    private final MarkTaskUndoneCommandParser parser = new MarkTaskUndoneCommandParser();
    private final ModuleName moduleName1 = new ModuleName(MODULE_NAME_0);
    private final StudentId studentId1 = new StudentId(VALID_STUDENT_ID_AMY);
    private final TaskId taskId1 = new TaskId(VALID_TASK_ID_0);

    @Test
    public void parse_allFieldsPresent_success() {
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MODULE_NAME_DESC_0 + STUDENT_ID_DESC_AMY
                + TASK_ID_DESC_0, new MarkTaskUndoneCommand(moduleName1, studentId1, taskId1));

        // multiple module names -> last module name accepted
        assertParseSuccess(parser, MODULE_NAME_DESC_1 + MODULE_NAME_DESC_0
                + STUDENT_ID_DESC_AMY + TASK_ID_DESC_0, new MarkTaskUndoneCommand(moduleName1, studentId1, taskId1));

        // multiple student IDs -> last student ID accepted
        assertParseSuccess(parser, MODULE_NAME_DESC_0 + STUDENT_ID_DESC_BOB
                + STUDENT_ID_DESC_AMY + TASK_ID_DESC_0, new MarkTaskUndoneCommand(moduleName1, studentId1, taskId1));

        // multiple task IDs -> last task ID accepted
        assertParseSuccess(parser, MODULE_NAME_DESC_0 + STUDENT_ID_DESC_AMY
                + TASK_ID_DESC_1 + TASK_ID_DESC_0, new MarkTaskUndoneCommand(moduleName1, studentId1, taskId1));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkTaskUndoneCommand.MESSAGE_USAGE);

        // missing module name prefix
        assertParseFailure(parser, MODULE_NAME_0 + STUDENT_ID_DESC_AMY + TASK_ID_DESC_0, expectedMessage);

        // missing student ID prefix
        assertParseFailure(parser, MODULE_NAME_DESC_0 + VALID_STUDENT_ID_AMY + TASK_ID_DESC_0,
                expectedMessage);

        // missing task ID prefix
        assertParseFailure(parser, MODULE_NAME_DESC_0 + STUDENT_ID_DESC_AMY + VALID_TASK_ID_0,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid module name
        assertParseFailure(parser, INVALID_MODULE_NAME_DESC + STUDENT_ID_DESC_AMY + TASK_ID_DESC_0,
                ModuleName.MESSAGE_CONSTRAINTS);

        // invalid student ID
        assertParseFailure(parser, MODULE_NAME_DESC_0 + INVALID_STUDENT_ID_DESC + TASK_ID_DESC_0,
                StudentId.MESSAGE_CONSTRAINTS);

        // invalid task ID
        assertParseFailure(parser, MODULE_NAME_DESC_0 + STUDENT_ID_DESC_AMY + INVALID_TASK_ID_DESC,
                TaskId.MESSAGE_CONSTRAINTS);
    }

}
