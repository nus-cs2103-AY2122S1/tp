package seedu.address.logic.parser.friends;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.SCHEDULE_FRIEND_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRIEND_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GAME_ID_CSGO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CliSyntax.FLAG_ADD;
import static seedu.address.logic.parser.CliSyntax.FLAG_ADD_GAME_SKILL;
import static seedu.address.logic.parser.CliSyntax.FLAG_DELETE;
import static seedu.address.logic.parser.CliSyntax.FLAG_EDIT;
import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND_NAME;
import static seedu.address.logic.parser.CliSyntax.FLAG_GAME;
import static seedu.address.logic.parser.CliSyntax.FLAG_GET;
import static seedu.address.logic.parser.CliSyntax.FLAG_LINK;
import static seedu.address.logic.parser.CliSyntax.FLAG_LIST;
import static seedu.address.logic.parser.CliSyntax.FLAG_POSTFIX;
import static seedu.address.logic.parser.CliSyntax.FLAG_UNLINK;
import static seedu.address.logic.parser.CliSyntax.FLAG_USERNAME;
import static seedu.address.logic.parser.CliSyntax.FLAG_VALUE;
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
        assertThrows(ParseException.class, () -> parser.parse("              "));
    }

    @Test
    public void parseAddFriend_validCommandFlag_correctParserReturned() throws ParseException {
        String addInput = FLAG_POSTFIX.getFlag() + FLAG_ADD.getFlag() + VALID_FRIEND_ID_AMY;
        assertEquals(parser.parse(addInput), new AddFriendCommandParser().parse(addInput));
    }

    @Test
    public void parseAddFriendGameSkill_validCommandFlag_correctParserReturned() throws ParseException {
        String addFriendGameSkillInput = FLAG_POSTFIX.getFlag() + FLAG_ADD_GAME_SKILL.getFlag() + VALID_FRIEND_ID_AMY
                + " " + FLAG_GAME + VALID_GAME_ID_CSGO + " " + FLAG_VALUE + "3";
        assertEquals(parser.parse(addFriendGameSkillInput),
                new AddFriendGameSkillCommandParser().parse(addFriendGameSkillInput));
    }

    @Test
    public void parseEdit_validCommandFlag_correctParserReturned() throws ParseException {
        String editInput = FLAG_POSTFIX.getFlag() + FLAG_EDIT.getFlag() + VALID_FRIEND_ID_AMY + " "
                + FLAG_FRIEND_NAME + VALID_NAME_AMY;
        assertEquals(parser.parse(editInput), new EditFriendCommandParser().parse(editInput));
    }

    @Test
    public void parseDelete_validCommandFlag_correctParserReturned() throws ParseException {
        String deleteInput = FLAG_POSTFIX.getFlag() + FLAG_DELETE.getFlag() + VALID_FRIEND_ID_AMY;
        assertEquals(parser.parse(deleteInput), new DeleteFriendCommandParser().parse(deleteInput));
    }

    @Test
    public void parseSchedule_validCommandFlag_correctParserReturned() throws ParseException {
        String scheduleInput = SCHEDULE_FRIEND_AMY;
        assertEquals(parser.parse(scheduleInput), new ScheduleFriendCommandParser().parse(scheduleInput));
    }

    @Test
    public void parseGet_validCommandFlag_correctParserReturned() throws ParseException {
        String getInput = FLAG_POSTFIX.getFlag() + FLAG_GET.getFlag() + VALID_FRIEND_ID_AMY;
        assertEquals(parser.parse(getInput), new GetFriendCommandParser().parse(getInput));
    }

    @Test
    public void parseList_validCommandFlag_correctParserReturned() throws ParseException {
        String listInput = FLAG_POSTFIX.getFlag() + FLAG_LIST.getFlag();
        assertEquals(parser.parse(listInput), new ListFriendCommandParser().parse(listInput));
    }

    @Test
    public void parseLink_validCommandFlag_correctParserReturned() throws ParseException {
        String linkInput = FLAG_POSTFIX.getFlag() + FLAG_LINK + "Draco " + FLAG_USERNAME
                + "GoldNova " + FLAG_GAME + "CSGO";
        assertEquals(parser.parse(linkInput), new LinkFriendCommandParser().parse(linkInput));
    }

    @Test
    public void parseUnlink_validCommandFlag_correctParserReturned() throws ParseException {
        String unlinkInput = FLAG_POSTFIX.getFlag() + FLAG_UNLINK + "Draco " + FLAG_GAME + "CSGO";
        assertEquals(parser.parse(unlinkInput), new UnlinkFriendCommandParser().parse(unlinkInput));
    }
}
