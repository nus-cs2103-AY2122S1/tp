package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.ui.CommandHistory.DEFAULT_COMMAND;

import org.junit.jupiter.api.Test;

class CommandHistoryTest {

    @Test
    public void equals() {
        CommandHistory firstCommandHistory = new CommandHistory();
        firstCommandHistory.add("first test command");

        CommandHistory secondCommandHistory = new CommandHistory();
        secondCommandHistory.add("first test command");
        secondCommandHistory.add("second test command");

        // same object -> returns true
        assertTrue(firstCommandHistory.equals(firstCommandHistory));

        // different object -> returns false
        assertFalse(firstCommandHistory.equals(secondCommandHistory));

        // different types -> returns false
        assertFalse(firstCommandHistory.equals(1));

        // null -> returns false
        assertFalse(firstCommandHistory.equals(null));
    }

    @Test
    public void test_compareCommandHistory_returnsTrue() {
        CommandHistory firstCommandHistory = new CommandHistory();
        firstCommandHistory.add("first test command");
        firstCommandHistory.add("second test command");

        CommandHistory secondCommandHistory = new CommandHistory();
        secondCommandHistory.add("first test command");
        secondCommandHistory.add("second test command");

        // same history of commands
        assertEquals(firstCommandHistory, secondCommandHistory);
    }

    @Test
    public void test_compareCommandHistory_returnsFalse() {
        CommandHistory firstCommandHistory = new CommandHistory();
        firstCommandHistory.add("first test command");

        CommandHistory secondCommandHistory = new CommandHistory();
        secondCommandHistory.add("first test command");
        secondCommandHistory.add("second test command");

        // different history of commands
        assertNotEquals(firstCommandHistory, secondCommandHistory);

        // null
        assertNotEquals(firstCommandHistory, null);
    }

    @Test
    public void test_getPrevCommandEmptyHistory_returnsTrue() {
        CommandHistory emptyCommandHistory = new CommandHistory();

        // empty history
        assertEquals(emptyCommandHistory.getPrevCommand(), DEFAULT_COMMAND);
    }

    @Test
    public void test_getPrevCommandNonEmptyHistory_returnsTrue() {
        CommandHistory nonEmptyCommandHistory = new CommandHistory();
        nonEmptyCommandHistory.add("first test command");
        nonEmptyCommandHistory.add("second test command");

        // get previous command
        assertEquals(nonEmptyCommandHistory.getPrevCommand(), "second test command");

        // get next previous command
        assertEquals(nonEmptyCommandHistory.getPrevCommand(), "first test command");

        // if counter reaches the first command, get first command
        assertEquals(nonEmptyCommandHistory.getPrevCommand(), "first test command");
    }

    @Test
    public void test_getNextCommandEmptyHistory_returnsTrue() {
        CommandHistory emptyCommandHistory = new CommandHistory();

        // empty history
        assertEquals(emptyCommandHistory.getNextCommand(), DEFAULT_COMMAND);
    }

    @Test
    public void test_getNextCommandNonEmptyHistory_returnsTrue() {
        CommandHistory nonEmptyCommandHistory = new CommandHistory();
        nonEmptyCommandHistory.add("first test command");
        nonEmptyCommandHistory.add("second test command");

        nonEmptyCommandHistory.getPrevCommand();
        nonEmptyCommandHistory.getPrevCommand();

        // get next command
        assertEquals(nonEmptyCommandHistory.getNextCommand(), "first test command");

        // get next latest command
        assertEquals(nonEmptyCommandHistory.getNextCommand(), "second test command");

        // if counter reaches the last command, get last command
        assertEquals(nonEmptyCommandHistory.getNextCommand(), "second test command");
    }
}
