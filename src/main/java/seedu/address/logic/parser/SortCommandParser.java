package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SortCommandName;
import seedu.address.logic.commands.SortCommandRating;
import seedu.address.logic.parser.exceptions.ParseException;

public class SortCommandParser implements Parser<SortCommand> {
    public SortCommand parse(String args) throws ParseException {
        ParseException pe;

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
