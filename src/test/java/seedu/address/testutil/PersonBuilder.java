package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_STUDENTID = "A0213256H";
    public static final String DEFAULT_CLASSID = "B01";
    public static final String DEFAULT_GRADE = "A+";

    private Name name;
    private StudentId studentId;
    private ClassId classId;
    private Grade grade;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        studentId = new StudentId(DEFAULT_STUDENTID);
        classId = new ClassId(DEFAULT_CLASSID);
        grade = new Grade(DEFAULT_GRADE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        studentId = personToCopy.getStudentId();
        classId = personToCopy.getClassId();
        grade = personToCopy.getGrade();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }


    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withStudentId(String studentId) {
        this.studentId = new StudentId(studentId);
        return this;
    }

    /**
     * Sets the {@code ClassId} of the {@code Person} that we are building.
     */
    public PersonBuilder withClassId(String classId) {
        this.classId = new ClassId(classId);
        return this;
    }

    /**
     * Sets the {@code Grade} of the {@code Person} that we are building.
     */
    public PersonBuilder withGrade(String grade) {
        this.grade = new Grade(grade);
        return this;
    }

    public Person build() {
        return new Person(name, studentId, classId, grade);
    }

}
