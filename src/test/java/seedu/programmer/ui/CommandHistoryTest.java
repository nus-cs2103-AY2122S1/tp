package seedu.programmer.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.programmer.ui.components.CommandHistory;

public class CommandHistoryTest {

    private CommandHistory commandHistory;

    @BeforeEach
    public void setUp() {
        commandHistory = new CommandHistory();
    }

    @Test
    public void add_sameInputs_equal() {
        commandHistory.add("test input one");
        commandHistory.add("test input two");

        CommandHistory commandHistoryTwo = new CommandHistory();
        commandHistoryTwo.add("test input one");

        assertNotEquals(commandHistory, commandHistoryTwo);

        commandHistoryTwo.add("test input two");
        assertEquals(commandHistory, commandHistoryTwo);

        commandHistoryTwo.add("test input three");
        assertNotEquals(commandHistory, commandHistoryTwo);
        assertTrue(commandHistory.isAtLastIndex());
    }

    @Test
    public void add_sampleCommands_indexInvariantHold() {
        commandHistory.add("first command");
        commandHistory.add("second command");
        commandHistory.add("123");
        commandHistory.add("add");
        assertTrue(commandHistory.isAtLastIndex());
    }

    @Test
    public void add_differentInputs_notEqual() {
        commandHistory.add("test input one");
        commandHistory.add("test input two");

        CommandHistory commandHistoryTwo = new CommandHistory();
        commandHistoryTwo.add("test input two");
        commandHistoryTwo.add("test input one");

        assertNotEquals(commandHistory, commandHistoryTwo);
    }

    @Test
    public void getPrevCommand_validHistory_success() {
        commandHistory.add("one");
        commandHistory.add("two");

        // Retrieve previous command
        assertEquals(commandHistory.getPrevCommand(), "one");

        // Retrieve the next previous command -> return the current command
        assertEquals(commandHistory.getCurrentCommand(), "one");

        // Simulate multiple up keys pressed
        assertEquals(commandHistory.getCurrentCommand(), "one");
        assertEquals(commandHistory.getCurrentCommand(), "one");
        assertTrue(commandHistory.isAtFirstIndex());
    }

    @Test
    public void getNextCommand_validHistory_success() {
        commandHistory.add("one");
        commandHistory.add("two");
        commandHistory.add("three");

        // Pressing down key will return the current command if it is the most recent command
        assertEquals(commandHistory.getCurrentCommand(), "three");

        // Pressing up key thrice will return the first command
        commandHistory.getPrevCommand();
        commandHistory.getPrevCommand();
        assertEquals(commandHistory.getCurrentCommand(), "one");

        // Retrieve the next command
        assertEquals(commandHistory.getNextCommand(), "two");
        // Retrieve the next command again
        assertEquals(commandHistory.getNextCommand(), "three");
        assertTrue(commandHistory.isAtLastIndex());
    }

    @Test
    public void getCurrentCommand_emptyHistory_returnsEmptyString() {
        assertTrue(commandHistory.isEmpty());
        assertEquals(commandHistory.getCurrentCommand(), "");
    }

    @Test
    public void getCurrentCommand_validHistory_returnsMostRecentCommand() {
        commandHistory.add("first command");
        commandHistory.add("second command");
        commandHistory.add("third command");
        assertEquals(commandHistory.getCurrentCommand(), "third command");
    }

    @Test
    public void equals() {
        CommandHistory commandHistoryOne = new CommandHistory();
        commandHistoryOne.add("test");
        CommandHistory commandHistoryOneCopy = new CommandHistory();
        commandHistoryOneCopy.add("test");
        CommandHistory commandHistoryTwo = new CommandHistory();
        commandHistoryTwo.add("test");
        commandHistoryTwo.add("test2");
        CommandHistory commandHistoryTwoCopy = new CommandHistory();
        commandHistoryTwoCopy.add("test");
        commandHistoryTwoCopy.add("test2");
        commandHistoryTwoCopy.getPrevCommand();

        // same values -> returns true
        assertEquals(commandHistoryOne, commandHistoryOneCopy);

        // same object -> returns true
        assertEquals(commandHistoryOne, commandHistoryOne);

        // null -> returns false
        assertNotEquals(null, commandHistoryOne);

        // different type -> returns false
        assertNotEquals(5, commandHistoryOne);

        // different commandHistory -> returns false
        assertNotEquals(commandHistoryOne, commandHistoryTwo);

        // different counter -> returns false
        assertNotEquals(commandHistoryTwo, commandHistoryTwoCopy);
    }
}
