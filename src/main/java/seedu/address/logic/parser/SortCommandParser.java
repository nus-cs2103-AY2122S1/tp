package seedu.address.logic.parser;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        boolean reverseOrder = false;

        if (!trimmedArgs.isEmpty()) {
            if (trimmedArgs.equals("-r")) {
                reverseOrder = true;
            }
        } else {
            reverseOrder = false;
        }

        return new SortCommand(reverseOrder);
    }

}

