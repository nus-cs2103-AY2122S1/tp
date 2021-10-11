package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.friends.ListFriendCommand;
import seedu.address.logic.parser.friends.ListFriendCommandParser;
import seedu.address.model.friend.FriendIdContainsKeywordPredicate;

public class GetFriendListFriendCommandParserTest {

    private ListFriendCommandParser parser = new ListFriendCommandParser();

    @Test
    public void parse_emptyArgs_success() {
        String userInput = " ";
        ListFriendCommand expectedListFriendCommand =
                new ListFriendCommand(new FriendIdContainsKeywordPredicate(userInput));
        assertParseSuccess(parser, userInput, expectedListFriendCommand);
    }

    @Test
    public void parse_validArgs_returnsListCommand() {
        // filter by friend and keyword test
        String userInput = " --friend test";
        String expectedParsedKeyword = "test";
        ListFriendCommand expectedListFriendCommand =
                new ListFriendCommand(new FriendIdContainsKeywordPredicate(expectedParsedKeyword));
        // TODO Update after list command is updated
        // assertParseSuccess(parser, userInput, expectedListFriendCommand);

        // with friend filter but without keyword (defaults to list all friends)

        // userInput = " --friend";
        // expectedListFriendCommand = new ListFriendCommand(new FriendIdContainsKeywordPredicate(""));
        // assertParseSuccess(parser, userInput, expectedListFriendCommand);

        // without friend filter (defaults to friend) but with keyword test
        userInput = "test";
        expectedListFriendCommand = new ListFriendCommand(new FriendIdContainsKeywordPredicate("test"));
        assertParseSuccess(parser, userInput, expectedListFriendCommand);


    }

}
