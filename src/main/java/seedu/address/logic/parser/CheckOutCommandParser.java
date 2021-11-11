package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CheckOutCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CheckOutCommand object
 */
public class CheckOutCommandParser implements Parser<CheckOutCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CheckOutCommand
     * and returns a CheckOutCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public CheckOutCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            Index roomIndex = ParserUtil.parseIndex(args);
            return new CheckOutCommand(roomIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckOutCommand.MESSAGE_USAGE), pe);
        }
    }

}
