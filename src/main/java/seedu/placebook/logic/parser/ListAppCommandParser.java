package seedu.placebook.logic.parser;

import seedu.placebook.logic.commands.ListAppCommand;
import seedu.placebook.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindAppCommand object
 */
public class ListAppCommandParser implements Parser<ListAppCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindAppCommand
     * and returns a FindAppCommand object for execution.
     * @return ListAppCommand
     * @throws ParseException if the user input does not conform to the expected format
     */
    public ListAppCommand parse (String args) throws ParseException {
        String sortBy = args.trim();

        return new ListAppCommand(sortBy);
    }

}
