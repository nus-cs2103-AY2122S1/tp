package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE;

import java.nio.file.Path;

import seedu.address.commons.util.FileUtil;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ExportCommandParser implements Parser<ExportCommand> {
    @Override
    public ExportCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FILE);

        Path path;
        if (argMultimap.getValue(PREFIX_FILE).isPresent()) {
            path = ParserUtil.parsePath(argMultimap.getValue(PREFIX_FILE).get(), ".csv");
        } else {
            path = generateNewPath(0);
        }

        return new ExportCommand(path);
    }

    private Path generateNewPath(int tries) throws ParseException {
        String pathString = String.format(ExportCommand.BASE_PATH, tries == 0 ? "" : "(" + tries + ")");
        Path path = ParserUtil.parsePath(pathString, ".csv");
        if (FileUtil.isFileExists(path)) {
            return generateNewPath(tries + 1);
        } else {
            return path;
        }
    }
}
