package seedu.anilist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.anilist.logic.commands.CommandTestUtil.INVALID_ACTION_ALPHA;
import static seedu.anilist.logic.commands.CommandTestUtil.INVALID_ACTION_EMPTY_STRING;
import static seedu.anilist.logic.commands.CommandTestUtil.INVALID_ACTION_NUMERIC;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_ACTION_ADD;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_ACTION_ADD_SHORT_FORM;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_ACTION_DELETE;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_ACTION_DELETE_SHORT_FORM;
import static seedu.anilist.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ActionTest {

    @Test
    public void isValidActionTest() {
        // null action string
        assertThrows(NullPointerException.class, () -> Action.isValidAction(null));

        assertFalse(Action.isValidAction(INVALID_ACTION_ALPHA));
        assertFalse(Action.isValidAction(INVALID_ACTION_EMPTY_STRING));
        assertFalse(Action.isValidAction(INVALID_ACTION_NUMERIC));

        assertTrue(Action.isValidAction(VALID_ACTION_ADD));
        assertTrue(Action.isValidAction(VALID_ACTION_ADD_SHORT_FORM));
        assertTrue(Action.isValidAction(VALID_ACTION_DELETE));
        assertTrue(Action.isValidAction(VALID_ACTION_DELETE_SHORT_FORM));
    }

    @Test void actionFromString() {
        assertThrows(AssertionError.class, () -> Action.actionFromString((String) null));

        assertEquals(Action.DEFAULT, Action.actionFromString(INVALID_ACTION_EMPTY_STRING));
        assertEquals(Action.ADD, Action.actionFromString(VALID_ACTION_ADD));
        assertEquals(Action.DELETE, Action.actionFromString(VALID_ACTION_DELETE_SHORT_FORM));


    }
}
