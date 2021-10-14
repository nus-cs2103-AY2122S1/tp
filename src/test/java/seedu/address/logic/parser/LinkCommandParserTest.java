package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.LinkCommand;
import seedu.address.model.friend.FriendId;
import seedu.address.model.friend.gamefriendlink.UserName;
import seedu.address.model.game.GameId;


class LinkCommandParserTest {
    private LinkCommandParser parser = new LinkCommandParser();
    private final String invalidCommandFormatMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            LinkCommand.MESSAGE_USAGE);

    @Test
    public void parse_validArgs_returnsLinkCommand() {
        String userInput = " --friend Draco --game CSGO --user GoldNova";
        FriendId friendId = new FriendId("Draco");
        GameId gameId = new GameId("CSGO");
        UserName userName = new UserName("GoldNova");
        assertParseSuccess(parser, userInput, new LinkCommand(friendId, gameId, userName));
    }

    @Test
    public void parse_validArgsInDifferentOrder_returnsLinkCommand() {
        String userInput = " --game CSGO --user GoldNova --friend Draco";
        FriendId friendId = new FriendId("Draco");
        GameId gameId = new GameId("CSGO");
        UserName userName = new UserName("GoldNova");
        assertParseSuccess(parser, userInput, new LinkCommand(friendId, gameId, userName));
    }

    @Test
    public void parse_compulsoryFieldsMissing_failure() {
        String userInput = " --game CSGO --user SmurfLord";
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
