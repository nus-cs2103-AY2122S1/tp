package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindRoomCommand;
import seedu.address.model.room.RoomNumberContainsKeywordsPredicate;

public class FindRoomCommandParserTest {

    private FindRoomCommandParser parser = new FindRoomCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRoomCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindRoomCommand expectedFindCommand =
                new FindRoomCommand(new RoomNumberContainsKeywordsPredicate(Arrays.asList("001", "002")));
        assertParseSuccess(parser, "001 002", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 001 \n \t 002  \t", expectedFindCommand);
    }

    @Test
    public void parse_validStringArgs_throwsParseException() {
        // no leading and trailing whitespaces
        assertParseFailure(parser, "hello",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRoomCommand.MESSAGE_INVALID_ROOM_NUMBER));

        // multiple whitespaces between keywords
        assertParseFailure(parser, "   \n hello  ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRoomCommand.MESSAGE_INVALID_ROOM_NUMBER));

        // multiple strings as input
        assertParseFailure(parser, "hello search",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRoomCommand.MESSAGE_INVALID_ROOM_NUMBER));

        // valid inputs followed by an invalid one
        assertParseFailure(parser, " 001 002 hello",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRoomCommand.MESSAGE_INVALID_ROOM_NUMBER));
    }

    @Test
    public void parse_negativeRoomNumber_throwsParseException() {
        // no leading and trailing whitespaces
        assertParseFailure(parser, "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRoomCommand.MESSAGE_INVALID_ROOM_NUMBER));

        // multiple whitespaces between keywords
        assertParseFailure(parser, "   -1  ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRoomCommand.MESSAGE_INVALID_ROOM_NUMBER));

        // multiple strings as input
        assertParseFailure(parser, "-1 2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRoomCommand.MESSAGE_INVALID_ROOM_NUMBER));

        // valid inputs followed by an invalid one
        assertParseFailure(parser, " 001 002 -1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRoomCommand.MESSAGE_INVALID_ROOM_NUMBER));
    }

    @Test
    public void parse_roomNumberLargerThan999_throwsParseException() {
        // no leading and trailing whitespaces
        assertParseFailure(parser, "1000",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRoomCommand.MESSAGE_INVALID_ROOM_NUMBER));

        // multiple whitespaces between keywords
        assertParseFailure(parser, "   1000  ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRoomCommand.MESSAGE_INVALID_ROOM_NUMBER));

        // multiple strings as input
        assertParseFailure(parser, "1000 1001",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRoomCommand.MESSAGE_INVALID_ROOM_NUMBER));

        // valid inputs followed by an invalid one
        assertParseFailure(parser, " 001 002 1000",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRoomCommand.MESSAGE_INVALID_ROOM_NUMBER));
    }

}
