package seedu.address.logic.parser.friends;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_GAME;
import static seedu.address.logic.parser.CliSyntax.FLAG_UNLINK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.friends.UnlinkFriendCommand;
import seedu.address.model.friend.FriendId;
import seedu.address.model.game.GameId;

public class UnlinkFriendCommandParserTest {

    private UnlinkFriendCommandParser parser = new UnlinkFriendCommandParser();
    private final String invalidCommandFormatMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            UnlinkFriendCommand.MESSAGE_USAGE);

    @Test
    public void parse_validArgs_returnsUnlinkFriendCommand() {
        String userInput = " " + FLAG_UNLINK + "Draco " + FLAG_GAME + "CSGO";
        FriendId friendId = new FriendId("Draco");
        GameId gameId = new GameId("CSGO");
        assertParseSuccess(parser, userInput, new UnlinkFriendCommand(friendId, gameId));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String userInput = " " + FLAG_UNLINK + "Draco";
        assertParseFailure(parser, userInput, invalidCommandFormatMessage);
    }

    @Test
    public void parse_noInput_failure() {
        // no input
        assertParseFailure(parser, "", invalidCommandFormatMessage);

        // whitespace only
        assertParseFailure(parser, " ", invalidCommandFormatMessage);
    }
}
