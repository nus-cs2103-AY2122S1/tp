package seedu.address.logic.parser.friends;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DAY_TIME_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRIEND_ID_AMY;
import static seedu.address.logic.parser.CliSyntax.FLAG_FREE;
import static seedu.address.logic.parser.CliSyntax.FLAG_PERIOD;
import static seedu.address.logic.parser.CliSyntax.FLAG_SCHEDULE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.friends.ScheduleFriendCommandParser.INVALID_PERIOD_OR_FREE_ARGUMENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.friends.ScheduleFriendCommand;
import seedu.address.model.friend.FriendId;

class ScheduleFriendCommandParserTest {

    private ScheduleFriendCommandParser parser = new ScheduleFriendCommandParser();

    @Test
    public void parse_validArgs_returnsScheduleFriendCommand() {
        String startTime = "0800";
        String endTime = "0900";
        String day = "1";
        String isFree = "1";
        String userInput = " " + FLAG_SCHEDULE + " " + VALID_FRIEND_ID_AMY + " " + FLAG_PERIOD
                + " " + startTime + " " + endTime + " " + day + " " + FLAG_FREE
                + " " + isFree;

        ScheduleFriendCommand expected = new ScheduleFriendCommand(new FriendId(VALID_FRIEND_ID_AMY),
                1, startTime, endTime, true);
        assertParseSuccess(parser, userInput, expected);
    }

    @Test
    void parse_missingArgs_failure() {
        String startTime = "0800";
        String endTime = "0200";
        String day = "1";
        String isFree = "1";

        // missing isFree
        String userInput = " " + FLAG_SCHEDULE + " " + VALID_FRIEND_ID_AMY + " " + FLAG_PERIOD
                + " " + startTime + " " + endTime + " " + day;
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ScheduleFriendCommand.MESSAGE_USAGE));

        // missing endTime
        userInput = " " + FLAG_SCHEDULE + VALID_FRIEND_ID_AMY + " " + FLAG_PERIOD
                + startTime + " " + day + " " + FLAG_FREE + isFree;
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_DAY_TIME_FORMAT,
                INVALID_PERIOD_OR_FREE_ARGUMENT));

    }


}
