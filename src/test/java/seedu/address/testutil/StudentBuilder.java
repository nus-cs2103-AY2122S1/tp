package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Involvement;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.student.Address;
import seedu.address.model.person.student.FormClass;
import seedu.address.model.person.student.MedicalHistory;
import seedu.address.model.person.student.Student;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_EMERGENCY_CONTACT = "91349081";
    public static final String DEFAULT_INVOLVEMENT = "Math class";
    public static final String DEFAULT_FORM_CLASS = "4E1";
    public static final String DEFAULT_GENDER = "M";
    public static final String DEFAULT_MEDICAL_HISTORY = "";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Involvement involvement;
    private Set<Tag> tags;
    private Phone emergencyContact;
    private FormClass formClass;
    private Gender gender;
    private MedicalHistory medicalHistory;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        involvement = new Involvement(DEFAULT_INVOLVEMENT);
        tags = new HashSet<>();
        emergencyContact = new Phone(DEFAULT_EMERGENCY_CONTACT);
        formClass = new FormClass(DEFAULT_FORM_CLASS);
        gender = new Gender(DEFAULT_GENDER);
        medicalHistory = new MedicalHistory(DEFAULT_MEDICAL_HISTORY);
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        address = studentToCopy.getAddress();
        involvement = studentToCopy.getInvolvement();
        tags = new HashSet<>(studentToCopy.getTags());
        emergencyContact = studentToCopy.getEmergencyContact();
        formClass = studentToCopy.getFormClass();
        gender = studentToCopy.getGender();
        medicalHistory = studentToCopy.getMedicalHistory();
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
     * Sets the {@code Emergency Contact} of the {@code Student} that we are building.
     */

    public StudentBuilder withEmergencyContact(String phone) {
        this.emergencyContact = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Involvement} of the {@code Student} that we are building.
     */

    public StudentBuilder withInvolvement(String involvement) {
        this.involvement = new Involvement(involvement);
        return this;
    }

    /**
     * Sets the {@code Form Class} of the {@code Student} that we are building.
     */
    public StudentBuilder withFormClass(String formClass) {
        this.formClass = new FormClass(formClass);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Student} that we are building.
     */
    public StudentBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code MedicalHistory} of the {@code Student} that we are building.
     */
    public StudentBuilder withMedicalHistory(String medicalHistory) {
        this.medicalHistory = new MedicalHistory(medicalHistory);
        return this;
    }

    /**
     * Builds the {@code Student}.
     * @return the student.
     */
    public Student build() {
        return new Student(name, phone, email, gender, involvement, address,
            emergencyContact, formClass, tags, medicalHistory);
    }

    /**
     * Builds the {@code Student} for edit tests.
     * @return the student.
     */
    public Student buildForEdit() {
        return new Student(name, phone, email, gender, involvement, address,
            emergencyContact, formClass, tags, new MedicalHistory("ADHD"));
    }

}
