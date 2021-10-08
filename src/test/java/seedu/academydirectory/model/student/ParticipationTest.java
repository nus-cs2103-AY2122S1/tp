package seedu.academydirectory.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class ParticipationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Participation(null));
    }

    @Test
    public void constructor_invalidAttendance_throwsNegativeArraySizeException() {
        Integer invalidParticipation = -1;
        assertThrows(NegativeArraySizeException.class, () -> new Participation(invalidParticipation));
    }

    @Test
    public void add_validInput_changeParticipationCount() {
        Participation participation = new Participation(10);

        participation.add(1, 10); // note that the index supplied here is Ones-based
        assertTrue(participation.getParticipationArray()[0] == 10);

        participation.add(1, -100);
        assertTrue(participation.getParticipationArray()[0] == 0);

        participation.add(1, 100);
        assertTrue(participation.getParticipationArray()[0] == 100);

        participation.add(1, 100);
        assertTrue(participation.getParticipationArray()[0] == 100);
    }

    @Test
    public void isValidParticipation() {

        assertTrue(Participation.isValidParticipation("1"));
        assertTrue(Participation.isValidParticipation("-100"));
        assertTrue(Participation.isValidParticipation("0"));
        assertTrue(Participation.isValidParticipation("-0"));

        assertFalse(Participation.isValidParticipation(""));
        assertFalse(Participation.isValidParticipation(" "));
        assertFalse(Participation.isValidParticipation("."));
        assertFalse(Participation.isValidParticipation("-"));
        assertFalse(Participation.isValidParticipation("a"));
        assertFalse(Participation.isValidParticipation("1a"));
        assertFalse(Participation.isValidParticipation("a1"));
        assertFalse(Participation.isValidParticipation("-101"));
        assertFalse(Participation.isValidParticipation("101"));

    }

    @Test
    public void equals() {
        Participation participation = new Participation(10);
        Participation participatioCopy = new Participation(10);
        Participation participationDiffSize = new Participation(13);
        Participation participationDiffCount = new Participation(10).add(1, 10);

        // reflexive
        assertTrue(participation.equals(participation));

        // equal to another participation of same size and values
        assertTrue(participation.equals(participatioCopy));

        // not equal to another participation of different size
        assertFalse(participation.equals(participationDiffSize));

        // not equal to another participation of different count
        assertFalse(participation.equals(participationDiffCount));
    }

    @Test
    public void setParticipation_validInput_participationCountChangesCorrectly() {
        Participation participation = new Participation(10);
        int[] intArray = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        participation.setParticipation(intArray);

        assertTrue(participation.getParticipationArray()[0] == 1);
        assertTrue(participation.getParticipationArray()[1] == 2);
        assertTrue(participation.getParticipationArray()[2] == 3);
        assertTrue(participation.getParticipationArray()[3] == 4);
        assertTrue(participation.getParticipationArray()[4] == 5);
        assertTrue(participation.getParticipationArray()[5] == 6);
        assertTrue(participation.getParticipationArray()[6] == 7);
        assertTrue(participation.getParticipationArray()[7] == 8);
        assertTrue(participation.getParticipationArray()[8] == 9);
        assertTrue(participation.getParticipationArray()[9] == 10);

    }
}
