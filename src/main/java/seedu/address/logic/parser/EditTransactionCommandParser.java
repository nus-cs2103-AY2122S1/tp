package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BILLING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSACTION;

import java.util.stream.Stream;

import seedu.address.commons.status.ExecutionStatus;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.EditTransactionCommand;
import seedu.address.logic.commands.EditTransactionCommand.EditTransactionDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Timestamp;

/**
 * Parses input arguments and creates a new EditTransactionCommand object.
 */
public class EditTransactionCommandParser extends EditCommandParser implements Parser<EditTransactionCommand> {

    private final ExecutionStatus executionStatus;

    /**
     * Constructs a {@code EditTransactionCommandParser} with the given {@code ExecutionStatus}.
     */
    public EditTransactionCommandParser(ExecutionStatus executionStatus) {
        this.executionStatus = executionStatus;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditTransactionCommand
     * and returns an EditTransactionCommand object for execution.
     *
     * @param args the input arguments related edit transaction command to be parsed.
     * @return EditTransactionCommand the class for process input edit transaction command string.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EditTransactionCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TRANSACTION, PREFIX_ID, PREFIX_BILLING);

        if (!arePrefixesPresent(argMultimap, PREFIX_TRANSACTION, PREFIX_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditTransactionCommand.MESSAGE_USAGE));
        }

        String ids = argMultimap.getValue(PREFIX_ID).get();
        seedu.address.model.member.Id memberId =
                ParserUtil.parseMemberId(ids.substring(0, seedu.address.model.member.Id.LENGTH));
        seedu.address.model.transaction.Id transactionId =
                ParserUtil.parseTransactionId(ids.substring(seedu.address.model.member.Id.LENGTH));

        EditTransactionDescriptor editTransactionDescriptor = new EditTransactionDescriptor();
        if (argMultimap.getValue(PREFIX_BILLING).isPresent()) {
            editTransactionDescriptor.setBilling(
                    ParserUtil.parseBilling(argMultimap.getValue(PREFIX_BILLING).get()));
        }
        if (!editTransactionDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditTransactionCommand.MESSAGE_NOT_EDITED);
        }
        Timestamp timestamp = executionStatus == ExecutionStatus.NORMAL
                ? ParserUtil.parseTimestamp(DateTimeUtil.generateTimestamp())
                : ParserUtil.parseTimestamp(DateTimeUtil.generateTimestampStub());
        editTransactionDescriptor.setTimestamp(timestamp);

        return new EditTransactionCommand(memberId, transactionId, editTransactionDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
