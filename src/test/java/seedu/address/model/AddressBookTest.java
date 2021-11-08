package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    public void resetData_withDuplicatePersonsSameContactNumberDifferentEmail_throwsDuplicatePersonException() {
        // Two persons with the same contact number but different email
        Person editedBob = new PersonBuilder(BOB).withPhone(VALID_PHONE_AMY)
                .withTags(VALID_TAG_HUSBAND).build();
        List<Person> newPersons = Arrays.asList(AMY, editedBob);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicatePersonsSameEmailDifferentContactNumber_throwsDuplicatePersonException() {
        // Two persons with the same email but different contact number
        Person editedBob = new PersonBuilder(BOB).withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_HUSBAND).build();
        List<Person> newPersons = Arrays.asList(AMY, editedBob);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicatePersonsSameEmailSameContactNumber_throwsDuplicatePersonException() {
        // Two persons with the same email but different contact number
        Person editedBob = new PersonBuilder(BOB).withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_HUSBAND).build();
        List<Person> newPersons = Arrays.asList(AMY, editedBob);
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
        addressBook.addPerson(AMY);
        Person editedBob = new PersonBuilder(BOB).withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(addressBook.hasPerson(editedBob));
    }

    @Test
    public void getDuplicate_noPersonInAddressBook_returnsEmptyList() {
        assertTrue(addressBook.getDuplicate(AMY).isEmpty());
    }

    @Test
    public void getDuplicate_noPersonWithSameIdentityFieldsInAddressBook_returnsEmptyList() {
        addressBook.addPerson(AMY);
        assertTrue(addressBook.getDuplicate(BOB).isEmpty());
    }

    @Test
    public void getDuplicate_personWithSameIdentityFieldsInAddressBook_returnsDuplicatePerson() {
        addressBook.addPerson(AMY);
        Person editedBob = new PersonBuilder(BOB).withPhone(VALID_PHONE_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertEquals(addressBook.getDuplicate(editedBob).get(0), AMY);
    }

    @Test
    public void getDuplicate_personsWithSameIdentityFieldsInAddressBook_returnsDuplicatePersons() {
        addressBook.addPerson(AMY);
        addressBook.addPerson(BOB);
        Person editedBob = new PersonBuilder(BOB).withPhone(VALID_PHONE_AMY).withTags(VALID_TAG_HUSBAND).build();
        List<Person> duplicates = addressBook.getDuplicate(editedBob);
        assertEquals(duplicates.get(0), AMY);
        assertEquals(duplicates.get(1), BOB);
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
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
