package seedu.teachbook.logic.parser;

import static seedu.teachbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.teachbook.logic.parser.CliSyntax.PREFIX_COLUMN;

import java.util.List;
import java.util.stream.Stream;

import seedu.teachbook.logic.commands.PrintCommand;
import seedu.teachbook.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new PrintClassCommand object
 */
public class PrintCommandParser implements Parser<PrintCommand> {

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the SelectClassCommand
     * and returns a SelectClassCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public PrintCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COLUMN);

        List<String> columnList = argMultimap.getAllValues(PREFIX_COLUMN);

        if (!arePrefixesPresent(argMultimap)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PrintCommand.MESSAGE_USAGE));
        }

        return new PrintCommand(columnList);
    }

}
