package seedu.teachbook.logic.parser;

import static seedu.teachbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.teachbook.commons.core.index.Index;
import seedu.teachbook.logic.commands.MarkCommand;
import seedu.teachbook.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkCommand object.
 */
public class MarkCommandParser implements Parser<MarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkCommand and returns a MarkCommand object
     * for execution.
     *
     * @param args input arguments to be parsed.
     * @return {@code MarkCommand} as a result of the parsing of the input arguments.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public MarkCommand parse(String args) throws ParseException {
        if (ParserUtil.parseAll(args)) {
            return new MarkCommand();
        }
        try {
            List<Index> indices = ParserUtil.parseIndices(args);
            return new MarkCommand(indices);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE), pe);
        }
    }
}
