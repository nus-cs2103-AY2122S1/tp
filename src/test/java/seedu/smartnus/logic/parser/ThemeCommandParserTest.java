package seedu.smartnus.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.smartnus.commons.core.theme.DarkTheme;
import seedu.smartnus.commons.core.theme.LightTheme;
import seedu.smartnus.logic.commands.ThemeCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;

public class ThemeCommandParserTest {

    public static final String INVALID_ARGUMENT = "@TASedg";
    private final ThemeCommandParser parser = new ThemeCommandParser();

    @Test
    public void parse_fail_test() {
        assertParseFailure(parser, INVALID_ARGUMENT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_get_theme() throws ParseException {
        assertEquals(new ThemeCommand(new DarkTheme()), parser.parse(" dark"));
        assertEquals(new ThemeCommand(new LightTheme()), parser.parse(" light"));
    }
}
