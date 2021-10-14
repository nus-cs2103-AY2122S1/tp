package seedu.address.logic.parser.friends;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

class FriendCommandParserTest {

    private FriendCommandParser parser = new FriendCommandParser();

    @Test
    public void parse_invalidFlag_exceptionThrown() {
        // unrecognised flag
        assertThrows(ParseException.class, () -> parser.parse(" --invalid"));
        // empty input
        assertThrows(ParseException.class, () -> parser.parse(""));
    }

    @Test
    public void parse_validFlag_success() throws ParseException {
        // list friend flag
        String input = " --list";
        assertEquals(parser.parse(input), new ListFriendCommandParser().parse(input));
        // TODO add tests for add, delete and get when all are complete
    }

}
