package seedu.address.logic.parser;

import seedu.address.logic.commands.TAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.member.Member;
import seedu.address.model.member.UniqueMemberList;
import seedu.address.model.task.MemberID;
import seedu.address.model.task.Task;

import java.util.List;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Parses input arguments and creates a new TAddCommand object
 */
public class TAddCommandParser implements Parser<TAddCommand> {
    public TAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_TASKNAME, PREFIX_MEMBER_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_TASKNAME, PREFIX_MEMBER_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TAddCommand.MESSAGE_USAGE));
        }

        Task task = ParserUtil.parseTask(argMultimap.getValue(PREFIX_TASKNAME).get());
        MemberID memberID = ParserUtil.parseMemberID(argMultimap.getValue(PREFIX_MEMBER_ID).get());

        return new TAddCommand(memberID, task);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
