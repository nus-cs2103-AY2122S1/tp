package seedu.address.commons.util.history;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CopyableHistoryTest {
    private final History<CopyableStub> history = new CopyableHistory<>();

    @Test
    public void constructor_withoutCapacity_success() {
        assertDoesNotThrow(() -> new CopyableHistory<CopyableStub>());
    }

    @Test
    public void constructor_withCapacity_success() {
        assertDoesNotThrow(() -> new CopyableHistory<CopyableStub>(100));
    }

    @Test
    public void add_copiesTheProvidedCopyable() {
        CopyableStub copyable = new CopyableStub();
        history.add(copyable);
        assertTrue(copyable.getCopyInvocationCount() > 0);
    }

    @Test
    public void get_copiesTheProvidedCopyable() {
        CopyableStub copyable = new CopyableStub();
        history.add(copyable);
        copyable.resetCopyInvocationCount();
        history.get(0);
        assertTrue(copyable.getCopyInvocationCount() > 0);
    }
}
