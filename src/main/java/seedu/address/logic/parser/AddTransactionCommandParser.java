package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BILLING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSACTION;

import java.util.List;
import java.util.stream.Stream;

import seedu.address.commons.status.ExecutionStatus;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.AddTransactionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.Timestamp;
import seedu.address.model.member.Member;
import seedu.address.model.transaction.Billing;
import seedu.address.model.transaction.Transaction;

/**
 * Parses input arguments and creates a new AddTransactionCommand object.
 */
public class AddTransactionCommandParser extends AddCommandParser implements Parser<AddTransactionCommand> {

    private static final String ID_STUB = "000001";

    private final Model model;
    private final ExecutionStatus executionStatus;

    /**
     * Constructs a {@code AddTransactionCommandParser} with the given {@code Model} and {@code ExecutionStatus}.
     */
    public AddTransactionCommandParser(Model model, ExecutionStatus executionStatus) {
        this.model = model;
        this.executionStatus = executionStatus;
    }

    private String generateId(seedu.address.model.member.Id id) throws ParseException {
        List<Member> lastShownList = model.getUpdatedMemberList();
        Member memberToEdit = lastShownList.stream()
                .filter(member -> id.equals(member.getId())).findAny().orElse(null);
        if (memberToEdit != null) {
            List<Transaction> transactionList = memberToEdit.getTransactions();
            long latestId = 0;
            if (transactionList.size() > 0) {
                latestId = transactionList.get(transactionList.size() - 1).getId().getLongValue();
            }
            if (latestId == seedu.address.model.transaction.Id.MAX) {
                throw new ParseException(AddTransactionCommand.MESSAGE_FULL);
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
     * Parses the given {@code String} of arguments in the context of the AddTransactionCommand
     * and returns an AddTransactionCommand object for execution.
     *
     * @param args the input arguments related add transaction command to be parsed.
     * @return AddTransactionCommand the class for process input add transaction command string.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public AddTransactionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TRANSACTION, PREFIX_BILLING, PREFIX_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_TRANSACTION, PREFIX_BILLING, PREFIX_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTransactionCommand.MESSAGE_USAGE));
        }

        Billing billing = ParserUtil.parseBilling(argMultimap.getValue(PREFIX_BILLING).get());
        Timestamp timestamp = executionStatus == ExecutionStatus.NORMAL
                ? ParserUtil.parseTimestamp(DateTimeUtil.generateTimestamp())
                : ParserUtil.parseTimestamp(DateTimeUtil.generateTimestampStub());
        seedu.address.model.member.Id memberId = ParserUtil.parseMemberId(argMultimap.getValue(PREFIX_ID).get());
        seedu.address.model.transaction.Id transactionId = executionStatus == ExecutionStatus.NORMAL
                ? ParserUtil.parseTransactionId(generateId(memberId))
                : ParserUtil.parseTransactionId(generateIdStub());

        Transaction transaction = new Transaction(transactionId, timestamp, billing);

        return new AddTransactionCommand(transaction, memberId);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
