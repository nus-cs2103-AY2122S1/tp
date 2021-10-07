package seedu.address.logic.parser.friends;

import static seedu.address.logic.commands.CommandTestUtil.FRIEND_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FRIEND_ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalFriends.AMY;
import static seedu.address.testutil.TypicalFriends.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.friends.AddFriendCommand;
import seedu.address.model.friend.Friend;
import seedu.address.testutil.FriendBuilder;

public class AddFriendCommandParserTest {
    private final AddFriendCommandParser parser = new AddFriendCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Friend expectedFriend = new FriendBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + FRIEND_ID_DESC_BOB,
                new AddFriendCommand(expectedFriend));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + FRIEND_ID_DESC_BOB,
                new AddFriendCommand(expectedFriend));


        // multiple friendId - last friendId accepted
        assertParseSuccess(parser, NAME_DESC_BOB + FRIEND_ID_DESC_AMY
                 + FRIEND_ID_DESC_BOB, new AddFriendCommand(expectedFriend));

        // multiple tags - all accepted
        Friend expectedFriendMultipleTags = new FriendBuilder(BOB)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + FRIEND_ID_DESC_BOB,
                new AddFriendCommand(expectedFriendMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Friend expectedFriend = new FriendBuilder(AMY).withGames().build();
        assertParseSuccess(parser, NAME_DESC_AMY + FRIEND_ID_DESC_AMY,
                new AddFriendCommand(expectedFriend));
    }
}
