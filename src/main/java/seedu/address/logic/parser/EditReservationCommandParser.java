package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESERVATION;

import java.util.stream.Stream;

import seedu.address.logic.commands.EditReservationCommand;
import seedu.address.logic.commands.EditReservationCommand.EditReservationDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditReservationCommand object.
 */
public class EditReservationCommandParser extends EditCommandParser implements Parser<EditReservationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @param args the input arguments related edit reservation command to be parsed.
     * @return EditReservationCommand the class for process input edit reservation command string.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EditReservationCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_RESERVATION, PREFIX_ID,
                PREFIX_DATE_TIME, PREFIX_REMARK);

        if (!arePrefixesPresent(argMultimap, PREFIX_RESERVATION, PREFIX_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditReservationCommand.MESSAGE_USAGE));
        }

        String ids = argMultimap.getValue(PREFIX_ID).get();
        seedu.address.model.member.Id memberId =
                ParserUtil.parseMemberId(ids.substring(0, seedu.address.model.member.Id.LENGTH));
        seedu.address.model.reservation.Id reservationId =
                ParserUtil.parseReservationId(ids.substring(seedu.address.model.member.Id.LENGTH));

        EditReservationDescriptor editReservationDescriptor = new EditReservationDescriptor();
        if (argMultimap.getValue(PREFIX_DATE_TIME).isPresent()) {
            editReservationDescriptor.setDateTime(
                    ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATE_TIME).get()));
        }
        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            editReservationDescriptor.setRemark(
                    ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get()));
        }
        if (!editReservationDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditReservationCommand.MESSAGE_NOT_EDITED);
        }

        return new EditReservationCommand(memberId, reservationId, editReservationDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
