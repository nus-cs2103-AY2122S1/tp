package seedu.notor.logic.parser.person;

import seedu.notor.logic.commands.person.PersonArchiveAllCommand;
import seedu.notor.logic.parser.exceptions.ParseException;

public class PersonArchiveAllCommandParser extends PersonCommandParser {
    public PersonArchiveAllCommandParser() throws ParseException {
        super(null, null);
    }

    /**
     * Parses the given arguments in the context of the PersonArchiveAllCommand
     * and returns a PersonArchiveAllCommand object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format.
     */
    @Override
    public PersonArchiveAllCommand parse() throws ParseException {
        return new PersonArchiveAllCommand();
    }
}
