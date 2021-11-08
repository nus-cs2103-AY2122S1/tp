package nurseybook.model;

import static nurseybook.testutil.Assert.assertThrows;
import static nurseybook.testutil.TypicalElderlies.AMY;
import static nurseybook.testutil.TypicalElderlies.BOB;
import static nurseybook.testutil.TypicalElderlies.getTypicalNurseyBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import nurseybook.logic.commands.CommandResult;
import nurseybook.testutil.NurseyBookBuilder;

// Solution below adapted from https://github.com/se-edu/addressbook-level4
public class VersionedNurseyBookTest {

    private final ReadOnlyNurseyBook nurseyBookInitial = getTypicalNurseyBook();
    private final ReadOnlyNurseyBook nurseyBookWithAmy = new NurseyBookBuilder().withElderly(AMY).build();
    private final ReadOnlyNurseyBook nurseyBookWithBob = new NurseyBookBuilder().withElderly(BOB).build();
    private final CommandResult dummyCommandResult = new CommandResult("feedback");

    @Test
    public void commit_initialState_noStatesRemovedCurrentStateSaved() {
        VersionedNurseyBook versionedNurseyBook = new VersionedNurseyBook(nurseyBookInitial);

        versionedNurseyBook.commit(dummyCommandResult);
        assertNurseyBookListStatus(versionedNurseyBook,
                Collections.singletonList(nurseyBookInitial),
                nurseyBookInitial,
                Collections.emptyList());
    }

    @Test
    public void commit_multipleStatesPointerAtEndOfStateList_noStatesRemovedCurrentStateSaved() {
        VersionedNurseyBook versionedNurseyBook = initialiseMultipleStatesVersionedNurseyBook();

        versionedNurseyBook.commit(dummyCommandResult);
        assertNurseyBookListStatus(versionedNurseyBook,
                Arrays.asList(nurseyBookInitial, nurseyBookWithAmy, nurseyBookWithBob),
                nurseyBookWithBob,
                Collections.emptyList());
    }

    @Test
    public void commit_multipleStatesPointerNotAtEndOfStateList_statesAfterPointerRemovedCurrentStateSaved() {
        VersionedNurseyBook versionedNurseyBook = initialiseMultipleStatesVersionedNurseyBook();
        versionedNurseyBook.undo();

        versionedNurseyBook.commit(dummyCommandResult);
        assertNurseyBookListStatus(versionedNurseyBook,
                Arrays.asList(nurseyBookInitial, nurseyBookWithAmy),
                nurseyBookWithAmy,
                Collections.emptyList());
    }

    //=========== tests for undo =============================================================

    @Test
    public void canUndo_multipleStatesPointerAtEndOfStateList_returnsTrue() {
        VersionedNurseyBook versionedNurseyBook = initialiseMultipleStatesVersionedNurseyBook();

        assertTrue(versionedNurseyBook.canUndo());
    }

    @Test
    public void canUndo_multipleStatesPointerAtMiddleOfStateList_returnsTrue() {
        VersionedNurseyBook versionedNurseyBook = initialiseMultipleStatesVersionedNurseyBook();
        versionedNurseyBook.undo();

        assertTrue(versionedNurseyBook.canUndo());
    }

    @Test
    public void canUndo_initialState_returnsFalse() {
        VersionedNurseyBook versionedNurseyBook = new VersionedNurseyBook(getTypicalNurseyBook());

        assertFalse(versionedNurseyBook.canUndo());
    }

    @Test
    public void canUndo_multipleStatesPointerAtStartOfStateList_returnsFalse() {
        VersionedNurseyBook versionedNurseyBook = initialiseMultipleStatesVersionedNurseyBook();
        versionedNurseyBook.undo();
        versionedNurseyBook.undo();

        assertFalse(versionedNurseyBook.canUndo());
    }

