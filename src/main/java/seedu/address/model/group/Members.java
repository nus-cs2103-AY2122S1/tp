package seedu.address.model.group;

import java.util.ArrayList;
import java.util.HashSet;

import seedu.address.model.student.Student;

public class Members {

    public static final String MESSAGE_CONSTRAINTS = "Weeks should be between 1 and %1$s";

    public final HashSet<Student> studentList;

    /**
     * Constructs a {@code Members}.
     */
    public Members() {
        studentList = new HashSet<>();
    }

    /**
     * Constructs a existing {@code Members}.
     */
    public Members(ArrayList<Student> studentList) {
        this.studentList = new HashSet<>();
        this.studentList.addAll(studentList);
    }

    public void addMember(Student student) {
        this.studentList.add(student);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Members // instanceof handles nulls
                && studentList.equals(((Members) other).studentList)); // state check
    }

    @Override
    public int hashCode() {
        return studentList.hashCode();
    }

}
