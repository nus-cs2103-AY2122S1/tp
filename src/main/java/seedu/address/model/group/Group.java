package seedu.address.model.group;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

/**
 * Represents a Group in tApp.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Group {

    // Identity fields
    private final GroupName name;

    // Data fields
    private final Members members;
    private final Set<Tag> tags = new HashSet<>();
    private final GroupGithub link;

    /**
     * Every field must be present and not null.
     * Constructor for a new Group object
     */
    public Group(GroupName name, Members members, GroupGithub link, Set<Tag> tags) {
        requireAllNonNull(name, members, link);
        this.name = name;
        this.members = members;
        this.link = link;
        this.tags.addAll(tags);
    }

    public Group(GroupName name, Set<Tag> tags) {
        requireAllNonNull(name);
        this.name = name;
        this.members = new Members();
        this.link = new GroupGithub();
        this.tags.addAll(tags);
    }

    public GroupName getName() {
        return name;
    }

    /**
     * Returns a mutable student set
     */
    public Members getMembers() {
        return members;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Student> getMembersSet() {
        return Collections.unmodifiableSet(members.studentList);
    }

    /**
     * Returns the Github link
     */
    public GroupGithub getGroupGithub() {
        return link;
    }

    /**
     * Returns the formatted Github link
     */
    public String getGroupGithubLink() {
        return String.format(link.toString(), getName());
    }


    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both groups have the same name.
     * This defines a weaker notion of equality between two groups.
     */
    public boolean isSameGroup(Group otherGroup) {
        if (otherGroup == this) {
            return true;
        }

        return otherGroup != null
                && otherGroup.getName().equals(getName());
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Group)) {
            return false;
        }

        Group otherGroup = (Group) other;
        return otherGroup.getName().equals(getName())
                && otherGroup.getMembers().equals(getMembers())
                && otherGroup.getGroupGithub().equals(getGroupGithub())
                && otherGroup.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, members, tags, link);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Members: ")
                .append(getMembers())
                .append("; Github: ")
                .append(getGroupGithub());;

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }

}