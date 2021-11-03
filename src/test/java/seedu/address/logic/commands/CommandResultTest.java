package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalFriends;
import seedu.address.testutil.TypicalGames;

public class CommandResultTest {

    private static final String FEEDBACK = "feedback";
    private static final String DIFF_FEEDBACK = "different";

    @Test
    public void getFeedbackToUser() {
        CommandResult commandResult = new CommandResult(FEEDBACK);

        // same feedback value
        assertEquals(FEEDBACK, commandResult.getFeedbackToUser());

        // different feedback value
        assertNotEquals(DIFF_FEEDBACK, commandResult.getFeedbackToUser());
    }

    @Test
    public void getFriendToGet() {
        CommandResult commandResult = new CommandResult(FEEDBACK, CommandType.FRIEND_GET, TypicalFriends.ALICE);

        // same friend value
        assertEquals(TypicalFriends.ALICE, commandResult.getFriendToGet());

        // different feedback value
        assertNotEquals(TypicalFriends.BOB, commandResult.getFriendToGet());
    }

    @Test
    public void getGameToGet() {
        CommandResult commandResult = new CommandResult(FEEDBACK, CommandType.GAME_GET, TypicalGames.APEX_LEGENDS);

        // same friend value
        assertEquals(TypicalGames.APEX_LEGENDS, commandResult.getGameToGet());

        // different feedback value
        assertNotEquals(TypicalGames.CSGO, commandResult.getGameToGet());
    }

    @Test
    public void getCommandType() {
        CommandResult commandResult = new CommandResult(FEEDBACK, CommandType.FRIEND_GET, TypicalFriends.ALICE);

        // same feedback value
        assertEquals(CommandType.FRIEND_GET, commandResult.getCommandType());

        // different feedback value
        assertNotEquals(CommandType.FRIEND_ADD, commandResult.getCommandType());
    }

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult(FEEDBACK);

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult(FEEDBACK)));
        assertTrue(commandResult.equals(new CommandResult(FEEDBACK, false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult(DIFF_FEEDBACK)));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult(FEEDBACK, true, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult(FEEDBACK, false, true)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult(FEEDBACK);

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult(FEEDBACK).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult(DIFF_FEEDBACK).hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult(FEEDBACK, true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult(FEEDBACK, false, true).hashCode());
    }
}
