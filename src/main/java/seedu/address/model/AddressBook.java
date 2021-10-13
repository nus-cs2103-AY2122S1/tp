package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.folder.Folder;
import seedu.address.model.folder.FolderName;
import seedu.address.model.folder.UniqueFolderList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson and .isSameFolder comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueFolderList folders;
    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        folders = new UniqueFolderList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons and Folders in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// static operations

    /**
     * Returns a new address book with empty folders with the copied names from model
     * @param model model to copy folder names from
     * @return address book with copied folders
     */
    public static AddressBook withFolders(Model model) {
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        ObservableList<Folder> folders = addressBook.getFolderList();
        AddressBook newAddressBook = new AddressBook();
        for (Folder folder : folders) {
            newAddressBook.addFolder(new Folder(folder.getFolderName()));
        }
        return newAddressBook;
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the folder list with {@code folders}.
     * {@code folders} must not contain duplicate folders.
     */
    public void setFolders(List<Folder> folders) {
        this.folders.setFolders(folders);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setFolders(newData.getFolderList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// folder-level operations

    /**
     * Returns true if a folder with the same identity as {@code folder} exists in the address book.
     */
    public boolean hasFolder(Folder folder) {
        requireNonNull(folder);
        return folders.contains(folder);
    }

    /**
     * Adds a folder to the address book.
     * The folder must not already exist in the address book.
     */
    public void addFolder(Folder f) {
        folders.add(f);
    }

    /**
     * Adds a Contact to the Folder.
     * The contact must not already exist in the Folder.
     */
    public void addContactToFolder(Person target, FolderName folderName) {
        requireAllNonNull(target, folderName);
        folders.addContact(target, folderName);
    }


    /**
     * Deletes a folder in the address book.
     * The folder must already exist in the address book.
     */
    public void deleteFolder(Folder f) {
        folders.remove(f);
    }


    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Folder> getFolderList() {
        return folders.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
