package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.folder.Folder;
import seedu.address.model.folder.FolderName;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Folder> filteredFolders;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredFolders = new FilteredList<>(this.addressBook.getFolderList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public void addFolder(Folder folder) {
        addressBook.addFolder(folder);
        updateFilteredFolderList(PREDICATE_SHOW_ALL_FOLDERS);
    }

    /**
     * Deletes a folder
     */
    public void deleteFolder(Folder folder) {
        addressBook.deleteFolder(folder);
        updateFilteredFolderList(PREDICATE_SHOW_ALL_FOLDERS);
    }

    @Override
    public void setNewFolder(Folder oldFolder, Folder newFolder) {
        requireAllNonNull(oldFolder, newFolder);
        addressBook.setNewFolder(oldFolder, newFolder);
        updateFilteredFolderList(PREDICATE_SHOW_ALL_FOLDERS);

    }

    @Override
    public boolean hasFolder(Folder folder) {
        requireNonNull(folder);
        return addressBook.hasFolder(folder);
    }

    @Override
    public boolean hasFolderName(FolderName folderName) {
        requireNonNull(folderName);
        return addressBook.hasFolderName(folderName);
    }

    @Override
    public boolean folderContainsPerson(Person target, FolderName name) {
        requireAllNonNull(target, name);
        return addressBook.folderContainsPerson(target, name);
    }

    @Override
    public void addContactToFolder(Person target, FolderName folderName) {
        requireAllNonNull(target, folderName);
        addressBook.addContactToFolder(target, folderName);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Filtered Folder List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Folder} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Folder> getFilteredFolderList() {
        return filteredFolders;
    }

    @Override
    public void updateFilteredFolderList(Predicate<Folder> predicate) {
        requireNonNull(predicate);
        filteredFolders.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredFolders.equals(other.filteredFolders);
    }

}
