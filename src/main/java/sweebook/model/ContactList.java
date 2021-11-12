package sweebook.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import sweebook.model.person.Person;
import sweebook.model.person.UniquePersonList;

/**
 * Wraps all data at the contact list level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class ContactList implements ReadOnlyContactList {

    private final UniquePersonList persons;

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

    public ContactList() {}

    /**
     * Creates an ContactList using the Persons in the {@code toBeCopied}
     */
    public ContactList(ReadOnlyContactList toBeCopied) {
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
    }

    /**
     * Resets the existing data of this {@code ContactList} with {@code newData}.
     */
    public void resetData(ReadOnlyContactList newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the contact list.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns the message constraint that comes from {@code person} having the same equality
     * (as defined in Person#isSamePerson) with another person in the contact list.
     * PRECONDITION: otherPerson returns false with Person#isSamePerson.
     */
    public String getSamePersonConstraintMessage(Person person) {
        requireNonNull(person);
        return persons.getSamePersonConstrantMessage(person);
    }

    /**
     * Adds a person to the contact list.
     * The person must not already exist in the contact list.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the contact list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the contact list.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code ContactList}.
     * {@code key} must exist in the contact list.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactList // instanceof handles nulls
                && persons.equals(((ContactList) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
