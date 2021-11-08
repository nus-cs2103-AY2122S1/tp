package seedu.tuitione.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.tuitione.model.remark.Remark;
import seedu.tuitione.model.student.Address;
import seedu.tuitione.model.student.Email;
import seedu.tuitione.model.student.Grade;
import seedu.tuitione.model.student.Name;
import seedu.tuitione.model.student.ParentContact;
import seedu.tuitione.model.student.Student;
import seedu.tuitione.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_GRADE = "S1";

    private Name name;
    private ParentContact parentContact;
    private Email email;
    private Address address;
    private Grade grade;
    private Set<Remark> remarks;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        parentContact = new ParentContact(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        grade = new Grade(DEFAULT_GRADE);
        remarks = new HashSet<>();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        parentContact = studentToCopy.getParentContact();
        email = studentToCopy.getEmail();
        address = studentToCopy.getAddress();
        grade = studentToCopy.getGrade();
        remarks = new HashSet<>(studentToCopy.getRemarks());
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code remarks} into a {@code Set<Remark>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withRemarks(String ... remarks) {
        this.remarks = SampleDataUtil.getRemarkSet(remarks);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Student} that we are building.
     */
    public StudentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.parentContact = new ParentContact(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Grade} of the {@code Student} that we are building.
     */
    public StudentBuilder withGrade(String grade) {
        this.grade = new Grade(grade);
        return this;
    }

    public Student build() {
        return new Student(name, parentContact, email, address, grade, remarks);
    }

}
