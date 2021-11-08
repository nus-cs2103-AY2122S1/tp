package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ATTENDEE_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.ATTENDEE_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ATTENDEE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATETIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_VENUE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDEE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VENUE_1;
import static seedu.address.logic.commands.CommandTestUtil.VENUE_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.VENUE_DESC_2;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalAddressBookObjects.BIOLOGY_CONSULT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meeting.MeetCommand;
import seedu.address.model.meeting.Attendee;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.DescriptionType;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.MeetingBuilder;

public class MeetCommandParserTest {
    private MeetCommandParser parser = new MeetCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Meeting expectedMeeting = new MeetingBuilder(BIOLOGY_CONSULT).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DATETIME_DESC_1
                        + ATTENDEE_DESC_1 + TITLE_DESC_1 + VENUE_DESC_1,
                new MeetCommand(expectedMeeting));

        // multiple dateTime - last dateTime accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DATETIME_DESC_2
                        + DATETIME_DESC_1 + ATTENDEE_DESC_1 + TITLE_DESC_1 + VENUE_DESC_1,
                new MeetCommand(expectedMeeting));

        // multiple attendee - last attendee accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DATETIME_DESC_1
                        + ATTENDEE_DESC_2 + ATTENDEE_DESC_1 + TITLE_DESC_1 + VENUE_DESC_1,
                new MeetCommand(expectedMeeting));

        // multiple title - last title accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DATETIME_DESC_1
                        + ATTENDEE_DESC_1 + TITLE_DESC_2 + TITLE_DESC_1 + VENUE_DESC_1,
                new MeetCommand(expectedMeeting));

        // multiple venue- last venue accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DATETIME_DESC_1
                        + ATTENDEE_DESC_1 + TITLE_DESC_1 + VENUE_DESC_2 + VENUE_DESC_1,
                new MeetCommand(expectedMeeting));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MeetCommand.MESSAGE_USAGE);

        // missing dateTime prefix
        assertParseFailure(parser,
                 VALID_DATETIME_1 + ATTENDEE_DESC_1 + TITLE_DESC_1 + VENUE_DESC_1,
                expectedMessage);

        // missing title prefix
        assertParseFailure(parser,
                DATETIME_DESC_1 + ATTENDEE_DESC_1 + VALID_TITLE_1 + VENUE_DESC_1,
                expectedMessage);

        // missing attendee prefix
        assertParseFailure(parser,
                DATETIME_DESC_1 + VALID_ATTENDEE_1 + TITLE_DESC_1 + VENUE_DESC_1,
                expectedMessage);

        // missing venue prefix
        assertParseFailure(parser,
                DATETIME_DESC_1 + ATTENDEE_DESC_1 + TITLE_DESC_1 + VALID_ATTENDEE_1,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
                VALID_DATETIME_1 + VALID_ATTENDEE_1 + VALID_TITLE_1 + VALID_VENUE_1,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid dateTime
        assertParseFailure(parser,
                INVALID_DATETIME_DESC + ATTENDEE_DESC_1 + TITLE_DESC_1 + VENUE_DESC_1,
                DateTime.MESSAGE_CONSTRAINTS);

        // invalid attendee
        assertParseFailure(parser,
                DATETIME_DESC_1 + INVALID_ATTENDEE_DESC + TITLE_DESC_1 + VENUE_DESC_1,
                Attendee.MESSAGE_CONSTRAINTS);

        // invalid title
        assertParseFailure(parser,
                DATETIME_DESC_1 + ATTENDEE_DESC_1 + INVALID_TITLE_DESC + VENUE_DESC_1,
                Description.getMessageConstraints(DescriptionType.TITLE));

        // invalid venue
        assertParseFailure(parser,
                DATETIME_DESC_1 + ATTENDEE_DESC_1 + TITLE_DESC_1 + INVALID_VENUE_DESC,
                Description.getMessageConstraints(DescriptionType.VENUE));

        // two invalid values, only first invalid value reported
        assertParseFailure(parser,
                INVALID_DATETIME_DESC + INVALID_ATTENDEE_DESC + TITLE_DESC_1 + VENUE_DESC_1,
                DateTime.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY
                        + DATETIME_DESC_1 + ATTENDEE_DESC_1 + TITLE_DESC_1 + VENUE_DESC_1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MeetCommand.MESSAGE_USAGE));
    }
}
