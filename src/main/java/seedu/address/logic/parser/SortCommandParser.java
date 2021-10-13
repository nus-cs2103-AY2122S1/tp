package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ORDER;

import java.util.Optional;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {
    public enum Order {
        TIME("time"),
        ASCENDING("asc"),
        DESCENDING("desc");
        public static final String ORDER_CONSTRAINT = "Sort order should be 'o/time', 'o/asc', or 'o/desc'";
        private String order;
        Order(String order) {
            this.order = order;
        }
        @Override
        public String toString() {
            return order;
        }
    }

    @Override
    public SortCommand parse(String args) throws ParseException {
        if (args.equals("")) {
            //sort by time
            return new SortCommand(Order.TIME);
        }
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SORT_ORDER);
        Optional s = argMultimap.getValue(PREFIX_SORT_ORDER);
        if (s.isEmpty()) {
            //wrong format, not using prefix 'o/'
            throw new ParseException(SortCommand.MESSAGE_USAGE);
        }
        try {
            Order order = ParserUtil.parseOrder((String) s.get());
            return new SortCommand(order);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE), pe);
        }
    }
}
