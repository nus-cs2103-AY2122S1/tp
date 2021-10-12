package seedu.address.logic.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.commons.util.CopyableInt.COPYABLE_ONE;
import static seedu.address.commons.util.CopyableInt.COPYABLE_THREE;
import static seedu.address.commons.util.CopyableInt.COPYABLE_TWO;
import static seedu.address.commons.util.CopyableInt.COPYABLE_ZERO;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.CopyableInt;

public class HistoryTest {
    private Historyable<CopyableInt> history = new History<>();

    protected HistoryTest() {
        history.push(COPYABLE_ZERO);
        history.push(COPYABLE_ONE);
        history.push(COPYABLE_TWO);
    }

    @Test
    void size_success() {
        assertEquals(3, history.size());
    }

    @Test
    void go_validInput_success() {
        CopyableInt copyableOne = history.go(-1);
        assertEquals(COPYABLE_ONE.getNumber(), copyableOne.getNumber());
    }

    @Test
    void go_tooLargeInput_success() {
        CopyableInt copyableTwo = history.go(100); // Should return the last pushed item.
        assertEquals(COPYABLE_TWO.getNumber(), copyableTwo.getNumber());
    }

    @Test
    void go_tooSmallInput_success() {
        CopyableInt copyableZero = history.go(-100); // Should return the first pushed item.
        assertEquals(COPYABLE_ZERO.getNumber(), copyableZero.getNumber());
    }

    @Test
    void push_expected_success() {
        history.push(COPYABLE_THREE);
        CopyableInt currentCopyableInt = history.getCurrentState();
        assertNotEquals(COPYABLE_THREE, currentCopyableInt);
        assertEquals(COPYABLE_THREE.getNumber(), currentCopyableInt.getNumber());
    }

    @Test
    void push_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> history.push(null));
    }

    @Test
    void pop_success() {
        CopyableInt poppedCopyableInt = history.pop();
        assertNotEquals(COPYABLE_TWO, poppedCopyableInt);
        assertEquals(COPYABLE_TWO.getNumber(), poppedCopyableInt.getNumber());
    }
}
