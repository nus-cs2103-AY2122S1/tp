package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    private Person alex = new PersonBuilder()
            .withName("Alex Marcus")
            .withPhone("91234567")
            .withEmail("e0000007@u.nus.edu")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withTags("friends")
            .withGitHubId("alex-marcus")
            .withNusNetworkId("e0000007")
            .withType("student")
            .withStudentId("A0000010X")
            .withTutorialId("00")
            .build();
    private Person carol = new PersonBuilder()
            .withName("Carol Heinz")
            .withPhone("97897897")
            .withEmail("e0000009@u.nus.edu")
            .withAddress("wall street")
            .withGitHubId("carol-heinz")
            .withNusNetworkId("e0000009")
            .withType("student")
            .withStudentId("A0001000X")
            .withTutorialId("02")
            .build();

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
    public void mergeFile_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.mergeFile(null));
    }

    @Test
    public void mergeFile_noDuplicates_success() throws DataConversionException {
        AddressBook addAddressBook = getTypicalAddressBook();
        AddressBook mergeAddressBook = getTypicalAddressBook();
        addAddressBook.addPerson(alex);
        addAddressBook.addPerson(carol);
        mergeAddressBook.mergeFile(Paths.get("src/test/data/ImportTest/noDuplicates.json"));
        assertEquals(addAddressBook, mergeAddressBook);
    }

    @Test
    public void mergeFile_withDuplicates_success() throws DataConversionException {
        AddressBook addAddressBook = getTypicalAddressBook();
        AddressBook mergeAddressBook = getTypicalAddressBook();
        addAddressBook.addPerson(alex);
        mergeAddressBook.mergeFile(Paths.get("src/test/data/ImportTest/withDuplicates.json"));
        assertEquals(addAddressBook, mergeAddressBook);
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
    public void hashCodeTest() throws DataConversionException {
        AddressBook addressBook1 = getTypicalAddressBook();
        AddressBook addressBook2 = getTypicalAddressBook();
        assertTrue(addressBook1.hashCode() == addressBook1.hashCode());
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
