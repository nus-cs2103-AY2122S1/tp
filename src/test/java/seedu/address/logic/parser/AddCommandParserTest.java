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
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.TIMESLOT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TIMESLOT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMESLOT_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalEvents.AMY;
import static seedu.address.testutil.TypicalEvents.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.event.Date;
import seedu.address.model.event.Event;
import seedu.address.model.event.Location;
import seedu.address.model.event.Name;
import seedu.address.model.event.TimeSlot;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EventBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Event expectedEvent = new EventBuilder(BOB).withTags(VALID_TAG_FRIEND).withRemark(VALID_REMARK_BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + DATE_DESC_BOB + TIMESLOT_DESC_BOB
                + LOCATION_DESC_BOB + TAG_DESC_FRIEND + REMARK_DESC_BOB, new AddCommand(expectedEvent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + DATE_DESC_BOB + TIMESLOT_DESC_BOB
                + LOCATION_DESC_BOB + TAG_DESC_FRIEND + REMARK_DESC_BOB, new AddCommand(expectedEvent));

        // multiple dates - last date accepted
        assertParseSuccess(parser, NAME_DESC_BOB + DATE_DESC_AMY + DATE_DESC_BOB + TIMESLOT_DESC_BOB
                + LOCATION_DESC_BOB + TAG_DESC_FRIEND + REMARK_DESC_BOB, new AddCommand(expectedEvent));

        // multiple time - last time accepted
        assertParseSuccess(parser, NAME_DESC_BOB + DATE_DESC_BOB + TIMESLOT_DESC_AMY + TIMESLOT_DESC_BOB
                + LOCATION_DESC_BOB + TAG_DESC_FRIEND + REMARK_DESC_BOB, new AddCommand(expectedEvent));

        // multiple location - last location accepted
        assertParseSuccess(parser, NAME_DESC_BOB + DATE_DESC_BOB + TIMESLOT_DESC_BOB + TIMESLOT_DESC_BOB
                + LOCATION_DESC_BOB + TAG_DESC_FRIEND + REMARK_DESC_BOB, new AddCommand(expectedEvent));

        // multiple tags - all accepted
        Event expectedEventMultipleTags = new EventBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .withRemark(VALID_REMARK_BOB).build();
        assertParseSuccess(parser, NAME_DESC_BOB + DATE_DESC_BOB + TIMESLOT_DESC_BOB + LOCATION_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + REMARK_DESC_BOB, new AddCommand(expectedEventMultipleTags));

        // multiple remarks - last remark accepted
        assertParseSuccess(parser, NAME_DESC_BOB + DATE_DESC_BOB + TIMESLOT_DESC_BOB + TIMESLOT_DESC_BOB
                + LOCATION_DESC_BOB + TAG_DESC_FRIEND + REMARK_DESC_AMY + REMARK_DESC_BOB,
                new AddCommand(expectedEvent));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags and remarks
        Event expectedEvent = new EventBuilder(AMY).withTags().withRemark("").build();
        assertParseSuccess(parser, NAME_DESC_AMY + DATE_DESC_AMY + TIMESLOT_DESC_AMY + LOCATION_DESC_AMY,
                new AddCommand(expectedEvent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + DATE_DESC_BOB + TIMESLOT_DESC_BOB + LOCATION_DESC_BOB,
                expectedMessage);

        // missing date prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_DATE_BOB + TIMESLOT_DESC_BOB + LOCATION_DESC_BOB,
                expectedMessage);

        // missing time prefix
        assertParseFailure(parser, NAME_DESC_BOB + DATE_DESC_BOB + VALID_TIMESLOT_BOB + LOCATION_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + DATE_DESC_BOB + TIMESLOT_DESC_BOB + VALID_LOCATION_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_DATE_BOB + VALID_TIMESLOT_BOB + VALID_LOCATION_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + DATE_DESC_BOB + TIMESLOT_DESC_BOB + LOCATION_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + REMARK_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_DATE_DESC + TIMESLOT_DESC_BOB + LOCATION_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + REMARK_DESC_BOB, Date.MESSAGE_CONSTRAINTS);

        // invalid time
        assertParseFailure(parser, NAME_DESC_BOB + DATE_DESC_BOB + INVALID_TIME_DESC + LOCATION_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + REMARK_DESC_BOB, TimeSlot.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + DATE_DESC_BOB + TIMESLOT_DESC_BOB + INVALID_LOCATION_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + REMARK_DESC_BOB, Location.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + DATE_DESC_BOB + TIMESLOT_DESC_BOB + LOCATION_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND + REMARK_DESC_BOB, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + DATE_DESC_BOB + TIMESLOT_DESC_BOB + INVALID_LOCATION_DESC
                + REMARK_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + DATE_DESC_BOB + TIMESLOT_DESC_BOB
                + LOCATION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + REMARK_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
