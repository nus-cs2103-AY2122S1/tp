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

    // Data fields
    private final Phone phone;
    private final Email email;
    private final Nationality nationality;
    private final TutorialGroup tutorialGroup;
    private final Gender gender;
    private final Remark remark;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<SocialHandle> socialHandles = new HashSet<>();

    /**
     * Constructs a {@code Person}
     */
    public Person(Name name, Phone phone, Email email, Nationality nationality,
                  TutorialGroup tutorialGroup, Gender gender,
                  Remark remark, Set<Tag> tags, Set<SocialHandle> socialHandles) {
        requireAllNonNull(name, phone, email, nationality, tutorialGroup, gender, remark, tags, socialHandles);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.nationality = nationality;
        this.tutorialGroup = tutorialGroup;
        this.gender = gender;
        this.remark = remark;
        this.tags.addAll(tags);
        this.socialHandles.addAll(socialHandles);
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

    public Nationality getNationality() {
        return nationality;
    }

    public TutorialGroup getTutorialGroup() {
        return tutorialGroup;
    }

    public Gender getGender() {
        return gender;
    }

    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Tag getFirstTag() {
        if (!tags.isEmpty()) {
            return tags.iterator().next();
        }
        return null;
    }

    public Set<SocialHandle> getSocialHandles() {
        return Collections.unmodifiableSet(socialHandles);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return equals(otherPerson);
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
                && otherPerson.getNationality().equals(getNationality())
                && otherPerson.getTutorialGroup().equals(getTutorialGroup())
                && otherPerson.getGender().equals(getGender())
                && otherPerson.getRemark().equals(getRemark())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getSocialHandles().equals(getSocialHandles());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, nationality, tutorialGroup,
                gender, remark, tags, socialHandles);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ").append(getName());
        if (!getGender().toString().isEmpty()) {
            builder.append("; Gender: ").append(getGender());
        }
        if (!getPhone().toString().isEmpty()) {
            builder.append("; Phone: ").append(getPhone());
        }
        if (!getEmail().toString().isEmpty()) {
            builder.append("; Email: ").append(getEmail());
        }
        if (!getNationality().toString().isEmpty()) {
            builder.append("; Nationality: ").append(getNationality());
        }
        if (!getTutorialGroup().toString().isEmpty()) {
            builder.append("; Tutorial Group: ").append(getTutorialGroup());
        }
        if (!getRemark().toString().isEmpty()) {
            builder.append("; Remark: ").append(getRemark());
        }

        Set<SocialHandle> socialHandles = getSocialHandles();
        if (!socialHandles.isEmpty()) {
            builder.append("; Social Handles: ");
            socialHandles.forEach(builder::append);
        }
        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }
}
