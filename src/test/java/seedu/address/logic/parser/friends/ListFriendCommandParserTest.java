package seedu.address.logic.parser.friends;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.friends.ListFriendCommand;
import seedu.address.model.friend.FriendIdContainsKeywordPredicate;

public class ListFriendCommandParserTest {

    private ListFriendCommandParser parser = new ListFriendCommandParser();

    @Test
    public void parse_emptyArgs_success() {
        String userInput = "";
        ListFriendCommand expectedListFriendCommand =
                new ListFriendCommand(new FriendIdContainsKeywordPredicate(userInput));
        assertParseSuccess(parser, userInput, expectedListFriendCommand);
    }

    @Test
    public void parse_validArgs_returnsListCommand() {
        // filter by friend and keyword test
        String userInput = " --list test";
        String expectedParsedKeyword = "test";
        ListFriendCommand expectedListFriendCommand =
                new ListFriendCommand(new FriendIdContainsKeywordPredicate(expectedParsedKeyword));
        assertParseSuccess(parser, userInput, expectedListFriendCommand);

        // with friend filter but without keyword (defaults to list all friends)
        userInput = " --list";
        expectedListFriendCommand = new ListFriendCommand(new FriendIdContainsKeywordPredicate(""));
        assertParseSuccess(parser, userInput, expectedListFriendCommand);
    }

}
