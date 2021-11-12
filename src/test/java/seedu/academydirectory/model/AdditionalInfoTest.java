package seedu.academydirectory.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.commons.core.index.Index;
import seedu.academydirectory.model.student.Student;
import seedu.academydirectory.testutil.StudentBuilder;

public class AdditionalInfoTest {

    @Test
    public void testEmptyAdditionalInfo() {
        AdditionalInfo<?> emptyInfo1 = AdditionalInfo.empty();
        AdditionalInfo<String> emptyInfo2 = AdditionalInfo.empty();
        AdditionalInfo emptyInfo3 = AdditionalInfo.empty(); // Deliberately set to be raw type
        AdditionalInfo<Student> emptyInfo4 = AdditionalInfo.empty();

        // empty info are all equal regardless of the type we passed in,
        // be it wildcard, String, or student, or raw type
        assertEquals(emptyInfo1, emptyInfo2);
        assertEquals(emptyInfo2, emptyInfo3);
        assertEquals(emptyInfo3, emptyInfo4);
    }

    @Test
    public void testNonEmptyAdditionalInfo() {
        AdditionalInfo<String> stringInfo = AdditionalInfo.of("Daenerys Targaryen");

        // two additional info are equal to each other if the info itself is equal
        assertEquals(stringInfo, AdditionalInfo.of("Daenerys Targaryen"));
        assertNotEquals(stringInfo, AdditionalInfo.of("Mother of Dragons"));

        // A non-empty additional info is always not equal to an empty one
        assertNotEquals(stringInfo, AdditionalInfo.empty());

        AdditionalInfo<Student> studentInfo = AdditionalInfo.of(new StudentBuilder().build());

        // Same action, but for the Student class instead
        assertEquals(studentInfo, AdditionalInfo.of(new StudentBuilder().build()));
        assertNotEquals(studentInfo, AdditionalInfo.of("Tyrion Lannister"));

        assertNotEquals(studentInfo, AdditionalInfo.empty());
    }

    @Test
    public void testPresentOrEmpty() {
        AdditionalInfo<?> additionalInfo1 = AdditionalInfo.empty();
        AdditionalInfo<String> stringInfo = AdditionalInfo.of("Arya Stark");
        AdditionalInfo<Student> studentInfo = AdditionalInfo.of(new StudentBuilder().build());

        // Test whether an additional info is empty or not
        assertTrue(additionalInfo1.isEmpty());

        // Test whether an additional info is present or not
        assertTrue(stringInfo.isPresent());
        assertFalse(studentInfo.isEmpty());
    }

    @Test
    public void testGetter() {
        AdditionalInfo<?> additionalInfo1 = AdditionalInfo.empty();
        AdditionalInfo<String> stringInfo = AdditionalInfo.of("Arya Stark");
        AdditionalInfo<Student> studentInfo = AdditionalInfo.of(new StudentBuilder().build());
        AdditionalInfo<Index> indexInfo = AdditionalInfo.of(INDEX_FIRST_STUDENT);

        // If it is empty, NoSuchElementException is thrown
        assertThrows(NoSuchElementException.class, additionalInfo1::get);

        // Test whether getter function returns the correct result in String
        assertEquals(stringInfo.get(), "Arya Stark");
        assertNotEquals(stringInfo.get(), "Jon Snow");

        // Same action but for Student class and for Index class
        assertEquals(studentInfo.get(), new StudentBuilder().build());
        assertEquals(indexInfo.get(), INDEX_FIRST_STUDENT);
    }
}
