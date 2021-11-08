package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESERVATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSACTION;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Distinguishes between delete member and delete transaction commands and
 * returns an DeleteMemberCommandParser or DeleteTransactionCommandParser
 * depends on the very first prefix appears after command word.
 */
public class DeleteCommandPrefixParser {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommandParser
     * and returns an DeleteCommandParser object for execution.
     *
     * @param args the input arguments related delete command to be parsed.
     * @return DeleteMemberCommandParser or DeleteTransactionCommandParser or DeleteReservationCommandParser.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteCommandParser parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_MEMBER, PREFIX_TRANSACTION, PREFIX_RESERVATION);

        if (argMultimap.getValue(PREFIX_MEMBER).isPresent()
                && argMultimap.getValue(PREFIX_TRANSACTION).isEmpty()
                && argMultimap.getValue(PREFIX_RESERVATION).isEmpty()) {
            return new DeleteMemberCommandParser();
        } else if (argMultimap.getValue(PREFIX_TRANSACTION).isPresent()
                && argMultimap.getValue(PREFIX_MEMBER).isEmpty()
                && argMultimap.getValue(PREFIX_RESERVATION).isEmpty()) {
            return new DeleteTransactionCommandParser();
        } else if (argMultimap.getValue(PREFIX_RESERVATION).isPresent()
                && argMultimap.getValue(PREFIX_MEMBER).isEmpty()
                && argMultimap.getValue(PREFIX_TRANSACTION).isEmpty()) {
            return new DeleteReservationCommandParser();
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
    }
}
