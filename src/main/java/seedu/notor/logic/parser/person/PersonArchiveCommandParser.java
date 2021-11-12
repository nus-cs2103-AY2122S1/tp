package seedu.notor.logic.parser.person;

import seedu.notor.logic.commands.person.PersonArchiveCommand;
import seedu.notor.logic.parser.exceptions.ParseException;

public class PersonArchiveCommandParser extends PersonCommandParser {
    public PersonArchiveCommandParser(String unparsedIndex) throws ParseException {
        super(unparsedIndex, null);
    }

    /**
     * Parses the given {@code Index} of arguments in the context of the PersonArchiveCommand
     * and returns a PersonArchiveCommand object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format.
     */
    @Override
    public PersonArchiveCommand parse() throws ParseException {
        return new PersonArchiveCommand(index);
    }
}
