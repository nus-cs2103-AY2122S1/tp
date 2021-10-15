package seedu.academydirectory.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SortableTest {
    @Test
    public void isValidSortable() {

        // null sortable
        assertThrows(NullPointerException.class, () -> Sortable.isValidSortable(null));

        assertTrue(Sortable.isValidSortable("PE"));
        assertTrue(Sortable.isValidSortable("pe"));
        assertTrue(Sortable.isValidSortable("pE"));
        assertTrue(Sortable.isValidSortable("Pe"));

        assertTrue(Sortable.isValidSortable("RA1"));
        assertTrue(Sortable.isValidSortable("ra1"));
        assertTrue(Sortable.isValidSortable("rA1"));
        assertTrue(Sortable.isValidSortable("Ra1"));


        assertTrue(Sortable.isValidSortable("RA2"));
        assertTrue(Sortable.isValidSortable("ra2"));
        assertTrue(Sortable.isValidSortable("rA2"));
        assertTrue(Sortable.isValidSortable("Ra2"));

        assertTrue(Sortable.isValidSortable("MIDTERM"));
        assertTrue(Sortable.isValidSortable("MiDTERM"));
        assertTrue(Sortable.isValidSortable("midterm"));
        assertTrue(Sortable.isValidSortable("Midterm"));
        assertTrue(Sortable.isValidSortable("mIdterm"));

        assertTrue(Sortable.isValidSortable("FINAL"));
        assertTrue(Sortable.isValidSortable("final"));
        assertTrue(Sortable.isValidSortable("FiNAL"));
        assertTrue(Sortable.isValidSortable("Final"));

        assertTrue(Sortable.isValidSortable("AVERAGE"));
        assertTrue(Sortable.isValidSortable("average"));
        assertTrue(Sortable.isValidSortable("AVeRAGE"));

        assertTrue(Sortable.isValidSortable("name"));
        assertTrue(Sortable.isValidSortable("NAME"));
        assertTrue(Sortable.isValidSortable("nAME"));
        assertTrue(Sortable.isValidSortable("Name"));

        assertTrue(Sortable.isValidSortable("participation"));
        assertTrue(Sortable.isValidSortable("PARTICIPATION"));
        assertTrue(Sortable.isValidSortable("Participation"));
        assertTrue(Sortable.isValidSortable("PARTICIPATIOn"));

        assertFalse(Sortable.isValidSortable(""));
        assertFalse(Sortable.isValidSortable(" "));
        assertFalse(Sortable.isValidSortable("b"));


    }

    @Test
    public void isValidOrder() {
        assertThrows(NullPointerException.class, () -> Sortable.Order.isValidOrder(null));

        assertTrue(Sortable.Order.isValidOrder("asc"));
        assertTrue(Sortable.Order.isValidOrder("aSC"));
        assertTrue(Sortable.Order.isValidOrder("ASC"));

        assertTrue(Sortable.Order.isValidOrder("desc"));
        assertTrue(Sortable.Order.isValidOrder("dESC"));
        assertTrue(Sortable.Order.isValidOrder("DESC"));
        assertTrue(Sortable.Order.isValidOrder("dEsc"));
        assertTrue(Sortable.Order.isValidOrder("DEsc"));

        assertFalse(Sortable.Order.isValidOrder("des"));
        assertFalse(Sortable.Order.isValidOrder("esc"));

    }
}
