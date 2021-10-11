package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_FILEPATH_NOT_EXIST;

import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.commons.util.FileUtil;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates an ImportCommand object
 */
public class ImportCommandParser implements Parser {

    /**
     * Parses the given {@code String} of arguments in the context of the ImportCommand
     * and returns an ImportCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ImportCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String argsTrimmed = args.trim();
        if (argsTrimmed.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
        }
        Path filePath = Paths.get(argsTrimmed);
        if (!FileUtil.isFileExists(filePath)) {
            throw new ParseException(
                    String.format(MESSAGE_FILEPATH_NOT_EXIST, argsTrimmed)
            );
        }
        return new ImportCommand(filePath);
    }
}
