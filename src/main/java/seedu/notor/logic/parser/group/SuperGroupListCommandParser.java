package seedu.notor.logic.parser.group;

import seedu.notor.logic.commands.group.GroupCommand;
import seedu.notor.logic.commands.group.SuperGroupListCommand;
import seedu.notor.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class SuperGroupListCommandParser extends GroupCommandParser {
    public SuperGroupListCommandParser(String arguments) throws ParseException {
        super(null, arguments);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the SuperGroupListCommand
     * and returns a SuperGroupListCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SuperGroupListCommand parse(String args) throws ParseException {
        return new SuperGroupListCommand();
    }

    @Override
    public GroupCommand parse() throws ParseException {
        return parse(arguments);
    }
}
