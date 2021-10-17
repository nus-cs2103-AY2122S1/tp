package seedu.academydirectory.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.testutil.StudentBuilder;

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

        participation.add(1, 500);
        assertTrue(participation.getParticipationArray()[0] == 500);

        participation.add(1, 500);
        assertTrue(participation.getParticipationArray()[0] == 500);
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
        assertFalse(Participation.isValidParticipation("-501"));
        assertFalse(Participation.isValidParticipation("501"));

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

        assertEquals(1, participation.getParticipationArray()[0]);
        assertEquals(2, participation.getParticipationArray()[1]);
        assertEquals(3, participation.getParticipationArray()[2]);
        assertEquals(4, participation.getParticipationArray()[3]);
        assertEquals(5, participation.getParticipationArray()[4]);
        assertEquals(6, participation.getParticipationArray()[5]);
        assertEquals(7, participation.getParticipationArray()[6]);
        assertEquals(8, participation.getParticipationArray()[7]);
        assertEquals(9, participation.getParticipationArray()[8]);
        assertEquals(10, participation.getParticipationArray()[9]);

    }

    @Test
    public void getComparator() {
        Comparator<Student> ascendingComparator = Participation.getComparator(true);
        Comparator<Student> descendingComparator = Participation.getComparator(false);

        Participation highParticipation = new Participation(12);
        Participation mediumParticipation = new Participation(12);
        Participation lowParticipation = new Participation(12);

        highParticipation.add(1, 12);
        mediumParticipation.add(1, 6);
        lowParticipation.add(1, 1);

        Student studentHigherParticipation = new StudentBuilder().build();
        studentHigherParticipation.setParticipation(highParticipation);

        Student studentMediumParticipation = new StudentBuilder().build();
        studentMediumParticipation.setParticipation(mediumParticipation);

        Student studentLowerParticipation = new StudentBuilder().build();
        studentLowerParticipation.setParticipation(lowParticipation);


        List<Student> studentList = Arrays.asList(
                studentHigherParticipation,
                studentLowerParticipation,
                studentMediumParticipation);

        List<Student> expectedAscendingList = Arrays.asList(
                studentLowerParticipation,
                studentMediumParticipation,
                studentHigherParticipation);


        List<Student> expectedDescendingList = Arrays.asList(
                studentHigherParticipation,
                studentMediumParticipation,
                studentLowerParticipation
        );

        // check not sorted prior to sort
        assertNotEquals(studentList, expectedAscendingList);
        assertNotEquals(studentList, expectedDescendingList);

        // sort by ascending
        studentList.sort(ascendingComparator);
        assertEquals(studentList, expectedAscendingList);
        assertNotEquals(studentList, expectedDescendingList);

        // sort by descending
        studentList.sort(descendingComparator);
        assertEquals(studentList, expectedDescendingList);
        assertNotEquals(studentList, expectedAscendingList);

    }
}
