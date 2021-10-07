package seedu.address.logic.parser.friends;

import org.junit.jupiter.api.*;
import seedu.address.logic.commands.friends.*;
import seedu.address.model.friend.*;
import seedu.address.testutil.*;

import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.*;
import static seedu.address.testutil.TypicalFriends.*;

public class AddFriendCommandParserTest {
    private final AddFriendCommandParser parser = new AddFriendCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Friend expectedFriend = new FriendBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + FRIEND_ID_DESC_BOB
                , new AddFriendCommand(expectedFriend));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + FRIEND_ID_DESC_BOB,
                new AddFriendCommand(expectedFriend));


        // multiple friendId - last friendId accepted
        assertParseSuccess(parser, NAME_DESC_BOB + FRIEND_ID_DESC_AMY
                 + FRIEND_ID_DESC_BOB, new AddFriendCommand(expectedFriend));

        // multiple tags - all accepted
        Friend expectedFriendMultipleTags = new FriendBuilder(BOB)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + FRIEND_ID_DESC_BOB
                , new AddFriendCommand(expectedFriendMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Friend expectedFriend = new FriendBuilder(AMY).withGames().build();
        assertParseSuccess(parser, NAME_DESC_AMY + FRIEND_ID_DESC_AMY,
                new AddFriendCommand(expectedFriend));
    }
}
