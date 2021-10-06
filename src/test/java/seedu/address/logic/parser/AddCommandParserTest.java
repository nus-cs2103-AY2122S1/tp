package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.FRIEND_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FRIEND_ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GAME_DESC_APEX_LEGENDS;
import static seedu.address.logic.commands.CommandTestUtil.GAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GAME_DESC_CSGO;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GAME_APEX_LEGENDS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GAME_CSGO;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalFriends.AMY;
import static seedu.address.testutil.TypicalFriends.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.friend.Friend;
import seedu.address.testutil.FriendBuilder;

public class AddCommandParserTest {
    private final AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Friend expectedFriend = new FriendBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + FRIEND_ID_DESC_BOB
                + GAME_DESC_BOB, new AddCommand(expectedFriend));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + FRIEND_ID_DESC_BOB + GAME_DESC_BOB,
                new AddCommand(expectedFriend));


        // multiple friendId - last friendId accepted
        assertParseSuccess(parser, NAME_DESC_BOB + FRIEND_ID_DESC_AMY
                + GAME_DESC_BOB + FRIEND_ID_DESC_BOB, new AddCommand(expectedFriend));

        // multiple tags - all accepted
        Friend expectedFriendMultipleTags = new FriendBuilder(BOB).withGames(VALID_GAME_APEX_LEGENDS, VALID_GAME_CSGO)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + FRIEND_ID_DESC_BOB
                + GAME_DESC_APEX_LEGENDS + GAME_DESC_CSGO, new AddCommand(expectedFriendMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Friend expectedFriend = new FriendBuilder(AMY).withGames().build();
        assertParseSuccess(parser, NAME_DESC_AMY + FRIEND_ID_DESC_AMY,
                new AddCommand(expectedFriend));
    }

    //    @Test
    //    public void parse_compulsoryFieldMissing_failure() {
    //        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);
    //
    //        // missing name prefix
    //        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
    //                expectedMessage);
    //
    //        // missing phone prefix
    //        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
    //                expectedMessage);
    //
    //        // missing email prefix
    //        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
    //                expectedMessage);
    //
    //        // missing address prefix
    //        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
    //                expectedMessage);
    //
    //        // all prefixes missing
    //        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
    //                expectedMessage);
    //    }
    //
    //    @Test
    //    public void parse_invalidValue_failure() {
    //        // invalid name
    //        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
    //                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, FriendName.MESSAGE_CONSTRAINTS);
    //
    //        // invalid phone
    //        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
    //                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);
    //
    //        // invalid email
    //        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
    //                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);
    //
    //        // invalid address
    //        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
    //                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);
    //
    //        // invalid tag
    //        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
    //                + INVALID_TAG_DESC + VALID_GAME_APEX_LEGENDS, Tag.MESSAGE_CONSTRAINTS);
    //
    //        // two invalid values, only first invalid value reported
    //        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
    //                FriendName.MESSAGE_CONSTRAINTS);
    //
    //        // non-empty preamble
    //        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
    //                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
    //                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    //    }
}
