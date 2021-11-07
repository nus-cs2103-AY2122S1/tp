package seedu.address.logic.parser.guest;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT_NUMBER;

import seedu.address.logic.commands.guest.DeleteGuestCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

// @@author NicolasCwy
public class DeleteGuestCommandParser implements Parser<DeleteGuestCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteGuestCommand
     * and returns an DeleteGuestCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteGuestCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PASSPORT_NUMBER);

        if (argMultimap.getValue(PREFIX_PASSPORT_NUMBER).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGuestCommand.MESSAGE_USAGE));
        }

        return new DeleteGuestCommand(ParserUtil.parsePassportNumber(
                argMultimap.getValue(PREFIX_PASSPORT_NUMBER).get()));
    }
}
