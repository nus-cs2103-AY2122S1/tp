package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.task.TdelCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new TdelCommand object
 */
public class TdelCommandParser implements Parser<TdelCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TdelCommand
     * and returns an TdelCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TdelCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_TASK_INDEX);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_TASK_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TdelCommand.MESSAGE_USAGE));
        }

        Index taskId = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TASK_INDEX).get());

        return new TdelCommand(taskId);
    }
}
