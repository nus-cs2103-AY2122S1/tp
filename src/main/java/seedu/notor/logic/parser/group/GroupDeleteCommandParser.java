package seedu.notor.logic.parser.group;

import seedu.notor.logic.commands.group.GroupDeleteCommand;
import seedu.notor.logic.parser.exceptions.ParseException;

public class GroupDeleteCommandParser extends GroupCommandParser {
    public GroupDeleteCommandParser(String unparsedIndex) throws ParseException {
        super(unparsedIndex, null);
    }

    /**
     * Parses the given {@code Index} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public GroupDeleteCommand parse() throws ParseException {
        return new GroupDeleteCommand(index);
    }
}
