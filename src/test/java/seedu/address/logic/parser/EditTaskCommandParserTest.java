package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_TASK1;
import static seedu.address.logic.commands.EditTaskCommand.MESSAGE_TASK_NOT_EDITED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.model.task.Task;

public class EditTaskCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

    private static final String VALID_TASK = PREFIX_TASK + VALID_TASK_TASK1;

    private static final String INVALID_TASK = PREFIX_TASK + " ";

    private static final String VALID_TASK_INDEX = PREFIX_TASK_INDEX + "1 ";

    private EditTaskCommandParser parser = new EditTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TASK_INDEX + VALID_TASK, MESSAGE_INVALID_FORMAT);

        // no task index specified
        assertParseFailure(parser, "1 " + VALID_TASK, MESSAGE_INVALID_FORMAT);

        // no task specified
        assertParseFailure(parser, "1 " + VALID_TASK_INDEX, MESSAGE_TASK_NOT_EDITED);

        // no index, task index and no task specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5 " + VALID_TASK_INDEX + VALID_TASK, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0 " + VALID_TASK_INDEX + VALID_TASK, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidTaskIndex_failure() {
        // negative task index
        assertParseFailure(parser, "1 ti/-5 " + VALID_TASK, MESSAGE_INVALID_INDEX);

        // zero task index
        assertParseFailure(parser, "1 ti/0 " + VALID_TASK, MESSAGE_INVALID_INDEX);

        // invalid arguments being parsed as task index
        assertParseFailure(parser, "1 ti/1 some random string", MESSAGE_INVALID_INDEX);

        // invalid prefix being parsed as task index
        assertParseFailure(parser, "1 ti/1 i/ string", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidTask_failure() {
        assertParseFailure(parser, "1 " + VALID_TASK_INDEX + INVALID_TASK, Task.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + VALID_TASK_INDEX + VALID_TASK;
        Index targetTaskIndex = INDEX_FIRST_TASK;
        Task editedTask = new Task(VALID_TASK_TASK1);

        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex,
                targetTaskIndex, editedTask);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
