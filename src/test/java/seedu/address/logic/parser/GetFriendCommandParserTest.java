package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND_SPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.friends.GetFriendCommand;
import seedu.address.logic.parser.friends.GetFriendCommandParser;
import seedu.address.model.friend.FriendIdMatchesKeywordPredicate;

public class GetFriendCommandParserTest {

    private GetFriendCommandParser parser = new GetFriendCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetFriendCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgsNoFlag_returnsGetCommand() {
        // no leading and trailing whitespaces
        String friendId = "95352563";
        GetFriendCommand expectedGetFriendCommand =
                new GetFriendCommand(new FriendIdMatchesKeywordPredicate(friendId));
        assertParseSuccess(parser, friendId, expectedGetFriendCommand);

        // leading and trailing whitespaces
        assertParseSuccess(parser, "    " + friendId + "    \t", expectedGetFriendCommand);
    }

    @Test
    public void parse_validArgsFriendFlag_returnsGetCommand() {
        // no additional whitespaces
        String friendId = "95352563";
        GetFriendCommand expectedGetFriendCommand =
                new GetFriendCommand(new FriendIdMatchesKeywordPredicate(friendId));
        assertParseSuccess(parser, " " + FLAG_FRIEND_SPACE + friendId, expectedGetFriendCommand);

        // leading, within and trailing whitespaces
        assertParseSuccess(parser, "    " + FLAG_FRIEND_SPACE + "    " + friendId
                + "    \t", expectedGetFriendCommand);
    }

}
