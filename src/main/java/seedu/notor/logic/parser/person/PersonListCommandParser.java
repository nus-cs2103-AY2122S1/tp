package seedu.notor.logic.parser.person;

import seedu.notor.logic.commands.person.PersonCommand;
import seedu.notor.logic.commands.person.PersonListCommand;
import seedu.notor.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class PersonListCommandParser extends PersonCommandParser {
    public PersonListCommandParser(String arguments) throws ParseException {
        super(null, arguments);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the PersonListCommand
     * and returns a PersonListCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public PersonListCommand parse(String args) throws ParseException {
        return new PersonListCommand();
    }

    @Override
    public PersonCommand parse() throws ParseException {
        return parse(arguments);
    }
}
