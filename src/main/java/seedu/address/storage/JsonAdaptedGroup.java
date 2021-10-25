package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.RepoName;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.group.LinkYear;
import seedu.address.model.group.Members;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Group}.
 */
class JsonAdaptedGroup {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Group's %s field is missing!";

    private final String name;
    private final String year;
    private final String repoName;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final ArrayList<JsonAdaptedStudent> members = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedGroup} with the given group details.
     */
    @JsonCreator
    public JsonAdaptedGroup(@JsonProperty("name") String name,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                              @JsonProperty("members") ArrayList<JsonAdaptedStudent> members,
                              @JsonProperty("year") String year,
                              @JsonProperty("repo") String repoName) {
        this.name = name;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.members.addAll(members);
        this.year = year;
        this.repoName = repoName;
    }

    /**
     * Converts a given {@code Group} into this class for Jackson use.
     */
    public JsonAdaptedGroup(Group source) {
        name = source.getName().name;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        members.addAll(source.getMembersList().stream()
                .map(JsonAdaptedStudent::new)
                .collect(Collectors.toList()));
        year = source.getYear().year;
        repoName = source.getRepoName().repoName;
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Group toModelType() throws IllegalValueException {
        final List<Tag> groupTags = new ArrayList<>();
        final ArrayList<Student> groupMembers = new ArrayList<>();
        final LinkYear modelYear;
        final RepoName modelRepoName;

        for (JsonAdaptedTag tag : tagged) {
            groupTags.add(tag.toModelType());
        }

        for (JsonAdaptedStudent student : members) {
            groupMembers.add(student.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GroupName.class.getSimpleName()));
        }
        if (!GroupName.isValidName(name)) {
            throw new IllegalValueException(GroupName.MESSAGE_CONSTRAINTS);
        }
        final GroupName modelName = new GroupName(name);

        if (year == null) {
            modelYear = new LinkYear();
        } else {
            if (!LinkYear.isValidYear(year)) {
                throw new IllegalValueException(LinkYear.MESSAGE_CONSTRAINTS);
            }
            modelYear = new LinkYear(year);
        }
        if (repoName == null) {
            modelRepoName = new RepoName();
        } else {
            if (!RepoName.isValidRepoName(repoName)) {
                throw new IllegalValueException(RepoName.MESSAGE_CONSTRAINTS);
            }
            modelRepoName = new RepoName(repoName);
        }

        final Set<Tag> modelTags = new HashSet<>(groupTags);
        final Members modelMembers = new Members(groupMembers);

        return new Group(modelName, modelMembers, modelYear, modelRepoName, modelTags);
    }
}
