package tutoraid.storage;

import static tutoraid.commons.core.Messages.MESSAGE_INVALID_JSON_CAPACITY;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import tutoraid.commons.exceptions.IllegalValueException;
import tutoraid.model.ReadOnlyLessonBook;
import tutoraid.model.ReadOnlyStudentBook;
import tutoraid.model.StudentBook;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.student.InitialStudent;
import tutoraid.model.student.Student;

/**
 * An Immutable StudentBook that is serializable to JSON format.
 */
@JsonRootName(value = "studentbook")
class JsonSerializableStudentBook {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableStudentBook} with the given students.
     */
    @JsonCreator
    public JsonSerializableStudentBook(@JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code ReadOnlyStudentBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableStudentBook}.
     */
    public JsonSerializableStudentBook(ReadOnlyStudentBook source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this student book into the model's {@code StudentBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public StudentBook toModelType(ReadOnlyLessonBook lessonBook) throws IllegalValueException {
        StudentBook studentBook = new StudentBook();
        List<Lesson> lessons = lessonBook.getLessonList();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            InitialStudent initialStudent = jsonAdaptedStudent.toModelType();
            makeStudent(initialStudent, lessons, studentBook);
        }
        return studentBook;
    }

    /**
     * Converts an InitialStudent object to a Student object while populating Lesson objects with Student object
     * references. The Student objects are then added to the given StudentBook.
     *
     * @param initialStudent The InitialStudent object to be converted after being populated with Lesson objects
     * @param lessons        The list of lessons in TutorAid
     * @param sb             The StudentBook to be updated with Student objects
     * @throws IllegalValueException if the JSON file is invalid
     */
    public static void makeStudent(InitialStudent initialStudent, List<Lesson> lessons, StudentBook sb)
            throws IllegalValueException {
        try {
            Student student = initialStudent.toStudent(lessons);
            if (sb.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            sb.addStudent(student);
        } catch (IOException e) {
            throw new IllegalValueException(MESSAGE_INVALID_JSON_CAPACITY);
        }
    }

}
