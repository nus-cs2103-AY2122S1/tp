package seedu.address.model;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();


    /**
     * Updates the assignment list in AddressBook to the active person's assignments.
     */
    void updateAssignmentList();

    /**
     * Empties all assignments from the assignment list panel.
     */
    void clearAssignmentList();

    /**
     * Returns an unmodifiable view of the assignment list.
     * This list will not contain any duplicate assignments.
     */
    ObservableList<Assignment> getAssignmentsList();

    /**
     * Returns the copy of assignment list.
     */
    ObservableList<Assignment> copyAssignmentList();

    /**
     * Returns the copy of person list.
     */
    ObservableList<Person> copyPersonList();

    /**
     * Returns a person, found in persons list, enclosed in an Optional that has a matching name to active person.
     * Otherwise, returns an empty Optional.
     *
     * @param persons list of person to select from
     * @return the person with matching name to active person enclosed in an Optional, or Empty Optional if
     * there are no active person.
     */
    Optional<Person> getActivePersonFromPersonList(List<Person> persons);

    /**
     * Returns the copy of address book.
     */
    AddressBook copyAddressBook();

    /**
     * Returns the current filter predicate acting on personList in ModelManager.
     *
     * @return the Predicate that PersonList is filtered by
     */
    Predicate<Person> getFilteredPersonListPredicate();

}
