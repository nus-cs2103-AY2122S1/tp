package seedu.tracker.logic.parser;

import static seedu.tracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.tracker.logic.commands.ViewCommand;
import seedu.tracker.logic.parser.exceptions.ParseException;

public class ViewCommandParser implements Parser<ViewCommand> {

    public ViewCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, )
            )
        }
    }
}
