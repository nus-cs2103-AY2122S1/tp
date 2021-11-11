package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.model.residency.ResidencyTest.CHECK_IN_TIME_ONE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.PERSON_LIST_ONE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalRecordsBook.RESIDENCY_ONE;
import static seedu.address.testutil.TypicalRecordsBook.getTypicalRecordsBook;
import static seedu.address.testutil.TypicalResidencyBook.getTypicalResidencyBook;
import static seedu.address.testutil.TypicalRooms.ROOM_ONE;
import static seedu.address.testutil.TypicalRooms.ROOM_TWO;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.residency.ReadOnlyResidencyBook;
import seedu.address.model.residency.Residency;
import seedu.address.model.residency.ResidencyBook;
import seedu.address.model.residency.exceptions.DuplicatePersonRegException;
import seedu.address.model.residency.exceptions.DuplicateRoomRegException;
import seedu.address.model.room.Room;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalRecordsBook;
import seedu.address.testutil.TypicalResidencyBook;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
        assertEquals(Collections.emptyList(), addressBook.getRoomList());
        assertEquals(Collections.emptyList(), addressBook.getResidencyList());
        assertEquals(Collections.emptyList(), addressBook.getRecordsList());
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
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void getRecord_validPerson_returnsTrue() {
        addressBook.setRecords(getTypicalRecordsBook());
        assertEquals(Optional.of(RESIDENCY_ONE), addressBook.getRecord(ALICE));
    }

    @Test
    public void getRecord_invalidPerson_returnsEmptyOptional() {
        addressBook.setRecords(getTypicalRecordsBook());
        assertEquals(Optional.empty(), addressBook.getRecord(DANIEL));
    }

    @Test
    public void getRecord_validRoom_returnsTrue() {
        addressBook.setRecords(getTypicalRecordsBook());
        assertEquals(Optional.of(RESIDENCY_ONE), addressBook.getRecord(ROOM_ONE));
    }

    @Test
    public void getRecord_invalidRoom_returnsEmptyOptional() {
        addressBook.setRecords(getTypicalRecordsBook());
        assertEquals(Optional.empty(), addressBook.getRecord(ROOM_TWO));
    }

    @Test
    public void register_nullResidency_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.register(null));
        assertThrows(NullPointerException.class, () -> addressBook.register(null, null));
        assertThrows(NullPointerException.class, () -> addressBook.register(null, null));
    }

    @Test
    public void register_validResidency_registersCorrectly() {
        Residency residency = new Residency(ROOM_TWO, PERSON_LIST_ONE, CHECK_IN_TIME_ONE, null);
        addressBook.register(residency);
        assertEquals(addressBook.getResidencyBook(), TypicalResidencyBook.getTypicalAddressBook().getResidencyBook());
    }

    @Test
    public void register_invalidResidencyWithDuplicateRooms_throwsDuplicateRoomRegException() {
        Residency invalidResidency = new Residency(ROOM_TWO, PERSON_LIST_ONE, CHECK_IN_TIME_ONE, null);
        addressBook.setResidencies(getTypicalResidencyBook());
        assertThrows(DuplicateRoomRegException.class, () -> addressBook.register(invalidResidency));
    }

    @Test
    public void register_invalidResidencyWithDuplicatePersons_throwsDuplicatePersonRegException() {
        Residency invalidResidency = new Residency(ROOM_ONE, PERSON_LIST_ONE);
        addressBook.setResidencies(getTypicalResidencyBook());
        assertThrows(DuplicatePersonRegException.class, () -> addressBook.register(invalidResidency));
    }

    @Test
    public void record_nullResidency_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.record(null));
    }

    @Test
    public void record_validResidency_registersCorrectly() {
        Residency residency = new Residency(ROOM_ONE, PERSON_LIST_ONE, CHECK_IN_TIME_ONE, null);
        addressBook.record(residency);
        assertEquals(addressBook.getRecordsBook(), TypicalRecordsBook.getTypicalAddressBook().getRecordsBook());
    }


    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Room> rooms = FXCollections.observableArrayList();
        private final ObservableList<Residency> residencies = FXCollections.observableArrayList();
        private final ResidencyBook residencyBook = new ResidencyBook(false);
        private final ObservableList<Residency> records = FXCollections.observableArrayList();
        private final ResidencyBook recordsBook = new ResidencyBook(true);

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Room> getRoomList() {
            return rooms;
        }

        @Override
        public ObservableList<Residency> getResidencyList() {
            return residencies;
        }

        @Override
        public ReadOnlyResidencyBook getResidencyBook() {
            return residencyBook;
        }

        @Override
        public ObservableList<Residency> getRecordsList() {
            return records;
        }

        @Override
        public ReadOnlyResidencyBook getRecordsBook() {
            return recordsBook;
        }
    }

}
