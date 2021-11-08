package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ORDER;

import java.util.Comparator;
import java.util.stream.Stream;

import seedu.address.logic.commands.SortCustomerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.SortOrder;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.person.customer.SortByCustomer;

/**
 * Parses input arguments and creates a new SortCustomerCommand object
 */
public class SortCustomerCommandParser implements Parser<SortCustomerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCustomerCommand
     * and returns a SortCustomerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public SortCustomerCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SORT_BY, PREFIX_SORT_ORDER);

        if (!arePrefixesPresent(argMultimap, PREFIX_SORT_BY, PREFIX_SORT_ORDER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCustomerCommand.MESSAGE_USAGE));
        }

        SortByCustomer sortBy = ParserUtil.parseSortByCustomer(argMultimap.getValue(PREFIX_SORT_BY).get());
        SortOrder sortingOrder = ParserUtil.parseSortingOrder(argMultimap.getValue(PREFIX_SORT_ORDER).get());
        Comparator<Customer> customerComparator = sortBy.selectComparator(sortingOrder.isAscending());
        return new SortCustomerCommand(customerComparator, sortBy.toString(), sortingOrder.toString());
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
