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

public class AssessmentTest {

    @Test
    public void isValidAssessment() {
        // null assessment
        assertThrows(NullPointerException.class, () -> Assessment.isValidAssessment(null));

        // blank assessment
        assertFalse(Assessment.isValidAssessment("")); // empty string
        assertFalse(Assessment.isValidAssessment(" ")); // spaces only

        // incorrect assessment
        assertFalse(Assessment.isValidAssessment("Quiz"));
        assertFalse(Assessment.isValidAssessment("RA"));
        assertFalse(Assessment.isValidAssessment("PEE"));
        assertFalse(Assessment.isValidAssessment("RA 1"));

        // case-insensitive assessment
        assertTrue(Assessment.isValidAssessment("rA1"));
        assertTrue(Assessment.isValidAssessment("midTerm"));

        // valid assessment
        assertTrue(Assessment.isValidAssessment("RA1"));
        assertTrue(Assessment.isValidAssessment("MIDTERM"));
        assertTrue(Assessment.isValidAssessment("RA2"));
        assertTrue(Assessment.isValidAssessment("PE"));
        assertTrue(Assessment.isValidAssessment("FINAL"));
    }

    @Test
    public void getAverageComparator() {
        Comparator<Student> ascendingComparator = Assessment.getAverageComparator(true);
        Comparator<Student> descendingComparator = Assessment.getAverageComparator(false);

        Student firstStudent = new StudentBuilder().build();
        Student secondStudent = new StudentBuilder().build();
        Student lastStudent = new StudentBuilder().build();

        firstStudent.getAssessment().updateAssessmentGrade("RA1", 1);
        secondStudent.getAssessment().updateAssessmentGrade("RA1", 2);
        lastStudent.getAssessment().updateAssessmentGrade("RA1", 3);


        List<Student> studentList = Arrays.asList(
                secondStudent,
                firstStudent,
                lastStudent);

        List<Student> expectedAscendingList = Arrays.asList(
                firstStudent,
                secondStudent,
                lastStudent
        );

        List<Student> expectedDescendingList = Arrays.asList(
                lastStudent,
                secondStudent,
                firstStudent
        );

        assertNotEquals(studentList, expectedAscendingList);
        assertNotEquals(studentList, expectedDescendingList);

        studentList.sort(ascendingComparator);
        assertEquals(studentList, expectedAscendingList);
        assertNotEquals(studentList, expectedDescendingList);

        studentList.sort(descendingComparator);
        assertEquals(studentList, expectedDescendingList);
        assertNotEquals(studentList, expectedAscendingList);
    }

    @Test
    public void getIndividualComparator() {
        Comparator<Student> ascendingRA1Comparator =
                Assessment.getIndividualComparator(true, "RA1");
        Comparator<Student> descendingRA1Comparator =
                Assessment.getIndividualComparator(false, "RA1");

        Student firstStudent = new StudentBuilder().build();
        Student secondStudent = new StudentBuilder().build();
        Student lastStudent = new StudentBuilder().build();

        firstStudent.getAssessment().updateAssessmentGrade("RA1", 1);
        secondStudent.getAssessment().updateAssessmentGrade("RA1", 2);
        lastStudent.getAssessment().updateAssessmentGrade("RA1", 3);


        List<Student> studentList = Arrays.asList(
                secondStudent,
                firstStudent,
                lastStudent);

        List<Student> expectedAscendingList = Arrays.asList(
                firstStudent,
                secondStudent,
                lastStudent
        );

        List<Student> expectedDescendingList = Arrays.asList(
                lastStudent,
                secondStudent,
                firstStudent
        );

        assertNotEquals(studentList, expectedAscendingList);
        assertNotEquals(studentList, expectedDescendingList);

        studentList.sort(ascendingRA1Comparator);
        assertEquals(studentList, expectedAscendingList);
        assertNotEquals(studentList, expectedDescendingList);

        studentList.sort(descendingRA1Comparator);
        assertEquals(studentList, expectedDescendingList);
        assertNotEquals(studentList, expectedAscendingList);
    }
}
