package seedu.address.logic.parser;

import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class ExportCommandParser implements Parser<ExportCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ExportCommand parse(String args) throws ParseException {
        String fileName = args.trim();

        if (!fileName.endsWith(".json") || fileName.equals(".json")) {
            throw new ParseException(String.format("%s.\n%s", ExportCommand.MESSAGE_WRONG_FORMAT,
                    ExportCommand.MESSAGE_USAGE));
        }

        return new ExportCommand(fileName);
    }

}
