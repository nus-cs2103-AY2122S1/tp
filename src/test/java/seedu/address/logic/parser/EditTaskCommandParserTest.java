package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DEADLINE_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DEADLINE_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.TASK_ID_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.TASK_ID_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.model.module.ModuleName;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskId;
import seedu.address.model.task.TaskName;
import seedu.address.testutil.EditTaskDescriptorBuilder;

public class EditTaskCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

    private EditTaskCommandParser parser = new EditTaskCommandParser();

    private ModuleName moduleName = new ModuleName(MODULE_NAME_0);

    @Test
    public void parse_missingParts_failure() {
        // no task id specified
        assertParseFailure(parser, MODULE_NAME_DESC_0, MESSAGE_INVALID_FORMAT);

        // no module name specified
        assertParseFailure(parser, VALID_TASK_ID_0, MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_MODULE_NAME_DESC + TASK_ID_DESC_0,
                ModuleName.MESSAGE_CONSTRAINTS); // invalid module name
        assertParseFailure(parser, MODULE_NAME_DESC_0 + TASK_ID_DESC_0 + INVALID_TASK_NAME_DESC,
                TaskName.MESSAGE_CONSTRAINTS); // invalid task name
        assertParseFailure(parser, MODULE_NAME_DESC_0 + INVALID_TASK_ID_DESC,
                TaskId.MESSAGE_CONSTRAINTS); // invalid task id
        assertParseFailure(parser, MODULE_NAME_DESC_0 + TASK_ID_DESC_0 + INVALID_TASK_DEADLINE_DESC,
                TaskDeadline.MESSAGE_CONSTRAINTS); // invalid task deadline

        // invalid task name followed by valid deadline
        assertParseFailure(parser, MODULE_NAME_DESC_0 + TASK_ID_DESC_0 + INVALID_TASK_NAME_DESC
                + TASK_DEADLINE_DESC_0, TaskName.MESSAGE_CONSTRAINTS);

        // valid task name followed by invalid name. The test case for invalid task name followed by valid task name
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, MODULE_NAME_DESC_0 + TASK_ID_DESC_0 + TASK_NAME_DESC_0
                + INVALID_TASK_NAME_DESC, TaskName.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, MODULE_NAME_DESC_0 + TASK_ID_DESC_0 + INVALID_TASK_NAME_DESC
                        + INVALID_TASK_DEADLINE_DESC,
                TaskName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = MODULE_NAME_DESC_0 + TASK_ID_DESC_1 + TASK_NAME_DESC_0
                + TASK_DEADLINE_DESC_0;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskName(VALID_TASK_NAME_0)
                .withTaskId(VALID_TASK_ID_1).withTaskDeadline(VALID_TASK_DEADLINE_0).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(moduleName, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = MODULE_NAME_DESC_0 + TASK_ID_DESC_1 + TASK_DEADLINE_DESC_0;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskId(VALID_TASK_ID_1)
                .withTaskDeadline(VALID_TASK_DEADLINE_0).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(moduleName, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        String userInput = MODULE_NAME_DESC_0 + TASK_ID_DESC_0 + TASK_NAME_DESC_0;
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskId(VALID_TASK_ID_0)
                .withTaskName(VALID_TASK_NAME_0).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(moduleName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // deadline
        userInput = MODULE_NAME_DESC_0 + TASK_ID_DESC_0 + TASK_DEADLINE_DESC_0;
        descriptor = new EditTaskDescriptorBuilder().withTaskId(VALID_TASK_ID_0)
                .withTaskDeadline(VALID_TASK_DEADLINE_0).build();
        expectedCommand = new EditTaskCommand(moduleName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = MODULE_NAME_DESC_0 + TASK_ID_DESC_0 + TASK_DEADLINE_DESC_0
                + TASK_ID_DESC_0 + TASK_DEADLINE_DESC_1 + TASK_ID_DESC_1;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskId(VALID_TASK_ID_1)
                .withTaskDeadline(VALID_TASK_DEADLINE_1).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(moduleName, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        String userInput = MODULE_NAME_DESC_0 + TASK_ID_DESC_1 + INVALID_TASK_NAME_DESC + TASK_NAME_DESC_0;
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskId(VALID_TASK_ID_1)
                .withTaskName(VALID_TASK_NAME_0).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(moduleName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = MODULE_NAME_DESC_0 + TASK_ID_DESC_1 + INVALID_TASK_DEADLINE_DESC + TASK_DEADLINE_DESC_1
                + TASK_NAME_DESC_1;
        descriptor = new EditTaskDescriptorBuilder().withTaskId(VALID_TASK_ID_1)
                .withTaskDeadline(VALID_TASK_DEADLINE_1).withTaskName(VALID_TASK_NAME_1).build();
        expectedCommand = new EditTaskCommand(moduleName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
