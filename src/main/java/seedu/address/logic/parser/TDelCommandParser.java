package seedu.address.logic.parser;

import seedu.address.logic.commands.TAddCommand;
import seedu.address.logic.commands.TDelCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.MemberID;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskID;

import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Parses input arguments and creates a new TDelCommand object
 */
public class TDelCommandParser implements Parser<TDelCommand> {
    public TDelCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_TASK_ID, PREFIX_MEMBER_ID_DEL);

        if (!arePrefixesPresent(argMultimap, PREFIX_TASK_ID, PREFIX_MEMBER_ID_DEL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TDelCommand.MESSAGE_USAGE));
        }

        TaskID taskID = ParserUtil.parseTaskID(argMultimap.getValue(PREFIX_TASK_ID).get());
        MemberID memberID = ParserUtil.parseMemberID(argMultimap.getValue(PREFIX_MEMBER_ID_DEL).get());

        return new TDelCommand(memberID, taskID);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
