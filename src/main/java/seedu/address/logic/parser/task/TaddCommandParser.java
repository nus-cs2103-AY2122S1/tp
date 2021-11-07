package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.task.TaddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Name;
import seedu.address.model.module.task.Task;
import seedu.address.model.module.task.TaskDeadline;

/**
 * Parses input arguments and creates a new TaddCommand object
 */
public class TaddCommandParser implements Parser<TaddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TaddCommand
     * and returns an TaddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TaddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_NAME, PREFIX_DATE, PREFIX_MEMBER_INDEX);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DATE, PREFIX_MEMBER_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaddCommand.MESSAGE_USAGE));
        }

        Set<Index> memberIdList = ParserUtil.parseIndices(argMultimap.getAllValues(PREFIX_MEMBER_INDEX));
        Name taskName = ParserUtil.parseTaskName(argMultimap.getValue(PREFIX_NAME).get());
        TaskDeadline taskDeadline = ParserUtil.parseTaskDeadline(argMultimap.getValue(PREFIX_DATE).get());
        Task taskToAdd = new Task(taskName, taskDeadline);

        return new TaddCommand(memberIdList, taskToAdd);
    }
}
