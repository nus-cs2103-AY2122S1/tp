package seedu.address.model.group;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.student.Student;

public class Members {

    public final ObservableList<Student> studentList = FXCollections.observableArrayList();

    /**
     * Constructs a {@code Members}.
     */
    public Members() { }

    /**
     * Constructs a existing {@code Members}.
     */
    public Members(List<Student> studentList) {
        this.studentList.addAll(studentList);
    }

    public void addMember(Student student) {
        this.studentList.add(student);
    }

    public void removeMember(Student student) {
        this.studentList.removeIf(s -> s.isSameStudent(student));
    }

    public void removeAllMembers() {
        this.studentList.clear();
    }

    /**
     * Updates a group member when edited
     * @param target group member to be edited
     * @param student edited group member
     */
    public void updateMember(Student target, Student student) {
        int index = studentList.indexOf(target);
        studentList.set(index, student);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Members // instanceof handles nulls
                && studentList.equals(((Members) other).studentList)); // state check
    }

    @Override
    public String toString() {
        if (studentList.isEmpty()) {
            return "No members";
        }
        StringBuilder sb = new StringBuilder();
        for (Student m : studentList) {
            sb.append(m.getName().fullName).append(", ");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return studentList.hashCode();
    }

}
