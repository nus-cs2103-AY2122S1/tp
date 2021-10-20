package seedu.address.model.tutorialgroup;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tutorialclass.exceptions.TutorialClassNotFoundException;
import seedu.address.model.tutorialgroup.exceptions.DuplicateTutorialGroupException;
import seedu.address.model.tutorialgroup.exceptions.TutorialGroupNotFoundException;

public class UniqueTutorialGroupList implements Iterable<TutorialGroup> {

    private final ObservableList<TutorialGroup> internalList = FXCollections.observableArrayList();
    private final ObservableList<TutorialGroup> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent tutorial class as the given argument.
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
     * Replaces the tutorial class {@code target} in the list with {@code editedTutorialClass}.
     * {@code target} must exist in the list.
     * The student identity of {@code editedTutorialClass} must not be the same as another
     * existing tutorial class in the list.
     */
    public void setTutorialGroup(TutorialGroup target, TutorialGroup editedTutorialGroup) {
        requireAllNonNull(target, editedTutorialGroup);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TutorialClassNotFoundException();
        }

        if (!target.isSameTutorialGroup(editedTutorialGroup) && contains(editedTutorialGroup)) {
            throw new DuplicateTutorialGroupException();
        }

        internalList.set(index, editedTutorialGroup);
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

    public void setTutorialClasses(UniqueTutorialGroupList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tutorialClasses}.
     * {@code tutorialClasses} must not contain duplicate tutorial classes.
     */
    public void setTutorialGroups(List<TutorialGroup> tutorialGroups) {
        requireAllNonNull(tutorialGroups);
        if (!tutorialGroupsAreUnique(tutorialGroups)) {
            throw new DuplicateTutorialGroupException();
        }

        internalList.setAll(tutorialGroups);
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

    /**
     * Returns true if {@code tutorial classes} contains only unique tutorial classes.
     */
    private boolean tutorialGroupsAreUnique(List<TutorialGroup> tutorialGroups) {
        for (int i = 0; i < tutorialGroups.size() - 1; i++) {
            for (int j = i + 1; j < tutorialGroups.size(); j++) {
                if (tutorialGroups.get(i).isSameTutorialGroup(tutorialGroups.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
