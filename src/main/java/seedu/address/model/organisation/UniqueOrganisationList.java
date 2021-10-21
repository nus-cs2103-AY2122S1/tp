package seedu.address.model.organisation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;

/**
 * A list of organisations that enforces uniqueness between its elements and does not allow nulls.
 * An organisation is considered unique by comparing using {@code Organisation#isSameOrganisation(Organisation)}. As such, adding and updating of
 * organisations uses Organisation#isSameOrganisation(Organisation) for equality so as to ensure that the organisation being added or updated is
 * unique in terms of identity in the UniqueOrganisationList. However, the removal of a organisation uses Organisation#equals(Object) so
 * as to ensure that the organisation with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Organisation#isSameOrganisation(Organisation)
 */
public class UniqueOrganisationList implements Iterable<Organisation> {

    private final ObservableList<Organisation> internalList = FXCollections.observableArrayList();
    private final ObservableList<Organisation> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public UniqueOrganisationList() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public UniqueOrganisationList(UniqueOrganisationList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Returns true if an organisation with the same identity as {@code organisation} exists in the list.
     */
    public boolean hasOrganisation(Organisation organisation) {
        requireNonNull(organisation);
        return this.contains(organisation);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(UniqueOrganisationList newData) {
        requireNonNull(newData);

        setOrganisations(newData);
    }

    public void setOrganisations(UniqueOrganisationList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Returns true if the list contains an equivalent organisation as the given argument.
     */
    public boolean contains(Organisation toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameOrganisation);
    }

    /**
     * Adds an organisation to the list.
     * The organisation must not already exist in the list.
     */
    public void add(Organisation toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
//    public void setPerson(Person target, Person editedPerson) {
//        requireAllNonNull(target, editedPerson);
//
//        int index = internalList.indexOf(target);
//        if (index == -1) {
//            throw new PersonNotFoundException();
//        }
//
//        if (!target.isSamePerson(editedPerson) && contains(editedPerson)) {
//            throw new DuplicatePersonException();
//        }
//
//        internalList.set(index, editedPerson);
//    }

    /**
     * Removes the equivalent organisation from the list.
     * The organisation must exist in the list.
     */
    public void remove(Organisation toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            // throw organisation exception here
            // throw new PersonNotFoundException();
        }
    }

//    public void setPersons(seedu.address.model.person.UniquePersonList replacement) {
//        requireNonNull(replacement);
//        internalList.setAll(replacement.internalList);
//    }
//
//    /**
//     * Replaces the contents of this list with {@code persons}.
//     * {@code persons} must not contain duplicate persons.
//     */
//    public void setPersons(List<Person> persons) {
//        requireAllNonNull(persons);
//        if (!personsAreUnique(persons)) {
//            throw new DuplicatePersonException();
//        }
//
//        internalList.setAll(persons);
//    }

    /**
     * Sort organisation list alphabetically
     */
    public void sortOrganisations() {
        Comparator<Organisation> comparator = new Comparator<Organisation>() {
            public int compare(Organisation o1, Organisation o2) {
                return o1.getName().toString().compareTo(o2.getName().toString());
            }
        };
        internalList.sort(comparator);
    }

    public ObservableList<Organisation> getOrganisationList() {
        return this.asUnmodifiableObservableList();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Organisation> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Organisation> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.organisation.UniqueOrganisationList // instanceof handles nulls
                && internalList.equals(((seedu.address.model.organisation.UniqueOrganisationList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code organisations} contains only unique organisations.
     */
    private boolean organisationsAreUnique(List<Organisation> organisations) {
        for (int i = 0; i < organisations.size() - 1; i++) {
            for (int j = i + 1; j < organisations.size(); j++) {
                if (organisations.get(i).isSameOrganisation(organisations.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

