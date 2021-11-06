package safeforhall.logic.parser;

import static java.util.Objects.requireNonNull;

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
        String fileName = ParserUtil.parseImportFileName(args);

        return new ImportCommand(fileName);
    }
}
