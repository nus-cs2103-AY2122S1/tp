package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INCOMING_MONTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INCOMING_WEEK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NORMAL_LIST;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListCommandParser implements Parser<ListCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INCOMING_MONTH, PREFIX_INCOMING_WEEK, PREFIX_NORMAL_LIST);

        if (argMultimap.isMultiplePresent(PREFIX_INCOMING_MONTH, PREFIX_INCOMING_WEEK, PREFIX_NORMAL_LIST)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        } else if (ParserUtil.isPrefixPresentAndEmpty(argMultimap, PREFIX_INCOMING_MONTH)) {
            return new ListCommand(true, true);
        } else if (ParserUtil.isPrefixPresentAndEmpty(argMultimap, PREFIX_INCOMING_WEEK)) {
            return new ListCommand(true, false);
        } else if (ParserUtil.isPrefixPresentAndEmpty(argMultimap, PREFIX_NORMAL_LIST)) {
            return new ListCommand();
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }
    }


}
