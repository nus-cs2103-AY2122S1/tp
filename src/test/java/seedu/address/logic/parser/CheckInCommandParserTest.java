package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ROOM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CheckInCommand;

public class CheckInCommandParserTest {

    private CheckInCommandParser parser = new CheckInCommandParser();

    @Test
    public void parse_singleGuest_returnsCheckInCommand() {
        String userInput = INDEX_FIRST_ROOM.getOneBased()
                + " g/"
                + INDEX_FIRST_PERSON.getOneBased();
        List<Index> guestIndexes = Arrays.asList(INDEX_FIRST_PERSON);
        CheckInCommand checkInCommand = new CheckInCommand(INDEX_FIRST_ROOM, guestIndexes);

        assertParseSuccess(parser, userInput, checkInCommand);
    }

    @Test
    public void parse_multipleGuests_returnsCheckInCommand() {
        String userInput = INDEX_FIRST_ROOM.getOneBased()
                + " g/"
                + INDEX_FIRST_PERSON.getOneBased()
                + " g/"
                + INDEX_SECOND_PERSON.getOneBased()
                + " g/"
                + INDEX_THIRD_PERSON.getOneBased();
        List<Index> guestIndexes = Arrays.asList(
                INDEX_FIRST_PERSON,
                INDEX_SECOND_PERSON,
                INDEX_THIRD_PERSON);
        CheckInCommand checkInCommand = new CheckInCommand(INDEX_FIRST_ROOM, guestIndexes);

        assertParseSuccess(parser, userInput, checkInCommand);
    }

    @Test
    public void parse_invalidPreamble_throwsParseException() {
        String userInput = "a g/"
                + INDEX_FIRST_PERSON.getOneBased();

        assertParseFailure(
                parser,
                userInput,
                CheckInCommand.MESSAGE_ROOM_INVALID_INDEX);
    }

    @Test
    public void parse_invalidGuestIndex_throwsParseException() {
        String userInput = INDEX_FIRST_ROOM.getOneBased()
                + " g/ a";

        assertParseFailure(
                parser,
                userInput,
                CheckInCommand.MESSAGE_PERSON_INVALID_INDEX);
    }

}
