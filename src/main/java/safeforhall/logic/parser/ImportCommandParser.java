package safeforhall.logic.parser;

import static java.util.Objects.requireNonNull;
import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import safeforhall.logic.commands.ImportCommand;
import safeforhall.logic.parser.exceptions.ParseException;

public class ImportCommandParser implements Parser<ImportCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ImportCommand
     * and returns a ImportCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ImportCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
        }

        String[] givenKeywords = trimmedArgs.split("\\s+");

        return new ImportCommand(givenKeywords[0]);
    }
}
