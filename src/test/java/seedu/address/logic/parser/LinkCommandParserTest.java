package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.LinkCommand;
import seedu.address.model.friend.FriendId;

class LinkCommandParserTest {
    private LinkCommandParser parser = new LinkCommandParser();

    @Test
    public void parse_validArgs_returnsLinkCommand() {
        String userInput = " Draco --g CSGO:Silver1";
        HashMap<String, String> games = new HashMap<>();
        games.put("CSGO", "Silver1");
        assertParseSuccess(parser, userInput, new LinkCommand(new FriendId("Draco"), games));
    }
}
