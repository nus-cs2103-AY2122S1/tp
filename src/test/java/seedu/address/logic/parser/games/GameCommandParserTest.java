package seedu.address.logic.parser.games;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GAME_ID_CSGO;
import static seedu.address.logic.parser.CliSyntax.FLAG_ADD;
import static seedu.address.logic.parser.CliSyntax.FLAG_DELETE;
import static seedu.address.logic.parser.CliSyntax.FLAG_GET;
import static seedu.address.logic.parser.CliSyntax.FLAG_POSTFIX;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

class GameCommandParserTest {

    private GameCommandParser parser = new GameCommandParser();

    @Test
    public void parse_invalidFlag_exceptionThrown() {
        // unrecognised flag
        assertThrows(ParseException.class, () -> parser.parse(" --invalid"));
        // empty input
        assertThrows(ParseException.class, () -> parser.parse(""));
    }

    @Test
    public void parse_validFlag_success() throws ParseException {
        // add game flag
        String addInput = FLAG_POSTFIX.getFlag() + FLAG_ADD.getFlag() + VALID_GAME_ID_CSGO;
        assertEquals(parser.parse(addInput), new AddGameCommandParser().parse(addInput));

        // delete game flag
        String deleteInput = FLAG_POSTFIX.getFlag() + FLAG_DELETE.getFlag() + VALID_GAME_ID_CSGO;
        assertEquals(parser.parse(deleteInput), new DeleteGameCommandParser().parse(deleteInput));

        // get game flag
        String getInput = FLAG_POSTFIX.getFlag() + FLAG_GET.getFlag() + VALID_GAME_ID_CSGO;
        assertEquals(parser.parse(getInput), new GetGameCommandParser().parse(getInput));

        // list game flag
        String listInput = " --list";
        assertEquals(parser.parse(listInput), new ListGameCommandParser().parse(listInput));
    }

}
