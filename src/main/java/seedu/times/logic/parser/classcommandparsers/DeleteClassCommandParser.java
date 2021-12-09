package seedu.times.logic.parser.classcommandparsers;

import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.times.commons.core.index.Index;
import seedu.times.logic.commands.classcommands.DeleteClassCommand;
import seedu.times.logic.parser.Parser;
import seedu.times.logic.parser.ParserUtil;
import seedu.times.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DeleteClassCommand object
 */
public class DeleteClassCommandParser implements Parser<DeleteClassCommand> {

    @Override
    public DeleteClassCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteClassCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteClassCommand.MESSAGE_USAGE), pe);
        }
    }
}
