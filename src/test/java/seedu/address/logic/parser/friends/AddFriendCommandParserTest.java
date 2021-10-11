package seedu.address.logic.parser.friends;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.FRIEND_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FRIEND_ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalFriends.AMY;
import static seedu.address.testutil.TypicalFriends.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.friends.AddFriendCommand;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;
import seedu.address.model.friend.FriendName;
import seedu.address.testutil.FriendBuilder;

public class AddFriendCommandParserTest {
    private final AddFriendCommandParser parser = new AddFriendCommandParser();
    // TODO set a different message if preferred
    private final String invalidCommandFormatMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
           FriendId.MESSAGE_CONSTRAINTS);

    @Test
    public void parse_allFieldsPresent_success() {
        Friend expectedFriend = new FriendBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, FRIEND_ID_DESC_BOB + PREAMBLE_WHITESPACE + NAME_DESC_BOB,
                new AddFriendCommand(expectedFriend));

        // multiple names - last name accepted
        assertParseSuccess(parser, FRIEND_ID_DESC_BOB + NAME_DESC_AMY + NAME_DESC_BOB ,
                new AddFriendCommand(expectedFriend));
    }

    @Test
    public void parse_compulsoryFieldsMissing_failure() {
        // id missing
        assertParseFailure(parser, NAME_DESC_AMY, invalidCommandFormatMessage);
    }

    @Test
    public void parse_wrongFormatName_failure() {
        // --name without FRIEND_NAME
        String noNameInput = FRIEND_ID_DESC_AMY + " " + FLAG_FRIEND_NAME;
        assertParseFailure(parser, noNameInput, FriendName.MESSAGE_CONSTRAINTS);

        // whitespace is not a valid name
        assertParseFailure(parser, noNameInput + "  ", FriendName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_noInput_failure() {
        // no input
        assertParseFailure(parser, "", invalidCommandFormatMessage);

        // whitespace only
        assertParseFailure(parser, " ", invalidCommandFormatMessage);
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no friend name
        Friend expectedFriend = new FriendBuilder().withFriendId(AMY.getFriendId()
                .toString()).buildNoName();
        assertParseSuccess(parser, FRIEND_ID_DESC_AMY,
                new AddFriendCommand(expectedFriend));
    }
}
