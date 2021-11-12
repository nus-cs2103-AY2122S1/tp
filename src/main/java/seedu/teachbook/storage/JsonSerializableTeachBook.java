package seedu.teachbook.storage;

import static seedu.teachbook.model.gradeobject.GradingSystem.NOT_GRADED;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.teachbook.commons.exceptions.IllegalValueException;
import seedu.teachbook.model.ReadOnlyTeachBook;
import seedu.teachbook.model.TeachBook;
import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.gradeobject.Grade;
import seedu.teachbook.model.gradeobject.GradingSystem;
import seedu.teachbook.model.student.Student;

/**
 * An Immutable TeachBook that is serializable to JSON format.
 */
@JsonRootName(value = "teachbook")
class JsonSerializableTeachBook {

    public static final String MESSAGE_DUPLICATE_CLASS = "TeachBook contains duplicate classes.";
    public static final String MESSAGE_DUPLICATE_GRADE = "TeachBook grading system contains duplicate grades.";
    public static final String MESSAGE_INVALID_GRADE = "Grade is not valid.";

    private final List<JsonAdaptedClass> classes = new ArrayList<>();
    private final List<JsonAdaptedGrade> gradeList = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTeachBook} with the given students
     * .
     */
    @JsonCreator
    public JsonSerializableTeachBook(@JsonProperty("classes") List<JsonAdaptedClass> classes,
                                     @JsonProperty("gradeList") List<JsonAdaptedGrade> grades) {
        this.classes.addAll(classes);
        this.gradeList.addAll(grades);
    }

    /**
     * Converts a given {@code ReadOnlyTeachBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTeachBook}.
     */
    public JsonSerializableTeachBook(ReadOnlyTeachBook source) {
        classes.addAll(source.getClassList().stream().map(JsonAdaptedClass::new).collect(Collectors.toList()));
        gradeList.addAll(source.getGradingSystem().getGradeList().stream()
                .map(JsonAdaptedGrade::new).collect(Collectors.toList()));
    }

    /**
     * Converts this teachbook book into the model's {@code TeachBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TeachBook toModelType() throws IllegalValueException {
        TeachBook teachBook = new TeachBook();

        GradingSystem modelGradingSystem = new GradingSystem();
        for (JsonAdaptedGrade jsonAdaptedGrade : gradeList) {
            Grade grade = jsonAdaptedGrade.toModelType();
            if (grade.equals(NOT_GRADED)) {
                continue;
            }
            if (modelGradingSystem.hasGrade(grade)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_GRADE);
            }
            modelGradingSystem.addGrade(grade);
        }
        teachBook.setGradingSystem(modelGradingSystem);

        for (JsonAdaptedClass jsonAdaptedClass : classes) {
            Class classObj = jsonAdaptedClass.toModelType();
            if (teachBook.hasClass(classObj)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CLASS);
            }
            for (Student student : classObj.getStudentsOfThisClass()) {
                if (!modelGradingSystem.isValidGrade(student.getGrade())) {
                    throw new IllegalValueException(MESSAGE_INVALID_GRADE);
                }
            }
            teachBook.addClass(classObj);
        }

        return teachBook;
    }

}
