package seedu.address.logic.parser;

import seedu.address.commons.util.FileUtil;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class ExportCommandParser implements Parser<ExportCommand>{

    public static final String MESSAGE_NOT_CSV = "Failed! "
            + "Entries at following rows are wrongly formatted:";

    private static final String exportsDirectoryPath = System.getProperty("user.dir")
            + File.separator
            + "exports";

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ExportCommand parse(String args) throws ParseException {
        String filePath = exportsDirectoryPath + File.separator + args;
        if (!args.endsWith(".csv") || !FileUtil.isValidPath(filePath)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE));
        }
        return new ExportCommand(filePath);
    }
}
