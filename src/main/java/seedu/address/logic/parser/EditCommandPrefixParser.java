package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESERVATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSACTION;

import seedu.address.commons.status.ExecutionStatus;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Distinguishes between edit member and edit transaction commands and
 * returns an EditMemberCommandParser or EditTransactionCommandParser
 * depends on the very first prefix appears after command word.
 */
public class EditCommandPrefixParser {
    private final ExecutionStatus executionStatus;

    /**
     * Constructs a {@code EditCommandPrefixParser} with the {@code ExecutionStatus}.
     *
     * @param executionStatus normal or test.
     */
    public EditCommandPrefixParser(ExecutionStatus executionStatus) {
        this.executionStatus = executionStatus;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommandParser
     * and returns an EditCommandParser object for execution.
     *
     * @param args the input arguments related edit command to be parsed.
     * @return EditMemberCommandParser or EditTransactionCommandParser or AddReservationCommandParser.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EditCommandParser parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_MEMBER, PREFIX_TRANSACTION, PREFIX_RESERVATION);

        if (argMultimap.getValue(PREFIX_MEMBER).isPresent()
                && argMultimap.getValue(PREFIX_TRANSACTION).isEmpty()
                && argMultimap.getValue(PREFIX_RESERVATION).isEmpty()) {
            return new EditMemberCommandParser();
        } else if (argMultimap.getValue(PREFIX_TRANSACTION).isPresent()
                && argMultimap.getValue(PREFIX_MEMBER).isEmpty()
                && argMultimap.getValue(PREFIX_RESERVATION).isEmpty()) {
            return new EditTransactionCommandParser(executionStatus);
        } else if (argMultimap.getValue(PREFIX_RESERVATION).isPresent()
                && argMultimap.getValue(PREFIX_MEMBER).isEmpty()
                && argMultimap.getValue(PREFIX_TRANSACTION).isEmpty()) {
            return new EditReservationCommandParser();
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }
    }
}
