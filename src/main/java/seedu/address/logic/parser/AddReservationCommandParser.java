package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESERVATION;

import java.util.List;
import java.util.stream.Stream;

import seedu.address.commons.status.ExecutionStatus;
import seedu.address.logic.commands.AddReservationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.member.Member;
import seedu.address.model.reservation.DateTime;
import seedu.address.model.reservation.Remark;
import seedu.address.model.reservation.Reservation;

/**
 * Parses input arguments and creates a new AddReservationCommand object.
 */
public class AddReservationCommandParser extends AddCommandParser implements Parser<AddReservationCommand> {

    private static final String ID_STUB = "000001";
    private final Model model;
    private final ExecutionStatus executionStatus;

    /**
     * Constructs a {@code AddReservationCommandParser} with the given {@code Model} and {@code ExecutionStatus}.
     */
    public AddReservationCommandParser(Model model, ExecutionStatus executionStatus) {
        this.model = model;
        this.executionStatus = executionStatus;
    }

    private String generateId(seedu.address.model.member.Id id) throws ParseException {
        List<Member> lastShownList = model.getUpdatedMemberList();
        Member memberToEdit = lastShownList.stream()
                .filter(member -> id.equals(member.getId())).findAny().orElse(null);
        if (memberToEdit != null) {
            List<Reservation> reservationList = memberToEdit.getReservations();
            long latestId = 0;
            if (reservationList.size() > 0) {
                latestId = reservationList.get(reservationList.size() - 1).getId().getLongValue();
            }
            if (latestId == seedu.address.model.reservation.Id.MAX) {
                throw new ParseException(AddReservationCommand.MESSAGE_FULL);
            }
            return Long.toString(latestId + 1);
        } else {
            throw new ParseException(MESSAGE_INVALID_MEMBER_DISPLAYED_ID);
        }
    }

    private String generateIdStub() {
        return ID_STUB;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddReservationCommand
     * and returns an AddReservationCommand object for execution.
     *
     * @param args the input arguments related add reservation command to be parsed.
     * @return AddReservationCommand the class for process input add reservation command string.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public AddReservationCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_RESERVATION, PREFIX_DATE_TIME, PREFIX_REMARK, PREFIX_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_RESERVATION, PREFIX_DATE_TIME, PREFIX_REMARK, PREFIX_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddReservationCommand.MESSAGE_USAGE));
        }

        DateTime dateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATE_TIME).get());
        Remark remark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get());
        seedu.address.model.member.Id memberId = ParserUtil.parseMemberId(argMultimap.getValue(PREFIX_ID).get());
        seedu.address.model.reservation.Id reservationId = executionStatus == ExecutionStatus.NORMAL
                ? ParserUtil.parseReservationId(generateId(memberId))
                : ParserUtil.parseReservationId(generateIdStub());

        Reservation reservation = new Reservation(reservationId, dateTime, remark);

        return new AddReservationCommand(reservation, memberId);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
