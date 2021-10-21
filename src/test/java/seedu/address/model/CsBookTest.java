package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.getTypicalCsBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.group.Group;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.DuplicateStudentException;
import seedu.address.testutil.StudentBuilder;

public class CsBookTest {

    private final CsBook csBook = new CsBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), csBook.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> csBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyCsBook_replacesData() {
        CsBook newData = getTypicalCsBook();
        csBook.resetData(newData);
        assertEquals(newData, csBook);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two students with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        CsBookStub newData = new CsBookStub(newStudents);
        assertThrows(DuplicateStudentException.class, () -> csBook.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> csBook.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInCsBook_returnsFalse() {
        assertFalse(csBook.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInCsBook_returnsTrue() {
        csBook.addStudent(ALICE);
        assertTrue(csBook.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentWithSameIdentityFieldsInCsBook_returnsTrue() {
        csBook.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).build();
        assertTrue(csBook.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> csBook.getStudentList().remove(0));
    }

    /**
     * A stub ReadOnlyCsBook whose students list can violate interface constraints.
     */
    private static class CsBookStub implements ReadOnlyCsBook {
        private final ObservableList<Student> students = FXCollections.observableArrayList();
        private final ObservableList<Group> groups = FXCollections.observableArrayList();

        CsBookStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }

        @Override
        public ObservableList<Group> getGroupList() {
            return groups;
        }
    }

}
