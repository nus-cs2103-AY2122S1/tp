package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.id.UniqueId;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.NoOverlapLessonList;

/**
 * Jackson-friendly version of {@link Group}.
 */
public class JsonAdaptedGroup {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Group's %s field is missing!";

    private final String uniqueId;
    private final String groupName;
    private final List<JsonAdaptedUniqueId> assignedPersonIds = new ArrayList<>();
    private final List<JsonAdaptedUniqueId> assignedTaskIds = new ArrayList<>();
    private final List<JsonAdaptedLesson> lessonsList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedGroup} with the given group details.
     */
    @JsonCreator
    public JsonAdaptedGroup(@JsonProperty("uniqueId") String uniqueId, @JsonProperty("name") String groupName,
                            @JsonProperty("assignedPersonIds") List<JsonAdaptedUniqueId> assignedPersonIds,
                             @JsonProperty("assignedTaskIds") List<JsonAdaptedUniqueId> assignedTaskIds,
                             @JsonProperty("lessonsList") List<JsonAdaptedLesson> lessonsList) {
        this.uniqueId = uniqueId;
        this.groupName = groupName;
        if (assignedPersonIds != null) {
            this.assignedPersonIds.addAll(assignedPersonIds);
        }
        if (assignedTaskIds != null) {
            this.assignedTaskIds.addAll(assignedTaskIds);
        }
        if (lessonsList != null) {
            this.lessonsList.addAll(lessonsList);
        }
    }

    /**
     * Converts a given {@code Group} into this class for Jackson use.
     */
    public JsonAdaptedGroup(Group source) {
        uniqueId = source.getId().getUuid().toString();
        groupName = source.getName().name;
        assignedPersonIds.addAll(source.getAssignedPersonIds().stream()
                .map(JsonAdaptedUniqueId::new)
                .collect(Collectors.toList()));
        assignedTaskIds.addAll(source.getAssignedTaskIds().stream()
                .map(JsonAdaptedUniqueId::new)
                .collect(Collectors.toList()));
        lessonsList.addAll(source.getLessons().stream()
                .map(JsonAdaptedLesson::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted group object into the model's {@code Group} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted group.
     */
    public Group toModelType() throws IllegalValueException {
        final List<UniqueId> groupAssignedPersonIds = new ArrayList<>();
        for (JsonAdaptedUniqueId id : assignedPersonIds) {
            groupAssignedPersonIds.add(id.toModelType());
        }

        final List<UniqueId> groupAssignedTaskIds = new ArrayList<>();
        for (JsonAdaptedUniqueId id : assignedTaskIds) {
            groupAssignedTaskIds.add(id.toModelType());
        }

        if (groupName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GroupName.class.getSimpleName()));
        }
        if (!GroupName.isValidName(groupName)) {
            throw new IllegalValueException(GroupName.MESSAGE_CONSTRAINTS);
        }
        final GroupName modelName = new GroupName(groupName);

        final Set<UniqueId> modelAssignedPersonIds = new HashSet<>(groupAssignedPersonIds);

        final Set<UniqueId> modelAssignedTaskIds = new HashSet<>(groupAssignedTaskIds);

        final List<Lesson> modelLessonsList = new ArrayList<>();
        for (JsonAdaptedLesson l : lessonsList) {
            modelLessonsList.add(l.toModelType());
        }
        if (NoOverlapLessonList.doAnyLessonsOverlap(modelLessonsList)) {
            throw new IllegalValueException(NoOverlapLessonList.LESSON_OVERLAP);
        }

        NoOverlapLessonList lessonsList = NoOverlapLessonList.of(modelLessonsList);

        if (uniqueId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    UniqueId.class.getSimpleName()));
        }
        final UniqueId modelUniqueId = UniqueId.generateId(uniqueId);

        return new Group(modelName, modelUniqueId, modelAssignedTaskIds, modelAssignedPersonIds, lessonsList);
    }

}
