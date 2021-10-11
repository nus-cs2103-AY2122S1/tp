package seedu.address.model.moduleclass;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.moduleclass.exceptions.DuplicateModuleClassException;
import seedu.address.model.moduleclass.exceptions.ModuleClassNotFoundException;

public class UniqueModuleClassList implements Iterable<ModuleClass> {

    private final ObservableList<ModuleClass> internalList = FXCollections.observableArrayList();
    private final ObservableList<ModuleClass> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent module class as the given argument.
     */
    public boolean contains(ModuleClass toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameModuleClass);
    }

    /**
     * Adds a class to the list.
     * The class must not already exist in the list.
     */
    public void add(ModuleClass toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateModuleClassException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent class from the list.
     * The class must exist in the list.
     */
    public void remove(ModuleClass toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ModuleClassNotFoundException();
        }
    }

    /**
     * Replaces the class {@code target} in the list with {@code editedClass}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedClass} must not be the same as another existing class in the list.
     */
    public void setModuleClass(ModuleClass target, ModuleClass editedClass) {
        requireAllNonNull(target, editedClass);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ModuleClassNotFoundException();
        }

        if (!target.isSameModuleClass(editedClass) && contains(editedClass)) {
            throw new DuplicateModuleClassException();
        }

        internalList.set(index, editedClass);
    }

    public void setModuleClasses(UniqueModuleClassList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code moduleClasses}.
     * {@code moduleClasses} must not contain duplicate module classes.
     */
    public void setModuleClasses(List<ModuleClass> moduleClasses) {
        requireAllNonNull(moduleClasses);
        if (!moduleClassesAreUnique(moduleClasses)) {
            throw new DuplicateModuleClassException();
        }

        internalList.setAll(moduleClasses);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<ModuleClass> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<ModuleClass> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueModuleClassList // instanceof handles nulls
                && internalList.equals(((UniqueModuleClassList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code moduleClasses} contains only unique classes.
     */
    private boolean moduleClassesAreUnique(List<ModuleClass> moduleClasses) {
        for (int i = 0; i < moduleClasses.size() - 1; i++) {
            for (int j = i + 1; j < moduleClasses.size(); j++) {
                if (moduleClasses.get(i).isSameModuleClass(moduleClasses.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}


