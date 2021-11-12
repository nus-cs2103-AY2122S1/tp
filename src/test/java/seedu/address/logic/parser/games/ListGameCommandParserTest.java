package seedu.address.logic.parser.games;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.games.ListGameCommand;
import seedu.address.model.game.GameIdContainsKeywordPredicate;

public class ListGameCommandParserTest {

    private ListGameCommandParser parser = new ListGameCommandParser();

    @Test
    public void parse_emptyArgs_success() {
        String userInput = "";
        ListGameCommand expectedListGameCommand =
                new ListGameCommand(new GameIdContainsKeywordPredicate(userInput));
        assertParseSuccess(parser, userInput, expectedListGameCommand);
    }

    @Test
    public void parse_validArgs_returnsListCommand() {
        // filter by gameId and keyword test
        String userInput = " --list test";
        String expectedParsedKeyword = "test";
        ListGameCommand expectedListGameCommand =
                new ListGameCommand(new GameIdContainsKeywordPredicate(expectedParsedKeyword));
        assertParseSuccess(parser, userInput, expectedListGameCommand);

        // with game filter but without keyword (defaults to list all games)
        userInput = " --list";
        expectedListGameCommand = new ListGameCommand(new GameIdContainsKeywordPredicate(""));
        assertParseSuccess(parser, userInput, expectedListGameCommand);
    }

}
