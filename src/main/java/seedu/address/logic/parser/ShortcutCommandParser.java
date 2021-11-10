package seedu.address.logic.parser;

import seedu.address.logic.commands.ShortcutCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ShortcutCommand object
 */
public class ShortcutCommandParser implements Parser<ShortcutCommand> {

    @Override
    public ShortcutCommand parse(String userInput) throws ParseException {
        String trimmedInput = userInput.trim();
        if (trimmedInput.isBlank()) {
            throw new ParseException(ShortcutCommand.MESSAGE_USAGE);
        }
        return new ShortcutCommand(trimmedInput);
    }
}
