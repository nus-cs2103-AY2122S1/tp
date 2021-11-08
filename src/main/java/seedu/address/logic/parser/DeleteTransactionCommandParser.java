package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSACTION;

import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteTransactionCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteTransactionCommand object.
 */
public class DeleteTransactionCommandParser extends DeleteCommandParser implements Parser<DeleteTransactionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTransactionCommand
     * and returns a DeleteTransactionCommand object for execution.
     *
     * @param args the input arguments related delete transaction command to be parsed.
     * @return DeleteTransactionCommand the class for process input delete transaction command string.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteTransactionCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TRANSACTION, PREFIX_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_TRANSACTION, PREFIX_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTransactionCommand.MESSAGE_USAGE));
        }

        String ids = argMultimap.getValue(PREFIX_ID).get();
        seedu.address.model.member.Id memberId =
                ParserUtil.parseMemberId(ids.substring(0, seedu.address.model.member.Id.LENGTH));
        seedu.address.model.transaction.Id transactionId =
                ParserUtil.parseTransactionId(ids.substring(seedu.address.model.member.Id.LENGTH));

        return new DeleteTransactionCommand(memberId, transactionId);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
