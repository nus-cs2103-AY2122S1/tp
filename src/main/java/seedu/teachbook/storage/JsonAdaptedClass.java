package seedu.teachbook.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.teachbook.commons.exceptions.IllegalValueException;
import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.classobject.ClassName;
import seedu.teachbook.model.student.Student;

/**
 * Jackson-friendly version of {@link Class}.
 */
class JsonAdaptedClass {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Any class contains duplicate students.";
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Class's %s field is missing!";

    private final String className;
    private final List<JsonAdaptedStudent> classList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedClass} with the given class details.
     */
    @JsonCreator
    public JsonAdaptedClass(@JsonProperty("className") String name,
                            @JsonProperty("classList") List<JsonAdaptedStudent> classList) {
        this.className = name;
        if (classList != null) {
            this.classList.addAll(classList);
        }
    }

    /**
     * Converts a given {@code Class} into this class for Jackson use.
     */
    public JsonAdaptedClass(Class source) {
        className = source.getClassName().nameOfClass;
        classList.addAll(source.getStudentsOfThisClass().stream()
                .map(JsonAdaptedStudent::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted class object into the model's {@code Class} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted class.
     */
    public Class toModelType() throws IllegalValueException {
        if (className == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ClassName.class.getSimpleName()));
        }
        if (!ClassName.isValidClassName(className)) {
            throw new IllegalValueException(ClassName.MESSAGE_CONSTRAINTS);
        }
        final ClassName modelClassName = new ClassName(className);

        Class modelClass = new Class(modelClassName);
        for (JsonAdaptedStudent student : classList) {
            Student toAdd = student.toModelType();
            toAdd.setStudentClass(modelClass);
            if (modelClass.containsStudent(toAdd)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            modelClass.addStudent(toAdd);
        }

        return modelClass;
    }

}
