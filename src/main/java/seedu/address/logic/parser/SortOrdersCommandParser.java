package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ORDERING;

import java.util.stream.Stream;

import seedu.address.logic.commands.SortOrdersCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.sort.SortDescriptor;
import seedu.address.model.sort.SortField;
import seedu.address.model.sort.SortOrdering;

public class SortOrdersCommandParser implements Parser<SortOrdersCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortOrderCommand
     * and returns a SortOrderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortOrdersCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SORT_ORDERING, PREFIX_SORT_FIELD);

        if (!arePrefixesPresent(argMultimap, PREFIX_SORT_FIELD)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortOrdersCommand.MESSAGE_USAGE));
        }

        SortField sortField = ParserUtil.parseSortField(argMultimap.getValue(PREFIX_SORT_FIELD).get());
        SortOrdering sortOrdering = ParserUtil.parseSortOrder(argMultimap.getValue(PREFIX_SORT_ORDERING)
                .orElse("ascending"));

        SortDescriptor sortDescriptor = new SortDescriptor(sortField, sortOrdering);

        return new SortOrdersCommand(sortDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
