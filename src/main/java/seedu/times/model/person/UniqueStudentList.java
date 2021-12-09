package seedu.times.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.times.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.times.commons.core.LogsCenter;
import seedu.times.model.person.exceptions.DuplicatePersonException;
import seedu.times.model.person.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and
 * updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniqueStudentList implements Iterable<Student> {

    private final ObservableList<Student> internalList = FXCollections.observableArrayList();
    private final ObservableList<Student> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    private final Logger logger = LogsCenter.getLogger(UniqueStudentList.class);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Student toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Student toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        int listSize = internalList.size();

        /*
         * TODO:
         * As of the new implementation there is a possibility a Student has multiple classes, so this is commented out
         * for now
         *        for (int i = 0; i < listSize; i++) {
         *
         *             if (internalList.get(i).getClassTiming().isEarlier(toAdd.getClassTiming())) {
         *                 continue;
         *             } else {
         *                 internalList.add(i, toAdd);
         *                 break;
         *             }
         *         }
         */

        if (listSize == 0 || internalList.size() == listSize) {
            internalList.add(toAdd);
        }

    }

    public Student getStudent(Name name) {
        requireAllNonNull(name);
        for (Student student : internalList) {
            if (student.getName() == name) {
                return student;
            }
        }
        return null;
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setPerson(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedStudent) && contains(editedStudent)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedStudent);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Student toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniqueStudentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        logger.info("List of Students set from replace UniquePersonList.");
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Student> students) {
        requireAllNonNull(students);
        if (!personsAreUnique(students)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(students);
        logger.info("List of Students set from List<Student>.");
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Student> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Student> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueStudentList // instanceof handles nulls
                        && internalList.equals(((UniqueStudentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean personsAreUnique(List<Student> students) {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).isSamePerson(students.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
