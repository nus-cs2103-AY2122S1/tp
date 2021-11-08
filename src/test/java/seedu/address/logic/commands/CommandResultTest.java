package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalAddressBookObjects.ALICE;
import static seedu.address.testutil.TypicalAddressBookObjects.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.student.Student;
public class CommandResultTest {
    private Student alice = ALICE;
    private Student bob = BOB;

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false, false, false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false, false, false, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true, false, false, false)));

        //different showMedical value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, true, false, false)));

        //different student value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, false, alice, false, false)));

        //different showMeeting value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, false, true, false)));

        //different quitMeeting value ->
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, false, false, true)));

    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false, false, false, false)
                .hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true, false, false, false)
                .hashCode());

        // different showMedical value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false, true, false, false)
                .hashCode());

        // different student -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false, false, bob, false, false)
                .hashCode());

        //different showMeeting value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false, false, true, false)
                .hashCode());

        //different showMeeting value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false, false, false, true)
                .hashCode());

    }
}
