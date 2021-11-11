package nustracker.model.student;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nustracker.commons.util.CollectionUtil;
import nustracker.model.student.exceptions.DuplicateStudentException;
import nustracker.model.student.exceptions.StudentNotFoundException;

/**
 * A list of students that enforces uniqueness between its elements and does not allow nulls.
 * A student is considered unique by comparing using {@code Student#isSameStudent(Student)}.
 * As such, adding and updating of students uses Student#isSameStudent(Student) for equality
 * to ensure that the student being added or updated is unique in terms of identity in the UniqueStudentList.
 * However, the removal of a student uses Student#equals(Object) to ensure that the student with exactly the
 * same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Student#hasDuplicateCredentials(Student)
 */
public class UniqueStudentList implements Iterable<Student> {

    private final ObservableList<Student> internalList = FXCollections.observableArrayList();
    private final ObservableList<Student> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /** Fast retrieval of students by StudentId. */
    private final HashMap<StudentId, Student> studentIdStudentHashMap = new HashMap<>();

    /**
     * Returns true if the list contains an equivalent student as the given argument.
     */
    public boolean contains(Student toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::hasDuplicateCredentials);
    }

    /**
     * Adds a student to the list.
     * The student must not already exist in the list.
     */
    public void add(Student toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateStudentException();
        }
        internalList.add(toAdd);
        studentIdStudentHashMap.put(toAdd.getStudentId(), toAdd);
    }

    /**
     * Gets a student from the address book by his/her studentId.
     * Returns null if student does not exist.
     *
     * @param studentId The student's student ID.
     * @return The student from the list that has the same studentId as the given ID.
     */
    public Student get(StudentId studentId) {
        requireNonNull(studentId);

        Student returnThis = studentIdStudentHashMap.get(studentId);

        return returnThis;

    }

    /**
     * Gets a Student by his/her Phone.
     * Null is returned if the {@code Student} with this {@code Phone} does not exist.
     *
     * @param phone The student's phone.
     * @return The student that has the same Phone number as the given Phone, null if that student does not exist.
     */
    public Student getByPhone(Phone phone) {
        for (Student currStudent : internalUnmodifiableList) {
            if (currStudent.getPhone().equals(phone)) {
                return currStudent;
            }
        }

        return null;
    }

    /**
     * Gets a Student by his/her Email.
     * Null is returned if the {@code Student} with this {@code Email} does not exist.
     *
     * @param email The student's email.
     * @return The student that has the same Email as the given Email, null if that student does not exist.
     */
    public Student getByEmail(Email email) {
        for (Student currStudent : internalUnmodifiableList) {
            if (currStudent.getEmail().equals(email)) {
                return currStudent;
            }
        }

        return null;
    }


    /**
     * Replaces the student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the list.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the list.
     */
    public void setStudent(Student target, Student editedStudent) {
        CollectionUtil.requireAllNonNull(target, editedStudent);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new StudentNotFoundException();
        }

        if (!target.hasDuplicateCredentials(editedStudent) && contains(editedStudent)) {
            throw new DuplicateStudentException();
        }

        internalList.set(index, editedStudent);
        studentIdStudentHashMap.put(editedStudent.getStudentId(), editedStudent);
    }

    /**
     * Removes the equivalent student from the list.
     * The student must exist in the list.
     */
    public void remove(Student toRemove) {
        requireNonNull(toRemove);

        // Does not throw error for missing student so we remove from HashMap first
        studentIdStudentHashMap.remove(toRemove.getStudentId());

        if (!internalList.remove(toRemove)) {
            throw new StudentNotFoundException();
        }
    }

    public void setStudents(UniqueStudentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        CollectionUtil.requireAllNonNull(students);
        if (!studentsAreUnique(students)) {
            throw new DuplicateStudentException();
        }

        internalList.setAll(students);

        // Set to student ID to Student HashMap as well
        studentIdStudentHashMap.clear();
        internalList.stream().forEach(student -> studentIdStudentHashMap.put(student.getStudentId(), student));
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
     * Returns true if {@code students} contains only unique students.
     */
    private boolean studentsAreUnique(List<Student> students) {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).hasDuplicateCredentials(students.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
