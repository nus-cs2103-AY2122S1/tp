package seedu.tuitione.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.tuitione.testutil.Assert.assertThrows;
import static seedu.tuitione.testutil.TypicalStudents.ALICE;
import static seedu.tuitione.testutil.TypicalStudents.getTypicalTuitione;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.student.Student;
import seedu.tuitione.model.student.exceptions.DuplicateStudentException;
import seedu.tuitione.testutil.StudentBuilder;

public class TuitioneTest {

    private final Tuitione tuitione = new Tuitione();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), tuitione.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tuitione.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTuitione_replacesData() {
        Tuitione newData = getTypicalTuitione();
        tuitione.resetData(newData);
        assertEquals(newData, tuitione);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two students with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        TuitioneStub newData = new TuitioneStub(newStudents);

        assertThrows(DuplicateStudentException.class, () -> tuitione.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tuitione.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInTuitione_returnsFalse() {
        assertFalse(tuitione.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInTuitione_returnsTrue() {
        tuitione.addStudent(ALICE);
        assertTrue(tuitione.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentWithSameIdentityFieldsInTuitione_returnsTrue() {
        tuitione.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(tuitione.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> tuitione.getStudentList().remove(0));
    }

    /**
     * A stub ReadOnlyTuitione whose students list can violate interface constraints.
     */
    private static class TuitioneStub implements ReadOnlyTuitione {
        private final ObservableList<Student> students = FXCollections.observableArrayList();
        private final ObservableList<Lesson> lessons = FXCollections.observableArrayList();

        // overloaded constructor that does not add lessons
        TuitioneStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        TuitioneStub(Collection<Student> students, Collection<Lesson> lessons) {
            this.students.setAll(students);
            this.lessons.setAll(lessons);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }

        @Override
        public ObservableList<Lesson> getLessonList() {
            return lessons;
        }
    }

}
