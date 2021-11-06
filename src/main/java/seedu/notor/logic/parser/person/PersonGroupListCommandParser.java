package seedu.notor.logic.parser.person;

import seedu.notor.logic.commands.person.PersonCommand;
import seedu.notor.logic.commands.person.PersonGroupListCommand;
import seedu.notor.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object. Note that this index is a group index
 */
public class PersonGroupListCommandParser extends PersonCommandParser {
    public PersonGroupListCommandParser(String unparsedIndex) throws ParseException {
        super(unparsedIndex, null);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the PersonFindCommand
     * and returns a PersonFindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public PersonCommand parse() throws ParseException {
        return new PersonGroupListCommand(index);
    }
}
