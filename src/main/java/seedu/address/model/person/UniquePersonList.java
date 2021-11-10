package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniquePersonList implements Iterable<Person> {

    private final ObservableList<Person> internalList = FXCollections.observableArrayList();
    private final ObservableList<Person> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     *
     * @param toCheck is the Person to be checked.
     * @return Boolean representation of whether the UniquePersonList
     * contains the Person toCheck.
     */
    public boolean contains(Person toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().parallel().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     *
     * @param toAdd is the Person to be added.
     */
    public void add(Person toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
        sortList();
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     *
     * @param target is the target to be replaced by the editedPerson.
     * @param editedPerson is the Person to replace the target Person.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedPerson) && contains(editedPerson)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedPerson);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     *
     * @param toRemove is the Person to be removed.
     */
    public void remove(Person toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    /**
     * Favorites the equivalent person from the list.
     * The person must exist in the list.
     *
     * @param toFavorite is the Person to be favorited.
     */
    public void favorite(Person toFavorite) {
        requireNonNull(toFavorite);
        if (!internalList.stream().anyMatch(x -> x.equals(toFavorite))) {
            throw new PersonNotFoundException();
        } else {
            internalList.forEach(person -> {
                if (person.equals(toFavorite)) {
                    person.setIsFavorite();
                }
            }
            );
        }
    }

    /**
     * Un-favorites the equivalent person from the list.
     * The person must exist in the list.
     *
     * @param toUnfavorite is the Person to be unfavorited.
     */
    public void unfavorite(Person toUnfavorite) {
        requireNonNull(toUnfavorite);
        if (!internalList.stream().anyMatch(x -> x.equals(toUnfavorite))) {
            throw new PersonNotFoundException();
        } else {
            internalList.forEach(person -> {
                if (person.equals(toUnfavorite)) {
                    person.setIsNotFavorite();
                }
            }
            );
        }
    }

    /**
     * Replaces the people in {@code replacement}.
     *
     * @param replacement for {@code internalList}.
     */
    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     *
     * @param persons is the List of people to be set in
     *        in the {@code internalList}.
     */
    public void setPersons(List<Person> persons) {
        requireAllNonNull(persons);
        if (!personsAreUnique(persons)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(persons);
    }

    public ObservableList<Person> getInternalList() {
        return internalList;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     *
     * @return Unmodifiable list of Person in {@code internalList}.
     */
    public ObservableList<Person> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Sorts the {@code internalList}.
     */
    public void sortList() {
        Collections.sort(internalList);
    }

    /**
     * Returns the iterator for {@code internalList}.
     *
     * @return iterator for {@code internalList}.
     */
    @Override
    public Iterator<Person> iterator() {
        return internalList.iterator();
    }


    /**
     * Method to compare two UniquePersonList objects.
     *
     * @param other is the object that is going to be compared
     *              to the UniquePersonList object that called this method.
     * @return boolean representation of whether the UniquePersonList
     * object is equal to the other object passed as parameter.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonList // instanceof handles nulls
                        && internalList.equals(((UniquePersonList) other).internalList));
    }

    /**
     * Returns the {@code hashCode} of {@code internalList}.
     *
     * @return hashCode of {@code internalList}.
     */
    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     *
     * @param persons is a list of Person objects.
     * @return Boolean representation of whether the Person
     * objects in the list are unique.
     */
    private boolean personsAreUnique(List<Person> persons) {
        for (int i = 0; i < persons.size() - 1; i++) {
            for (int j = i + 1; j < persons.size(); j++) {
                if (persons.get(i).isSamePerson(persons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
