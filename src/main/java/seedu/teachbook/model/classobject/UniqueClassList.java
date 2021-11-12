package seedu.teachbook.model.classobject;

import static java.util.Objects.requireNonNull;
import static seedu.teachbook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.teachbook.commons.core.index.GeneralIndex;
import seedu.teachbook.model.classobject.exceptions.DuplicateClassException;
import seedu.teachbook.model.classobject.exceptions.NoClassAtIndexException;
import seedu.teachbook.model.classobject.exceptions.NoClassWithNameException;

/**
 * A list of classes that enforces uniqueness between its elements and does not allow nulls.
 * A class is considered unique by comparing using {@code Class#isSameClass(Class)}.
 * As such, adding and updating of classes uses {@code Class#isSameClass(Class)} for equality
 * to ensure that the class being added or updated is unique in terms of identity in the UniqueClassList.
 * However, the removal of a class uses {@code Class#equals(Object)} to ensure that
 * the class with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Class#isSameClass(Class)
 */
public class UniqueClassList implements Iterable<Class> {

    private final ObservableList<Class> internalList = FXCollections.observableArrayList();
    private final ObservableList<Class> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent class as the given argument.
     */
    public boolean contains(Class toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameClass);
    }

    /**
     * Adds a class to the list.
     * The class must not already exist in the list.
     */
    public void add(Class toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateClassException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the class {@code target} in the list with {@code editedClass}.
     * {@code target} must exist in the list.
     * The class identity of {@code editedClass} must not be the same as another existing class in the list.
     */
    public void setClass(Class target, Class editedClass) {
        requireAllNonNull(target, editedClass);
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new NoClassWithNameException();
        }
        if (!target.isSameClass(editedClass) && contains(editedClass)) {
            throw new DuplicateClassException();
        }
        internalList.set(index, editedClass);
    }

    /**
     * Removes the equivalent class from the list.
     * The class must exist in the list.
     */
    public void remove(Class toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new NoClassWithNameException();
        }
    }

    public void setClasses(UniqueClassList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code classes}.
     * {@code classes} must not contain duplicate classes.
     */
    public void setClasses(List<Class> classes) {
        requireAllNonNull(classes);
        if (!classesAreUnique(classes)) {
            throw new DuplicateClassException();
        }
        internalList.setAll(classes);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Class> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    public Class getClassAtIndex(GeneralIndex index) {
        try {
            return internalList.get(index.getZeroBased());
        } catch (IndexOutOfBoundsException exception) {
            throw new NoClassAtIndexException();
        }
    }

    /**
     * Returns the index of the class with the specified name in the list.
     * There should be at most one class with the name in the list.
     * Throws a {@code NoClassWithNameException} if the list does not contain such a class.
     *
     * @param className name of the class to be located.
     * @return the index of the class with the specified name.
     * @throws NoClassWithNameException if the list does not contain any class with the name.
     */
    public GeneralIndex locateClass(ClassNameDescriptor className) throws NoClassWithNameException {
        for (int i = 0; i < internalList.size(); i++) {
            if (internalList.get(i).getClassName().equals((className))) {
                return GeneralIndex.fromZeroBased(i);
            }
        }
        throw new NoClassWithNameException();
    }

    /**
     * Returns the number of classes in this list.
     *
     * @return the number of classes in this list.
     */
    public int size() {
        assert (internalList.size() >= 0);
        return internalList.size();
    }

    /**
     * Returns {@code true} if this list contains no class.
     *
     * @return @code true} if this list contains no class.
     */
    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    @Override
    public Iterator<Class> iterator() {
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

    /**
     * Returns true if {@code Classes} contains only unique Classes.
     */
    private boolean classesAreUnique(List<Class> classes) {
        for (int i = 0; i < classes.size() - 1; i++) {
            for (int j = i + 1; j < classes.size(); j++) {
                if (classes.get(i).isSameClass(classes.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
