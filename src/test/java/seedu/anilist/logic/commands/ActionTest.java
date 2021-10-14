package seedu.anilist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.anilist.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ActionTest {
    private static final String INVALID_ACTION = "shopping";
    private static final String INVALID_ACTION_EMPTY_STRING = "";

    private static final String VALID_ACTION_ADD = "add";
    private static final String VALID_ACTION_DELETE = "delete";

    @Test
    public void isValidActionTest() {
        // null action string
        assertThrows(NullPointerException.class, () -> Action.isValidAction(null));

        assertFalse(Action.isValidAction(INVALID_ACTION));
        assertFalse(Action.isValidAction(INVALID_ACTION_EMPTY_STRING));

        assertTrue(Action.isValidAction(VALID_ACTION_ADD));
    }

    @Test void actionFromString() {
        assertThrows(AssertionError.class, () -> Action.actionFromString((String) null));

        assertEquals(Action.DEFAULT, Action.actionFromString(INVALID_ACTION));
        assertEquals(Action.ADD, Action.actionFromString(VALID_ACTION_ADD));
        assertEquals(Action.DELETE, Action.actionFromString(VALID_ACTION_DELETE));


    }
}
