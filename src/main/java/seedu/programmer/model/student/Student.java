package seedu.programmer.model.student;

import static seedu.programmer.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Represents a student in the ProgrammerError.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final StudentId studentId;
    private final ClassId classId;
    private final Grade grade;
    private List<LabResult> labResultList;

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, StudentId studentId, ClassId classId, Grade grade) {
        requireAllNonNull(name, studentId, classId, grade);
        this.name = name;
        this.studentId = studentId;
        this.classId = classId;
        this.grade = grade;
        this.labResultList = new ArrayList<>();
    }

    public Name getName() {
        return name;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public ClassId getClassId() {
        return classId;
    }

    public Grade getGrade() {
        return grade;
    }

    public List<LabResult> getLabResultList() {
        return labResultList;
    }

    /**
     * Add a lab result into the student's record
     * */
    public void addLabResult(LabResult result) {
        if (this.labResultList == null) {
            labResultList = new ArrayList<>();
        }
        this.labResultList.add(result);
    }

    public void setLabResultRecord(List<LabResult> labResultRecord) {
        this.labResultList = labResultRecord;
    }
    /**
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getStudentId().equals(getStudentId());
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getStudentId().equals(getStudentId())
                && otherStudent.getClassId().equals(getClassId())
                && otherStudent.getGrade().equals(getGrade())
                && otherStudent.getLabResultList().equals(getLabResultList());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, studentId, classId, grade);
    }

    @Override
    public String toString() {
        return getName()
                + "; Student Id: "
                + getStudentId()
                + "; Class Id: "
                + getClassId()
                + "; Grade: "
                + getGrade();
    }

}
