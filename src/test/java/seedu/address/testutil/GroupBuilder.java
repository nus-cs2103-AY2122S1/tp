package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.commons.RepoName;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.group.LinkYear;
import seedu.address.model.group.Members;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class GroupBuilder {

    public static final String DEFAULT_GROUP_NAME = "w14-4";

    private GroupName groupName;
    private Set<Tag> tags;
    private Members members;
    private LinkYear year;
    private RepoName repoName;

    /**
     * Creates a {@code GroupBuild} with the default details.
     */
    public GroupBuilder() {
        this.groupName = new GroupName(DEFAULT_GROUP_NAME);
        this.tags = new HashSet<>();
        this.members = new Members();
        this.year = new LinkYear();
        this.repoName = new RepoName();
    }

    /**
     * Initializes the TaskGroupBuilder with the data of {@code groupToCopy}.
     */
    public GroupBuilder(Group groupToCopy) {
        this.groupName = groupToCopy.getName();
        this.tags = new HashSet<>(groupToCopy.getTags());
        this.repoName = groupToCopy.getRepoName();
        this.members = groupToCopy.getMembers();
        this.year = groupToCopy.getYear();
    }

    /**
     * Sets the {@code Name} of the {@code Group} that we are building.
     */
    public GroupBuilder withName(String name) {
        this.groupName = new GroupName(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Group} that we are building.
     */
    public GroupBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code members} into a {@code Set<Members>} and set it to the {@code Group} that we are building.
     */
    public GroupBuilder withMembers(Student... students) {
        ArrayList<Student> studentList = new ArrayList<>();
        for (Student student : students) {
            studentList.add(new StudentBuilder(student).build());
        }
        members = new Members(studentList);
        return this;
    }

    /**
     * Sets the {@code LinkYear} of the {@code Group} that we are building.
     */
    public GroupBuilder withYear(String year) {
        this.year = new LinkYear(year);
        return this;
    }

    /**
     * Sets the {@code RepoName} of the {@code Group} that we are building.
     */
    public GroupBuilder withRepo(String name) {
        repoName = new RepoName(name);
        return this;
    }

    public Group build() {
        return new Group(groupName, members, year, repoName, tags);
    }

}
