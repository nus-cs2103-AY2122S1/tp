package seedu.notor.logic.parser.person;

import static java.util.Objects.requireNonNull;

import seedu.notor.commons.core.Messages;
import seedu.notor.commons.core.index.Index;
import seedu.notor.commons.exceptions.IllegalValueException;
import seedu.notor.logic.commands.person.PersonNoteCommand;
import seedu.notor.logic.parser.ArgumentMultimap;
import seedu.notor.logic.parser.ArgumentTokenizer;
import seedu.notor.logic.parser.exceptions.ParseException;

public class PersonNoteCommandParser extends PersonCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the NoteCommand
     * and returns a NoteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public PersonNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        Index index;

        try {
            index = super.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    PersonNoteCommand.MESSAGE_USAGE), ive);
        }

        return new PersonNoteCommand(index);
    }
}
