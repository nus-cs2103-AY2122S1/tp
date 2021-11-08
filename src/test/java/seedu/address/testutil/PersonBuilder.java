package seedu.address.testutil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.done.Done;
import seedu.address.model.interview.Interview;
import seedu.address.model.notes.Notes;
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
    public static final String DEFAULT_EXPERIENCE = "0";
    public static final String DEFAULT_INTERVIEW = "2021-10-21, 19:00";
    public static final String DEFAULT_NOTES = "This applicant is a very good candidate for the job!";

    private Name name;
    private Phone phone;
    private Email email;
    private Role role;
    private EmploymentType employmentType;
    private ExpectedSalary expectedSalary;
    private LevelOfEducation levelOfEducation;
    private Experience experience;
    private Set<Tag> tags;
    private Optional<Interview> interview;
    private Optional<Notes> notes;
    private Done done;

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
        interview = Optional.ofNullable(new Interview(DEFAULT_INTERVIEW));
        notes = Optional.ofNullable(new Notes(DEFAULT_NOTES));
        done = new Done();
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
        interview = personToCopy.getInterview();
        notes = personToCopy.getNotes();
        done = personToCopy.getDone();
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
    public PersonBuilder withExperience(String experience) {
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
     * Sets the {@code Interview} of the {@code Person} that we are building.
     */
    public PersonBuilder withInterview(String interview) {
        this.interview = Optional.ofNullable(new Interview(interview));
        return this;
    }

    /**
     * Sets the {@code Notes} of the {@code Person} that we are building.
     */
    public PersonBuilder withNotes(String notes) {
        this.notes = Optional.ofNullable(new Notes(notes));
        return this;
    }

    /**
     * Sets the {@code Done} of the {@code Person} that we are building.
     */
    public PersonBuilder withDone(String done) {
        this.done = new Done(done);
        return this;
    }

    /**
     * Builds a {@code Person} with the given information.
     */
    public Person build() {
        return new Person(name, phone, email, role, employmentType,
                expectedSalary, levelOfEducation, experience, tags, interview, notes, done);
    }

}
