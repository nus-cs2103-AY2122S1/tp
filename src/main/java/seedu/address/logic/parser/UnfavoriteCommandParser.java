package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnfavoriteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
public class UnfavoriteCommandParser implements Parser<UnfavoriteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnfavoriteCommand
     * and returns a UnfavoriteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnfavoriteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UnfavoriteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnfavoriteCommand.MESSAGE_USAGE), pe);
        }
    }
}
