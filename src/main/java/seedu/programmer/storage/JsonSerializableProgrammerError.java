package seedu.programmer.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.programmer.commons.exceptions.IllegalValueException;
import seedu.programmer.model.ProgrammerError;
import seedu.programmer.model.ReadOnlyProgrammerError;
import seedu.programmer.model.student.Student;

/**
 * An Immutable ProgrammerError that is serializable to JSON format.
 */
@JsonRootName(value = "programmererror")
class JsonSerializableProgrammerError {

    public static final String MESSAGE_DUPLICATE_STUDENT = "students list contains duplicate student(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableProgrammerError} with the given students.
     */
    @JsonCreator
    public JsonSerializableProgrammerError(@JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code ReadOnlyProgrammerError} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableProgrammerError}.
     */

    public JsonSerializableProgrammerError(ReadOnlyProgrammerError source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
    }

    /**
     * Converts ProgrammerError into the model's {@code ProgrammerError} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ProgrammerError toModelType() throws IllegalValueException {
        ProgrammerError programmerError = new ProgrammerError();
        for (JsonAdaptedStudent jsonAdaptedstudent : students) {
            Student student = jsonAdaptedstudent.toModelType();
            if (programmerError.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            programmerError.addStudent(student);
        }
        return programmerError;
    }

}
