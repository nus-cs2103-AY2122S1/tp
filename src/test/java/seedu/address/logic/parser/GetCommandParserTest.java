package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GetCommand;
import seedu.address.model.friend.FriendIdMatchesKeywordPredicate;

public class GetCommandParserTest {

    private GetCommandParser parser = new GetCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgsNoFlag_returnsGetCommand() {
        // no leading and trailing whitespaces
        String friendId = "95352563";
        GetCommand expectedGetCommand =
                new GetCommand(new FriendIdMatchesKeywordPredicate(friendId));
        assertParseSuccess(parser, friendId, expectedGetCommand);

        // leading and trailing whitespaces
        assertParseSuccess(parser, "    " + friendId + "    \t", expectedGetCommand);
    }

    @Test
    public void parse_validArgsFriendFlag_returnsGetCommand() {
        // no additional whitespaces
        String friendId = "95352563";
        GetCommand expectedGetCommand =
                new GetCommand(new FriendIdMatchesKeywordPredicate(friendId));
        assertParseSuccess(parser, " " + FLAG_FRIEND + friendId, expectedGetCommand);

        // leading, within and trailing whitespaces
        assertParseSuccess(parser, "    " + FLAG_FRIEND + "    " + friendId + "    \t", expectedGetCommand);
    }

}
