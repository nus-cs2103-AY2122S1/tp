package tutoraid.logic.parser;

import static java.util.Objects.requireNonNull;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LIST_ALL;

import tutoraid.logic.commands.ListCommand;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of ListCommand
     * and returns a ListCommand object for execution. The command object can instruct
     * TutorAid to show student fields or hide them when the list of students is displayed.
     *
     * @param args Arguments provided by the user command
     * @return A ListCommand object corresponding to the command
     */
    public ListCommand parse(String args) {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LIST_ALL);
        if (argMultimap.getValue(PREFIX_LIST_ALL).isPresent()) {
            return new ListCommand(true);
        } else { // all unexpected input is ignored
            return new ListCommand(false);
        }
    }
}
