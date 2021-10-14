package seedu.address.logic.parser.games;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalGames.CSGO;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.games.DeleteGameCommand;

public class DeleteGameCommandParserTest {
    private DeleteGameCommandParser parser = new DeleteGameCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteGameCommand() {
        String userInput = " " + DeleteGameCommand.COMMAND_WORD + " " + CSGO.getGameId();
        assertParseSuccess(parser, userInput,
                new DeleteGameCommand(CSGO.getGameId()));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, DeleteGameCommand.COMMAND_WORD, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteGameCommand.MESSAGE_USAGE));
    }
}
