package seedu.address.ui.util;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class InputHistoryTest {
    @Test
    public void getInstance_testSingleSingletonObject_sameInstance() {
        InputHistory instance1 = InputHistory.getInstance();
        InputHistory instance2 = InputHistory.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    public void addToHistoryTest() {
        InputHistory.resetSingleton();
        InputHistory history = InputHistory.getInstance();
        assertTrue(history.toString().equals("0"));
        history.addToHistory("a");
        assertTrue(history.toString().equals("a;1"));
        history.addToHistory("b");
        assertTrue(history.toString().equals("a;b;2"));
        history.addToHistory("c");
        assertTrue(history.toString().equals("a;b;c;3"));
        history.addToHistory(" "); // empty command
        assertTrue(history.toString().equals("a;b;c;3"));
    }

    @Test
    public void getPreviousInputTest_emptyList_emptyString() {
        InputHistory.resetSingleton();
        InputHistory history = InputHistory.getInstance();
        assertTrue(history.getPreviousInput().equals("")); // empty list
    }

    @Test
    public void getNextInputTest_emptyList_emptyString() {
        InputHistory.resetSingleton();
        InputHistory history = InputHistory.getInstance();
        assertTrue(history.getNextInput().equals("")); // empty list
    }

    @Test
    public void combinedTest_simpleNavigation() {
        InputHistory.resetSingleton();
        InputHistory history = InputHistory.getInstance();
        history.addToHistory("a");
        history.addToHistory("b");
        history.addToHistory("c");
        assertTrue(history.getPreviousInput().equals("c"));
        assertTrue(history.getPreviousInput().equals("b"));
        assertTrue(history.getPreviousInput().equals("a"));
        assertTrue(history.getPreviousInput().equals("a"));
        assertTrue(history.getNextInput().equals("b"));
        assertTrue(history.getNextInput().equals("c"));
        assertTrue(history.getNextInput().equals(""));
        assertTrue(history.getNextInput().equals(""));
        assertTrue(history.getPreviousInput().equals("c"));
    }

    @Test
    public void combinedTest_addingInputs() {
        InputHistory.resetSingleton();
        InputHistory history = InputHistory.getInstance();
        history.addToHistory("a");
        history.addToHistory("b");
        history.addToHistory("c");
        history.addToHistory("d");
        assertTrue(history.getPreviousInput().equals("d"));
        assertTrue(history.getPreviousInput().equals("c"));
        assertTrue(history.getPreviousInput().equals("b"));
        assertTrue(history.getPreviousInput().equals("a"));
        assertTrue(history.getPreviousInput().equals("a"));
        history.addToHistory("e");
        assertTrue(history.getNextInput().equals(""));
        assertTrue(history.getPreviousInput().equals("e"));
        assertTrue(history.getPreviousInput().equals("d"));
        assertTrue(history.getPreviousInput().equals("c"));
        assertTrue(history.getPreviousInput().equals("b"));
        assertTrue(history.getPreviousInput().equals("a"));
        assertTrue(history.getNextInput().equals("b"));
        assertTrue(history.getNextInput().equals("c"));
        assertTrue(history.getNextInput().equals("d"));
        assertTrue(history.getNextInput().equals("e"));
        InputHistory.resetSingleton();
    }

    @Test
    public void combinedTest_spaceAsCommand() {
        InputHistory.resetSingleton();
        InputHistory history = InputHistory.getInstance();

        history.addToHistory("a");
        history.addToHistory("b");
        history.addToHistory("c");
        history.addToHistory(" ");
        assertTrue(history.getPreviousInput().equals("c"));
        assertTrue(history.getPreviousInput().equals("b"));
        assertTrue(history.getNextInput().equals("c"));
        assertTrue(history.getPreviousInput().equals("b"));
        history.addToHistory(" ");
        assertTrue(history.getPreviousInput().equals("c"));
        assertTrue(history.getPreviousInput().equals("b"));
    }
}
