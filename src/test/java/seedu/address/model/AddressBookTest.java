package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalFacilities.KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1;
import static seedu.address.testutil.TypicalFacilities.KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_10;
import static seedu.address.testutil.TypicalFacilities.KENT_RIDGE_SPORT_HALL_5_COURT_1;
import static seedu.address.testutil.TypicalFacilities.KENT_RIDGE_SPORT_HALL_5_COURT_2;
import static seedu.address.testutil.TypicalFacilities.TAMPINES_HUB_FIELD_SECTION_B;
import static seedu.address.testutil.TypicalFacilities.UNIVERSITY_TOWN_SPORTS_HALL_1_COURT_20;
import static seedu.address.testutil.TypicalFacilities.UNIVERSITY_TOWN_SPORTS_HALL_COURT_21;
import static seedu.address.testutil.TypicalFacilities.UTOWN_FIELD_SECTION_A;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;

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
    public void split_noPersonsInFilteredList_noChangesToFacilities() {
        ObservableList<Person> emptyPersonsList = FXCollections.emptyObservableList();
        FilteredList<Person> emptyList = new FilteredList<>(emptyPersonsList);
        ObservableList<Facility> facilities = addressBook.getFacilityList();
        addressBook.split(emptyList);
        assertEquals(facilities, addressBook.getFacilityList());
    }

    @Test
    public void split_personPresentInFilteredList_facilitiesUpdate() {
        AddressBook newData = getTypicalAddressBook();
        FilteredList<Person> filteredList = new FilteredList<Person>(newData.getPersonList());
        addressBook.split(filteredList);

        AddressBook expected = getTypicalAddressBook();
        TAMPINES_HUB_FIELD_SECTION_B.addPersonToFacility(ALICE);
        TAMPINES_HUB_FIELD_SECTION_B.addPersonToFacility(BENSON);
        TAMPINES_HUB_FIELD_SECTION_B.addPersonToFacility(CARL);
        TAMPINES_HUB_FIELD_SECTION_B.addPersonToFacility(DANIEL);
        KENT_RIDGE_SPORT_HALL_5_COURT_1.addPersonToFacility(ELLE);
        KENT_RIDGE_SPORT_HALL_5_COURT_1.addPersonToFacility(FIONA);
        KENT_RIDGE_SPORT_HALL_5_COURT_1.addPersonToFacility(GEORGE);
        KENT_RIDGE_SPORT_HALL_5_COURT_1.addPersonToFacility(HOON);
        KENT_RIDGE_SPORT_HALL_5_COURT_2.addPersonToFacility(IDA);
        KENT_RIDGE_SPORT_HALL_5_COURT_2.addPersonToFacility(AMY);
        KENT_RIDGE_SPORT_HALL_5_COURT_2.addPersonToFacility(BOB);
        expected.setFacilities(Arrays.asList(
                TAMPINES_HUB_FIELD_SECTION_B,
                KENT_RIDGE_SPORT_HALL_5_COURT_1,
                KENT_RIDGE_SPORT_HALL_5_COURT_2,
                UNIVERSITY_TOWN_SPORTS_HALL_1_COURT_20,
                UNIVERSITY_TOWN_SPORTS_HALL_COURT_21,
                KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1,
                KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_10,
                UTOWN_FIELD_SECTION_A));
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
