package seedu.teachbook.logic.parser;

import seedu.teachbook.logic.commands.ListCommand;
import seedu.teachbook.logic.commands.SortCommand;
import seedu.teachbook.logic.commands.SortNameCommand;
import seedu.teachbook.logic.parser.exceptions.ParseException;

import static seedu.teachbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class SortCommandParser implements Parser<SortCommand> {

    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.equals("name")) {
            return new SortCommand(true);
        } else if (trimmedArgs.equals("grade")) {
            return new SortCommand(false);
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
}
