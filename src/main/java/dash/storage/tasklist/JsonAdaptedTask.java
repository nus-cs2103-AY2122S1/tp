package dash.storage.tasklist;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import dash.commons.exceptions.IllegalValueException;
import dash.logic.parser.exceptions.ParseException;
import dash.model.person.Person;
import dash.model.tag.Tag;
import dash.model.task.CompletionStatus;
import dash.model.task.Task;
import dash.model.task.TaskDate;
import dash.model.task.TaskDescription;
import dash.storage.JsonAdaptedTag;
import dash.storage.addressbook.JsonAdaptedPerson;


/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's TaskDescription field is missing!";

    private final String description;
    private final boolean isComplete;
    private final String taskDate;
    private final List<JsonAdaptedPerson> people = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("description") String description,
                           @JsonProperty("isComplete") boolean isComplete,
                           @JsonProperty("taskDate") String taskDate,
                           @JsonProperty("people") List<JsonAdaptedPerson> people,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.description = description;
        this.isComplete = isComplete;
        this.taskDate = taskDate;
        if (people != null) {
            this.people.addAll(people);
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        description = source.getTaskDescription().description;
        taskDate = source.getTaskDate().toString();
        isComplete = source.getCompletionStatus().get();
        people.addAll(source.getPeople().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Task toModelType() throws IllegalValueException {
        final List<Tag> taskTags = new ArrayList<>();
        final List<Person> taskPeople = new ArrayList<>();
        TaskDate modelTaskDate;
        for (JsonAdaptedTag tag : tagged) {
            taskTags.add(tag.toModelType());
        }

        for (JsonAdaptedPerson person : people) {
            taskPeople.add(person.toModelType());
        }

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskDescription.class.getSimpleName()));
        }
        if (!TaskDescription.isValidDescription(description)) {
            throw new IllegalValueException(TaskDescription.MESSAGE_CONSTRAINTS);
        }

        if (taskDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskDate.class.getSimpleName()));
        }

        if (taskDate == "") {
            modelTaskDate = new TaskDate();
        } else {
            try {
                modelTaskDate = new TaskDate(taskDate, false);
            } catch (IllegalArgumentException e) {
                throw new ParseException(TaskDate.MESSAGE_CONSTRAINTS);
            }
        }

        final TaskDescription modelTaskDescription = new TaskDescription(description);
        final CompletionStatus modelCompletionStatus = new CompletionStatus(isComplete);
        final Set<Person> modelPeople = new HashSet<>(taskPeople);
        final Set<Tag> modelTags = new HashSet<>(taskTags);

        return new Task(modelTaskDescription, modelCompletionStatus, modelTaskDate, modelPeople, modelTags);
    }

}
