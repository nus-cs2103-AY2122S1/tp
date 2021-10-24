package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ORDER;

import java.util.stream.Stream;

import seedu.address.logic.commands.SortMemberCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.sort.SortOrder;


public class SortMemberCommandParser implements Parser<SortMemberCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortMemberCommand
     * and returns an SortMemberCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortMemberCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SORT_ORDER);
        if (!arePrefixesPresent(argMultimap, PREFIX_SORT_ORDER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException((String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortMemberCommand.MESSAGE_USAGE)));
        }
        SortOrder sortOrder = ParserUtil.parseSortOrder(argMultimap.getValue(PREFIX_SORT_ORDER).get());
        return new SortMemberCommand(sortOrder);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
