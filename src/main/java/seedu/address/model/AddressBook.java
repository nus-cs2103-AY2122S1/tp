package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.SortCommandParser;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.tuition.TuitionClass;
import seedu.address.model.tuition.UniqueTuitionList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {
    private static final Logger LOGGER = LogsCenter.getLogger(AddressBook.class);

    private final UniquePersonList persons;
    private final UniqueTuitionList tuitions;
    private SortCommandParser.Order order;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        tuitions = new UniqueTuitionList();
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
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setTuition(List<TuitionClass> tuitionClasses) {
        this.tuitions.setTuitions(tuitionClasses);
        if (this.order != null) {
            this.tuitions.sort(order);
        }
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setTuition(TuitionClass target, TuitionClass editedTuition) {
        requireNonNull(editedTuition);
        tuitions.setTuition(target, editedTuition);
        if (this.order != null) {
            this.tuitions.sort(order);
        }
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setTuition(newData.getTuitionList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    public Person getPerson(Index index) {
        requireNonNull(index);
        if (persons.personListSize() < index.getOneBased()) {
            return null;
        }
        return persons.getPerson(index.getOneBased() - 1);
    }

    public TuitionClass getTuition(Index index) {
        requireNonNull(index);
        if (tuitions.tuitionListSize() < index.getOneBased()) {
            return null;
        }
        return tuitions.getTuitionClass(index.getOneBased() - 1);
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
    public ObservableList<TuitionClass> getTuitionList() {
        return tuitions.asUnmodifiableObservableList();
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

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasTuition(TuitionClass tuitionClass) {
        requireNonNull(tuitionClass);
        return tuitions.contains(tuitionClass);
    }

    /**
     * Adds a tuition class to the address book.
     * The tuition class must not already exist in the address book.
     */
    public void addTuition(TuitionClass t) {
        tuitions.add(t);
        if (this.order != null) {
            this.tuitions.sort(order);
        }
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTuition(TuitionClass key) {
        tuitions.remove(key);
    }

    public TuitionClass addToClass(TuitionClass tuitionClass, Person person) {
        return tuitionClass.addStudent(person);
    }

    /**
     * Returns a person with the same name as the input person.
     *
     * @param otherPerson the person to be checked
     * @return the person with the same name as input.
     */
    public Person getSameNamePerson(Person otherPerson) {
        return this.persons.getSameNamePerson(otherPerson);
    }

    /**
     * Returns the tuition class with a certain id.
     * @param id the class with this id is to be found.
     * @return the class found if exists, return null otherwise.
     */
    public TuitionClass getClassById(Integer id) {
        for (TuitionClass tuitionClass: tuitions) {
            if (tuitionClass.getId() == id) {
                return tuitionClass;
            }
        }
        return null;
    }

    /**
     * Sorts the tuition class list and memorize the order prefered by the tutor.
     * @param order the order the list is to be sorted with.
     */
    public void sort(SortCommandParser.Order order) {
        this.order = order;
        this.tuitions.sort(order);
    }
}
