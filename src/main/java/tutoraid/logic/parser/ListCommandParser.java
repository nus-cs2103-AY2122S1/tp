package tutoraid.logic.parser;

import static java.util.Objects.requireNonNull;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LIST_ALL;

import tutoraid.logic.commands.ListCommand;
import tutoraid.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    public ListCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LIST_ALL);
        if (argMultimap.getValue(PREFIX_LIST_ALL).isEmpty()) {
            return new ListCommand(false);
        } else {
            return new ListCommand(true);
        }
    }
}
