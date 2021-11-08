package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.task.TundoneCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new TundoneCommand object.
 */
public class TundoneCommandParser implements Parser<TundoneCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TundoneCommand
     * and returns a TundoneCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public TundoneCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_TASK_INDEX);
        if (!argMultimap.getValue(PREFIX_TASK_INDEX).isPresent()
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TundoneCommand.MESSAGE_USAGE));
        }

        Set<Index> indexSet = ParserUtil.parseIndices(argMultimap.getAllValues(PREFIX_TASK_INDEX));

        return new TundoneCommand(indexSet);
    }
}
