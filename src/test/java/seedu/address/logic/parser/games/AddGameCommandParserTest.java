package seedu.address.logic.parser.games;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.GAME_DESC_CSGO;
import static seedu.address.logic.commands.CommandTestUtil.GAME_DESC_VALORANT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CliSyntax.FLAG_ADD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalGames.CSGO;
import static seedu.address.testutil.TypicalGames.VALORANT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.games.AddGameCommand;
import seedu.address.model.game.Game;
import seedu.address.testutil.GameBuilder;

public class AddGameCommandParserTest {
    private final AddGameCommandParser parser = new AddGameCommandParser();
    private final String invalidCommandFormatMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            AddGameCommand.MESSAGE_USAGE);

    @Test
    public void parse_allFieldsPresent_success() {
        Game expectedGameCsgo = new GameBuilder(CSGO).build();
        Game expectedGameValorant = new GameBuilder(VALORANT).build();

        // normal command
        assertParseSuccess(parser, GAME_DESC_VALORANT,
                new AddGameCommand(expectedGameValorant));

        // with whitespace preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GAME_DESC_CSGO,
                new AddGameCommand(expectedGameCsgo));
    }

    @Test
    public void parse_compulsoryFieldsMissing_failure() {
        // name id missing
        assertParseFailure(parser, NAME_DESC_AMY, invalidCommandFormatMessage);
    }

    @Test
    public void parse_wrongFormatName_failure() {
        // --add without GAME_ID
        String noNameInput = FLAG_ADD + " ";
        assertParseFailure(parser, noNameInput, invalidCommandFormatMessage);

        // whitespace is not a valid GAME_ID
        assertParseFailure(parser, noNameInput + "    ", invalidCommandFormatMessage);
    }

    @Test
    public void parse_noInput_failure() {
        // no input
        assertParseFailure(parser, "", invalidCommandFormatMessage);

        // whitespace only
        assertParseFailure(parser, " ", invalidCommandFormatMessage);
    }
}
