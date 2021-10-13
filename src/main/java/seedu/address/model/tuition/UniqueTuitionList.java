package seedu.address.model.tuition;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.parser.SortCommandParser;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tuition.exceptions.DuplicateTuitionException;
import seedu.address.model.tuition.exceptions.TuitionNotFoundException;

/**
 * A list of tuition that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code TuitionClass#isSameTuition(TuitionClass)}.
 * As such, adding and updating of tuition uses TuitionClass#isSameTuition(TuitionClass) for
 * equality to ensure that the Tuition being added or updated is
 * unique in terms of identity in the UniqueTuitionList.
 * However, the removal of a person uses TuitionClass#equals(Object) to ensure
 * that the TuitionClass with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see TuitionClass#isSameTuition(TuitionClass)
 */
public class UniqueTuitionList implements Iterable<TuitionClass> {

    private final ObservableList<TuitionClass> internalList = FXCollections.observableArrayList();
    private final ObservableList<TuitionClass> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    private SortCommandParser.Order order;

    /**
     * Returns true if the list contains an equivalent tuitionClass as the given argument.
     */
    public boolean contains(TuitionClass toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTuition);
    }

    /**
     * Adds a tuition to the list.
     * The tuition must not already exist in the list.
     */
    public void add(TuitionClass toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTuitionException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the tuition {@code target} in the list with {@code editedTuition}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setTuition(TuitionClass target, TuitionClass editedTuition) {
        requireAllNonNull(target, editedTuition);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSameTuition(editedTuition) && contains(editedTuition)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedTuition);
    }

    /**
     * Removes the equivalent tuition class from the list.
     * The class must exist in the list.
     */
    public void remove(TuitionClass toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TuitionNotFoundException();
        }
    }

    public void setTuitions(seedu.address.model.tuition.UniqueTuitionList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tuitions}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setTuitions(List<TuitionClass> tuitions) {
        requireAllNonNull(tuitions);
        if (!tuitionsAreUnique(tuitions)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(tuitions);
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
                || (other instanceof seedu.address.model.person.UniquePersonList // instanceof handles nulls
                && internalList.equals(((seedu.address.model.tuition.UniqueTuitionList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean tuitionsAreUnique(List<TuitionClass> tuitionClasses) {
        for (int i = 0; i < tuitionClasses.size() - 1; i++) {
            for (int j = i + 1; j < tuitionClasses.size(); j++) {
                if (tuitionClasses.get(i).isSameTuition(tuitionClasses.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public TuitionClass getTuitionClass(int index) {
        return this.internalList.get(index);
    }

    public int tuitionListSize() {
        return this.internalList.size();
    }

    /**
     * Sorts the tuition class list according to time or alphabetically order.
     * @param order the order to sort the list with.
     */
    public void sort(SortCommandParser.Order order) {
        this.order = order;
        internalList.sort(new TuitionClassComparator(order));
    }
    class TuitionClassComparator implements Comparator<TuitionClass> {
        private SortCommandParser.Order order;
        public TuitionClassComparator(SortCommandParser.Order order) {
            this.order = order;
        }

        @Override
        public int compare(TuitionClass o1, TuitionClass o2) {
            switch (order) {
            case ASCENDING:
                return o1.getName().getName().compareTo(o2.getName().getName());
            case DESCENDING:
                return o2.getName().getName().compareTo(o1.getName().getName());
            case TIME:
                return o1.getTimeslot().compareTimeOrder(o2.getTimeslot().getTime());
            default:
                return 0;
            }
        }
    }
}

