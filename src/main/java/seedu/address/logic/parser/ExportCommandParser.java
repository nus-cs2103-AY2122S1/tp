package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.io.File;

import seedu.address.commons.util.CsvWriter;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class ExportCommandParser implements Parser<ExportCommand> {

    private static final String EXPORTS_DIRECTORY_PATH = System.getProperty("user.dir")
            + File.separator
            + "exports";

    private static final String REGEX = "^[a-zA-Z0-9._ -]+$";
    private static final String CSV_EXTENSION = ".csv";

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ExportCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (!trimmedArgs.matches(REGEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE));
        }
        String filePath = EXPORTS_DIRECTORY_PATH + File.separator + trimmedArgs + CSV_EXTENSION;
        return new ExportCommand(filePath, new CsvWriter());
    }
}
