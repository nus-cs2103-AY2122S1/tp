package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFolders.CCA;
import static seedu.address.testutil.TypicalFolders.CS2103;
import static seedu.address.testutil.TypicalFolders.EXCLUDED_FOLDER;
import static seedu.address.testutil.TypicalFolders.getTypicalAddressBookWithFolders;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.folder.Folder;
import seedu.address.model.folder.FolderName;
import seedu.address.model.folder.exceptions.DuplicateFolderException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
        assertEquals(Collections.emptyList(), addressBook.getFolderList());
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
    public void resetData_withValidReadOnlyAddressBookWithFolders_replacesData() {
        AddressBook newData = getTypicalAddressBookWithFolders();
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
    public void resetData_withDuplicateFolders_throwsDuplicateFolderException() {
        // Two persons with the same identity fields
        Folder ccaDuplicate = new Folder(new FolderName("CCA"));
        List<Folder> newFolders = Arrays.asList(CCA, ccaDuplicate);
        AddressBookStub newData = new AddressBookStub(addressBook.getPersonList(), newFolders);

        assertThrows(DuplicateFolderException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasFolder_nullFolder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasFolder(null));
    }

    @Test
    public void hasFolder_folderNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasFolder(EXCLUDED_FOLDER));
    }

    @Test
    public void hasFolder_folderInAddressBook_returnsTrue() {
        addressBook.addFolder(CS2103);
        assertTrue(addressBook.hasFolder(CS2103));
    }

    @Test
    public void hasFolder_folderWithSameName_returnsTrue() {
        addressBook.addFolder(CCA);
        Folder ccaDuplicate = new Folder(new FolderName("CCA"));
        assertTrue(addressBook.hasFolder(ccaDuplicate));
    }

    @Test
    public void withFolders_success() {
        addressBook.addFolder(CS2103);
        Model model = new ModelManager(addressBook, new UserPrefs());
        Model actualModel = new ModelManager(AddressBook.withFolders(model), new UserPrefs());
        AddressBook addressBookWithFolder = new AddressBook();
        addressBookWithFolder.addFolder(CS2103);
        Model expectedModel = new ModelManager(addressBookWithFolder, new UserPrefs());
        assertEquals(actualModel, expectedModel);
    }

    /**
     * A stub ReadOnlyAddressBook whose persons and folders list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Folder> folders = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons, Collection<Folder> folders) {
            this.persons.setAll(persons);
            this.folders.setAll(folders);
        }

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
            this.folders.setAll(Collections.emptyList());
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Folder> getFolderList() {
            return folders;
        }
    }

}
