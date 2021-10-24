package seedu.address.logic.parser;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INCOMING_MONTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INCOMING_WEEK;

public class ListCommandParser implements Parser<ListCommand> {

    public ListCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INCOMING_MONTH, PREFIX_INCOMING_WEEK);
        if (argMultimap.isAllPresent(PREFIX_INCOMING_MONTH, PREFIX_INCOMING_WEEK)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        } else if (argMultimap.getValue(PREFIX_INCOMING_MONTH).isPresent()) {
            return new ListCommand(true, true);
        } else if (argMultimap.getValue(PREFIX_INCOMING_WEEK).isPresent()) {
            return new ListCommand(true, false);
        } else {
            return new ListCommand();
        }
    }


}
