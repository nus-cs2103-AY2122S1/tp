package seedu.notor.logic.parser.person;

import seedu.notor.logic.commands.person.PersonUnarchiveCommand;
import seedu.notor.logic.parser.exceptions.ParseException;

public class PersonUnarchiveCommandParser extends PersonCommandParser {
    public PersonUnarchiveCommandParser(String unparsedIndex) throws ParseException {
        super(unparsedIndex, null);
    }

    /**
     * Parses the given {@code Index} of arguments in the context of the PersonUnarchiveCommand
     * and returns a PersonUnarchiveCommand object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format.
     */
    @Override
    public PersonUnarchiveCommand parse() throws ParseException {
        return new PersonUnarchiveCommand(index);
    }
}
