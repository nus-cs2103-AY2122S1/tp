package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewClassCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new ViewClassCommand object
 */
public class ViewClassCommandParser implements Parser<ViewClassCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ViewClassCommand
     * and returns a ViewClassCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ViewClassCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewClassCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_CLASS_DISPLAYED_INDEX + MESSAGE_INVALID_COMMAND_FORMAT,
                            ViewClassCommand.MESSAGE_USAGE), pe);
        }
    }
}
