package seedu.address.logic.parser.guest;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PASSPORT_NUMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ROOM_NUMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.PASSPORT_NUMBER_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.PASSPORT_NUMBER_DESC_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.ROOM_NUMBER_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.ROOM_NUMBER_DESC_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_DELUXE_ROOM;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_VIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSPORT_NUMBER_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DELUXE_ROOM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_VIP;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.guest.TypicalGuests.ALICE_GUEST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.guest.CheckInNewGuestCommand;
import seedu.address.model.commonattributes.Email;
import seedu.address.model.commonattributes.Name;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.PassportNumber;
import seedu.address.model.guest.RoomNumber;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.guest.GuestBuilder;

public class CheckInCommandParserTest {

    private CheckInNewGuestCommandParser parser = new CheckInNewGuestCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        Guest expectedGuest =
                new GuestBuilder(ALICE_GUEST).withTags(VALID_TAG_DELUXE_ROOM).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_ALICE + EMAIL_DESC_ALICE + ROOM_NUMBER_DESC_ALICE
                        + TAG_DESC_DELUXE_ROOM + PASSPORT_NUMBER_DESC_ALICE,
                new CheckInNewGuestCommand(expectedGuest));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_BENSON + NAME_DESC_ALICE + EMAIL_DESC_ALICE + ROOM_NUMBER_DESC_ALICE
                        + TAG_DESC_DELUXE_ROOM + PASSPORT_NUMBER_DESC_ALICE,
                new CheckInNewGuestCommand(expectedGuest));

        // multiple room numbers - last room number accepted
        assertParseSuccess(parser, NAME_DESC_ALICE + EMAIL_DESC_ALICE + ROOM_NUMBER_DESC_BENSON + ROOM_NUMBER_DESC_ALICE
                        + TAG_DESC_DELUXE_ROOM + PASSPORT_NUMBER_DESC_ALICE,
                new CheckInNewGuestCommand(expectedGuest));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_ALICE + EMAIL_DESC_BENSON + EMAIL_DESC_ALICE + ROOM_NUMBER_DESC_ALICE
                        + TAG_DESC_DELUXE_ROOM + PASSPORT_NUMBER_DESC_ALICE,
                new CheckInNewGuestCommand(expectedGuest));

        // multiple passport numbers - last passport number accepted
        assertParseSuccess(parser, NAME_DESC_ALICE + EMAIL_DESC_ALICE + ROOM_NUMBER_DESC_ALICE
                        + TAG_DESC_DELUXE_ROOM + PASSPORT_NUMBER_DESC_BENSON + PASSPORT_NUMBER_DESC_ALICE,
                new CheckInNewGuestCommand(expectedGuest));

        // multiple tags - all accepted
        Guest expectedGuestMultipleTags =
                new GuestBuilder(ALICE_GUEST).withTags(VALID_TAG_VIP, VALID_TAG_DELUXE_ROOM).build();

        assertParseSuccess(parser, NAME_DESC_ALICE + EMAIL_DESC_ALICE + ROOM_NUMBER_DESC_ALICE
                        + TAG_DESC_VIP + TAG_DESC_DELUXE_ROOM + PASSPORT_NUMBER_DESC_ALICE,
                new CheckInNewGuestCommand(expectedGuestMultipleTags));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags

        Guest expectedGuest = new GuestBuilder(ALICE_GUEST).withTags().build();
        assertParseSuccess(parser, NAME_DESC_ALICE + EMAIL_DESC_ALICE + ROOM_NUMBER_DESC_ALICE
                        + PASSPORT_NUMBER_DESC_ALICE,
                new CheckInNewGuestCommand(expectedGuest));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckInNewGuestCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_ALICE + EMAIL_DESC_ALICE + ROOM_NUMBER_DESC_ALICE
                + PASSPORT_NUMBER_DESC_ALICE, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_ELLE + VALID_EMAIL_ALICE + ROOM_NUMBER_DESC_ALICE
                + PASSPORT_NUMBER_DESC_ALICE, expectedMessage);

        // missing room number prefix
        assertParseFailure(parser, NAME_DESC_ELLE + EMAIL_DESC_ALICE + VALID_ROOM_NUMBER_ALICE
                + PASSPORT_NUMBER_DESC_ALICE, expectedMessage);

        // missing passport number prefix
        assertParseFailure(parser, NAME_DESC_ELLE + EMAIL_DESC_ALICE + ROOM_NUMBER_DESC_ALICE
                + VALID_PASSPORT_NUMBER_ALICE, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
                VALID_NAME_ALICE + VALID_EMAIL_ALICE + VALID_ROOM_NUMBER_ALICE + VALID_PASSPORT_NUMBER_ALICE,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + EMAIL_DESC_ALICE + ROOM_NUMBER_DESC_ALICE
                + TAG_DESC_VIP + TAG_DESC_DELUXE_ROOM + PASSPORT_NUMBER_DESC_ALICE, Name.MESSAGE_CONSTRAINTS);

        // invalid room number
        assertParseFailure(parser, NAME_DESC_ELLE + EMAIL_DESC_ALICE + INVALID_ROOM_NUMBER_DESC
                + TAG_DESC_VIP + TAG_DESC_DELUXE_ROOM + PASSPORT_NUMBER_DESC_ALICE, RoomNumber.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_ELLE + INVALID_EMAIL_DESC + ROOM_NUMBER_DESC_ALICE
                + TAG_DESC_VIP + TAG_DESC_DELUXE_ROOM + PASSPORT_NUMBER_DESC_ALICE, Email.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_ELLE + EMAIL_DESC_ALICE + ROOM_NUMBER_DESC_ALICE
                + INVALID_TAG_DESC + TAG_DESC_DELUXE_ROOM + PASSPORT_NUMBER_DESC_ALICE, Tag.MESSAGE_CONSTRAINTS);

        // invalid passport number
        assertParseFailure(parser, NAME_DESC_ELLE + EMAIL_DESC_ALICE + ROOM_NUMBER_DESC_ALICE
                        + TAG_DESC_VIP + TAG_DESC_DELUXE_ROOM + INVALID_PASSPORT_NUMBER_DESC,
                PassportNumber.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + EMAIL_DESC_ALICE + INVALID_ROOM_NUMBER_DESC
                + PASSPORT_NUMBER_DESC_ALICE, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_ELLE + EMAIL_DESC_ALICE
                        + ROOM_NUMBER_DESC_ALICE + TAG_DESC_VIP + TAG_DESC_DELUXE_ROOM + PASSPORT_NUMBER_DESC_ALICE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckInNewGuestCommand.MESSAGE_USAGE));
    }
}
