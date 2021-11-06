package safeforhall.logic.parser;

import static java.util.Objects.requireNonNull;

import safeforhall.logic.commands.ExportCommand;
import safeforhall.logic.parser.exceptions.ParseException;

public class ExportCommandParser implements Parser<ExportCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ExportCommand
     * and returns a ExportCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ExportCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String fileName = ParserUtil.parseExportFileName(args);

        return new ExportCommand(fileName);
    }
}
