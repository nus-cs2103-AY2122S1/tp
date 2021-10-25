package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SortCommandName;
import seedu.address.logic.commands.SortCommandRating;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and create a new SortCommand object.
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String commandWord = ParserUtil.parseSortCommand(args);

        switch(commandWord) {
        case "name":
            return new SortCommandName();

        case "rating":
            return new SortCommandRating();

        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

    }
}
