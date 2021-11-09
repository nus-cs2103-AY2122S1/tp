package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DEADLINE_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DEADLINE_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.TASK_ID_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.TASK_ID_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.module.ModuleName;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskId;
import seedu.address.model.task.TaskName;
import seedu.address.testutil.TaskBuilder;

public class AddTaskCommandParserTest {

    private AddTaskCommandParser parser = new AddTaskCommandParser();
    private Task testTask = new TaskBuilder().withId(VALID_TASK_ID_1).withName(VALID_TASK_NAME_1)
            .withDeadline(VALID_TASK_DEADLINE_1).build();
    private ModuleName moduleName = new ModuleName(MODULE_NAME_0);

    @Test
    public void parse_allFieldsPresent_success() {

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MODULE_NAME_DESC_0 + TASK_ID_DESC_1
                + TASK_NAME_DESC_1 + TASK_DEADLINE_DESC_1, new AddTaskCommand(moduleName, testTask));

        // multiple module names - last module name accepted
        assertParseSuccess(parser, MODULE_NAME_DESC_1 + MODULE_NAME_DESC_0 + TASK_ID_DESC_1 + TASK_NAME_DESC_1
                + TASK_DEADLINE_DESC_1, new AddTaskCommand(moduleName, testTask));

        // multiple task ids - last task id accepted
        assertParseSuccess(parser, MODULE_NAME_DESC_0 + TASK_ID_DESC_0 + TASK_ID_DESC_1 + TASK_NAME_DESC_1
                + TASK_DEADLINE_DESC_1, new AddTaskCommand(moduleName, testTask));

        // multiple task names - last task name accepted
        assertParseSuccess(parser, MODULE_NAME_DESC_0 + TASK_ID_DESC_1 + TASK_NAME_DESC_0 + TASK_NAME_DESC_1
                + TASK_DEADLINE_DESC_1, new AddTaskCommand(moduleName, testTask));

        // multiple task deadlines - last task deadline accepted
        assertParseSuccess(parser, MODULE_NAME_DESC_0 + TASK_ID_DESC_1 + TASK_NAME_DESC_1
                + TASK_DEADLINE_DESC_0 + TASK_DEADLINE_DESC_1, new AddTaskCommand(moduleName, testTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing module name prefix
        assertParseFailure(parser, MODULE_NAME_0 + TASK_ID_DESC_1 + TASK_NAME_DESC_1 + TASK_DEADLINE_DESC_1,
                expectedMessage);

        // missing task id prefix
        assertParseFailure(parser, MODULE_NAME_DESC_0 + VALID_TASK_ID_1 + TASK_NAME_DESC_1
                + TASK_DEADLINE_DESC_1, expectedMessage);

        // missing task name prefix
        assertParseFailure(parser, MODULE_NAME_DESC_0 + TASK_ID_DESC_1 + VALID_TASK_NAME_1
                + TASK_DEADLINE_DESC_1, expectedMessage);

        // missing task deadline prefix
        assertParseFailure(parser, MODULE_NAME_DESC_0 + TASK_ID_DESC_1 + TASK_NAME_DESC_1
                + VALID_TASK_DEADLINE_1, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid module name
        assertParseFailure(parser, INVALID_MODULE_NAME_DESC + TASK_ID_DESC_1 + TASK_NAME_DESC_1
                + TASK_DEADLINE_DESC_1, ModuleName.MESSAGE_CONSTRAINTS);

        // invalid task id
        assertParseFailure(parser, MODULE_NAME_DESC_0 + INVALID_TASK_ID_DESC + TASK_NAME_DESC_1
                + TASK_DEADLINE_DESC_1, TaskId.MESSAGE_CONSTRAINTS);

        // invalid task name
        assertParseFailure(parser, MODULE_NAME_DESC_0 + TASK_ID_DESC_1 + INVALID_TASK_NAME_DESC
                + TASK_DEADLINE_DESC_1, TaskName.MESSAGE_CONSTRAINTS);

        // invalid task deadline
        assertParseFailure(parser, MODULE_NAME_DESC_0 + TASK_ID_DESC_1 + TASK_NAME_DESC_1
                + INVALID_TASK_DEADLINE_DESC, TaskDeadline.MESSAGE_CONSTRAINTS);
    }
}
