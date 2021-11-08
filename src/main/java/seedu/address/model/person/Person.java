package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.done.Done;
import seedu.address.model.interview.Interview;
import seedu.address.model.notes.Notes;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Category fields
    private final Role role;
    private final EmploymentType employmentType;
    private final ExpectedSalary expectedSalary;
    private final LevelOfEducation levelOfEducation;
    private final Experience experience;
    private final Optional<Interview> interview;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();
    private final Optional<Notes> notes;

    // Status fields
    private final Done done;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email,
                  Role role, EmploymentType employmentType, ExpectedSalary expectedSalary,
                  LevelOfEducation levelOfEducation, Experience experience, Set<Tag> tags,
                  Optional<Interview> interview, Optional<Notes> notes) {
        requireAllNonNull(name, phone, email, role, expectedSalary, levelOfEducation,
                experience, tags, interview, notes);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.employmentType = employmentType;
        this.expectedSalary = expectedSalary;
        this.levelOfEducation = levelOfEducation;
        this.experience = experience;
        this.tags.addAll(tags);
        this.interview = interview;
        this.notes = notes;
        this.done = new Done();
    }

    /**
     * Secondary constructor for Person.
     */
    public Person(Name name, Phone phone, Email email,
                  Role role, EmploymentType employmentType, ExpectedSalary expectedSalary,
                  LevelOfEducation levelOfEducation, Experience experience, Set<Tag> tags,
                  Optional<Interview> interview, Optional<Notes> notes, Done done) {
        requireAllNonNull(name, phone, email, role, expectedSalary, levelOfEducation, experience,
                tags, interview, notes, done);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.employmentType = employmentType;
        this.expectedSalary = expectedSalary;
        this.levelOfEducation = levelOfEducation;
        this.experience = experience;
        this.tags.addAll(tags);
        this.interview = interview;
        this.notes = notes;
        this.done = done;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public ExpectedSalary getExpectedSalary() {
        return expectedSalary;
    }

    public LevelOfEducation getLevelOfEducation() {
        return levelOfEducation;
    }

    public Experience getExperience() {
        return experience;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * If interview is present, return interview; Else, return an instance of empty interview.
     *
     * @return Person's interview timing or an empty interview.
     */
    public Optional<Interview> getInterview() {
        return interview;
    }

    public Optional<Notes> getNotes() {
        return notes;
    }

    public Done getDone() {
        return done;
    }

    /**
     * Returns true if both persons have the same email or contact number.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && (otherPerson.getEmail().equals(getEmail())
                        || otherPerson.getPhone().equals(getPhone()));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getRole().equals(getRole())
                && otherPerson.getEmploymentType().equals(getEmploymentType())
                && otherPerson.getExpectedSalary().equals(getExpectedSalary())
                && otherPerson.getLevelOfEducation().equals(getLevelOfEducation())
                && otherPerson.getExperience().equals(getExperience())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getInterview().equals(getInterview())
                && otherPerson.getNotes().equals(getNotes())
                && otherPerson.getDone().equals(getDone());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, role, employmentType,
                expectedSalary, levelOfEducation, experience, tags, interview, notes, done);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Applied Role: ")
                .append(getRole())
                .append("; Employment Type: ")
                .append(getEmploymentType())
                .append("; Expected Salary: ")
                .append(getExpectedSalary())
                .append("; Level of Education: ")
                .append(getLevelOfEducation())
                .append("; Years of Experience: ")
                .append(getExperience());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        builder.append("; Interview: ")
                .append(getInterview())
                .append("; Notes: ")
                .append(getNotes())
                .append("; Status: ")
                .append(getDone());

        return builder.append("\n").toString();
    }
}
