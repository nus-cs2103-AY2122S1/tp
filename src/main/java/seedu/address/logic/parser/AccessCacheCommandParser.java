package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INTERNAL_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_KEY_DIRECTION;

import seedu.address.logic.commands.AccessCacheCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AccessCacheCommand object.
 */
public class AccessCacheCommandParser implements Parser<Command> {

    public static final String UNKNOWN_KEY = "Unknown Key found in AccessCacheCommandParser!";

    @Override
    public Command parse(String internalCommandString) throws ParseException {
        requireNonNull(internalCommandString);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(internalCommandString, PREFIX_KEY_DIRECTION);
        if (argMultimap.getValue(PREFIX_KEY_DIRECTION).isEmpty()) {

            throw new ParseException(MESSAGE_INVALID_INTERNAL_COMMAND_FORMAT);
        }

        String key = argMultimap.getValue(PREFIX_KEY_DIRECTION).get();

        if (!AccessCacheCommand.isValidKey(key)) {
            throw new ParseException(UNKNOWN_KEY);
        }

        return new AccessCacheCommand(key);
    }
}
