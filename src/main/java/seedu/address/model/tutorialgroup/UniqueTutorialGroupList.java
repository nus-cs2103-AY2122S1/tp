package seedu.address.model.tutorialgroup;


import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tutorialgroup.exceptions.DuplicateTutorialGroupException;
import seedu.address.model.tutorialgroup.exceptions.TutorialGroupNotFoundException;

public class UniqueTutorialGroupList implements Iterable<TutorialGroup> {

    private final ObservableList<TutorialGroup> internalList = FXCollections.observableArrayList();
    private final ObservableList<TutorialGroup> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent tutorial group as the given argument.
     */
    public boolean contains(TutorialGroup toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTutorialGroup);
    }

    /**
     * Adds a tutorial class to the list.
     * The tutorial class must not already exist in the list.
     */
    public void add(TutorialGroup toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTutorialGroupException();
        }
        internalList.add(toAdd);
    }

    /**
     * Sorts the tutorial class list.
     * Sorts by ClassCode, followed by GroupType and then GroupName.
     */
    public void sort() {
        Comparator<TutorialGroup> compareByName = Comparator
                .comparing(TutorialGroup::getClassCode)
                .thenComparing(TutorialGroup::getGroupType)
                .thenComparing(TutorialGroup::getGroupNumber);
        internalList.sort(compareByName);
    }

    /**
     * Removes the equivalent tutorial class from the list.
     * The tutorial class must exist in the list.
     */
    public void remove(TutorialGroup toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TutorialGroupNotFoundException();
        }
    }

    public ObservableList<TutorialGroup> getInternalList() {
        return internalList;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<TutorialGroup> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<TutorialGroup> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTutorialGroupList // instanceof handles nulls
                && internalList.equals(((UniqueTutorialGroupList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
