package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkCompleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class MarkCompleteCommandParser implements Parser<MarkCompleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkCompleteCommand
     * and returns a MarkCompleteCOmmand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public MarkCompleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new MarkCompleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCompleteCommand.MESSAGE_USAGE), pe);
        }
    }
}
