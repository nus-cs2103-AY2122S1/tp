package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_ID;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TdoneCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new TdoneCommand object.
 */
public class TdoneCommandParser implements Parser<TdoneCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TdoneCommand
     * and returns a TdoneCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public TdoneCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_TASK_ID);
        if (!argMultimap.getValue(PREFIX_TASK_ID).isPresent()
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TdoneCommand.MESSAGE_USAGE));
        }

        Set<Index> indexSet = ParserUtil.parseIndices(argMultimap.getAllValues(PREFIX_TASK_ID));

        return new TdoneCommand(indexSet);
    }
}
