package seedu.notor.logic.parser.group;

import seedu.notor.logic.commands.group.SubGroupListCommand;
import seedu.notor.logic.parser.exceptions.ParseException;

public class SubGroupListCommandParser extends GroupCommandParser {

    public SubGroupListCommandParser(String unparsedIndex) throws ParseException {
        super(unparsedIndex, null);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the SubGroupListCommandParser
     * and returns a SubGroupListCommandParser object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SubGroupListCommand parse() throws ParseException {
        return new SubGroupListCommand(index);
    }
}
