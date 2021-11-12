package seedu.teachbook.logic.parser;

import static seedu.teachbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.teachbook.commons.core.index.Index;
import seedu.teachbook.logic.commands.UnmarkCommand;
import seedu.teachbook.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnmarkCommand object.
 */
public class UnmarkCommandParser implements Parser<UnmarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnmarkCommand and returns a UnmarkCommand
     * object for execution.
     *
     * @param args input arguments to be parsed.
     * @return {@code UnmarkCommand} as a result of the parsing of the input arguments.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public UnmarkCommand parse(String args) throws ParseException {
        if (ParserUtil.parseAll(args)) {
            return new UnmarkCommand();
        }
        try {
            List<Index> indices = ParserUtil.parseIndices(args);
            return new UnmarkCommand(indices);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE), pe);
        }
    }
}
