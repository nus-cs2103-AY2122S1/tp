package seedu.intern.commons.core.selection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intern.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SelectionTest {
    @Test
    public void createAllSelection() {
        Selection selection = Selection.fromAllFlag();

        // check no Index
        assertFalse(selection.hasIndex());
        assertThrows(NullPointerException.class, selection::getIndexOneBased);
        assertThrows(NullPointerException.class, selection::getIndexZeroBased);

        // check All flag present
        assertTrue(selection.hasAllSelectFlag());
        assertTrue(selection.checkAllSelected());
    }

    @Test
    public void createOneBasedSelection() {
        // invalid selection
        assertThrows(IndexOutOfBoundsException.class, () -> Selection.fromIndex(Index.fromOneBased(0)));

        // check equality using the same base
        assertEquals(1, Selection.fromIndex(Index.fromOneBased(1)).getIndexOneBased());
        assertEquals(5, Selection.fromIndex(Index.fromOneBased(5)).getIndexOneBased());

        // convert from one-based selection to zero-based selection
        assertEquals(0, Selection.fromIndex(Index.fromOneBased(1)).getIndexZeroBased());
        assertEquals(4, Selection.fromIndex(Index.fromOneBased(5)).getIndexZeroBased());

        // Check All flag not present
        assertFalse(Selection.fromIndex(Index.fromOneBased(1)).hasAllSelectFlag());
        assertThrows(NullPointerException.class, () -> Selection.fromIndex(Index.fromOneBased(1)).checkAllSelected());
    }

    @Test
    public void createZeroBasedSelection() {
        // invalid selection
        assertThrows(IndexOutOfBoundsException.class, () -> Selection.fromIndex(Index.fromOneBased(-1)));

        // check equality using the same base
        assertEquals(0, Selection.fromIndex(Index.fromZeroBased(0)).getIndexZeroBased());
        assertEquals(5, Selection.fromIndex(Index.fromZeroBased(5)).getIndexZeroBased());

        // convert from zero-based selection to one-based selection
        assertEquals(1, Selection.fromIndex(Index.fromZeroBased(0)).getIndexOneBased());
        assertEquals(6, Selection.fromIndex(Index.fromZeroBased(5)).getIndexOneBased());

        // Check All flag
        assertFalse(Selection.fromIndex(Index.fromZeroBased(0)).hasAllSelectFlag());
        assertThrows(NullPointerException.class, () -> Selection.fromIndex(Index.fromZeroBased(0)).checkAllSelected());
    }

    @Test
    public void equals() {
        final Selection fifthApplicantSelection = Selection.fromIndex(Index.fromOneBased(5));
        final Selection allApplicantSelection = Selection.fromAllFlag();

        // same values -> returns true
        assertEquals(Selection.fromIndex(Index.fromOneBased(5)), fifthApplicantSelection);
        assertEquals(Selection.fromIndex(Index.fromZeroBased(4)), fifthApplicantSelection);

        // same object -> returns true
        assertEquals(fifthApplicantSelection, fifthApplicantSelection);

        // null -> returns false
        assertNotEquals(fifthApplicantSelection, null);

        // different types -> returns false
        assertFalse(fifthApplicantSelection.equals(5.0f));

        // different selection -> returns false
        assertNotEquals(Selection.fromIndex(Index.fromZeroBased(1)), fifthApplicantSelection);

        // same values -> returns true
        assertEquals(Selection.fromAllFlag(), allApplicantSelection);

        // same object -> returns true
        assertEquals(allApplicantSelection, allApplicantSelection);

        // null -> returns false
        assertNotEquals(allApplicantSelection, null);

        // different types -> returns false
        assertNotEquals(allApplicantSelection, Boolean.TRUE);
    }
}
