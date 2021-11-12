package seedu.notor.logic.parser.person;

import seedu.notor.logic.commands.person.PersonNoteCommand;
import seedu.notor.logic.parser.exceptions.ParseException;

public class PersonNoteCommandParser extends PersonCommandParser {
    public PersonNoteCommandParser(String unparsedIndex) throws ParseException {
        super(unparsedIndex, null);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the NoteCommand
     * and returns a NoteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public PersonNoteCommand parse() throws ParseException {
        return new PersonNoteCommand(index);
    }
}
