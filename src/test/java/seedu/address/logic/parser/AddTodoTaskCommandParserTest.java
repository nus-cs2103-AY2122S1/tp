package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DESCRIPTION_DESC_PLAY;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DESCRIPTION_DESC_STUDY;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_DESC_PLAY;
import static seedu.address.logic.commands.CommandTestUtil.TASK_TAG_DESC_EXERCISE;
import static seedu.address.logic.commands.CommandTestUtil.TASK_TAG_DESC_FUN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION_PLAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_PLAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_TAG_EXERCISE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_TAG_FUN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_TAG_STUDY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTodoTaskCommand;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TodoTask;
import seedu.address.testutil.TodoTaskBuilder;

public class AddTodoTaskCommandParserTest {
    private final AddTodoTaskCommandParser parser = new AddTodoTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        TodoTask expectedTask = new TodoTaskBuilder().withName(VALID_TASK_NAME_PLAY)
                .withDescription(VALID_TASK_DESCRIPTION_PLAY)
                .withTags(VALID_TASK_TAG_EXERCISE)
                .build();

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TASK_NAME_DESC_PLAY
                        + TASK_DESCRIPTION_DESC_PLAY + TASK_TAG_DESC_EXERCISE, new AddTodoTaskCommand(expectedTask));

        assertParseSuccess(parser, TASK_NAME_DESC_PLAY
                + TASK_DESCRIPTION_DESC_PLAY + TASK_TAG_DESC_EXERCISE, new AddTodoTaskCommand(expectedTask));

        TodoTask expectedTaskWithMultipleTags = new TodoTaskBuilder().withName(VALID_TASK_NAME_PLAY)
                .withDescription(VALID_TASK_DESCRIPTION_PLAY)
                .withTags(VALID_TASK_TAG_EXERCISE, VALID_TASK_TAG_FUN)
                .build();

        assertParseSuccess(parser, TASK_NAME_DESC_PLAY
                + TASK_DESCRIPTION_DESC_PLAY + TASK_TAG_DESC_EXERCISE + TASK_TAG_DESC_FUN,
                new AddTodoTaskCommand(expectedTaskWithMultipleTags));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        TodoTask expectedTask = new TodoTaskBuilder().withName(VALID_TASK_NAME_PLAY)
                .withDescription(VALID_TASK_DESCRIPTION_PLAY)
                .build();
        assertParseSuccess(parser, TASK_NAME_DESC_PLAY
                + TASK_DESCRIPTION_DESC_PLAY, new AddTodoTaskCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTodoTaskCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_TASK_TAG_STUDY + TASK_DESCRIPTION_DESC_STUDY, expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_TASK_NAME_DESC + TASK_DESCRIPTION_DESC_PLAY
                + TASK_TAG_DESC_FUN, TaskName.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, TASK_NAME_DESC_PLAY + TASK_DESCRIPTION_DESC_PLAY
                        + INVALID_TASK_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);
    }

}
