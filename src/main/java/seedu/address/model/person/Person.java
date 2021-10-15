package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.skill.Framework;
import seedu.address.model.skill.Language;
import seedu.address.model.skill.Skill;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in ComputingConnection.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Personal Data fields
    private final Name name;
    private final Email email;

    // University Data fields
    private final Faculty faculty;
    private final Major major;

    // Skill Data fields
    private final Set<Skill> skills = new HashSet<>();
    private final Set<Language> languages = new HashSet<>();
    private final Set<Framework> frameworks = new HashSet<>();

    // Misc Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Email email, Faculty faculty, Major major, Set<Skill> skills,
                  Set<Language> languages, Set<Framework> frameworks, Set<Tag> tags) {
        requireAllNonNull(name, email, faculty, major, skills, languages, frameworks, tags);
        this.name = name;
        this.email = email;
        this.faculty = faculty;
        this.major = major;
        this.skills.addAll(skills);
        this.languages.addAll(languages);
        this.frameworks.addAll(frameworks);
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public Major getMajor() {
        return major;
    }

    /**
     * Returns an immutable skills set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Skill> getSkills() {
        return Collections.unmodifiableSet(skills);
    }

    /**
     * Returns an immutable languages set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Language> getLanguages() {
        return Collections.unmodifiableSet(languages);
    }

    /**
     * Returns an immutable frameworks set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Framework> getFrameworks() {
        return Collections.unmodifiableSet(frameworks);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
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
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getFaculty().equals(getFaculty())
                && otherPerson.getMajor().equals(getMajor())
                && otherPerson.getSkills().equals(getSkills())
                && otherPerson.getLanguages().equals(getLanguages())
                && otherPerson.getFrameworks().equals(getFrameworks())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, email, faculty, major, skills, languages, frameworks, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Email: ")
                .append(getEmail())
                .append("; Faculty: ")
                .append(getFaculty())
                .append("; Major: ")
                .append(getMajor());

        Set<Skill> skills = getSkills();
        if (!skills.isEmpty()) {
            builder.append("; Skills: ");
            skills.forEach(builder::append);
        }

        Set<Language> languages = getLanguages();
        if (!languages.isEmpty()) {
            builder.append("; Languages: ");
            languages.forEach(builder::append);
        }

        Set<Framework> frameworks = getFrameworks();
        if (!frameworks.isEmpty()) {
            builder.append("; Frameworks: ");
            frameworks.forEach(builder::append);
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
