package seedu.address.logic.parser;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.nio.file.Path;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

public class ImportCommandParser implements Parser<ImportCommand> {
    @Override
    public ImportCommand parse(String args) throws ParseException {
        requireNonNull(args);
        System.out.println("args: " + args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_FILE, PREFIX_GROUP, PREFIX_ASSESSMENT, PREFIX_TAG);

        if (argMultimap.getValue(PREFIX_FILE).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
        }

        Path filePath = argMultimap.getValue(PREFIX_FILE).map(Path::of).orElseThrow(() ->
                new ParseException(ImportCommand.MESSAGE_INVALID_FILE));

        int groupCount = getCount(argMultimap, PREFIX_GROUP);
        int assessmentCount = getCount(argMultimap, PREFIX_ASSESSMENT);
        int tagCount = getCount(argMultimap, PREFIX_TAG);

        return new ImportCommand(groupCount, assessmentCount, tagCount, filePath);
    }

    private int getCount(ArgumentMultimap argumentMultimap, Prefix prefix) throws ParseException{
        try {
            return argumentMultimap.getValue(prefix).map(String::trim).map(Integer::parseInt).orElse(0);
        } catch (NumberFormatException e) {
            throw new ParseException(ImportCommand.MESSAGE_INVALID_NUMBER);
        }
    }
}
