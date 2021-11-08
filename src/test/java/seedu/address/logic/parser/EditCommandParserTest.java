package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.TIMESLOT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TIMESLOT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENDTIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENDTIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTTIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTTIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.event.Date;
import seedu.address.model.event.Location;
import seedu.address.model.event.Name;
import seedu.address.model.event.TimeSlot;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditEventDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_DATE_DESC, Date.MESSAGE_CONSTRAINTS); // invalid date
        assertParseFailure(parser, "1" + INVALID_TIME_DESC, TimeSlot.MESSAGE_CONSTRAINTS); // invalid time
        assertParseFailure(parser, "1" + INVALID_LOCATION_DESC, Location.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid date followed by valid time
        assertParseFailure(parser, "1" + INVALID_DATE_DESC + TIMESLOT_DESC_AMY, Date.MESSAGE_CONSTRAINTS);

        // valid date followed by invalid date. The test case for invalid date followed by valid date
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + DATE_DESC_BOB + INVALID_DATE_DESC, Date.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Event} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_TIME_DESC + VALID_LOCATION_AMY + VALID_DATE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_EVENT;
        String userInput = targetIndex.getOneBased() + DATE_DESC_BOB + TAG_DESC_HUSBAND
                + TIMESLOT_DESC_AMY + LOCATION_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND + REMARK_DESC_AMY;

        EditCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_AMY)
                .withDate(VALID_DATE_BOB).withTimeSlot(VALID_STARTTIME_AMY, VALID_ENDTIME_AMY)
                .withLocation(VALID_LOCATION_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .withRemark(VALID_REMARK_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + DATE_DESC_BOB + TIMESLOT_DESC_AMY;

        EditCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withDate(VALID_DATE_BOB)
                .withTimeSlot(VALID_STARTTIME_AMY, VALID_ENDTIME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_EVENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + DATE_DESC_AMY;
        descriptor = new EditEventDescriptorBuilder().withDate(VALID_DATE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // time
        userInput = targetIndex.getOneBased() + TIMESLOT_DESC_AMY;
        descriptor = new EditEventDescriptorBuilder().withTimeSlot(VALID_STARTTIME_AMY, VALID_ENDTIME_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + LOCATION_DESC_AMY;
        descriptor = new EditEventDescriptorBuilder().withLocation(VALID_LOCATION_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditEventDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // remark
        userInput = targetIndex.getOneBased() + REMARK_DESC_AMY;
        descriptor = new EditEventDescriptorBuilder().withRemark(VALID_REMARK_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + DATE_DESC_AMY + LOCATION_DESC_AMY + TIMESLOT_DESC_AMY
                + TAG_DESC_FRIEND + DATE_DESC_AMY + LOCATION_DESC_AMY + TIMESLOT_DESC_AMY + TAG_DESC_FRIEND
                + REMARK_DESC_AMY + DATE_DESC_BOB + LOCATION_DESC_BOB + TIMESLOT_DESC_BOB + TAG_DESC_HUSBAND
                + REMARK_DESC_BOB;

        EditCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withDate(VALID_DATE_BOB)
                .withTimeSlot(VALID_STARTTIME_BOB, VALID_ENDTIME_BOB)
                .withLocation(VALID_LOCATION_BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .withRemark(VALID_REMARK_BOB)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + INVALID_DATE_DESC + DATE_DESC_BOB;
        EditCommand.EditEventDescriptor descriptor =
                new EditEventDescriptorBuilder().withDate(VALID_DATE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + TIMESLOT_DESC_BOB + INVALID_DATE_DESC + LOCATION_DESC_BOB
                + DATE_DESC_BOB;
        descriptor = new EditEventDescriptorBuilder().withDate(VALID_DATE_BOB)
                .withTimeSlot(VALID_STARTTIME_BOB, VALID_ENDTIME_BOB)
                .withLocation(VALID_LOCATION_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_EVENT;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
