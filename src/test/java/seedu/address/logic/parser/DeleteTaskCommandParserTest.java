package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.TASK_ID_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID_0;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.model.module.ModuleName;
import seedu.address.model.task.TaskId;

public class DeleteTaskCommandParserTest {

    private DeleteTaskCommandParser parser = new DeleteTaskCommandParser();
    private ModuleName moduleName = new ModuleName(MODULE_NAME_0);
    private TaskId taskId = new TaskId(VALID_TASK_ID_0);

    @Test
    public void parse_validArgs_returnsDeleteTaskCommand() {
        assertParseSuccess(parser, MODULE_NAME_DESC_0 + TASK_ID_DESC_0,
                new DeleteTaskCommand(taskId, moduleName));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        final String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE);
        // invalid module name
        assertParseFailure(parser, INVALID_MODULE_NAME_DESC + TASK_NAME_DESC_0, expectedMessage);

        // invalid task id
        assertParseFailure(parser, MODULE_NAME_DESC_0 + INVALID_TASK_ID_DESC, TaskId.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        final String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE);

        // missing module name
        assertParseFailure(parser, TASK_NAME_DESC_0, expectedMessage);

        // missing task id
        assertParseFailure(parser, MODULE_NAME_DESC_0, expectedMessage);
    }
}
