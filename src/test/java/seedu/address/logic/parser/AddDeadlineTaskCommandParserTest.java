package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.AddDeadlineTaskCommand;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.*;
import seedu.address.testutil.DeadlineAndEventTaskBuilder;

public class AddDeadlineTaskCommandParserTest {
    
    private AddDeadlineTaskCommandParser parser = new AddDeadlineTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        DeadlineTask expectedTask = new DeadlineAndEventTaskBuilder().withName(VALID_TASK_NAME_PLAY)
                .withDescription(VALID_TASK_DESCRIPTION_PLAY)
                .withTags(VALID_TASK_TAG_EXERCISE)
                .withDate(VALID_TASK_DATE)
                .build();

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TASK_NAME_DESC_PLAY + TASK_DEADLINE_DESC
                + TASK_DESCRIPTION_DESC_PLAY + TASK_TAG_DESC_EXERCISE, new AddDeadlineTaskCommand(expectedTask));

        assertParseSuccess(parser, TASK_NAME_DESC_PLAY + TASK_DEADLINE_DESC
                + TASK_DESCRIPTION_DESC_PLAY + TASK_TAG_DESC_EXERCISE, new AddDeadlineTaskCommand(expectedTask));

        Task expectedTaskWithMultipleTags = new  DeadlineAndEventTaskBuilder().withName(VALID_TASK_NAME_PLAY)
                .withDescription(VALID_TASK_DESCRIPTION_PLAY)
                .withTags(VALID_TASK_TAG_EXERCISE, VALID_TASK_TAG_FUN)
                .withDate(VALID_TASK_DATE)
                .build();

        assertParseSuccess(parser, TASK_NAME_DESC_PLAY + TASK_DEADLINE_DESC
                        + TASK_DESCRIPTION_DESC_PLAY + TASK_TAG_DESC_EXERCISE + TASK_TAG_DESC_FUN,
                new AddDeadlineTaskCommand(expectedTaskWithMultipleTags));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        DeadlineTask expectedTask = new DeadlineAndEventTaskBuilder().withName(VALID_TASK_NAME_PLAY)
                .withDate(VALID_TASK_DATE)
                .build();
        assertParseSuccess(parser, TASK_NAME_DESC_PLAY + TASK_DEADLINE_DESC
                + TASK_DESCRIPTION_DESC_PLAY, new AddDeadlineTaskCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDeadlineTaskCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_TASK_TAG_STUDY + TASK_DEADLINE_DESC, expectedMessage);
        
        // missing deadline prefix
        assertParseFailure(parser, TASK_NAME_DESC_PLAY + VALID_TASK_DATE 
                + TASK_DESCRIPTION_DESC_PLAY, expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name 
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INVALID_NAME_DESC 
                + TASK_DEADLINE_DESC, TaskName.MESSAGE_CONSTRAINTS);

        // invalid date 
        assertParseFailure(parser, PREAMBLE_WHITESPACE + TASK_NAME_DESC_PLAY
                + INVALID_TASK_DEADLINE_DESC, TaskDate.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, PREAMBLE_WHITESPACE + TASK_NAME_DESC_PLAY
                + TASK_DEADLINE_DESC + INVALID_TASK_DEADLINE_DESC, TaskDate.MESSAGE_CONSTRAINTS);

        // invalid tags
        assertParseFailure(parser, PREAMBLE_WHITESPACE + TASK_NAME_DESC_PLAY
                + TASK_DEADLINE_DESC + TASK_DESCRIPTION_DESC_PLAY + INVALID_TASK_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);
    }


}
