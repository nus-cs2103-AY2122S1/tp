package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.DayOfWeek;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeallocateMemberCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code DeallocateMemberCommand} object.
 */
public class DeallocateMemberCommandParser implements Parser<DeallocateMemberCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeallocateMemberCommand
     * and returns a DeallocateMemberCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeallocateMemberCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] trimmedArgs = args.trim().split("\\s+");

        Index memberIndex;
        Index facilityIndex;
        DayOfWeek day;

        if (trimmedArgs.length != 3) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeallocateMemberCommand.MESSAGE_USAGE));
        }
        memberIndex = ParserUtil.parseIndex(trimmedArgs[0]);
        facilityIndex = ParserUtil.parseIndex(trimmedArgs[1]);
        day = ParserUtil.parseDay(trimmedArgs[2]);

        return new DeallocateMemberCommand(memberIndex, facilityIndex, day);
    }
}
