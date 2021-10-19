package seedu.notor.logic.parser.group;

import static seedu.notor.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.group.GroupCommand;
import seedu.notor.logic.parser.Parser;
import seedu.notor.logic.parser.ParserUtil;
import seedu.notor.logic.parser.exceptions.ParseException;

public abstract class GroupCommandParser extends Parser<GroupCommand> {
    protected String arguments;
    protected Index index;

    protected GroupCommandParser(String unparsedIndex, String arguments) throws ParseException {
        this.arguments = arguments;
        parseIndex(unparsedIndex);
    }

    private void parseIndex(String unparsedIndex) throws ParseException {
        if (unparsedIndex == null) {
            index = null;
        } else {
            try {
                index = ParserUtil.parseIndex(unparsedIndex);
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_USAGE),
                        pe);
            }
        }
    }

    public abstract GroupCommand parse() throws ParseException;
}
