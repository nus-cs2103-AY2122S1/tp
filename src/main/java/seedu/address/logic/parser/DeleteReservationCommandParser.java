package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteReservationCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteReservationCommandParser implements Parser<DeleteReservationCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteReservationCommand
     * and returns a DeleteReservationCommand object for execution.
     *
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public DeleteReservationCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteReservationCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteReservationCommand.MESSAGE_USAGE),
                    pe
            );
        }
    }
}