    @Test
    public void undo_multipleStatesPointerAtEndOfStateList_success() {
        VersionedNurseyBook versionedNurseyBook = initialiseMultipleStatesVersionedNurseyBook();

        versionedNurseyBook.undo();
        assertNurseyBookListStatus(versionedNurseyBook,
                Collections.singletonList(nurseyBookInitial),
                nurseyBookWithAmy,
                Collections.singletonList(nurseyBookWithBob));
    }

    @Test
    public void undo_multipleStatesPointerNotAtStartOfStateList_success() {
        VersionedNurseyBook versionedNurseyBook = initialiseMultipleStatesVersionedNurseyBook();
        versionedNurseyBook.undo();

        versionedNurseyBook.undo();
        assertNurseyBookListStatus(versionedNurseyBook,
                Collections.emptyList(),
                nurseyBookInitial,
                Arrays.asList(nurseyBookWithAmy, nurseyBookWithBob));
    }

    @Test
    public void undo_initialState_throwsNoUndoableStateException() {
        VersionedNurseyBook versionedNurseyBook = new VersionedNurseyBook(nurseyBookInitial);

        assertThrows(VersionedNurseyBook.NoUndoableStateException.class, versionedNurseyBook::undo);
    }

    @Test
    public void undo_multipleStatesPointerAtStartOfStateList_throwsNoUndoableStateException() {
        VersionedNurseyBook versionedNurseyBook = initialiseMultipleStatesVersionedNurseyBook();
        versionedNurseyBook.undo();
        versionedNurseyBook.undo();

        assertThrows(VersionedNurseyBook.NoUndoableStateException.class, versionedNurseyBook::undo);
    }

    //=========== tests for redo =============================================================

    @Test
    public void canRedo_multipleStatesPointerAtEndOfStateList_returnsFalse() {
        VersionedNurseyBook versionedNurseyBook = initialiseMultipleStatesVersionedNurseyBook();

        assertFalse(versionedNurseyBook.canRedo());
    }

    @Test
    public void canRedo_multipleStatesPointerAtMiddleOfStateList_returnsTrue() {
        VersionedNurseyBook versionedNurseyBook = initialiseMultipleStatesVersionedNurseyBook();
        versionedNurseyBook.undo();

        assertTrue(versionedNurseyBook.canRedo());
    }

    @Test
    public void canRedo_initialState_returnsFalse() {
        VersionedNurseyBook versionedNurseyBook = new VersionedNurseyBook(getTypicalNurseyBook());

        assertFalse(versionedNurseyBook.canRedo());
    }

    @Test
    public void canRedo_multipleStatesPointerAtStartOfStateList_returnsTrue() {
        VersionedNurseyBook versionedNurseyBook = initialiseMultipleStatesVersionedNurseyBook();
        versionedNurseyBook.undo();
        versionedNurseyBook.undo();

        assertTrue(versionedNurseyBook.canRedo());
    }

    @Test
    public void redo_multipleStatesPointerAtStartOfStateList_success() {
        VersionedNurseyBook versionedNurseyBook = initialiseMultipleStatesVersionedNurseyBook();
        versionedNurseyBook.undo();
        versionedNurseyBook.undo();

        versionedNurseyBook.redo();
        assertNurseyBookListStatus(versionedNurseyBook,
                Collections.singletonList(nurseyBookInitial),
                nurseyBookWithAmy,
                Collections.singletonList(nurseyBookWithBob));
    }

    @Test
    public void redo_multipleStatesPointerNotAtStartOfStateList_success() {
        VersionedNurseyBook versionedNurseyBook = initialiseMultipleStatesVersionedNurseyBook();
        versionedNurseyBook.undo();

        versionedNurseyBook.redo();
        assertNurseyBookListStatus(versionedNurseyBook,
                Arrays.asList(nurseyBookInitial, nurseyBookWithAmy),
                nurseyBookWithBob,
                Collections.emptyList());
    }

