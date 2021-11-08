package seedu.programmer.testutil;


import static seedu.programmer.testutil.TypicalLabs.getTypicalLabList;

import javafx.collections.ObservableList;
import seedu.programmer.model.student.ClassId;
import seedu.programmer.model.student.Email;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.Name;
import seedu.programmer.model.student.Student;
import seedu.programmer.model.student.StudentId;



/**
 * A utility class to help with building student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_STUDENT_ID = "A0213256H";
    public static final String DEFAULT_CLASS_ID = "B01";
    public static final String DEFAULT_EMAIL = "e0518541@u.nus.edu";

    private Name name;
    private StudentId studentId;
    private ClassId classId;
    private Email email;
    private ObservableList<Lab> labResultList;

    /**
     * Creates a {@code studentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        studentId = new StudentId(DEFAULT_STUDENT_ID);
        classId = new ClassId(DEFAULT_CLASS_ID);
        email = new Email(DEFAULT_EMAIL);
        labResultList = getTypicalLabList();
    }

    /**
     * Initializes the studentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        studentId = studentToCopy.getStudentId();
        classId = studentToCopy.getClassId();
        email = studentToCopy.getEmail();
        labResultList = studentToCopy.getLabList();
    }

    /**
     * Sets the {@code Name} of the {@code student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }


    /**
     * Sets the {@code programmer} of the {@code student} that we are building.
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
     * Sets the {@code Email} of the {@code student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code LabList} of the {@code student} that we are building.
     * @param labList
     * @return
     */
    public StudentBuilder withLabList(ObservableList<Lab> labList) {
        this.labResultList = labList;
        return this;
    }

    public Student build() {
        return new Student(name, studentId, classId, email, labResultList);
    }

    public Student buildNoLab() {
        return new Student(name, studentId, classId, email);
    }

}
