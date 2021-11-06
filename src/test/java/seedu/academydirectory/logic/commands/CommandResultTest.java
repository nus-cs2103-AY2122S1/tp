package seedu.academydirectory.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    /**
     * Condition for equal: Feedback, exit, show help are equal. Optional message are "optional"
     * and hence are not considered for the equal test.
     */
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values for feedback, exit, help -> equal
        assertEquals(commandResult, new CommandResult("feedback"));
        assertEquals(commandResult, new CommandResult("feedback", false, false));

        // same value for feedback, exit, help, optional -> equal
        assertEquals(commandResult, new CommandResult("feedback", Optional.empty()));

        // has optional string message and all false -> equal
        assertEquals(commandResult, new CommandResult("feedback",
                false, false, Optional.empty()));

        // same value for feedback, exit, help but different optional -> still equal
        assertEquals(commandResult, new CommandResult("feedback",
                false, false, Optional.of("feedback")));

        // same object -> equal
        assertEquals(commandResult, commandResult);

        // null -> not equal
        assertNotEquals(null, commandResult);

        // different types -> not equal
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> not equal
        assertNotEquals(commandResult, new CommandResult("different"));

        // different showHelp value -> not equal
        assertNotEquals(commandResult, new CommandResult("feedback", true, false));

        // different show help value and different optional -> not equal
        assertNotEquals(commandResult, new CommandResult("feedback", true, false,
                Optional.of("feedback")));

        // different exit value -> not equal
        assertNotEquals(commandResult, new CommandResult("feedback", false, true));

        CommandResult commandResult1 = new CommandResult("this is a feedback", Optional.of("feedback"));
        CommandResult commandResult2 = new CommandResult("this is a feedback", Optional.of("different"));

        // different values of optional but same type -> equal
        assertEquals(commandResult1, commandResult2);
        assertEquals(commandResult1, new CommandResult("this is a feedback"));

        // same optional object, different feedback -> not equal
        assertNotEquals(commandResult1, new CommandResult("another feedback", Optional.of("feedback")));

        // same optional, same feedback, different exit -> not equal
        assertNotEquals(commandResult1, new CommandResult("this is a feedback", false, true, Optional.of("feedback")));

        // same optional, same feedback, different help -> not equal
        assertNotEquals(commandResult1, new CommandResult("this is a feedback", true, false, Optional.of("feedback")));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // same value but different optional -> same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback", Optional.of("feedback"))
                .hashCode());

        // different value but same optional -> different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("another", Optional.empty()).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true).hashCode());
    }
}
