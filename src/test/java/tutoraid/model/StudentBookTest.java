package tutoraid.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PARENT_PHONE_BOB;
import static tutoraid.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tutoraid.model.student.Student;
import tutoraid.model.student.exceptions.DuplicateStudentException;
import tutoraid.testutil.PersonBuilder;
import tutoraid.testutil.Assert;
import tutoraid.testutil.TypicalPersons;

public class StudentBookTest {

    private final StudentBook studentBook = new StudentBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), studentBook.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> studentBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        StudentBook newData = TypicalPersons.getTypicalAddressBook();
        studentBook.resetData(newData);
        assertEquals(newData, studentBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two students with the same identity fields
        Student editedAlice = new PersonBuilder(TypicalPersons.ALICE).withParentPhone(VALID_PARENT_PHONE_BOB).build();
        List<Student> newStudents = Arrays.asList(TypicalPersons.ALICE, editedAlice);
        StudentBookStub newData = new StudentBookStub(newStudents);

        Assert.assertThrows(DuplicateStudentException.class, () -> studentBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> studentBook.hasStudent(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(studentBook.hasStudent(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        studentBook.addStudent(TypicalPersons.ALICE);
        assertTrue(studentBook.hasStudent(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        studentBook.addStudent(TypicalPersons.ALICE);
        Student editedAlice = new PersonBuilder(TypicalPersons.ALICE).withParentPhone(VALID_PARENT_PHONE_BOB).build();
        assertTrue(studentBook.hasStudent(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> studentBook.getStudentList().remove(0));
    }

    /**
     * A stub ReadOnlyStudentBook whose students list can violate interface constraints.
     */
    private static class StudentBookStub implements ReadOnlyStudentBook {
        private final ObservableList<Student> students = FXCollections.observableArrayList();

        StudentBookStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }
    }

}
