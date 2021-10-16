package tutoraid.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import tutoraid.commons.exceptions.IllegalValueException;
import tutoraid.model.ReadOnlyStudentBook;
import tutoraid.model.StudentBook;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.student.Student;

/**
 * An Immutable StudentBook that is serializable to JSON format.
 */
@JsonRootName(value = "studentbook")
class JsonSerializableStudentBook {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";
    public static final String MESSAGE_DUPLICATE_LESSON = "Lessons list contains duplicate lesson(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedLesson> lessons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableStudentBook} with the given students.
     */
    @JsonCreator
    public JsonSerializableStudentBook(@JsonProperty("students") List<JsonAdaptedStudent> students,
                                       @JsonProperty("lessons") List<JsonAdaptedLesson> lessons) {
        this.students.addAll(students);
        this.lessons.addAll(lessons);
    }

    /**
     * Converts a given {@code ReadOnlyStudentBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableStudentBook}.
     */
    public JsonSerializableStudentBook(ReadOnlyStudentBook source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        lessons.addAll(source.getLessonList().stream().map(JsonAdaptedLesson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this student book into the model's {@code StudentBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public StudentBook toModelType() throws IllegalValueException {
        StudentBook studentBook = new StudentBook();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (studentBook.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            studentBook.addStudent(student);
        }
        for (JsonAdaptedLesson jsonAdaptedLesson: lessons) {
            Lesson lesson = jsonAdaptedLesson.toModelType();
            if (studentBook.hasLesson(lesson)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_LESSON);
            }
            studentBook.addLesson(lesson);
        }
        return studentBook;
    }

}
