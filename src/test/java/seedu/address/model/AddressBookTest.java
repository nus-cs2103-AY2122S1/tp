package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookEmptyFacilityList;
import static seedu.address.testutil.TypicalFacilities.FIELD;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.facility.Facility;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.FacilityBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
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
        Person editedAlice = new PersonBuilder(ALICE).build();
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
        Person editedAlice = new PersonBuilder(ALICE).build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void split_noPersonsInFilteredList_noChangesToFacilities() {
        ObservableList<Person> emptyPersonsList = FXCollections.emptyObservableList();
        FilteredList<Person> emptyList = new FilteredList<>(emptyPersonsList);
        ObservableList<Facility> facilities = addressBook.getFacilityList();
        addressBook.split(emptyList, 1);
        assertEquals(facilities, addressBook.getFacilityList());
    }

    @Test
    public void split_personPresentInFilteredList_facilitiesUpdate() {
        AddressBook newData = new AddressBook(getTypicalAddressBookEmptyFacilityList());
        Facility firstFacility = new FacilityBuilder(FIELD).build();
        newData.addFacility(firstFacility);
        FilteredList<Person> filteredList = new FilteredList<>(newData.getPersonList());
        newData.split(filteredList, 1);

        Facility withAllocatedMembers = new FacilityBuilder(FIELD).build();
        for (Person person : filteredList) {
            withAllocatedMembers.addPersonToFacilityOnDay(person, DayOfWeek.of(1));
        }

        AddressBook expected = new AddressBook(getTypicalAddressBookEmptyFacilityList());
        expected.addFacility(withAllocatedMembers);

        assertEquals(expected, newData);
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Facility> facilities = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Facility> getFacilityList() {
            return facilities;
        }
    }

}
