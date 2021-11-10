package seedu.address.logic.parser;

import seedu.address.logic.commands.RemoveShortcutCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ShortcutCommand object
 */
public class RemoveShortcutCommandParser implements Parser<RemoveShortcutCommand> {

    @Override
    public RemoveShortcutCommand parse(String userInput) throws ParseException {
        String trimmedInput = userInput.trim();
        if (trimmedInput.isBlank()) {
            throw new ParseException(RemoveShortcutCommand.MESSAGE_USAGE);
        }
        return new RemoveShortcutCommand(trimmedInput);
    }
}
