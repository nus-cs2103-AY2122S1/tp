package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.GetCustomerReservingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new GetCustomerReservingCommand object
 */
public class GetCustomerReservingCommandParser implements Parser<GetCustomerReservingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GetCustomerReservingCommand
     * and returns a GetCustomerReservingCommand object for execution.
     *
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public GetCustomerReservingCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new GetCustomerReservingCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetCustomerReservingCommand.MESSAGE_USAGE),
                    pe
            );
        }
    }
}
