package seedu.address.testutil;

import seedu.address.model.student.ClassId;
import seedu.address.model.student.Grade;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;

/**
 * A utility class to help with building student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_STUDENT_ID = "A0213256H";
    public static final String DEFAULT_CLASS_ID = "B01";
    public static final String DEFAULT_GRADE = "A";

    private Name name;
    private StudentId studentId;
    private ClassId classId;
    private Grade grade;

    /**
     * Creates a {@code studentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        studentId = new StudentId(DEFAULT_STUDENT_ID);
        classId = new ClassId(DEFAULT_CLASS_ID);
        grade = new Grade(DEFAULT_GRADE);
    }

    /**
     * Initializes the studentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        studentId = studentToCopy.getStudentId();
        classId = studentToCopy.getClassId();
        grade = studentToCopy.getGrade();
    }

    /**
     * Sets the {@code Name} of the {@code student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }


    /**
     * Sets the {@code Address} of the {@code student} that we are building.
     */
    public StudentBuilder withStudentId(String studentId) {
        this.studentId = new StudentId(studentId);
        return this;
    }

    /**
     * Sets the {@code ClassId} of the {@code student} that we are building.
     */
    public StudentBuilder withClassId(String classId) {
        this.classId = new ClassId(classId);
        return this;
    }

    /**
     * Sets the {@code Grade} of the {@code student} that we are building.
     */
    public StudentBuilder withGrade(String grade) {
        this.grade = new Grade(grade);
        return this;
    }

    public Student build() {
        return new Student(name, studentId, classId, grade);
    }

}
