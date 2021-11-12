package seedu.teachbook.logic.parser;

import static seedu.teachbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.teachbook.commons.core.index.Index;
import seedu.teachbook.logic.commands.DeleteCommand;
import seedu.teachbook.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code DeleteCommand} object.
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code DeleteCommand}
     * and returns a {@code DeleteCommand} object for execution.
     *
     * @param args input arguments to be parsed.
     * @return a {@code DeleteCommand} object as a result of the parsing of the input arguments.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteCommand parse(String args) throws ParseException {
        if (ParserUtil.parseAll(args)) {
            return new DeleteCommand();
        }
        try {
            List<Index> indices = ParserUtil.parseIndices(args);
            return new DeleteCommand(indices);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
