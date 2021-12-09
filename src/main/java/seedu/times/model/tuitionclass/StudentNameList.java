package seedu.times.model.tuitionclass;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import seedu.times.model.person.Name;
import seedu.times.model.person.Person;
import seedu.times.model.person.Student;
import seedu.times.model.tuitionclass.exceptions.DuplicateStudentInClassException;

/**
 * Represents a unique list of names corresponding to the students
 * that a {@code TuitionClass} has.
 */
public class StudentNameList implements Iterable<Name> {

    private final List<Name> internalList = new ArrayList<>();

    /**
     * Constructs an StudentNameList from an array of Strings.
     *
     * @param nameList The String array to convert into an StudentNameList.
     */
    public StudentNameList(String[] nameList) {
        for (String name : nameList) {
            internalList.add(new Name(name));
        }
    }

    /**
     * Constructor that builds a StudentNameList from zero or more names.
     *
     * @param names Names to be stored in the StudentNameList.
     */
    public StudentNameList(Name... names) {
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
     * @param names to be added in StudentNameList form.
     */
    public void addAll(StudentNameList names) {
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

    public int size() {
        return internalList.size();
    }

    @Override
    public Iterator<Name> iterator() {
        return internalList.iterator();
    }

    /**
     * Sorts the internal Name list in the same order as the list of students given.
     *
     * @param listToSortBy The student list to sort the name list by.
     */
    public void sortListByList(List<Student> listToSortBy) {
        Collections.sort(internalList,
                Comparator.comparing(name -> listToSortBy.stream().map(Person::getName)
                        .collect(Collectors.toList()).indexOf(name)));
    }

    /**
     * Gets the Name at the given index.
     *
     * @param zeroBasedIndex The index to retrieve the Name from.
     * @return The Name at the given index.
     */
    public Name get(int zeroBasedIndex) {
        assert (zeroBasedIndex < internalList.size());
        return internalList.get(zeroBasedIndex);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof StudentNameList)) {
            return false;
        }

        return ((StudentNameList) o).internalList.equals(internalList);
    }

    /**
     * Replaces the old student name in the internalList with the new name.
     *
     * @param newName Name to replace the old name.
     * @param oldName Name to be replaced by the new name.
     */
    public void replaceStudentName(Name newName, Name oldName) {
        int oldNameIndex = internalList.indexOf(oldName);
        if (oldNameIndex != -1) {
            internalList.set(oldNameIndex, newName);
        }
    }

    /**
     * Utility method for testing
     */
    public String[] getNames() {
        int len = internalList.size();
        String[] res = new String[len];
        for (int i = 0; i < len; i++) {
            res[i] = internalList.get(i).getFullName();
        }
        return res;
    }
}
