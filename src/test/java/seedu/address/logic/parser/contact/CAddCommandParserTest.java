package seedu.address.logic.parser.contact;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.general.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.general.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.general.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.general.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.general.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.general.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.general.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.general.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.general.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.general.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.general.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.general.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.general.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.general.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.general.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.general.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.general.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.general.CommandTestUtil.TELEGRAM_DESC_AMY;
import static seedu.address.logic.commands.general.CommandTestUtil.TELEGRAM_DESC_BOB;
import static seedu.address.logic.commands.general.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.general.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.general.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.general.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.general.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.general.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.general.CommandTestUtil.ZOOM_DESC_AMY;
import static seedu.address.logic.commands.general.CommandTestUtil.ZOOM_DESC_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalContacts.AMY;
import static seedu.address.testutil.TypicalContacts.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.contact.CAddCommand;
import seedu.address.model.common.Address;
import seedu.address.model.common.Name;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ContactBuilder;

public class CAddCommandParserTest {
    private CAddCommandParser parser = new CAddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Contact expectedContact = new ContactBuilder(BOB).withTags(VALID_TAG_FRIEND).withMarked(false).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + ZOOM_DESC_BOB + TELEGRAM_DESC_BOB + TAG_DESC_FRIEND,
            new CAddCommand(expectedContact));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + ZOOM_DESC_BOB + TELEGRAM_DESC_BOB + TAG_DESC_FRIEND,
            new CAddCommand(expectedContact));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + ZOOM_DESC_BOB + TELEGRAM_DESC_BOB + TAG_DESC_FRIEND,
            new CAddCommand(expectedContact));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + ZOOM_DESC_BOB + TELEGRAM_DESC_BOB + TAG_DESC_FRIEND,
            new CAddCommand(expectedContact));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + ZOOM_DESC_BOB + TELEGRAM_DESC_BOB + TAG_DESC_FRIEND,
            new CAddCommand(expectedContact));

        // multiple tags - all accepted
        Contact expectedContactMultipleTags = new ContactBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
            .withMarked(false).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + ZOOM_DESC_BOB + TELEGRAM_DESC_BOB + TAG_DESC_FRIEND,
            new CAddCommand(expectedContactMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Contact expectedContact = new ContactBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                + TELEGRAM_DESC_AMY + ZOOM_DESC_AMY,
                new CAddCommand(expectedContact));

        // Missing phone
        Contact expectedContact2 = new ContactBuilder(BOB).withPhone(null).build();
        assertParseSuccess(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TELEGRAM_DESC_BOB + ZOOM_DESC_BOB + TAG_DESC_FRIEND + TAG_DESC_HUSBAND,
            new CAddCommand(expectedContact2));

        // Missing all optional fields
        Contact expectedContact3 = new ContactBuilder(AMY).withAddress(null).withTags().withPhone(null)
                .withTelegramHandle(null).withZoomLink(null).build();
        assertParseSuccess(parser, NAME_DESC_AMY + EMAIL_DESC_AMY, new CAddCommand(expectedContact3));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CAddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CAddCommand.MESSAGE_USAGE));
    }
}
