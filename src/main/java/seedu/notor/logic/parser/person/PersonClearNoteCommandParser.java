package seedu.notor.logic.parser.person;

import seedu.notor.logic.commands.person.PersonClearNoteCommand;
import seedu.notor.logic.parser.exceptions.ParseException;

public class PersonClearNoteCommandParser extends PersonCommandParser {
    public PersonClearNoteCommandParser(String unparsedIndex) throws ParseException {
        super(unparsedIndex, null);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the NoteCommand
     * and returns a NoteClearCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public PersonClearNoteCommand parse() throws ParseException {
        return new PersonClearNoteCommand(index);
    }
}
