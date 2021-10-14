package seedu.address.logic.parser.games;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_GET;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.game.GameId.MESSAGE_INVALID_CHARACTERS_IN_GAME_ID;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.games.GetGameCommand;
import seedu.address.model.game.GameId;

public class GetGameCommandParserTest {

    private GetGameCommandParser parser = new GetGameCommandParser();

    @Test
    public void parse_emptyArgsWithNoFlag_throwsParseException() {
        String emptyArgs1 = "";
        String emptyArgs2 = "    ";
        String emptyArgs3 = "--get";
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetGameCommand.MESSAGE_USAGE);

        assertParseFailure(parser, emptyArgs1, expectedMessage);
        assertParseFailure(parser, emptyArgs2, expectedMessage);
        assertParseFailure(parser, emptyArgs3, expectedMessage);
    }

    @Test
    public void parse_emptyArgsWithFlag_throwsParseException() {
        String emptyArgs1 = " " + FLAG_GET.getFlag();
        String emptyArgs2 = " " + FLAG_GET.getFlag() + "          ";
        String expectedMessage = MESSAGE_INVALID_CHARACTERS_IN_GAME_ID;

        assertParseFailure(parser, emptyArgs1, expectedMessage);
        assertParseFailure(parser, emptyArgs2, expectedMessage);
    }

    @Test
    public void parse_multipleWordArgsWithFlag_throwsParseException() {
        String emptyArgs = " " + FLAG_GET.getFlag() + "mine craft";
        String expectedMessage = MESSAGE_INVALID_CHARACTERS_IN_GAME_ID;

        assertParseFailure(parser, emptyArgs, expectedMessage);
    }

    @Test
    public void parse_singleWordArgsWithFlag_correctCommand() {
        String validArgs = "minecraft";
        GetGameCommand expectedCommand = new GetGameCommand(new GameId(validArgs));
        assertParseSuccess(parser, " " + FLAG_GET + " " + validArgs, expectedCommand);
    }
}
