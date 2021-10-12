package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTeachingAssistantBuddy;
import seedu.address.model.TeachingAssistantBuddy;
import seedu.address.model.module.student.Student;

/**
 * An Immutable TeachingAssistantBuddy that is serializable to JSON format.
 */
@JsonRootName(value = "assistantbuddy")
class JsonSerializableAssistantBuddy {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate student(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAssistantBuddy} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAssistantBuddy(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyTeachingAssistantBuddy} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAssistantBuddy}.
     */
    public JsonSerializableAssistantBuddy(ReadOnlyTeachingAssistantBuddy source) {
        persons.addAll(source.getStudentList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code TeachingAssistantBuddy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TeachingAssistantBuddy toModelType() throws IllegalValueException {
        TeachingAssistantBuddy teachingAssistantBuddy = new TeachingAssistantBuddy();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Student student = jsonAdaptedPerson.toModelType();
            if (teachingAssistantBuddy.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            teachingAssistantBuddy.addStudent(student);
        }
        return teachingAssistantBuddy;
    }

}
