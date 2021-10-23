package seedu.address.logic.parser.friends;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DAY_TIME_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_FREE;
import static seedu.address.logic.parser.CliSyntax.FLAG_PERIOD;
import static seedu.address.logic.parser.CliSyntax.FLAG_SCHEDULE;
import static seedu.address.logic.parser.ParserUtil.areFlagsPresent;

import seedu.address.logic.commands.friends.ScheduleFriendCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.friend.FriendId;
import seedu.address.model.friend.exceptions.InvalidDayTimeException;

/**
 * Parses input arguments and creates a new ListFriendCommand object
 */
public class ScheduleFriendCommandParser implements Parser<ScheduleFriendCommand> {

    public static final String INVALID_PERIOD_OR_FREE_ARGUMENT = "Invalid " + FLAG_PERIOD
            + " or " + FLAG_FREE + " arguments";

    /**
     * Parses the given {@code String} of arguments in the context of the ScheduleFriendCommand
     * and returns a ScheduleFriendCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ScheduleFriendCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, FLAG_SCHEDULE, FLAG_PERIOD, FLAG_FREE);

        if (!areFlagsPresent(argMultimap, FLAG_SCHEDULE, FLAG_PERIOD, FLAG_FREE)
                || argMultimap.getValue(FLAG_SCHEDULE).isEmpty()
                || argMultimap.getValue(FLAG_PERIOD).isEmpty()
                || argMultimap.getValue(FLAG_FREE).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ScheduleFriendCommand.MESSAGE_USAGE));
        }

        try {
            String[] period = argMultimap.getValue(FLAG_PERIOD).get().split(" ");
            if (period.length != 3 || !(argMultimap.getValue(FLAG_FREE).get().equals("0")
                    || argMultimap.getValue(FLAG_FREE).get().equals("1"))) {
                throw new InvalidDayTimeException(INVALID_PERIOD_OR_FREE_ARGUMENT);
            }
            int dayIndex = Integer.parseInt(period[2]);
            boolean isFree = argMultimap.getValue(FLAG_FREE).get().equals("1");
            FriendId friendId = ParserUtil.parseFriendId(argMultimap.getValue(FLAG_SCHEDULE).get());

            return new ScheduleFriendCommand(friendId, dayIndex, period[0], period[1], isFree);
        } catch (InvalidDayTimeException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_DAY_TIME_FORMAT,
                    e.getMessage()));
        }


    }

}
