package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.FavoriteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class FavoriteCommandParser implements Parser<FavoriteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FavoriteCommand
     * and returns a FavoriteCommand object for execution.
     *
     * @param args to be parsed.
     * @return FavoriteCommand containing the target index to be favorited.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FavoriteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new FavoriteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FavoriteCommand.MESSAGE_USAGE), pe);
        }
    }
}
