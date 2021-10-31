package seedu.track2gather.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.track2gather.model.person.Person;
import seedu.track2gather.model.person.UniquePersonList;

/**
 * Wraps all data at the contacts list level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class Track2Gather implements ReadOnlyTrack2Gather {

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

    public Track2Gather() {}

    /**
     * Creates a Track2Gather using the Persons in the {@code toBeCopied}
     */
    public Track2Gather(ReadOnlyTrack2Gather toBeCopied) {
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
     * Resets the existing data of this {@code Track2Gather} with {@code newData}.
     */
    public void resetData(ReadOnlyTrack2Gather newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    /**
     * Reset the call status of all {@code Person} in {@code Track2Gather} as not called.
     */
    public void resetAllCallStatuses() {
        for (Person i : persons) {
            persons.setPerson(i, new Person(i, i.getCallStatus().reset()));
        }
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the contacts list.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the contacts list.
     * The person must not already exist in the contacts list.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the contacts list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the contacts list.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code Track2Gather}.
     * {@code key} must exist in the contacts list.
     */
    public void removePerson(Person key) {
        persons.remove(key);
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
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Track2Gather // instanceof handles nulls
                && persons.equals(((Track2Gather) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
