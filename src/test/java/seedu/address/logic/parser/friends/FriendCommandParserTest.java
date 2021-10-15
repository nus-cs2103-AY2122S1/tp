package seedu.address.logic.parser.friends;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRIEND_ID_AMY;
import static seedu.address.logic.parser.CliSyntax.FLAG_ADD;
import static seedu.address.logic.parser.CliSyntax.FLAG_DELETE;
import static seedu.address.logic.parser.CliSyntax.FLAG_GET;
import static seedu.address.logic.parser.CliSyntax.FLAG_POSTFIX;
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
        // add friend flag
        String addInput = FLAG_POSTFIX.getFlag() + FLAG_ADD.getFlag() + VALID_FRIEND_ID_AMY;
        assertEquals(parser.parse(addInput), new AddFriendCommandParser().parse(addInput));

        // delete friend flag
        String deleteInput = FLAG_POSTFIX.getFlag() + FLAG_DELETE.getFlag() + VALID_FRIEND_ID_AMY;
        assertEquals(parser.parse(deleteInput), new DeleteFriendCommandParser().parse(deleteInput));

        // get friend flag
        String getInput = FLAG_POSTFIX.getFlag() + FLAG_GET.getFlag() + VALID_FRIEND_ID_AMY;
        assertEquals(parser.parse(getInput), new GetFriendCommandParser().parse(getInput));

        // list friend flag
        String listInput = " --list";
        assertEquals(parser.parse(listInput), new ListFriendCommandParser().parse(listInput));
    }

}
