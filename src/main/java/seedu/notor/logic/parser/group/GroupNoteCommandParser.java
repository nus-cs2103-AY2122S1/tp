package seedu.notor.logic.parser.group;

import seedu.notor.logic.commands.group.GroupNoteCommand;
import seedu.notor.logic.parser.exceptions.ParseException;

public class GroupNoteCommandParser extends GroupCommandParser {

    public GroupNoteCommandParser(String unparsedIndex) throws ParseException {
        super(unparsedIndex, null);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the NoteCommand
     * and returns a NoteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public GroupNoteCommand parse() throws ParseException {
        return new GroupNoteCommand(index);
    }
}
