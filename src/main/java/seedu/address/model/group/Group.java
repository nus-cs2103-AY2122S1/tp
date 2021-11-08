package seedu.address.model.group;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.commons.RepoName;
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
    private final LinkYear year;
    private final RepoName repoName;

    /**
     * Every field must be present and not null.
     * Constructor for a new Group object
     */
    public Group(GroupName name, Members members, LinkYear year, RepoName repoName, Set<Tag> tags) {
        requireNonNull(name);
        this.name = name;
        this.members = Objects.requireNonNullElseGet(members, Members::new);
        this.year = Objects.requireNonNullElseGet(year, LinkYear::new);
        this.repoName = Objects.requireNonNullElseGet(repoName, RepoName::new);
        this.tags.addAll(tags);
    }

    /**
     * Constructor for a new Group object given only name and tags
     */
    public Group(GroupName name, Set<Tag> tags) {
        this(name, new Members(), new LinkYear(), new RepoName(), tags);
    }

    public GroupName getName() {
        return name;
    }

    /**
     * Returns a mutable member list
     */
    public Members getMembers() {
        return members;
    }

    public void updateMember(Student target, Student editedStudent) {
        members.updateMember(target, editedStudent);
    }

    public void addMember(Student target) {
        members.addMember(target);
    }

    /**
     * Removes a member in the group
     */
    public void removeMember(Student student) {
        members.removeMember(student);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Student> getMembersList() {
        return Collections.unmodifiableList(members.studentList);
    }

    public LinkYear getYear() {
        return year;
    }

    public RepoName getRepoName() {
        return repoName;
    }

    public void removeAllMembers() {
        members.removeAllMembers();
    }

    /**
     * Returns the formatted GitHub link
     */
    public String getGroupGithubLink() {
        if (!year.isNull() && !repoName.isNull()) {
            return String.format(new GroupGithub(year, repoName).toString(), getName());
        } else {
            return "-";
        }
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
                && otherGroup.getMembersList().equals(getMembersList())
                && otherGroup.getGroupGithubLink().equals(getGroupGithubLink())
                && otherGroup.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, members, tags, year, repoName);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Members: ")
                .append(getMembers())
                .append("; Github: ")
                .append(getGroupGithubLink());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }

    /**
     * Makes a shallow copy of a group.
     *
     * @return a cloned Group with the exact same data fields as the original.
     */
    public Group clone() {
        return new Group(name, new Members(getMembersList()), year, repoName, new HashSet<>(tags));
    }

    /**
     * Represents a Group's name in tApp.
     * Guarantees: immutable;
     */
    public static class GroupGithub {

        public static final String GITHUB_ADDRESS = "https://github.com/";
        public static final String MODULE_CODE = "CS2103";
        public final LinkYear year;
        public final RepoName repoName;

        public final String link;

        /**
         * Constructs a {@code GroupGithub}.
         *
         * @param year A valid academic year.
         * @param repoName A valid repo name.
         */
        public GroupGithub(LinkYear year, RepoName repoName) {
            requireAllNonNull(year, repoName);
            this.year = year;
            this.repoName = repoName;
            this.link = GITHUB_ADDRESS + year + "-" + MODULE_CODE + "-%1$s/" + repoName;
        }

        @Override
        public String toString() {
            return link;
        }

        @Override
        public boolean equals(Object other) {
            return other == this // short circuit if same object
                    || (other instanceof GroupGithub // instanceof handles nulls
                    && link.equals(((GroupGithub) other).link)); // state check
        }

        @Override
        public int hashCode() {
            return link.hashCode();
        }

    }
}
