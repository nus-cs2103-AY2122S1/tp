package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Compatibility;
import seedu.address.model.person.Email;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.remark.Remark;
import seedu.address.model.skill.Framework;
import seedu.address.model.skill.Language;
import seedu.address.model.skill.Skill;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_FACULTY = "computing";
    public static final String DEFAULT_MAJOR = "computer science";
    public static final Integer DEFAULT_COMPATIBILITY = 98;

    private Name name;
    private Email email;
    private Faculty faculty;
    private Major major;
    private Compatibility compatibility;
    private Set<Skill> skills;
    private Set<Language> languages;
    private Set<Framework> frameworks;
    private Set<Tag> tags;
    private Set<Remark> remarks;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        email = new Email(DEFAULT_EMAIL);
        faculty = new Faculty(DEFAULT_FACULTY);
        major = new Major(DEFAULT_MAJOR);
        compatibility = new Compatibility(DEFAULT_COMPATIBILITY);
        skills = new HashSet<>();
        languages = new HashSet<>();
        frameworks = new HashSet<>();
        tags = new HashSet<>();
        remarks = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        email = personToCopy.getEmail();
        faculty = personToCopy.getFaculty();
        major = personToCopy.getMajor();
        compatibility = personToCopy.getCompatibility();
        skills = new HashSet<>(personToCopy.getSkills());
        languages = new HashSet<>(personToCopy.getLanguages());
        frameworks = new HashSet<>(personToCopy.getFrameworks());
        tags = new HashSet<>(personToCopy.getTags());
        remarks = new HashSet<>(personToCopy.getRemarks());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
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
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withRemarks(String ... remarks) {
        this.remarks = SampleDataUtil.getRemarkSet(remarks);
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
     * Sets the {@code Faculty} of the {@code Person} that we are building.
     */
    public PersonBuilder withFaculty(String faculty) {
        this.faculty = new Faculty(faculty);
        return this;
    }

    /**
     * Sets the {@code Major} of the {@code Person} that we are building.
     */
    public PersonBuilder withMajor(String major) {
        this.major = new Major(major);
        return this;
    }

    /**
     * Sets the {@code Major} of the {@code Person} that we are building.
     */
    public PersonBuilder withCompatibility(Integer compatibility) {
        this.compatibility = new Compatibility(compatibility);
        return this;
    }

    /**
     * Parses the {@code skills} into a {@code Set<Skill>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withSkills(String ... skills) {
        this.skills = SampleDataUtil.getSkillSet(skills);
        return this;
    }

    /**
     * Parses the {@code languages} into a {@code Set<Language>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withLanguages(String ... languages) {
        this.languages = SampleDataUtil.getLanguageSet(languages);
        return this;
    }

    /**
     * Parses the {@code frameworks} into a {@code Set<Framework>}
     * and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withFrameworks(String ... frameworks) {
        this.frameworks = SampleDataUtil.getFrameworkSet(frameworks);
        return this;
    }

    public Person build() {
        return new Person(name, email, faculty, major, compatibility, skills, languages, frameworks, tags, remarks);
    }

}
