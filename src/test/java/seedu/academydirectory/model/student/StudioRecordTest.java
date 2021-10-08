package seedu.academydirectory.model.student;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.testutil.Assert.assertThrows;

public class StudioRecordTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudioRecord(null));
    }



    @Test
    public void equals() {

        StudioRecord studioRecord = new StudioRecord(4);

        assertTrue(studioRecord.equals(studioRecord));

        StudioRecord studioRecordCopy = new StudioRecord(4);
        StudioRecord studioRecordDiff = new StudioRecord(5);

        assertTrue(studioRecord.equals(studioRecordCopy));
        assertFalse(studioRecord.equals(studioRecordDiff));

    }
}
