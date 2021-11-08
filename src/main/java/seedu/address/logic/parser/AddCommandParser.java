package seedu.address.logic.parser;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddCommandParser object.
 */
public abstract class AddCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @param args the input arguments related add command.
     * @return AddCommand the class for process input add command string.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public abstract AddCommand parse(String args) throws ParseException;
}
