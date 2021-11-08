package safeforhall.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static safeforhall.testutil.Assert.assertThrows;
import static safeforhall.testutil.TypicalEvents.BASKETBALL;
import static safeforhall.testutil.TypicalEvents.ROAD_RELAY;
import static safeforhall.testutil.TypicalPersons.ALICE;
import static safeforhall.testutil.TypicalPersons.BENSON;
import static safeforhall.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.model.event.Event;
import safeforhall.model.event.EventName;
import safeforhall.model.person.Person;
import safeforhall.model.person.exceptions.DuplicatePersonException;
import safeforhall.testutil.PersonBuilder;

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

    // TODO: Fix testcase
    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE)
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
        Person editedAlice = new PersonBuilder(ALICE)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void findEventSuccess() {
        addressBook.addEvent(BASKETBALL);
        addressBook.addEvent(ROAD_RELAY);
        assertEquals(addressBook.findEvent(new EventName("basketball")).get(), BASKETBALL);
        assertEquals(addressBook.findEvent(new EventName("road relay")).get(), ROAD_RELAY);
    }

    @Test
    public void findEventFailure() {
        EventName eventName = new EventName("non existent event");
        assertEquals(Optional.empty(), addressBook.findEvent(eventName));
    }

    @Test
    public void findPersonSuccess() throws CommandException {
        addressBook.addPerson(ALICE);
        addressBook.addPerson(BENSON);
        assertEquals(addressBook.findPerson("A100").get(), ALICE);
        assertEquals(addressBook.findPerson("A101").get(), BENSON);
        assertEquals(addressBook.findPerson("Alice Pauline").get(), ALICE);
        assertEquals(addressBook.findPerson("Benson Meier").get(), BENSON);
    }

    @Test
    public void findPersonFailure() throws CommandException {
        assertEquals(Optional.empty(), addressBook.findPerson("A401"));
        assertEquals(Optional.empty(), addressBook.findPerson("Johnny Lim"));
        assertThrows(CommandException.class, () -> addressBook.findPerson("T12"));
    }

    @Test
    public void checkHashCode() {
        try {
            addressBook.hashCode();
        } catch (NoSuchMethodError e) {
            fail();
        }
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Event> events = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Event> getEventList() {
            return events;
        }
    }

}
