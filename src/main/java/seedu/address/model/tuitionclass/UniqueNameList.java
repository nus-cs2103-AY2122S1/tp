package seedu.address.model.tuitionclass;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import seedu.address.model.person.Name;
import seedu.address.model.tuitionclass.exceptions.DuplicateStudentInClassException;

/**
 * Represents a unique list of names corresponding to the students
 * that a {@code TuitionClass} has.
 */
public class UniqueNameList implements Iterable<Name> {

    private final ArrayList<Name> internalList = new ArrayList<>();

    public UniqueNameList() {}

    public UniqueNameList(String[] nameList) {
        for (String name : nameList) {
            internalList.add(new Name(name));
        }
    }


    /**
     * Constructor that builds a UniqueNameList from zero or more names.
     *
     * @param names Names to be stored in the UniqueNameList.
     */
    public UniqueNameList(Name ... names) {
        for (Name name : names) {
            this.add(name);
        }
    }

    /**
     * Checks to see if the name in argument is already in the NameList.
     *
     * @param toCheck Name to check.
     * @return boolean, true if name is already in list, false otherwise.
     */
    public boolean contains(Name toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Removes name from the list.
     *
     * @param name to be removed.
     */
    public void remove(Name name) {
        internalList.remove(name);
    }

    /**
     * Removes all names provided from the list.
     * @param names The UniqueNameList of names to be removed.
     */
    public void removeAll(UniqueNameList names) {
        for (Name name : names) {
            remove(name);
        }
    }

    /**
     * Removes all names provided from the list.
     * @param names The list of names to be removed.
     */
    public void removeAll(List<Name> names) {
        internalList.removeAll(names);
    }

    /**
     * Adds a name to the list.
     *
     * @param toAdd name to be added.
     * @throws DuplicateStudentInClassException if name is already in the list.
     */
    public void add(Name toAdd) throws DuplicateStudentInClassException {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateStudentInClassException(toAdd);
        }
        internalList.add(toAdd);
    }

    /**
     * Adds all the names in the argument into the list.
     *
     * @param names to be added in UniqueNameList form.
     */
    public void addAll(UniqueNameList names) {
        for (Name name : names) {
            if (!this.contains(name)) {
                this.add(name);
            }
        }
    }

    /**
     * Adds all the names in argument into the list.
     *
     * @param names list of names to be added.
     * @throws DuplicateStudentInClassException if there are duplicate names in list.
     */
    public void addAll(List<Name> names) throws DuplicateStudentInClassException {
        for (int i = 0; i < names.size(); i++) {
            this.add(names.get(i));
        }
    }

    public String size() {
        return String.valueOf(internalList.size());
    }

    @Override
    public Iterator<Name> iterator() {
        return internalList.iterator();
    }
}
