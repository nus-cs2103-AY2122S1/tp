package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.student.Address;
import seedu.address.model.student.ClassCode;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentMark;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_CLASSCODE = "G06";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private ClassCode classCode;
    private Set<Tag> tags;
    private List<StudentMark> marks;
    private Set<TutorialGroup> tutorialGroups;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        classCode = new ClassCode(DEFAULT_CLASSCODE);
        tags = new HashSet<>();
        marks = new ArrayList<>();
        tutorialGroups = new HashSet<>();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        address = studentToCopy.getAddress();
        classCode = studentToCopy.getClassCode();
        tags = new HashSet<>(studentToCopy.getTags());
        marks = new ArrayList<>(studentToCopy.getMarks());
        tutorialGroups = new HashSet<>(studentToCopy.getTutorialGroups());;
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code tutorialGroups} into a {@code Set<TutorialGroup>} and
     * set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTutorialGroups(TutorialGroup... tutorialGroups) {
        this.tutorialGroups = SampleDataUtil.getTutorialGroupSet(tutorialGroups);
        return this;
    }

    /**
     * Parses the {@code marks} into a {@code List<StudentMark>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withMarks(String ... marks) {
        this.marks = SampleDataUtil.getMarkList(marks);
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
        this.phone = new Phone(phone);
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
     * Sets the {@code ClassCode} of the {@code Student} that we are building.
     */
    public StudentBuilder withClassCode (String classCode) {
        this.classCode = new ClassCode(classCode);
        return this;
    }



    public Student build() {
        return new Student(name, phone, email, address, classCode, tags, marks, tutorialGroups);
    }

}
