package seedu.times.model.tuitionclass;

import static java.util.Objects.requireNonNull;
import static seedu.times.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.times.model.person.Name;
import seedu.times.model.tuitionclass.exceptions.DuplicateClassException;
import seedu.times.model.tuitionclass.exceptions.OverlappingClassException;
import seedu.times.model.tuitionclass.exceptions.TuitionClassNotFoundException;

/**
 * A list of classes that makes sure there is no overlap between class timings.
 */
public class UniqueClassList implements Iterable<TuitionClass> {

    private final ObservableList<TuitionClass> internalList = FXCollections.observableArrayList();
    private final ObservableList<TuitionClass> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds a class to the list. TuitionClass must not overlap in timing with existing classes.
     *
     * @param toAdd The tuition class to add.
     */
    public void add(TuitionClass toAdd) throws OverlappingClassException {
        requireNonNull(toAdd);
        if (!isValidTiming(toAdd)) {
            throw new OverlappingClassException();
        }

        internalList.add(toAdd);
    }

    /**
     * Deletes a TuitionClass from the list.
     *
     * @param toDelete TuitionClass to be deleted.
     */
    public void delete(TuitionClass toDelete) {
        requireNonNull(toDelete);
        if (!internalList.remove(toDelete)) {
            throw new TuitionClassNotFoundException();
        }
    }

    /**
     * Removes a name from all the classes in the list.
     *
     * @param name to be removed.
     */
    public void removeStudent(Name name) {
        for (int i = 0; i < internalList.size(); i++) {
            TuitionClass tuitionClass = internalList.get(i);
            StudentNameList nameList = tuitionClass.getStudentList();
            if (nameList.contains(name)) {
                TuitionClass editedTuitionClass = tuitionClass.removeStudent(name);
                this.setClass(tuitionClass, editedTuitionClass);
            }
        }
    }

    public void setClasses(List<TuitionClass> classes) {
        requireNonNull(classes);
        //todo check that classes are unique
        List<TuitionClass> editedClasses = new ArrayList<>(classes);
        for (int i = 0; i < editedClasses.size(); i++) {
            for (int j = i + 1; j < editedClasses.size(); j++) {
                if (editedClasses.get(i).equals(editedClasses.get(j))) {
                    throw new DuplicateClassException();
                }
            }
        }
        internalList.setAll(editedClasses);
        //internalList.setAll(classes);
    }

    /**
     * Checks if the class timing is valid (no overlaps with current classes).
     */
    public boolean isValidTiming(TuitionClass otherClass) {
        requireNonNull(otherClass);
        for (TuitionClass tuitionClass : internalList) {
            if (tuitionClass.isOverlapping(otherClass)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if there is a class with the timing specified.
     */
    public boolean classExistAt(ClassTiming timing) {
        //TODO: probably need to modify cos need to consider overlap
        return internalList.stream().anyMatch(c -> {
            return c.getClassTiming().equals(timing);
        });
    }

    /**
     * Checks if there is an identical class in the internalMap.
     */
    public boolean contains(TuitionClass tuitionClass) {
        return internalList.stream().anyMatch(tuitionClass::isSameClass);
    }

    /**
     * Returns the class at the specified timing.
     * Throws an error if the timing is invalid or no classes exist at that timing.
     */
    public TuitionClass getClassAt(ClassTiming timing) {
        requireNonNull(timing);
        for (TuitionClass tuitionClass : internalList) {
            if (tuitionClass.getClassTiming().equals(timing)) {
                return tuitionClass;
            }
        }
        //if no class at specified timing returns null
        return null;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<TuitionClass> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<TuitionClass> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueClassList // instanceof handles nulls
                && internalList.equals(((UniqueClassList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    public void setClass(TuitionClass target, TuitionClass editedClass) {
        requireAllNonNull(target, editedClass);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TuitionClassNotFoundException();
        }

        if (!target.isSameClass(editedClass) && contains(editedClass)) {
            throw new DuplicateClassException();
        }

        //check for overlapping ClassTiming.
        for (TuitionClass tuitionClass : internalList) {
            if (tuitionClass.isSameClass(target)) {
                continue;
            } else if (tuitionClass.isOverlapping(editedClass)) {
                throw new OverlappingClassException();
            }
        }
        internalList.set(index, editedClass);
    }

    //TODO: need a personsAreUnique method probably
}
