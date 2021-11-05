package seedu.address.logic.parser;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ImportCommand object
 */
public class ImportCommandParser implements Parser<ImportCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ImportCommand
     * and returns a ImportCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ImportCommand parse(String args) throws ParseException {
        String fileName = args.trim();

        if (!fileName.endsWith(".json") || fileName.equals(".json")) {
            throw new ParseException(String.format("%s.\n%s", ImportCommand.MESSAGE_WRONG_FORMAT,
                    ImportCommand.MESSAGE_USAGE));

        }

        return new ImportCommand(fileName);
    }

}
