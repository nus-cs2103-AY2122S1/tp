package teletubbies.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import teletubbies.commons.util.InvalidationListenerManager;
import teletubbies.model.person.Person;
import teletubbies.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    //@@author pyokagan and Zhiyuan-Amos
    // Reused from https://github.com/se-edu/addressbook-level4/commit/9582d6ac94fcbd939f243b054332e98974d6c738
    // with minor modifications
    private final InvalidationListenerManager invalidationListenerManager = new InvalidationListenerManager();
    //@@author pyokagan and Zhiyuan-Amos

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
        indicateModified();
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same name {@code person} exists in the address book.
     */
    public boolean hasName(Person person) {
        requireNonNull(person);
        return persons.containsName(person);
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPhoneNumber(Person person) {
        requireNonNull(person);
        return persons.containsPhoneNumber(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
        indicateModified();
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
        indicateModified();
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
        indicateModified();
    }

    //@@author pyokagan and Zhiyuan-Amos
    // Reused from https://github.com/se-edu/addressbook-level4/commit/9582d6ac94fcbd939f243b054332e98974d6c738
    // with minor modifications
    @Override
    public void addListener(InvalidationListener listener) {
        invalidationListenerManager.addListener(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        invalidationListenerManager.removeListener(listener);
    }

    /**
     * Notifies listeners that the address book has been modified.
     */
    protected void indicateModified() {
        invalidationListenerManager.callListeners(this);
    }
    //@@author pyokagan and Zhiyuan-Amos

    /**
     * Replaces the given person {@code personToMerge} in the address book.
     * @param personToMerge Person from file being merged.
     */
    public void mergePerson(Person personToMerge) {
        persons.mergePerson(personToMerge);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons\n" + persons.asUnmodifiableObservableList();
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
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
