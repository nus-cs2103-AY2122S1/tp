package seedu.academydirectory.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.academydirectory.commons.exceptions.IllegalValueException;
import seedu.academydirectory.model.AcademyDirectory;
import seedu.academydirectory.model.ReadOnlyAcademyDirectory;
import seedu.academydirectory.model.student.Student;

/**
 * An Immutable AcademyDirectory that is serializable to JSON format.
 */
@JsonRootName(value = "academy directory")
class JsonSerializableAcademyDirectory {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAcademyDirectory} with the given students.
     */
    @JsonCreator
    public JsonSerializableAcademyDirectory(@JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code ReadOnlyAcademyDirectory} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAcademyDirectory}.
     */
    public JsonSerializableAcademyDirectory(ReadOnlyAcademyDirectory source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this academy directory into the model's {@code AcademyDirectory} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AcademyDirectory toModelType() throws IllegalValueException {
        AcademyDirectory academyDirectory = new AcademyDirectory();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (academyDirectory.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            academyDirectory.addStudent(student);
        }
        return academyDirectory;
    }

}
