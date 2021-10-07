package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.parser.SortCommandParser;
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
     */
    public boolean contains(Person toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Person toAdd) {
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
     */
    public void remove(Person toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        requireAllNonNull(persons);
        if (!personsAreUnique(persons)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(persons);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Person> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Person> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonList // instanceof handles nulls
                        && internalList.equals(((UniquePersonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
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

    /**
     * Sorts the contacts according to the field specified by user.
     */
    public void sortList(SortCommandParser.SortableField sf) {
        switch (sf) {
        case NAME:
            sortListByName();
            break;

        case MODULE_CODES:
            sortListByModule();
            break;

        default:
            // Left empty because SortCommandParser already helped
            // to ensure that the arguments will be correct
        }
    }

    private void sortListByName() {
        internalList.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    private void sortListByModule() {
        internalList.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                List<ModuleCode> o1List = new ArrayList<>(o1.getModuleCodes());
                List<ModuleCode> o2List = new ArrayList<>(o2.getModuleCodes());

                sortModuleList(o1List);
                sortModuleList(o2List);

                int comparisonResult = compareModuleList(o1List, o2List);
                if (comparisonResult == 0) {
                    // Both person have the same modules
                    // Proceed to compare by name
                    return o1.getName().compareTo(o2.getName());
                }
                return comparisonResult;
            }
        });
    }

    private void sortModuleList(List<ModuleCode> l) {
        Collections.sort(l, new Comparator<ModuleCode>() {
            @Override
            public int compare(ModuleCode code1, ModuleCode code2) {
                return code1.compareTo(code2);
            }
        });
    }

    private int compareModuleList(List<ModuleCode> l1, List<ModuleCode> l2) {
        for (int i = 0; i < l1.size(); i++) {
            for (int j = i; j < l2.size(); j++) {
                ModuleCode c1 = l1.get(i);
                ModuleCode c2 = l2.get(j);

                if (c1.compareTo(c2) < 0) {
                    return -1;
                } else if (c1.compareTo(c2) > 0) {
                    return 1;
                } else {
                    if (i == l1.size() - 1) {
                        // Will only reach here if c1.compareTo(c2) == 0
                        // and o2List.size() >= o1List.size()
                        return l1.size() - l2.size();
                    }
                    // To compare next module in the list, need to go back to first loop
                    break;
                }
            }
        }
        // Will only reach here if o2List is shorter than o1List
        // and o1List contains all modules inside o2List
        return 1;
    }
}
