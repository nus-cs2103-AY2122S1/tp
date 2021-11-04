package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_EVENTDATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DESCRIPTION_DESC_PLAY;
import static seedu.address.logic.commands.CommandTestUtil.TASK_EVENTDATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_DESC_PLAY;
import static seedu.address.logic.commands.CommandTestUtil.TASK_TAG_DESC_EXERCISE;
import static seedu.address.logic.commands.CommandTestUtil.TASK_TAG_DESC_FUN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION_PLAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_PLAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_TAG_EXERCISE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_TAG_FUN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_TAG_STUDY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddEventTaskCommand;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.EventTask;
import seedu.address.model.task.TaskDate;
import seedu.address.model.task.TaskName;
import seedu.address.testutil.EventTaskBuilder;

public class AddEventTaskCommandParserTest {
    private final AddEventTaskCommandParser parser = new AddEventTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        EventTask expectedTask = new EventTaskBuilder().withName(VALID_TASK_NAME_PLAY)
                .withDescription(VALID_TASK_DESCRIPTION_PLAY)
                .withTags(VALID_TASK_TAG_EXERCISE)
                .withDate(VALID_TASK_DATE)
                .build();

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TASK_NAME_DESC_PLAY + TASK_EVENTDATE_DESC
                + TASK_DESCRIPTION_DESC_PLAY + TASK_TAG_DESC_EXERCISE, new AddEventTaskCommand(expectedTask));

        assertParseSuccess(parser, TASK_NAME_DESC_PLAY + TASK_EVENTDATE_DESC
                + TASK_DESCRIPTION_DESC_PLAY + TASK_TAG_DESC_EXERCISE, new AddEventTaskCommand(expectedTask));

        EventTask expectedTaskWithMultipleTags = new EventTaskBuilder().withName(VALID_TASK_NAME_PLAY)
                .withDescription(VALID_TASK_DESCRIPTION_PLAY)
                .withTags(VALID_TASK_TAG_EXERCISE, VALID_TASK_TAG_FUN)
                .withDate(VALID_TASK_DATE)
                .build();

        assertParseSuccess(parser, TASK_NAME_DESC_PLAY + TASK_EVENTDATE_DESC
                        + TASK_DESCRIPTION_DESC_PLAY + TASK_TAG_DESC_EXERCISE + TASK_TAG_DESC_FUN,
                new AddEventTaskCommand(expectedTaskWithMultipleTags));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags and description
        EventTask expectedTaskWithoutTagsAndDescription = new EventTaskBuilder().withName(VALID_TASK_NAME_PLAY)
                .withDate(VALID_TASK_DATE)
                .build();

        assertParseSuccess(parser, TASK_NAME_DESC_PLAY + TASK_EVENTDATE_DESC,
                new AddEventTaskCommand(expectedTaskWithoutTagsAndDescription));

        // zero tags
        EventTask expectedTaskWithoutTags = new EventTaskBuilder().withName(VALID_TASK_NAME_PLAY)
                .withDescription(VALID_TASK_DESCRIPTION_PLAY)
                .withDate(VALID_TASK_DATE)
                .build();

        assertParseSuccess(parser, TASK_NAME_DESC_PLAY + TASK_EVENTDATE_DESC
                + TASK_DESCRIPTION_DESC_PLAY, new AddEventTaskCommand(expectedTaskWithoutTags));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventTaskCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_TASK_TAG_STUDY + TASK_EVENTDATE_DESC, expectedMessage);

        // missing event date prefix
        assertParseFailure(parser, TASK_NAME_DESC_PLAY + VALID_TASK_DATE
                + TASK_DESCRIPTION_DESC_PLAY, expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INVALID_NAME_DESC
                + TASK_EVENTDATE_DESC, TaskName.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, PREAMBLE_WHITESPACE + TASK_NAME_DESC_PLAY
                + INVALID_TASK_EVENTDATE_DESC, TaskDate.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, PREAMBLE_WHITESPACE + TASK_NAME_DESC_PLAY
                + TASK_EVENTDATE_DESC + INVALID_TASK_DEADLINE_DESC, TaskDate.MESSAGE_CONSTRAINTS);

        // invalid tags
        assertParseFailure(parser, PREAMBLE_WHITESPACE + TASK_NAME_DESC_PLAY
                + TASK_EVENTDATE_DESC + TASK_DESCRIPTION_DESC_PLAY + INVALID_TASK_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);
    }
}
