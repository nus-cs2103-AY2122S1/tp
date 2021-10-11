package seedu.address.logic.history;

import java.lang.NullPointerException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class SnapshotTest {
    @Test
    public void constructor_expected_success() {
        CopyableInt copyableInt = new CopyableInt(0);
        Snapshot<CopyableInt> snapshot = new Snapshot<>(copyableInt);
        assertNotEquals(copyableInt, snapshot.getState());
        assertEquals(copyableInt.getNumber(), snapshot.getState().getNumber());
    }

    @Test
    public void getState_expected_success() {
        int expectedState = 0;
        CopyableInt copyableInt = new CopyableInt(expectedState);
        Snapshot<CopyableInt> snapshot = new Snapshot<>(copyableInt);
        assertEquals(expectedState, snapshot.getState().getNumber());
    }

    @Test
    public void getState_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Snapshot<>(null));
    }

    @Test
    public void restore_originalSnapshot_success() {
        int expectedState = 0;
        CopyableInt copyableInt = new CopyableInt(expectedState);
        Snapshot<CopyableInt> snapshot = new Snapshot<>(copyableInt);
        Snapshot<CopyableInt> restoredSnapshot = snapshot.restore();
        assertNotEquals(snapshot, restoredSnapshot);
        assertEquals(expectedState, restoredSnapshot.getState().getNumber());
    }

    @Test
    public void restore_editedSnapshot_success() {
        int originalState = 0;
        int editedState = 1;
        CopyableInt originalCopyableInt = new CopyableInt(originalState);
        CopyableInt editedCopyableInt = new CopyableInt(editedState);
        Snapshot<CopyableInt> snapshot = new Snapshot<>(originalCopyableInt);
        Snapshot<CopyableInt> editedSnapshot = snapshot.edit(editedCopyableInt);
        Snapshot<CopyableInt> restoredSnapshot = editedSnapshot.restore();
        assertNotEquals(snapshot, restoredSnapshot);
        assertEquals(originalState, restoredSnapshot.getState().getNumber());
    }

    @Test
    public void edit_expected_success() {
        int originalState = 0;
        int editedState = 1;
        CopyableInt originalCopyableInt = new CopyableInt(originalState);
        CopyableInt editedCopyableInt = new CopyableInt(editedState);
        Snapshot<CopyableInt> snapshot = new Snapshot<>(originalCopyableInt);
        Snapshot<CopyableInt> editedSnapshot = snapshot.edit(editedCopyableInt);
        assertNotEquals(snapshot, editedSnapshot);
        assertNotEquals(snapshot.getState(), editedSnapshot.getState());
        assertNotEquals(snapshot.getState().getNumber(), editedSnapshot.getState().getNumber());
    }

    @Test
    public void edit_null_throwsNullPointerException() {
        Snapshot<CopyableInt> snapshot = new Snapshot<>(new CopyableInt(0));
        assertThrows(NullPointerException.class, () -> snapshot.edit(null));
    }
}
