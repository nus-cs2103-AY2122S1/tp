package seedu.fast.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.fast.model.person.Person;
import seedu.fast.model.person.PriorityPredicate;
import seedu.fast.model.person.UniquePersonList;
import seedu.fast.model.tag.PriorityTag;

/**
 * Wraps all data at the FAST level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class Fast implements ReadOnlyFast {

    private final UniquePersonList persons;
    private final FilteredList<Person> filteredPersons;


    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        filteredPersons = new FilteredList<>(this.getPersonList());
    }

    public Fast() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public Fast(ReadOnlyFast toBeCopied) {
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
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyFast newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
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

    /**
     * Sorts the list of persons by the given comparator
     */
    public void sortPersons(Comparator<Person> comparator) {
        requireNonNull(comparator);
        persons.sortPersons(comparator);
    }

    /**
     * Returns the number of persons tagged with the respective PriorityTag.
     */
    public int getHighPriorityCount() {
        String[] high = {PriorityTag.HighPriority.TERM};
        PriorityPredicate highPredicate = new PriorityPredicate(Arrays.asList(high));
        filteredPersons.setPredicate(highPredicate);
        return filteredPersons.size();
    }

    public int getMediumPriorityCount() {
        String[] medium = {PriorityTag.MediumPriority.TERM};
        PriorityPredicate mediumPredicate = new PriorityPredicate(Arrays.asList(medium));
        filteredPersons.setPredicate(mediumPredicate);
        return filteredPersons.size();
    }

    public int getLowPriorityCount() {
        String[] low = {PriorityTag.LowPriority.TERM};
        PriorityPredicate lowPredicate = new PriorityPredicate(Arrays.asList(low));
        filteredPersons.setPredicate(lowPredicate);
        return filteredPersons.size();
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
                || (other instanceof Fast // instanceof handles nulls
                && persons.equals(((Fast) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
