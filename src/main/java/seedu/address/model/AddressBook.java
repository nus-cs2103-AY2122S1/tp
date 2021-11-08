package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;

import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.UniqueAssignmentList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {
    private static final Optional<Person> ABSENCE_OF_ACTIVE_PERSON = Optional.empty();

    /* The person whose assignment is displayed on the Ui */
    private Optional<Person> activePerson = ABSENCE_OF_ACTIVE_PERSON;
    private Predicate<Person> filteredPersonListPredicate = PREDICATE_SHOW_ALL_PERSONS;
    private final UniquePersonList persons;
    private final UniqueAssignmentList assignments;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        assignments = new UniqueAssignmentList();
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
     * Replaces the contents of the person's assignment list with {@code assignments}.
     * {@code assignments} must not contain duplicate assignments.
     */
    public void setAssignments(List<Assignment> assignments) {
        this.assignments.setAssignments(assignments);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setAssignments(newData.copyAssignmentList());
        setPersons(newData.copyPersonList());

        activePerson = newData.getActivePersonFromPersonList(getPersonList());

        filteredPersonListPredicate = newData.getFilteredPersonListPredicate();
    }

    public void setFilteredPersonListPredicate(Predicate<Person> predicate) {
        this.filteredPersonListPredicate = predicate;
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
     * Returns true if a person has a similar email existing in the address book.
     *
     * @param person person to compare to.
     * @return true if email exists, false otherwise.
     */
    public boolean hasEmail(Person person) {
        requireNonNull(person);
        return persons.containsEmail(person);
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

    //// assignment-level operations
    /**
     * Returns true if an assignment with the same identity as {@code assignment} exists in the person's assignment
     * list.
     */
    public boolean hasAssignment(Person person, Assignment assignment) {
        requireNonNull(assignment);
        return person.getAssignments().contains(assignment);
    }

    /**
     * Adds an assignment to the person's assignment list.
     * The assignment must not already exist in the person's assignment list.
     */
    public void addAssignment(Person person, Assignment assignment) {
        person.getAssignments().add(assignment);
    }

    /**
     * Removes {@code key} from this {@code AddressBook's person assignment list}.
     * {@code key} must exist in the person assignment list.
     */
    public void removeAssignment(Person person, Assignment key) {
        person.getAssignments().delete(key);
    }

    /**
     * Marks {@code key} from this {@code AddressBook's person assignment list}.
     * {@code key} must exist in the person assignment list.
     */
    public void markAssignment(Person person, Assignment key) {
        person.getAssignments().done(key);
    }

    /**
     * Returns whether assignment completed or not.
     * {@code assignment} must exist in the person assignment list.
     */
    public boolean isAssignmentCompleted(Assignment assignment) {
        return assignment.isCompleted();
    }

    /**
     * Removes assignments from all persons' assignment list if completed
     */
    public void cleanAssignments() {
        for (Person person : this.getPersonList()) {
            List<Assignment> completedAssignmentList =
                    person.getAssignments().asUnmodifiableObservableList()
                            .stream()
                            .filter(Assignment::isCompleted)
                            .collect(Collectors.toList());
            for (Assignment assignment : completedAssignmentList) {
                this.removeAssignment(person, assignment);
            }
        }
    }

    /**
     * Retrieves the assignment list of the identified person {@code name} from this {@code AddressBook's person list}.
     */
    public ObservableList<Assignment> getPersonAssignmentList(Person person) {
        return person.getAssignmentAsUnmodifiableObservableList();
    }

    /**
     * Retrieves the currently stored assignment list stored in AddressBook of the active person.
     *
     * @return the current assignment list in AddressBook.
     */
    public ObservableList<Assignment> getCurrentAssignmentList() {
        return assignments.asUnmodifiableObservableList();
    }

    //// util methods

    /**
     * Changes the person whose assignment list is displayed. Changes {@code activePerson} to an empty Optional which
     * represents no assignment list selected if the person indicated does not exist in
     * {@code AddressBook's person list}.
     *
     * @param person
     */
    public void changeActivePerson(Person person) {
        requireNonNull(person);
        this.activePerson = Optional.of(person).filter(targetPerson -> hasPerson(targetPerson));
    }

    /**
     * Returns true if the active person is same as the person.
     * @param person the Person which is queried to find if he/she is the current activePerson
     * @return true if the person is the current activePerson
     */
    public boolean isActivePerson(Person person) {
        requireNonNull(person);
        return activePerson.map(activePerson -> activePerson.equals(person)).orElse(false);
    }

    /**
     * Checks if there is a person whose assignment is stored in AddressBook's assignments.
     * @return true if there is an active person.
     */
    public boolean hasActivePerson() {
        return activePerson.isPresent();
    }

    /**
     * Returns the current person whose assignment is stored in AddressBook's assignments. Assumes
     * that the caller of this method has checked that there is an active person.
     *
     * @return the current active person
     */
    public Person getActivePerson() {
        assert(activePerson.isPresent());

        return activePerson.get();
    }

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public void updateAssignmentList() {
        this.assignments.clearAllAssignments();

        if (activePerson.isPresent()) {
            this.assignments.setAssignments(getActivePerson().getAssignments());
        }
    }

    @Override
    public void clearAssignmentList() {
        this.assignments.clearAllAssignments();
        activePerson = ABSENCE_OF_ACTIVE_PERSON;
    }

    @Override
    public ObservableList<Assignment> getAssignmentsList() {
        return assignments.asUnmodifiableObservableList();
    }

    @Override
    public Predicate<Person> getFilteredPersonListPredicate() {
        return filteredPersonListPredicate;
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

    @Override
    public ObservableList<Assignment> copyAssignmentList() {
        return this.assignments.copyUniqueAssignmentList().asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Person> copyPersonList() {
        return this.persons.copyUniquePersonList().asUnmodifiableObservableList();
    }

    @Override
    public AddressBook copyAddressBook() {
        return new AddressBook(this);
    }

    @Override
    public Optional<Person> getActivePersonFromPersonList(List<Person> persons) {
        if (!activePerson.isPresent()) {
            return ABSENCE_OF_ACTIVE_PERSON;
        }

        Person actualActivePerson = getActivePerson();
        return persons.stream().filter(person -> person.isSamePerson(actualActivePerson)).findFirst();
    }

}
