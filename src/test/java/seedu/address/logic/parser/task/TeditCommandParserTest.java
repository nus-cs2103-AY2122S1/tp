package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_INDEX_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DEADLINE_DESC_POEM;
import static seedu.address.logic.commands.CommandTestUtil.TASK_INDEX_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_DESC_POEM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POEM_TASK_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POEM_TASK_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.task.TeditCommand;
import seedu.address.model.module.Name;
import seedu.address.model.module.task.TaskDeadline;

class TeditCommandParserTest {
    private TeditCommandParser parser = new TeditCommandParser();

    @Test
    void parse_allFieldsPresent_success() {
        Index expectedTaskId = Index.fromOneBased(VALID_TASK_INDEX);
        Name editedTaskName = new Name(VALID_POEM_TASK_NAME);
        TaskDeadline editedTaskDeadline = new TaskDeadline(VALID_POEM_TASK_DEADLINE);
        TeditCommand.EditTaskDescriptor descriptor = new TeditCommand.EditTaskDescriptor();
        descriptor.setName(editedTaskName);
        descriptor.setDeadline(editedTaskDeadline);

        assertParseSuccess(
                parser, TASK_INDEX_DESC_ONE + TASK_NAME_DESC_POEM + TASK_DEADLINE_DESC_POEM,
                new TeditCommand(expectedTaskId, descriptor));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TeditCommand.MESSAGE_USAGE);
        String expectedMessageForMissingOptional = TeditCommand.MESSAGE_NOT_EDITED;

        //missing task id
        assertParseFailure(parser, TASK_NAME_DESC_POEM, expectedMessage);

        //missing optional
        assertParseFailure(parser, TASK_INDEX_DESC_ONE, expectedMessageForMissingOptional);
    }

    @Test
    public void parse_invalidValue_failure() {
        //invalid task id
        assertParseFailure(parser, INVALID_TASK_INDEX_DESC + TASK_DEADLINE_DESC_POEM
                + TASK_DEADLINE_DESC_POEM, MESSAGE_INVALID_INDEX);

        //invalid task name
        assertParseFailure(parser, TASK_INDEX_DESC_ONE + INVALID_TASK_NAME_DESC
                + TASK_DEADLINE_DESC_POEM, Name.MESSAGE_CONSTRAINTS);

        //invalid task deadline
        assertParseFailure(parser, TASK_INDEX_DESC_ONE + TASK_NAME_DESC_POEM
                + INVALID_TASK_DEADLINE_DESC, TaskDeadline.MESSAGE_CONSTRAINTS);

    }
}
