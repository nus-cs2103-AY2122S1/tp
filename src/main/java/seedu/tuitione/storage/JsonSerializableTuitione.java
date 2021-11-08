package seedu.tuitione.storage;

import static seedu.tuitione.model.lesson.Lesson.CONFLICTING_TIMINGS_CONSTRAINT;
import static seedu.tuitione.model.lesson.Lesson.DIFFERENT_GRADE_CONSTRAINT;
import static seedu.tuitione.model.lesson.Lesson.LESSON_ENROLLMENT_MESSAGE_CONSTRAINT;
import static seedu.tuitione.model.lesson.Lesson.STUDENT_ALREADY_ENROLLED_CONSTRAINT;
import static seedu.tuitione.model.student.Student.STUDENT_ENROLLMENT_MESSAGE_CONSTRAINT;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.tuitione.commons.exceptions.IllegalValueException;
import seedu.tuitione.model.ReadOnlyTuitione;
import seedu.tuitione.model.Tuitione;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.student.Student;

/**
 * An Immutable Tuitione that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableTuitione {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";
    public static final String MESSAGE_DUPLICATE_LESSON = "Lessons list contains duplicate lesson(s).";
    public static final String MESSAGE_INVALID_LESSON_CODE = "Lessons list does not match with "
            + "student-associated lesson codes(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedLesson> lessons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTuitione} with the given students.
     */
    @JsonCreator
    public JsonSerializableTuitione(@JsonProperty("students") List<JsonAdaptedStudent> students,
            @JsonProperty("lessons") List<JsonAdaptedLesson> lessons) {
        this.students.addAll(students);
        this.lessons.addAll(lessons);
    }

    /**
     * Converts a given {@code ReadOnlyTuitione} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTuitione}.
     */
    public JsonSerializableTuitione(ReadOnlyTuitione source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        lessons.addAll(source.getLessonList().stream().map(JsonAdaptedLesson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this tuitione book into the model's {@code Tuitione} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Tuitione toModelType() throws IllegalValueException {
        List<Lesson> lessonList = prepareLessons();
        List<Student> studentList = prepareStudentsAndLinkages(lessonList);
        return prepareTuitione(lessonList, studentList);
    }

    /**
     * Prepares the model's {@code Tuitione} object with the lesson and student lists.
     */
    public Tuitione prepareTuitione(List<Lesson> lessons, List<Student> students) throws IllegalValueException {
        Tuitione tuitione = new Tuitione();
        for (Lesson lesson : lessons) {
            if (tuitione.hasLesson(lesson)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_LESSON);
            }
            tuitione.addLesson(lesson);
        }
        for (Student student : students) {
            if (tuitione.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            tuitione.addStudent(student);
        }
        return tuitione;
    }

    /**
     * Prepares the list of Lessons for model.
     */
    public List<Lesson> prepareLessons() throws IllegalValueException {
        List<Lesson> lessonList = new ArrayList<>();
        for (JsonAdaptedLesson jsonAdaptedLesson : lessons) {
            Lesson lesson = jsonAdaptedLesson.toModelType();
            lessonList.add(lesson);
        }
        return lessonList;
    }

    /**
     * Prepares the list of Students for model with their assigned lessons.
     */
    public List<Student> prepareStudentsAndLinkages(List<Lesson> lessonList) throws IllegalValueException {
        List<Student> studentList = new ArrayList<>();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            List<String> jsonLessonCodes = jsonAdaptedStudent.getLessonCodes();
            establishStudentLessonLinkages(student, jsonLessonCodes, lessonList);
            studentList.add(student);
        }
        return studentList;
    }

    /**
     * Prepare linkages between a student and lessons.
     */
    public void establishStudentLessonLinkages(Student student, List<String> jsonLessonCodes,
            List<Lesson> lessonList) throws IllegalValueException {

        for (String jsonLessonCode : jsonLessonCodes) {

            // find if lesson is present
            Optional<Lesson> lessonStream = lessonList.stream()
                    .filter(l -> l.getLessonCode().value.equals(jsonLessonCode))
                    .findFirst();
            Lesson lesson = lessonStream.orElseThrow(() -> new IllegalValueException(MESSAGE_INVALID_LESSON_CODE));

            // enrollment checks
            if (!student.isAbleToEnrollForMoreLessons()) {
                throw new IllegalValueException(String.format(STUDENT_ENROLLMENT_MESSAGE_CONSTRAINT,
                        student.getName()));

            } else if (lesson.containsStudent(student)) {
                throw new IllegalValueException(String.format(STUDENT_ALREADY_ENROLLED_CONSTRAINT,
                        student.getName(), lesson));

            } else if (lesson.doesStudentHaveConflictingTimings(student)) {
                throw new IllegalValueException(String.format(CONFLICTING_TIMINGS_CONSTRAINT,
                        student.getName(), lesson));

            } else if (!lesson.isStudentOfSameGrade(student)) {
                throw new IllegalValueException(String.format(DIFFERENT_GRADE_CONSTRAINT,
                        student.getName(), lesson));

            } else if (!lesson.isAbleToEnrollMoreStudents()) {
                throw new IllegalValueException(String.format(LESSON_ENROLLMENT_MESSAGE_CONSTRAINT, lesson));
            }

            lesson.enrollStudent(student);
        }
    }
}