    @Test
    public void redo_initialState_throwsNoRedoableStateException() {
        VersionedNurseyBook versionedNurseyBook = new VersionedNurseyBook(nurseyBookInitial);

        assertThrows(VersionedNurseyBook.NoRedoableStateException.class, versionedNurseyBook::redo);
    }

    @Test
    public void redo_multipleStatesPointerAtEndOfStateList_throwsNoRedoableStateException() {
        VersionedNurseyBook versionedNurseyBook = initialiseMultipleStatesVersionedNurseyBook();

        assertThrows(VersionedNurseyBook.NoRedoableStateException.class, versionedNurseyBook::redo);
    }


    private VersionedNurseyBook initialiseMultipleStatesVersionedNurseyBook() {
        VersionedNurseyBook versionedNurseyBook = new VersionedNurseyBook(getTypicalNurseyBook());
        versionedNurseyBook.resetData(nurseyBookWithAmy);
        versionedNurseyBook.commit(dummyCommandResult);
        versionedNurseyBook.resetData(nurseyBookWithBob);
        versionedNurseyBook.commit(dummyCommandResult);
        return versionedNurseyBook;
    }

    @Test
    public void equals() {
        VersionedNurseyBook versionedNurseyBook = initialiseMultipleStatesVersionedNurseyBook();

        // same object -> returns true
        assertTrue(versionedNurseyBook.equals(versionedNurseyBook));

        // same values -> returns true
        VersionedNurseyBook versionedNurseyBookCopy = initialiseMultipleStatesVersionedNurseyBook();
        assertTrue(versionedNurseyBook.equals(versionedNurseyBookCopy));

        // different types -> returns false
        assertFalse(versionedNurseyBook.equals(1));

        // null -> returns false
        assertFalse(versionedNurseyBook.equals(null));

        // different nurseyBookStateList -> returns false
        VersionedNurseyBook differentVersionedNurseyBook = new VersionedNurseyBook(nurseyBookInitial);
        assertFalse(versionedNurseyBook.equals(differentVersionedNurseyBook));

        // different currentStateIndex -> returns false
        versionedNurseyBookCopy.undo();
        assertFalse(versionedNurseyBook.equals(versionedNurseyBookCopy));
    }

    //@@author tsy24-reused
    //Reused from https://github.com/se-edu/addressbook-level4
    // with minor modifications
    /**
     * Asserts that {@code versionedNurseyBook} is currently pointing at {@code expectedCurrentState},
     * states before {@code versionedNurseyBook#currentStateIndex} is equal to {@code expectedStatesBeforeIndex},
     * and states after {@code versionedNurseyBook#currentStateIndex} is equal to {@code expectedStatesAfterIndex}.
     */
    private void assertNurseyBookListStatus(VersionedNurseyBook versionedNurseyBook,
                                            List<ReadOnlyNurseyBook> expectedStatesBeforeIndex,
                                            ReadOnlyNurseyBook expectedCurrentState,
                                            List<ReadOnlyNurseyBook> expectedStatesAfterIndex) {
        // check state currently pointing at is correct
        assertEquals(new NurseyBook(versionedNurseyBook), expectedCurrentState);

        // set index to 0
        while (versionedNurseyBook.canUndo()) {
            versionedNurseyBook.undo();
        }

        // check states before index are correct
        for (ReadOnlyNurseyBook expectedNurseyBook : expectedStatesBeforeIndex) {
            assertEquals(expectedNurseyBook, new NurseyBook(versionedNurseyBook));
            versionedNurseyBook.redo();
        }

        // check states after index are correct
        for (ReadOnlyNurseyBook expectedNurseyBook : expectedStatesAfterIndex) {
            versionedNurseyBook.redo();
            assertEquals(expectedNurseyBook, new NurseyBook(versionedNurseyBook));
        }

        // check that there are no more states
        assertFalse(versionedNurseyBook.canRedo());

        // revert index to original position
        expectedStatesAfterIndex.forEach(unused -> versionedNurseyBook.undo());
    }
    //@@author
}
