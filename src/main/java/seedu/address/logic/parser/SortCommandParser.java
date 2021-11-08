package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CREDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;

import seedu.address.commons.status.SortStatus;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.member.CreditSortComparator;

/**
 * Parses input arguments and creates a new SortCommand object.
 */
public class SortCommandParser implements Parser<SortCommand> {

    private static final int PREFIX_SIZE = 4;

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     *
     * @param args the input arguments related sort command to be parsed.
     * @return SortCommand the class for process input sort command string.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public SortCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MEMBER, PREFIX_CREDIT, PREFIX_ASC,
                PREFIX_DESC);

        if (argMultimap.getSize() != PREFIX_SIZE || argMultimap.getValue(PREFIX_MEMBER).isEmpty()
                || argMultimap.getValue(PREFIX_CREDIT).isEmpty()
                || (argMultimap.getValue(PREFIX_ASC).isEmpty() && argMultimap.getValue(PREFIX_DESC).isEmpty())
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_ASC).isPresent()) {
            return new SortCommand(new CreditSortComparator(SortStatus.ASC));
        }

        if (argMultimap.getValue(PREFIX_DESC).isPresent()) {
            return new SortCommand(new CreditSortComparator(SortStatus.DESC));
        }

        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

}
