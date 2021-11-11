package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.listroomargs.ListRoomArg;
import seedu.address.commons.core.listtype.ListType;
import seedu.address.logic.commands.ListCommand;
import seedu.address.model.room.RoomIsOccupiedPredicate;
import seedu.address.model.room.RoomIsVacantPredicate;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validGuestsArgs_returnsListCommandForGuests() {
        // no leading and trailing whitespaces
        ListCommand expectedListCommand =
                new ListCommand(new ListType("guests"));
        assertParseSuccess(parser, "guests", expectedListCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "  guests \n  ", expectedListCommand);
    }

    @Test
    public void parse_validRoomsArgs_returnsListCommandForRooms() {
        // no leading and trailing whitespaces
        ListCommand expectedListCommand =
                new ListCommand(new ListType("rooms"));
        assertParseSuccess(parser, "rooms", expectedListCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "  rooms \n  ", expectedListCommand);
    }

    @Test
    public void parse_validArgs_returnsListCommandForVacantRooms() {
        // no leading and trailing whitespaces
        ListCommand expectedListCommand =
                new ListCommand(new ListType("rooms"), new RoomIsVacantPredicate());
        assertParseSuccess(parser, "rooms vacant", expectedListCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "  rooms  vacant \n  ", expectedListCommand);
    }

    @Test
    public void parse_validArgs_returnsListCommandForOccupiedRooms() {
        // no leading and trailing whitespaces
        ListCommand expectedListCommand =
                new ListCommand(new ListType("rooms"), new RoomIsOccupiedPredicate());
        assertParseSuccess(parser, "rooms occupied", expectedListCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "  rooms  occupied \n  ", expectedListCommand);
    }

    @Test
    public void parse_validArgs_returnsListCommandForRecords() {
        // no leading and trailing whitespaces
        ListCommand expectedListCommand =
                new ListCommand(new ListType("records"));
        assertParseSuccess(parser, " records \n ", expectedListCommand);
    }


    @Test
    public void parse_extraArgs_throwsParseException() {
        assertParseFailure(parser, "guests xxx",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "records xxx",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }


    @Test
    public void parse_invalidListTypeArgs_throwsParseException() {
        assertParseFailure(parser, "xxx",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListType.MESSAGE_CONSTRAINTS));
        assertParseFailure(parser, "rooms vacant xxx",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListRoomArg.MESSAGE_CONSTRAINTS));
        assertParseFailure(parser, "rooms occupied xxx",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListRoomArg.MESSAGE_CONSTRAINTS));
    }

}
