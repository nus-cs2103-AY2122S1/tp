package seedu.notor.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.notor.model.group.SuperGroup;
import seedu.notor.model.person.Person;
import seedu.notor.model.util.UniqueList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class Notor implements ReadOnlyNotor {

    private final UniqueList<Person> persons;

    private final UniqueList<SuperGroup> superGroups;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniqueList<>();
        superGroups = new UniqueList<>();
    }

    public Notor() {}

    /**
     * Creates an Notor using the Persons in the {@code toBeCopied}
     */
    public Notor(ReadOnlyNotor toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setItems(persons);
    }

    /**
     * Replaces the contents of the person list with {@code SuperGroups}.
     * {@code SuperGroups} must not contain duplicate SuperGroups.
     */
    public void setSuperGroups(List<SuperGroup> superGroups) {
        this.superGroups.setItems(superGroups);
    }

    /**
     * Resets the existing data of this {@code Notor} with {@code newData}.
     */
    public void resetData(ReadOnlyNotor newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setSuperGroups(newData.getSuperGroups());
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
    public void addPerson(Person person) {
        persons.add(person);
    }

    /**
     * Gets Person based on name.
     */
    public Person findPerson(String name) {
        for (Person person: persons) {
            if (person.getName().fullName.equals(name)) {
                return person;
            }
        }
        return null;
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setItem(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code Notor}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// Group-level operations

    /**
     * Adds superGroup into the Notor.
     * @param sg the SuperGroup to be added into Notor.
     */
    public void addSuperGroup(SuperGroup sg) {
        superGroups.add(sg);
    }

    public void deleteSuperGroup(SuperGroup sg) {
        superGroups.remove(sg);
    }

    /**
     * Gets SuperGroup based on group name.
     */
    public SuperGroup findSuperGroup(String name) {
        for (SuperGroup superGroup: superGroups) {
            if (superGroup.getName().equals(name)) {
                return superGroup;
            }
        }
        return null;
    }

    /**
     * Returns true if SuperGroup exists.
     */
    public boolean hasSuperGroup(SuperGroup superGroup) {
        requireNonNull(superGroup);
        return superGroups.contains(superGroup);
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
    public ObservableList<SuperGroup> getSuperGroups() {
        return superGroups.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        // TODO: Add Group
        return other == this // short circuit if same object
                || (other instanceof Notor // instanceof handles nulls
                && persons.equals(((Notor) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
