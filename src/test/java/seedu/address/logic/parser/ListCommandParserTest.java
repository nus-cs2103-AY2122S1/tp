package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;
import seedu.address.model.friend.FriendIdContainsKeywordPredicate;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_emptyArgs_success() {
        String userInput = " ";
        ListCommand expectedListCommand = new ListCommand(new FriendIdContainsKeywordPredicate(userInput));
        assertParseSuccess(parser, userInput, expectedListCommand);
    }

    @Test
    public void parse_validArgs_returnsListCommand() {
        // filter by friend and keyword test
        String userInput = " --friend test";
        String expectedParsedKeyword = "test";
        ListCommand expectedListCommand = new ListCommand(new FriendIdContainsKeywordPredicate(expectedParsedKeyword));
        assertParseSuccess(parser, userInput, expectedListCommand);

        // with friend filter but without keyword (defaults to list all friends)
        userInput = " --friend";
        expectedListCommand = new ListCommand(new FriendIdContainsKeywordPredicate(""));
        assertParseSuccess(parser, userInput, expectedListCommand);

        // without friend filter (defaults to friend) but with keyword test
        userInput = "test";
        expectedListCommand = new ListCommand(new FriendIdContainsKeywordPredicate("test"));
        assertParseSuccess(parser, userInput, expectedListCommand);


    }

}
