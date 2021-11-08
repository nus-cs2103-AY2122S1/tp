package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESERVATION;

import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteReservationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.reservation.Id;

/**
 * Parses input arguments and creates a new DeleteReservationCommand object.
 */
public class DeleteReservationCommandParser extends DeleteCommandParser implements Parser<DeleteReservationCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteReservationCommand
     * and returns a DeleteReservationCommand object for execution.
     *
     * @param args the input arguments related delete reservation command to be parsed.
     * @return DeleteReservationCommand the class for process input delete reservation command string.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteReservationCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_RESERVATION, PREFIX_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_RESERVATION, PREFIX_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteReservationCommand.MESSAGE_USAGE));
        }

        String ids = argMultimap.getValue(PREFIX_ID).get();
        seedu.address.model.member.Id memberId =
                ParserUtil.parseMemberId(ids.substring(0, seedu.address.model.member.Id.LENGTH));
        Id reservationId =
                ParserUtil.parseReservationId(ids.substring(seedu.address.model.member.Id.LENGTH));

        return new DeleteReservationCommand(memberId, reservationId);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
