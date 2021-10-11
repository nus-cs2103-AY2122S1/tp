package seedu.academydirectory.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.academydirectory.testutil.Assert.assertThrows;
import static seedu.academydirectory.testutil.TypicalStudents.ALICE;
import static seedu.academydirectory.testutil.TypicalStudents.getTypicalAcademyDirectory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.academydirectory.model.student.Student;
import seedu.academydirectory.model.student.exceptions.DuplicateStudentException;
import seedu.academydirectory.testutil.StudentBuilder;

public class AcademyDirectoryTest {

    private final AcademyDirectory academyDirectory = new AcademyDirectory();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), academyDirectory.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> academyDirectory.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAcademyDirectory_replacesData() {
        AcademyDirectory newData = getTypicalAcademyDirectory();
        academyDirectory.resetData(newData);
        assertEquals(newData, academyDirectory);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two students with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        AcademyDirectoryStub newData = new AcademyDirectoryStub(newStudents);

        assertThrows(DuplicateStudentException.class, () -> academyDirectory.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> academyDirectory.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInAcademyDirectory_returnsFalse() {
        assertFalse(academyDirectory.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInAcademyDirectory_returnsTrue() {
        academyDirectory.addStudent(ALICE);
        assertTrue(academyDirectory.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentWithSameIdentityFieldsInAcademyDirectory_returnsTrue() {
        academyDirectory.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(academyDirectory.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> academyDirectory.getStudentList().remove(0));
    }

    /**
     * A stub ReadOnlyAcademyDirectory whose students list can violate interface constraints.
     */
    private static class AcademyDirectoryStub implements ReadOnlyAcademyDirectory {
        private final ObservableList<Student> students = FXCollections.observableArrayList();

        AcademyDirectoryStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }
    }

}
