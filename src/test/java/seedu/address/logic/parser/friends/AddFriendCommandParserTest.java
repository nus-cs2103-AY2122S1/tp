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
import seedu.address.model.friend.FriendName;
import seedu.address.testutil.FriendBuilder;

public class AddFriendCommandParserTest {
    private final AddFriendCommandParser parser = new AddFriendCommandParser();
    private final String invalidCommandFormatMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
           AddFriendCommand.MESSAGE_USAGE);

    @Test
    public void parse_allFieldsPresent_success() {
        // BOB has valid name and friendId
        Friend expectedFriendBob = new FriendBuilder(BOB).build();
        Friend expectedFriendAmy = new FriendBuilder(AMY).build();

        // normal command
        assertParseSuccess(parser, FRIEND_ID_DESC_BOB + NAME_DESC_BOB,
                new AddFriendCommand(expectedFriendBob));

        // with whitespace preamble
        assertParseSuccess(parser, FRIEND_ID_DESC_AMY + PREAMBLE_WHITESPACE + NAME_DESC_AMY,
                new AddFriendCommand(expectedFriendAmy));

        // multiple names - last name accepted
        assertParseSuccess(parser, FRIEND_ID_DESC_BOB + NAME_DESC_AMY + NAME_DESC_BOB ,
                new AddFriendCommand(expectedFriendBob));
    }

    @Test
    public void parse_compulsoryFieldsMissing_failure() {
        // name id missing
        assertParseFailure(parser, NAME_DESC_AMY, invalidCommandFormatMessage);
        assertParseFailure(parser, NAME_DESC_BOB, invalidCommandFormatMessage);
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
        Friend expectedFriendAmyDefaultName = new FriendBuilder().withFriendId(AMY.getFriendId()
                .toString()).withFriendName(null).build();
        assertParseSuccess(parser, FRIEND_ID_DESC_AMY,
                new AddFriendCommand(expectedFriendAmyDefaultName));
    }
}
