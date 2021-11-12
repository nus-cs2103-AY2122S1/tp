package seedu.notor.logic.parser.person;

import seedu.notor.logic.commands.person.PersonArchiveShowCommand;
import seedu.notor.logic.parser.exceptions.ParseException;

public class PersonArchiveShowCommandParser extends PersonCommandParser {
    public PersonArchiveShowCommandParser() throws ParseException {
        super(null, null);
    }

    /**
     * Parses the given {@code Index} of arguments in the context of the PersonArchiveShowCommand
     * and returns a PersonArchiveShowCommand object for execution.
     */
    @Override
    public PersonArchiveShowCommand parse() {
        return new PersonArchiveShowCommand();
    }
}
