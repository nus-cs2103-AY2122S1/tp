package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.student.Assessment;
import seedu.address.model.student.Group;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two students with the same identity fields
        Student editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newStudents);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasStudent(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasStudent(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addStudent(ALICE);
        assertTrue(addressBook.hasStudent(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addStudent(ALICE);
        Student editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasStudent(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getStudentList().remove(0));
    }

    @Test
    public void getGroup_groupExists_success() {
        AddressBook addressBook = new AddressBook();
        Group added = new Group("T01A");
        addressBook.addGroup(added);
        assertSame(addressBook.getGroup(new Group("T01A")), added);
    }

    @Test
    public void getAssessment_assessmentExists_success() {
        AddressBook addressBook = new AddressBook();
        Assessment added = new Assessment("P01");
        addressBook.addAssessment(added);
        assertSame(addressBook.getAssessment(new Assessment("P01")), added);
    }

    @Test
    public void getGroup_groupDoesNotExist_returnNull() {
        AddressBook addressBook = new AddressBook();
        Group added = new Group("T01A");
        addressBook.addGroup(added);
        assertSame(addressBook.getGroup(new Group("T01B")), null);
    }

    @Test
    public void getAssessment_assessmentDoesNotExist_returnNull() {
        AddressBook addressBook = new AddressBook();
        Assessment added = new Assessment("P01");
        addressBook.addAssessment(added);
        assertSame(addressBook.getAssessment(new Assessment("M01")), null);
    }

    @Test
    public void equals_hashCode() {
        assertEquals(new AddressBook(), new AddressBook());
        assertEquals(new AddressBook().hashCode(), new AddressBook().hashCode());

        AddressBook addressBook = new AddressBook();
        addressBook.addAssessment(new Assessment("P01"));
        AddressBook addressBook1 = new AddressBook(addressBook);
        assertEquals(addressBook, addressBook1);
        assertEquals(addressBook.hashCode(), addressBook1.hashCode());

        addressBook = new AddressBook();
        addressBook.addStudent(new PersonBuilder(ALICE).build());
        addressBook1 = new AddressBook(addressBook);
        assertEquals(addressBook, addressBook1);
        assertEquals(addressBook.hashCode(), addressBook1.hashCode());

        addressBook = new AddressBook();
        addressBook.addGroup(new Group("T01A"));
        addressBook1 = new AddressBook(addressBook);
        assertEquals(addressBook, addressBook1);
        assertEquals(addressBook.hashCode(), addressBook1.hashCode());
    }

    /**
     * A stub ReadOnlyAddressBook whose students list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Student> students = FXCollections.observableArrayList();

        AddressBookStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }

        @Override
        public List<Group> getGroupList() {
            return new ArrayList<>();
        }

        @Override
        public List<Assessment> getAssessmentList() {
            return new ArrayList<>();
        }
    }

}
