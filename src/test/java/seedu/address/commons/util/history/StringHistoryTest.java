package seedu.address.commons.util.history;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class StringHistoryTest {
    private static final String STRING_ZERO = "ZERO";
    private static final String STRING_ONE = "ONE";
    private static final String STRING_TWO = "TWO";

    private final History<String> history = new StringHistory(2);

    public StringHistoryTest() {
        history.add(STRING_ZERO);
    }

    @Test
    public void constructor_withoutCapacity_success() {
        assertDoesNotThrow(() -> new StringHistory());
    }

    @Test
    public void constructor_withCapacity_success() {
        assertDoesNotThrow(() -> new StringHistory(100));
    }

    @Test
    public void get_expectedIndex_successfullyRetrieved() {
        assertEquals(STRING_ZERO, history.get(0));
    }

    @Test
    public void get_tooLargeIndex_throwsNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> history.get(100));
    }


    @Test
    public void add_belowMaxCapacity_successfullyAdded() {
        history.add(STRING_ONE);
        assertEquals(STRING_ONE, history.get(0));
        assertEquals(STRING_ZERO, history.get(1));
    }

    @Test
    public void add_atMaxCapacity_successfullyAdded() {
        history.add(STRING_ONE);
        history.add(STRING_TWO);
        assertEquals(STRING_TWO, history.get(0));
        assertEquals(STRING_ONE, history.get(1));
    }

    @Test
    public void size_success() {
        assertEquals(1, history.size());
        history.add(STRING_ONE);
        assertEquals(2, history.size());
    }
}
