package seedu.address.logic.parser;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class ImportCommandParser implements Parser {
    public ImportCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String argsTrimmed = args.trim();
        if (argsTrimmed.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
        }
        Path filePath = Paths.get(argsTrimmed);
        return new ImportCommand(filePath);
    }
}
