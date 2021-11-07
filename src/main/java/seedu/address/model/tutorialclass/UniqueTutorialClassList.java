package seedu.address.model.tutorialclass;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tutorialclass.exceptions.DuplicateTutorialClassException;
import seedu.address.model.tutorialclass.exceptions.TutorialClassNotFoundException;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.model.tutorialgroup.exceptions.DuplicateTutorialGroupException;
import seedu.address.model.tutorialgroup.exceptions.TutorialGroupNotFoundException;

/**
 * A list of tutorial classes that enforces uniqueness between its elements and does not allow nulls.
 * A tutorial class is considered unique by comparing using {@code TutorialClass#isSameTutorialClass(TutorialClass)}.
 *
 * As such, adding and updating of students uses TutorialClass#isSameTutorialClass(TutorialClass) for equality
 * to ensure that the tutorial class being added or updated is unique in terms of identity
 * in the UniqueTutorialClassList. However, the removal of a tutorial class uses TutorialClass#equals(Object) to
 * ensure that the TutorialClass with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see TutorialClass#isSameTutorialClass(TutorialClass)
 */
public class UniqueTutorialClassList implements Iterable<TutorialClass> {

    private final ObservableList<TutorialClass> internalList = FXCollections.observableArrayList();
    private final ObservableList<TutorialClass> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent tutorial class as the given argument.
     */
    public boolean contains(TutorialClass toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTutorialClass);
    }

    /**
     * Returns true if the list contains the tutorial group.
     */
    public boolean contains(TutorialGroup toCheck) {
        requireNonNull(toCheck);

        // finds the tutorial class that the tutorial group belongs to
        TutorialClass toCheckTutorialClass = TutorialClass.createTestTutorialClass(toCheck.getClassCode());
        Optional<TutorialClass> result = internalList.stream()
                .filter(toCheckTutorialClass::isSameTutorialClass).findFirst();
        return result.get().getTutorialGroups().contains(toCheck);
    }

    /**
     * Adds a tutorial class to the list.
     * The tutorial class must not already exist in the list.
     */
    public void add(TutorialClass toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTutorialClassException();
        }
        internalList.add(toAdd);
    }

    /**
     * Adds a tutorial group to the a tutorial class in the tutorial class list.
     * The tutorial group must not already exist in the list.
     */
    public void add(TutorialGroup toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTutorialGroupException();
        }

        // finds the tutorial class that the tutorial group belongs to
        TutorialClass toCheckTutorialClass = TutorialClass.createTestTutorialClass(toAdd.getClassCode());
        Optional<TutorialClass> result = internalList.stream()
                .filter(toCheckTutorialClass::isSameTutorialClass).findFirst();

        // add the tutorial group to the tutorial class found
        result.get().addTutorialGroup(toAdd);
    }

    /**
     * Sorts the tutorial groups within each tutorial class list.
     */
    public void sort() {
        internalList.stream().forEach(x-> x.sortTutorialGroups());
    }

    /**
     * Removes the equivalent tutorial class from the list.
     * The tutorial class must exist in the list.
     */
    public void remove(TutorialClass toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TutorialClassNotFoundException();
        }
    }

    /**
     * Removes the equivalent tutorial group from the list.
     * The tutorial group must exist in the list.
     */
    public void remove(TutorialGroup toRemove) {
        requireNonNull(toRemove);

        if (!internalList.remove(toRemove)) {
            throw new TutorialGroupNotFoundException();
        }

        // finds the tutorial class that the tutorial group belongs to
        TutorialClass toCheckTutorialClass = TutorialClass.createTestTutorialClass(toRemove.getClassCode());
        Optional<TutorialClass> result = internalList.stream()
                .filter(toCheckTutorialClass::isSameTutorialClass).findFirst();

        // removes the tutorial group from the tutorial class found
        result.get().removeTutorialGroup(toRemove);
    }

    public void setTutorialClasses(UniqueTutorialClassList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tutorialClasses}.
     * {@code tutorialClasses} must not contain duplicate tutorial classes.
     */
    public void setTutorialClasses(List<TutorialClass> tutorialClasses) {
        requireAllNonNull(tutorialClasses);
        if (!tutorialClassesAreUnique(tutorialClasses)) {
            throw new DuplicateTutorialClassException();
        }

        internalList.setAll(tutorialClasses);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<TutorialClass> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<TutorialClass> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTutorialClassList // instanceof handles nulls
                && internalList.equals(((UniqueTutorialClassList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code tutorial classes} contains only unique tutorial classes.
     */
    private boolean tutorialClassesAreUnique(List<TutorialClass> tutorialClasses) {
        for (int i = 0; i < tutorialClasses.size() - 1; i++) {
            for (int j = i + 1; j < tutorialClasses.size(); j++) {
                if (tutorialClasses.get(i).isSameTutorialClass(tutorialClasses.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
