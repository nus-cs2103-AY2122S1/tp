package seedu.unify.logic.parser;

import static seedu.unify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.unify.logic.commands.CommandTestUtil.DATE_DESC_AMY;
import static seedu.unify.logic.commands.CommandTestUtil.DATE_DESC_BOB;
import static seedu.unify.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.unify.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.unify.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.unify.logic.commands.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.unify.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.unify.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.unify.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.unify.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.unify.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.unify.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.unify.logic.commands.CommandTestUtil.TIME_DESC_AMY;
import static seedu.unify.logic.commands.CommandTestUtil.TIME_DESC_BOB;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TIME_BOB;
import static seedu.unify.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.unify.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.unify.testutil.TypicalTasks.AMY;
import static seedu.unify.testutil.TypicalTasks.BOB;

import org.junit.jupiter.api.Test;

import seedu.unify.logic.commands.AddCommand;
import seedu.unify.model.task.Tag;
import seedu.unify.model.task.Date;
import seedu.unify.model.task.Name;
import seedu.unify.model.task.Task;
import seedu.unify.model.task.Time;
import seedu.unify.testutil.TaskBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + TIME_DESC_BOB
                + DATE_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + TIME_DESC_BOB
                + DATE_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + TIME_DESC_AMY + TIME_DESC_BOB
                + DATE_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple dates - last date accepted
        assertParseSuccess(parser, NAME_DESC_BOB + TIME_DESC_BOB + DATE_DESC_AMY
                + DATE_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple tags - all accepted
        Task expectedTaskMultipleTags = new TaskBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + TIME_DESC_BOB + DATE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Task expectedTask = new TaskBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + TIME_DESC_AMY + DATE_DESC_AMY,
                new AddCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + TIME_DESC_BOB + DATE_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_TIME_BOB + DATE_DESC_BOB,
                expectedMessage);

        // missing date prefix
        assertParseFailure(parser, NAME_DESC_BOB + TIME_DESC_BOB + VALID_DATE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_TIME_BOB + VALID_DATE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + TIME_DESC_BOB + DATE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_TIME_DESC + DATE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Time.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, NAME_DESC_BOB + TIME_DESC_BOB + INVALID_DATE_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Date.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + TIME_DESC_BOB + DATE_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + TIME_DESC_BOB + INVALID_DATE_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + TIME_DESC_BOB
                + DATE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
