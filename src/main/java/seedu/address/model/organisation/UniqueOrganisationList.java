package seedu.address.model.organisation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.organisation.exceptions.DuplicateOrganisationException;
import seedu.address.model.person.Name;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of organisations that enforces uniqueness between its elements and does not allow nulls.
 * An organisation is considered unique by comparing using {@code Organisation#isSameOrganisation(Organisation)}.
 * As such, adding and updating of organisations uses Organisation#isSameOrganisation(Organisation) for equality
 * so as to ensure that the organisation being added or updated is unique in terms of identity in the
 * UniqueOrganisationList. However, the removal of a organisation uses Organisation#equals(Object) so as
 * to ensure that the organisation with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Organisation#isSameOrganisation(Organisation)
 */
public class UniqueOrganisationList implements Iterable<Organisation> {

    private final ObservableList<Organisation> internalList = FXCollections.observableArrayList();
    private final ObservableList<Organisation> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    private FilteredList<Organisation> filteredOrganisations;

    public UniqueOrganisationList() {
        filteredOrganisations = new FilteredList<>(internalUnmodifiableList);
    }

    /**
     * Creates a UniqueOrganisationList using the Organisations in the {@code toBeCopied}
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
    public void setOrganisations(List<Organisation> organisations) {
        requireNonNull(organisations);
        if (!organisationsAreUnique(organisations)) {
            throw new DuplicateOrganisationException();
        }
        internalList.setAll(organisations);
    }

    /**
     * Replaces the given organisation {@code target} with {@code editedOrganisation}.
     * {@code target} must exist in the address book.
     * The organisation identity of {@code editedOrganisation} must not be the same as
     * another existing organisation in the address book.
     */
    public void setOrganisation(Organisation target, Organisation editedOrganisation) {
        requireAllNonNull(target, editedOrganisation);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSameOrganisation(editedOrganisation) && contains(editedOrganisation)) {
            throw new DuplicateOrganisationException();
        }

        internalList.set(index, editedOrganisation);
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
            throw new DuplicateOrganisationException();
        }
        internalList.add(toAdd);
    }

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

    public Organisation getByName(Name name) {
        Organisation o = null;
        for (Organisation organisation: internalList) {
            if (organisation.getName().equals(name)) {
                o = organisation;
            }
        }
        return o;
    }

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
        return internalUnmodifiableList;
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

