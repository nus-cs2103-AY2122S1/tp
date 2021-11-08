package seedu.smartnus.logic.parser;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.logic.commands.ThemeCommand.LIGHT_KEYWORD;

import seedu.smartnus.commons.core.theme.DarkTheme;
import seedu.smartnus.commons.core.theme.LightTheme;
import seedu.smartnus.commons.core.theme.Theme;
import seedu.smartnus.logic.commands.ThemeCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;

public class ThemeCommandParser implements Parser<ThemeCommand> {

    @Override
    public ThemeCommand parse(String args) throws ParseException {
        if (ParserUtil.isValidTheme(args.trim())) {
            return new ThemeCommand(getThemeClass(args.trim()));
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE));
        }
    }

    private Theme getThemeClass(String validTheme) {
        if (validTheme.equals(LIGHT_KEYWORD)) {
            return new LightTheme();
        } else {
            return new DarkTheme();
        }
    }
}
