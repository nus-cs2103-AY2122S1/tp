package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.SwitchCommand.MESSAGE_ADDRESSBOOK_NOT_FOUND;

import java.nio.file.Path;

import seedu.address.logic.commands.SwitchCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and create a new SwitchCommand object.
 */
public class SwitchCommandParser implements Parser<SwitchCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns an ViewCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public SwitchCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();
        String lowercaseArgs = trimmedArgs.toLowerCase();

        if (lowercaseArgs.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwitchCommand.MESSAGE_USAGE));
        }

        Path filePath;
        try {
            filePath = ParserUtil.parseFilePath(lowercaseArgs);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_ADDRESSBOOK_NOT_FOUND, trimmedArgs), pe);
        }

        return new SwitchCommand(filePath);
    }
}
