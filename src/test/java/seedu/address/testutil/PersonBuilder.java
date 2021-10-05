package seedu.address.testutil;

import seedu.address.model.person.ParentName;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentName;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_STUDENT_NAME = "Amy Bee";
    public static final String DEFAULT_STUDENT_PHONE = "96355255";
    public static final String DEFAULT_PARENT_NAME = "Mr Bee";
    public static final String DEFAULT_PARENT_PHONE = "85355255";

    private StudentName studentName;
    private Phone studentPhone;
    private ParentName parentName;
    private Phone parentPhone;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        studentName = new StudentName(DEFAULT_STUDENT_NAME);
        studentPhone = new Phone(DEFAULT_STUDENT_PHONE);
        parentName = new ParentName(DEFAULT_PARENT_NAME);
        parentPhone = new Phone(DEFAULT_PARENT_PHONE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        studentName = personToCopy.getStudentName();
        studentPhone = personToCopy.getStudentPhone();
        parentName = personToCopy.getParentName();
        parentPhone = personToCopy.getParentPhone();
    }

    /**
     * Sets the {@code StudentName} of the {@code Person} that we are building.
     */
    public PersonBuilder withStudentName(String name) {
        this.studentName = new StudentName(name);
        return this;
    }

    /**
     * Sets the {@code ParentName} of the {@code Person} that we are building.
     */
    public PersonBuilder withParentName(String name) {
        this.parentName = new ParentName(name);
        return this;
    }

    /**
     * Sets the {@code StudentPhone} of the {@code Person} that we are building.
     */
    public PersonBuilder withStudentPhone(String phone) {
        this.studentPhone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code ParentPhone} of the {@code Person} that we are building.
     */
    public PersonBuilder withParentPhone(String phone) {
        this.parentPhone = new Phone(phone);
        return this;
    }

    public Person build() {
        return new Person(studentName, studentPhone, parentName, parentPhone);
    }
}
