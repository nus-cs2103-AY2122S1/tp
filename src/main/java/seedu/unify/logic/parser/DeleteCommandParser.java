package seedu.unify.logic.parser;

import java.util.ArrayList;
import java.util.List;

import seedu.unify.commons.core.index.Index;
import seedu.unify.logic.commands.DeleteCommand;
import seedu.unify.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        try {
            List<Index> indexes = new ArrayList<>(ParserUtil.parseIndexes(args));
            return new DeleteCommand(indexes);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(pe.getMessage(), DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
