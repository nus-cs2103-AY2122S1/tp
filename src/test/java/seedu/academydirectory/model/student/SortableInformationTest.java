package seedu.academydirectory.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SortableInformationTest {
    @Test
    public void isValidSortable() {

        // null sortable
        assertThrows(NullPointerException.class, () -> SortableInformation.isValidSortable(null));

        assertTrue(SortableInformation.isValidSortable("PE"));
        assertTrue(SortableInformation.isValidSortable("pe"));
        assertTrue(SortableInformation.isValidSortable("pE"));
        assertTrue(SortableInformation.isValidSortable("Pe"));

        assertTrue(SortableInformation.isValidSortable("RA1"));
        assertTrue(SortableInformation.isValidSortable("ra1"));
        assertTrue(SortableInformation.isValidSortable("rA1"));
        assertTrue(SortableInformation.isValidSortable("Ra1"));


        assertTrue(SortableInformation.isValidSortable("RA2"));
        assertTrue(SortableInformation.isValidSortable("ra2"));
        assertTrue(SortableInformation.isValidSortable("rA2"));
        assertTrue(SortableInformation.isValidSortable("Ra2"));

        assertTrue(SortableInformation.isValidSortable("MIDTERM"));
        assertTrue(SortableInformation.isValidSortable("MiDTERM"));
        assertTrue(SortableInformation.isValidSortable("midterm"));
        assertTrue(SortableInformation.isValidSortable("Midterm"));
        assertTrue(SortableInformation.isValidSortable("mIdterm"));

        assertTrue(SortableInformation.isValidSortable("FINAL"));
        assertTrue(SortableInformation.isValidSortable("final"));
        assertTrue(SortableInformation.isValidSortable("FiNAL"));
        assertTrue(SortableInformation.isValidSortable("Final"));

        assertTrue(SortableInformation.isValidSortable("AVERAGE"));
        assertTrue(SortableInformation.isValidSortable("average"));
        assertTrue(SortableInformation.isValidSortable("AVeRAGE"));

        assertTrue(SortableInformation.isValidSortable("name"));
        assertTrue(SortableInformation.isValidSortable("NAME"));
        assertTrue(SortableInformation.isValidSortable("nAME"));
        assertTrue(SortableInformation.isValidSortable("Name"));

        assertTrue(SortableInformation.isValidSortable("participation"));
        assertTrue(SortableInformation.isValidSortable("PARTICIPATION"));
        assertTrue(SortableInformation.isValidSortable("Participation"));
        assertTrue(SortableInformation.isValidSortable("PARTICIPATIOn"));

        assertFalse(SortableInformation.isValidSortable(""));
        assertFalse(SortableInformation.isValidSortable(" "));
        assertFalse(SortableInformation.isValidSortable("b"));


    }

    @Test
    public void isValidOrder() {
        assertThrows(NullPointerException.class, () -> SortableInformation.Order.isValidOrder(null));

        assertTrue(SortableInformation.Order.isValidOrder("asc"));
        assertTrue(SortableInformation.Order.isValidOrder("aSC"));
        assertTrue(SortableInformation.Order.isValidOrder("ASC"));

        assertTrue(SortableInformation.Order.isValidOrder("desc"));
        assertTrue(SortableInformation.Order.isValidOrder("dESC"));
        assertTrue(SortableInformation.Order.isValidOrder("DESC"));
        assertTrue(SortableInformation.Order.isValidOrder("dEsc"));
        assertTrue(SortableInformation.Order.isValidOrder("DEsc"));

        assertFalse(SortableInformation.Order.isValidOrder("des"));
        assertFalse(SortableInformation.Order.isValidOrder("esc"));

    }
}
