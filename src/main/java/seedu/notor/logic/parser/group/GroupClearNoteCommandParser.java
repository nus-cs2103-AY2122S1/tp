package seedu.notor.logic.parser.group;

import seedu.notor.logic.commands.group.GroupClearNoteCommand;
import seedu.notor.logic.parser.exceptions.ParseException;

public class GroupClearNoteCommandParser extends GroupCommandParser {

    public GroupClearNoteCommandParser(String unparsedIndex) throws ParseException {
        super(unparsedIndex, null);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the GroupClearNoteCommand
     * and returns a GroupClearNoteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public GroupClearNoteCommand parse() throws ParseException {
        return new GroupClearNoteCommand(index);
    }
}
