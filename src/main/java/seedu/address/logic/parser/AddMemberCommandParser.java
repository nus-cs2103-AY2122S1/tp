package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.status.ExecutionStatus;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.AddMemberCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.Timestamp;
import seedu.address.model.member.Address;
import seedu.address.model.member.Credit;
import seedu.address.model.member.Email;
import seedu.address.model.member.Id;
import seedu.address.model.member.Member;
import seedu.address.model.member.Name;
import seedu.address.model.member.Phone;
import seedu.address.model.member.Point;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;

/**
 * Parses input arguments and creates a new AddMemberCommand object.
 */
public class AddMemberCommandParser extends AddCommandParser implements Parser<AddMemberCommand> {

    private static final String ID_STUB = "00001";

    private final Model model;
    private final ExecutionStatus executionStatus;

    /**
     * Constructs a {@code AddMemberCommandParser} with the given {@code Model} and {@code ExecutionStatus}.
     */
    public AddMemberCommandParser(Model model, ExecutionStatus executionStatus) {
        this.model = model;
        this.executionStatus = executionStatus;
    }

    private String generateId() throws ParseException {
        List<Member> memberList = model.getEzFoodie().getMemberList();
        long latestId = 0;
        if (memberList.size() > 0) {
            latestId = memberList.get(memberList.size() - 1).getId().getLongValue();
        }
        if (latestId == Id.MAX) {
            throw new ParseException(AddMemberCommand.MESSAGE_FULL);
        }
        return Long.toString(latestId + 1);
    }

    private String generateIdStub() {
        return ID_STUB;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddMemberCommand
     * and returns an AddMemberCommand object for execution.
     *
     * @param args the input arguments related add member command to be parsed.
     * @return AddMemberCommand the class for process input add member command string.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public AddMemberCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MEMBER, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap,
                PREFIX_MEMBER, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMemberCommand.MESSAGE_USAGE));
        }

        Id id = executionStatus == ExecutionStatus.NORMAL
                ? ParserUtil.parseMemberId(generateId())
                : ParserUtil.parseMemberId(generateIdStub());

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Timestamp timestamp = executionStatus == ExecutionStatus.NORMAL
                ? ParserUtil.parseTimestamp(DateTimeUtil.generateTimestamp())
                : ParserUtil.parseTimestamp(DateTimeUtil.generateTimestampStub());
        Credit credit = new Credit("0");
        Point point = new Point(credit.getStringValue());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        List<Transaction> transactionList = new ArrayList<>();
        List<Reservation> reservationList = new ArrayList<>();
        Member member = new Member(id, name, phone, email, address, timestamp, credit, point,
                transactionList, reservationList, tagList);
        return new AddMemberCommand(member);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
