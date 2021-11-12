package seedu.academydirectory.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudioRecordTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudioRecord(null));
    }

    @Test
    public void equals() {

        StudioRecord studioRecord = new StudioRecord(4);
        assertEquals(studioRecord, studioRecord);

        StudioRecord studioRecordCopy = new StudioRecord(4);
        StudioRecord studioRecordDiff = new StudioRecord(5);

        assertEquals(studioRecord, studioRecordCopy);
        assertNotEquals(studioRecord, studioRecordDiff);

        Participation participation = new Participation(4);
        Attendance attendance = new Attendance(4);

        assertEquals(studioRecord, new StudioRecord(attendance, participation));
    }

    @Test
    public void testVisualizeForView() {
        Participation participation = new Participation(5);
        Attendance attendance = new Attendance(5);
        StudioRecord emptyRecord = new StudioRecord(attendance, participation);

        // if attendance has not been updated
        assertTrue(emptyRecord.visualizeForView().contains(StudioRecord.SESSION_PLACEHOLDER));
        assertTrue(emptyRecord.visualizeForView().contains(StudioRecord.NOT_ATTENDED));

        attendance.setAttendance(new boolean[]{true, true, true, true, true});
        participation.setParticipation(new int[]{150, 150, 150, 150, 150});
        StudioRecord fullRecord = new StudioRecord(attendance, participation);

        // if attendance has been updated
        assertTrue(fullRecord.visualizeForView().contains(StudioRecord.PARTICIPATION_PLACEHOLDER));
        assertTrue(fullRecord.visualizeForView().contains(StudioRecord.ATTENDED));
    }
}
