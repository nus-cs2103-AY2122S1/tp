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

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void getComparator() {
        Comparator<Student> ascendingComparator = Name.getComparator(true);
        Comparator<Student> descendingComparator = Name.getComparator(false);

        Student firstStudent = new StudentBuilder().withName("abc").build();
        Student middleStudent = new StudentBuilder().withName("ghi").build();
        Student lastStudent = new StudentBuilder().withName("xyz").build();

        List<Student> studentList = Arrays.asList(
                middleStudent,
                firstStudent,
                lastStudent);

        List<Student> expectedAscendingList = Arrays.asList(
                firstStudent,
                middleStudent,
                lastStudent
        );

        List<Student> expectedDescendingList = Arrays.asList(
                lastStudent,
                middleStudent,
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
}
