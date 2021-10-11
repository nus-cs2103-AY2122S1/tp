package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final GitHubId gitHubId;
    private final NusNetworkId nusNetworkId;
    private final Type type;
    private final StudentId studentId;
    private final Email email;
    private final TutorialId tutorialId;

    // Data fields
    private final Address address;
    private final Phone phone;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags, GitHubId gitHubId,
                  NusNetworkId nusNetworkId, Type type, StudentId studentId, TutorialId tutorialId) {
        requireAllNonNull(name, phone, email, address, tags, gitHubId, nusNetworkId, type, studentId);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.gitHubId = gitHubId;
        this.nusNetworkId = nusNetworkId;
        this.type = type;
        this.studentId = studentId;
        this.tutorialId = tutorialId;
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

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public GitHubId getGitHubId() {
        return gitHubId;
    }

    public NusNetworkId getNusNetworkId() {
        return nusNetworkId;
    }

    public Type getType() {
        return type;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public TutorialId getTutorialId() {
        return tutorialId;
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
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getGitHubId().equals(getGitHubId())
                && otherPerson.getNusNetworkId().equals(getNusNetworkId())
                && otherPerson.getType().equals(getType())
                && otherPerson.getStudentId().equals(getStudentId())
                && otherPerson.getTutorialId().equals(getTutorialId());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, gitHubId, nusNetworkId, type, studentId, tutorialId);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; GitHub_ID: ")
                .append(getGitHubId())
                .append("; NUS_Network:ID ")
                .append(getNusNetworkId())
                .append("; Type: ")
                .append(getType())
                .append("; Student_ID: ")
                .append(getStudentId())
                .append("; Tutorial_ID ")
                .append(getTutorialId());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }

}
