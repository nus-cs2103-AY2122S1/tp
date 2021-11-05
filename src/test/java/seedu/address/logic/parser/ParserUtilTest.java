package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.friend.FriendId;
import seedu.address.model.friend.FriendName;
import seedu.address.model.game.GameId;
import seedu.address.model.gamefriendlink.UserName;

public class ParserUtilTest {
    private static final String INVALID_FRIEND_ID = "Rachel Peters";
    private static final String INVALID_FRIEND_NAME = "Rachel-Peters";
    private static final String INVALID_GAME_ID = "League of Legends";
    private static final String INVALID_USERNAME = "Rachel-1234";
    private static final String VALID_FRIEND_ID = "RachelPeters";
    private static final String VALID_FRIEND_NAME = "Rachel Peters";
    private static final String VALID_GAME_ID = "LeagueOfLegends";
    private static final String VALID_USERNAME = "Rachel#1234";
    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_ITEM, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_ITEM, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseFriendId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseFriendId((String) null));
    }

    @Test
    public void parseFriendId_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseFriendId(INVALID_FRIEND_ID));
    }

    @Test
    public void parseFriendId_validValueWithoutSpaces_returnsId() throws Exception {
        FriendId expectedFriendId = new FriendId(VALID_FRIEND_ID);
        assertEquals(expectedFriendId, ParserUtil.parseFriendId(VALID_FRIEND_ID));
    }

    @Test
    public void parseFriendId_validValueWithWhitespace_returnsTrimmedId() throws Exception {
        String idWithWhitespace = WHITESPACE + VALID_FRIEND_ID + WHITESPACE;
        FriendId expectedFriendId = new FriendId(VALID_FRIEND_ID);
        assertEquals(expectedFriendId, ParserUtil.parseFriendId(idWithWhitespace));
    }

    @Test
    public void parseFriendName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseFriendName((String) null));
    }

    @Test
    public void parseFriendName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseFriendName(INVALID_FRIEND_NAME));
    }

    @Test
    public void parseFriendName_validValueWithoutSpaces_returnsName() throws Exception {
        FriendName expectedFriendName = new FriendName(VALID_FRIEND_NAME);
        assertEquals(expectedFriendName, ParserUtil.parseFriendName(VALID_FRIEND_NAME));
    }

    @Test
    public void parseFriendName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_FRIEND_NAME + WHITESPACE;
        FriendName expectedFriendName = new FriendName(VALID_FRIEND_NAME);
        assertEquals(expectedFriendName, ParserUtil.parseFriendName(nameWithWhitespace));
    }

    @Test
    public void parseGameId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGameId((String) null));
    }

    @Test
    public void parseGameId_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGameId(INVALID_GAME_ID));
    }

    @Test
    public void parseGameId_validValueWithoutSpaces_returnsId() throws Exception {
        GameId expectedGameId = new GameId(VALID_GAME_ID);
        assertEquals(expectedGameId, ParserUtil.parseGameId(VALID_GAME_ID));
    }

    @Test
    public void parseGameId_validValueWithWhitespace_returnsTrimmedId() throws Exception {
        String idWithWhitespace = WHITESPACE + VALID_GAME_ID + WHITESPACE;
        GameId expectedGameId = new GameId(VALID_GAME_ID);
        assertEquals(expectedGameId, ParserUtil.parseGameId(idWithWhitespace));
    }

    @Test
    public void parseUserName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseUserName((String) null));
    }

    @Test
    public void parseUserName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseUserName(INVALID_USERNAME));
    }

    @Test
    public void parseUserName_validValueWithoutSpaces_returnsName() throws Exception {
        UserName expectedUserName = new UserName(VALID_USERNAME);
        assertEquals(expectedUserName, ParserUtil.parseUserName(VALID_USERNAME));
    }

    @Test
    public void parseUserName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_USERNAME + WHITESPACE;
        UserName expectedUserName = new UserName(VALID_USERNAME);
        assertEquals(expectedUserName, ParserUtil.parseUserName(nameWithWhitespace));
    }
}
