package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Email;
import seedu.address.model.person.EmploymentType;
import seedu.address.model.person.ExpectedSalary;
import seedu.address.model.person.Experience;
import seedu.address.model.person.LevelOfEducation;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ROLE = "Software Engineer";
    public static final String DEFAULT_EMPLOYMENT_TYPE = "Full time";
    public static final String DEFAULT_EXPECTED_SALARY = "3200";
    public static final String DEFAULT_LEVEL_OF_EDUCATION = "PhD";
    public static final Integer DEFAULT_EXPERIENCE = 0;

    private Name name;
    private Phone phone;
    private Email email;
    private Role role;
    private EmploymentType employmentType;
    private ExpectedSalary expectedSalary;
    private LevelOfEducation levelOfEducation;
    private Experience experience;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        role = new Role(DEFAULT_ROLE);
        employmentType = new EmploymentType(DEFAULT_EMPLOYMENT_TYPE);
        expectedSalary = new ExpectedSalary(DEFAULT_EXPECTED_SALARY);
        levelOfEducation = new LevelOfEducation(DEFAULT_LEVEL_OF_EDUCATION);
        experience = new Experience(DEFAULT_EXPERIENCE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        role = personToCopy.getRole();
        employmentType = personToCopy.getEmploymentType();
        expectedSalary = personToCopy.getExpectedSalary();
        levelOfEducation = personToCopy.getLevelOfEducation();
        experience = personToCopy.getExperience();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code Person} that we are building.
     */
    public PersonBuilder withRole(String role) {
        this.role = new Role(role);
        return this;
    }

    /**
     * Sets the {@code EmploymentType} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmploymentType(String employmentType) {
        this.employmentType = new EmploymentType(employmentType);
        return this;
    }

    /**
     * Sets the {@code ExpectedSalary} of the {@code Person} that we are building.
     */
    public PersonBuilder withExpectedSalary(String expectedSalary) {
        this.expectedSalary = new ExpectedSalary(expectedSalary);
        return this;
    }

    /**
     * Sets the {@code Level of Education} of the {@code Person} that we are building.
     */
    public PersonBuilder withLevelOfEducation(String levelOfEducation) {
        this.levelOfEducation = new LevelOfEducation(levelOfEducation);
        return this;
    }

    /**
     * Sets the {@code Experience} of the {@code Person} that we are building.
     */
    public PersonBuilder withExperience(Integer experience) {
        this.experience = new Experience(experience);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Builds a {@code Person} with the given information.
     */
    public Person build() {
        return new Person(name, phone, email, role, employmentType,
                expectedSalary, levelOfEducation, experience, tags);
    }

}
