package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_INTERNATIONAL;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.ALICIA;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.JOHN;
import static seedu.address.testutil.TypicalPersons.NOAH;
import static seedu.address.testutil.TypicalPersons.OLIVIA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.parser.SortCommandParser;
import seedu.address.model.moduleclass.ModuleClass;
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
        Person editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_INTERNATIONAL)
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
        Person editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_INTERNATIONAL)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void sortAddressBookByName_success() {
        List<Person> persons = new ArrayList<>();
        persons.add(BOB);
        persons.add(ALICE);
        addressBook.setPersons(persons);

        AddressBook expectedAddressBook = new AddressBook();
        List<Person> expectedPersons = new ArrayList<>();
        expectedPersons.add(ALICE);
        expectedPersons.add(BOB);
        expectedAddressBook.setPersons(expectedPersons);

        addressBook.sortAddressBook(SortCommandParser.SortableField.NAME);
        assertEquals(expectedAddressBook, addressBook);
    }

    @Test
    public void sortAddressBookByModule_success() {
        List<Person> persons = new ArrayList<>();
        persons.add(JOHN);
        persons.add(OLIVIA);
        persons.add(ALICIA);
        persons.add(NOAH);
        addressBook.setPersons(persons);

        AddressBook expectedAddressBook = new AddressBook();
        List<Person> expectedPersons = new ArrayList<>();
        expectedPersons.add(NOAH);
        expectedPersons.add(ALICIA);
        expectedPersons.add(OLIVIA);
        expectedPersons.add(JOHN);
        expectedAddressBook.setPersons(expectedPersons);

        addressBook.sortAddressBook(SortCommandParser.SortableField.MODULE_CODES);
        assertEquals(expectedAddressBook, addressBook);
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<ModuleClass> classes = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<ModuleClass> getModuleClassList() {
            return classes;
        }
    }

}
