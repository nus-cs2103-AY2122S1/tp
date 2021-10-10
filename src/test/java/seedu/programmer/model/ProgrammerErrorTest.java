package seedu.programmer.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.programmer.testutil.Assert.assertThrows;
import static seedu.programmer.testutil.TypicalPersons.ALICE;

import static seedu.programmer.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;

import static seedu.programmer.testutil.TypicalPersons.getTypicalProgrammerError;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.programmer.model.person.Person;
import seedu.programmer.model.person.exceptions.DuplicatePersonException;
import seedu.programmer.testutil.PersonBuilder;

public class ProgrammerErrorTest {

    private final ProgrammerError programmerError = new ProgrammerError();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), programmerError.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> programmerError.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        ProgrammerError newData = getTypicalProgrammerError();
        programmerError.resetData(newData);
        assertEquals(newData, programmerError);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withStudentId(VALID_STUDENTID_BOB).build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> programmerError.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> programmerError.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(programmerError.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        programmerError.addPerson(ALICE);
        assertTrue(programmerError.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        programmerError.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withStudentId(VALID_STUDENTID_BOB).build();
        assertTrue(programmerError.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> programmerError.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyProgrammerError {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
