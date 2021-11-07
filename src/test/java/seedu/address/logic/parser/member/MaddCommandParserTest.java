package seedu.address.logic.parser.member;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_POSITION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.POSITION_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.POSITION_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalMembers.AMY;
import static seedu.address.testutil.TypicalMembers.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.member.MaddCommand;
import seedu.address.logic.parser.member.MaddCommandParser;
import seedu.address.model.module.Name;
import seedu.address.model.module.member.Address;
import seedu.address.model.module.member.Email;
import seedu.address.model.module.member.Member;
import seedu.address.model.module.member.Phone;
import seedu.address.model.module.member.position.Position;
import seedu.address.testutil.MemberBuilder;

public class MaddCommandParserTest {
    private MaddCommandParser parser = new MaddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Member expectedMember = new MemberBuilder(BOB).withPositions(VALID_POSITION_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + POSITION_DESC_FRIEND, new MaddCommand(expectedMember));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + POSITION_DESC_FRIEND, new MaddCommand(expectedMember));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + POSITION_DESC_FRIEND, new MaddCommand(expectedMember));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + POSITION_DESC_FRIEND, new MaddCommand(expectedMember));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + POSITION_DESC_FRIEND, new MaddCommand(expectedMember));

        // multiple tags - all accepted
        Member expectedMemberMultiplePositions =
                new MemberBuilder(BOB).withPositions(VALID_POSITION_FRIEND, VALID_POSITION_HUSBAND)
                        .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + POSITION_DESC_HUSBAND + POSITION_DESC_FRIEND, new MaddCommand(expectedMemberMultiplePositions));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Member expectedMember = new MemberBuilder(AMY).withPositions().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new MaddCommand(expectedMember));

        // no email
        expectedMember = new MemberBuilder(AMY).withEmail(null).build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + ADDRESS_DESC_AMY + POSITION_DESC_FRIEND,
                new MaddCommand(expectedMember));

        // no address
        expectedMember = new MemberBuilder(AMY).withAddress(null).build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + POSITION_DESC_FRIEND,
                new MaddCommand(expectedMember));

        // no address and no email
        expectedMember = new MemberBuilder(AMY).withEmail(null).withAddress(null).build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + POSITION_DESC_FRIEND,
                new MaddCommand(expectedMember));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MaddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + POSITION_DESC_HUSBAND + POSITION_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + POSITION_DESC_HUSBAND + POSITION_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + POSITION_DESC_HUSBAND + POSITION_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + POSITION_DESC_HUSBAND + POSITION_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid position
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_POSITION_DESC + VALID_POSITION_FRIEND, Position.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + POSITION_DESC_HUSBAND + POSITION_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MaddCommand.MESSAGE_USAGE));

    }
}
