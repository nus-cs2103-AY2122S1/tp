package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PinCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code PinCommand} object.
 */
public class PinCommandParser implements Parser<PinCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code PinCommand}
     * and returns a {@code PinCommand} object for execution.
     *
     * @param args user input.
     * @return {@code PinCommand} which pins a contact at the specific index.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public PinCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new PinCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PinCommand.MESSAGE_USAGE), pe);
        }
    }

}
