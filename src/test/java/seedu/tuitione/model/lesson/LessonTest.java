package seedu.tuitione.model.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tuitione.model.lesson.Lesson.CONFLICTING_TIMINGS_CONSTRAINT;
import static seedu.tuitione.model.lesson.Lesson.DIFFERENT_GRADE_CONSTRAINT;
import static seedu.tuitione.model.lesson.Lesson.LESSON_ENROLLMENT_MESSAGE_CONSTRAINT;
import static seedu.tuitione.model.lesson.Lesson.MAX_LESSON_SIZE;
import static seedu.tuitione.model.lesson.Lesson.STUDENT_ALREADY_ENROLLED_CONSTRAINT;
import static seedu.tuitione.model.lesson.Lesson.STUDENT_NOT_ENROLLED_CONSTRAINT;
import static seedu.tuitione.testutil.Assert.assertThrows;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.tuitione.model.student.Student;
import seedu.tuitione.testutil.LessonBuilder;
import seedu.tuitione.testutil.StudentBuilder;

public class LessonTest {

    private Lesson defaultLesson;

    @BeforeEach
    public void setUp() {
        defaultLesson = LessonBuilder.getDefault(); // reset defaultLesson
    }

    @Test
    public void constructor_validSubject_returnsSubject() {
        Lesson sameLesson = LessonBuilder.getDefault();
        assertEquals(defaultLesson, sameLesson);
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Lesson(null, null, null, null));
    }

    @Test
    public void isSameLesson() {
        // this -> true
        assertTrue(defaultLesson.isSameLesson(defaultLesson));

        // new instance with same (default) lesson details -> true
        Lesson otherLesson = new LessonBuilder().build();
        assertTrue(defaultLesson.isSameLesson(otherLesson));

        // different lesson code -> false
        Lesson differentLesson = new LessonBuilder().withSubject("Different").build(); // change subject to change code
        assertFalse(defaultLesson.isSameLesson(differentLesson));
    }

    @Test
    public void runEnrollmentChecks_invalidCases_throwsIllegalArgumentException() {
        String expectedMessage;

        // different grade student
        Student differentGradeStudent = new StudentBuilder().withGrade("P5").build();
        expectedMessage = String.format(DIFFERENT_GRADE_CONSTRAINT, differentGradeStudent.getName(), defaultLesson);
        assertThrows(IllegalArgumentException.class, expectedMessage, () ->
                defaultLesson.runEnrollmentChecks(differentGradeStudent));

        // student with clashing timing
        Student busyStudent = new StudentBuilder().build();
        Lesson clashingLesson = new LessonBuilder().withSubject("Random").build();
        clashingLesson.enrollStudent(busyStudent);
        expectedMessage = String.format(CONFLICTING_TIMINGS_CONSTRAINT, busyStudent.getName(), defaultLesson);
        assertThrows(IllegalArgumentException.class, expectedMessage, () ->
                defaultLesson.runEnrollmentChecks(busyStudent));

        // student already enrolled
        Student enrolledStudent = new StudentBuilder().build();
        defaultLesson.enrollStudent(enrolledStudent);
        expectedMessage = String.format(STUDENT_ALREADY_ENROLLED_CONSTRAINT, enrolledStudent.getName(), defaultLesson);
        assertThrows(IllegalArgumentException.class, expectedMessage, () ->
                defaultLesson.runEnrollmentChecks(enrolledStudent));

        // hit max capacity
        IntStream.rangeClosed(1, MAX_LESSON_SIZE - 1).boxed()
                .map(num -> new StudentBuilder().withName("Temp" + num).build())
                .forEach(s -> defaultLesson.enrollStudent(s));
        Student studentEnteringFullLesson = new StudentBuilder().withName("Temp").build();
        expectedMessage = String.format(LESSON_ENROLLMENT_MESSAGE_CONSTRAINT, defaultLesson);
        assertThrows(IllegalArgumentException.class, expectedMessage, () ->
                defaultLesson.runEnrollmentChecks(studentEnteringFullLesson));
    }

    @Test
    public void doesStudentHaveConflictingTimings() {
        // eligible student
        Student eligibleStudent = new StudentBuilder().build();
        assertFalse(defaultLesson.doesStudentHaveConflictingTimings(eligibleStudent));

        // student with clashing timing
        Student busyStudent = new StudentBuilder().build();
        Lesson clashingLesson = LessonBuilder.getDefault();
        clashingLesson.enrollStudent(busyStudent);
        assertTrue(defaultLesson.doesStudentHaveConflictingTimings(busyStudent));
    }

    @Test
    public void isStudentOfSameGrade() {
        // eligible student
        Student eligibleStudent = new StudentBuilder().build();
        assertTrue(defaultLesson.isStudentOfSameGrade(eligibleStudent));

        // different grade student
        Student differentGradeStudent = new StudentBuilder().withGrade("P5").build();
        assertFalse(defaultLesson.isStudentOfSameGrade(differentGradeStudent));
    }

    @Test
    public void isAbleToUnenroll() {
        Student student = new StudentBuilder().withGrade(defaultLesson.getGrade().value).build();
        // student is not enrolled
        assertFalse(defaultLesson.isAbleToUnenroll(student));

        // enroll and test
        defaultLesson.enrollStudent(student);
        assertTrue(defaultLesson.isAbleToUnenroll(student));
    }

    @Test
    public void containsStudent() {
        Student enrolledStudent = new StudentBuilder().build();

        // student not present
        assertFalse(defaultLesson.containsStudent(enrolledStudent));

        // student present
        defaultLesson.enrollStudent(enrolledStudent);
        assertTrue(defaultLesson.containsStudent(enrolledStudent));
    }

    @Test
    public void addStudent() {
        Student student = new StudentBuilder().withGrade(defaultLesson.getGrade().value).build();
        defaultLesson.enrollStudent(student);

        assertEquals(1, defaultLesson.getStudents().size());
        assertTrue(defaultLesson.containsStudent(student));

        assertEquals(1, student.getLessons().size());
        assertTrue(student.getLessons().contains(defaultLesson));
        assertEquals(defaultLesson.getPrice().value, student.getSubscriptionPrice());
    }

    @Test
    public void updateStudent_previouslyEnrolledStudent() {
        Student student = new StudentBuilder().withGrade(defaultLesson.getGrade().value).build();
        defaultLesson.enrollStudent(student);

        // edit Name
        Student editedNameStudent = new StudentBuilder(student).withName("Edited Name Here").build();
        defaultLesson.updateStudent(student, editedNameStudent);
        assertTrue(editedNameStudent.containsLesson(defaultLesson));
        assertFalse(student.containsLesson(defaultLesson));
        assertEquals(1, defaultLesson.getStudents().size());
        assertEquals(defaultLesson.getStudents().get(0), editedNameStudent);
        defaultLesson.updateStudent(editedNameStudent, student); // revert back

        // edit Parent Contact
        Student editedParentContact = new StudentBuilder(student).withPhone("98765432").build();
        defaultLesson.updateStudent(student, editedParentContact);
        assertTrue(editedParentContact.containsLesson(defaultLesson));
        assertFalse(student.containsLesson(defaultLesson));
        assertEquals(1, defaultLesson.getStudents().size());
        assertEquals(defaultLesson.getStudents().get(0), editedParentContact);
        defaultLesson.updateStudent(editedParentContact, student); // revert back

        // edit Email
        Student editedEmailStudent = new StudentBuilder(student).withEmail("fake@gmail.com").build();
        defaultLesson.updateStudent(student, editedEmailStudent);
        assertTrue(editedEmailStudent.containsLesson(defaultLesson));
        assertFalse(student.containsLesson(defaultLesson));
        assertEquals(1, defaultLesson.getStudents().size());
        assertEquals(defaultLesson.getStudents().get(0), editedEmailStudent);
        defaultLesson.updateStudent(editedEmailStudent, student); // revert back

        // edit Address
        Student editedAddressStudent = new StudentBuilder(student).withAddress("Blk 75, Dover Cresent Rd").build();
        defaultLesson.updateStudent(student, editedAddressStudent);
        assertTrue(editedAddressStudent.containsLesson(defaultLesson));
        assertFalse(student.containsLesson(defaultLesson));
        assertEquals(1, defaultLesson.getStudents().size());
        assertEquals(defaultLesson.getStudents().get(0), editedAddressStudent);
        defaultLesson.updateStudent(editedAddressStudent, student); // revert back

        // edit Remarks
        Student editedRemarksStudent = new StudentBuilder(student).withRemarks("Remark", "Remarks", "Mark").build();
        defaultLesson.updateStudent(student, editedRemarksStudent);
        assertTrue(editedRemarksStudent.containsLesson(defaultLesson));
        assertFalse(student.containsLesson(defaultLesson));
        assertEquals(1, defaultLesson.getStudents().size());
        assertEquals(defaultLesson.getStudents().get(0), editedRemarksStudent);
        defaultLesson.updateStudent(editedRemarksStudent, student); // revert back
    }

    @Test
    public void updateStudent_notPreviouslyEnrolledStudent_throwsIllegalArgumentException() {
        StudentBuilder sb = new StudentBuilder().withGrade(defaultLesson.getGrade().value);
        Student student = sb.build();
        defaultLesson.enrollStudent(student);

        // we edit a student who has not been enrolled at all
        Student unrelatedStudent = sb.withName("Edited Name Here").build();
        String expectedMessage = String.format(STUDENT_NOT_ENROLLED_CONSTRAINT,
                unrelatedStudent.getName(), defaultLesson);
        assertThrows(IllegalArgumentException.class, expectedMessage, () ->
                defaultLesson.updateStudent(unrelatedStudent, unrelatedStudent));
    }

    @Test
    public void updateStudent_differentGradeStudent_throwsIllegalArgumentException() {
        StudentBuilder sb = new StudentBuilder().withGrade(defaultLesson.getGrade().value);
        Student student = sb.build();
        defaultLesson.enrollStudent(student);

        // we edit a student to have a different grade
        Student wrongGradeStudent = sb.withGrade("P5").build();
        String expectedMessage = String.format(DIFFERENT_GRADE_CONSTRAINT,
                wrongGradeStudent.getName(), defaultLesson);
        assertThrows(IllegalArgumentException.class, expectedMessage, () ->
                defaultLesson.updateStudent(student, wrongGradeStudent));
    }

    @Test
    public void removeStudent() {
        Student toRemove = new StudentBuilder().build();
        // student not present
        String notPresentMessage = String.format(STUDENT_NOT_ENROLLED_CONSTRAINT, toRemove.getName(), defaultLesson);
        assertThrows(IllegalArgumentException.class, notPresentMessage, () -> defaultLesson.unenrollStudent(toRemove));
        assertEquals(0, defaultLesson.getLessonSize());

        // student present and to remove
        defaultLesson.enrollStudent(toRemove);
        assertEquals(1, defaultLesson.getLessonSize());

        defaultLesson.unenrollStudent(toRemove);
        assertEquals(0, defaultLesson.getLessonSize());
    }

    @Test
    public void equals() {
        // this -> true
        assertEquals(defaultLesson, defaultLesson);

        // null -> false
        assertNotEquals(defaultLesson, null);

        // different instance -> false
        assertNotEquals(defaultLesson, 5);

        // instance of but different params -> false
        Lesson differentSubjectLesson = new LessonBuilder().withSubject("English").build();
        assertNotEquals(defaultLesson, differentSubjectLesson);

        Lesson differentGradeLesson = new LessonBuilder().withGrade("S3").build();
        assertNotEquals(defaultLesson, differentGradeLesson);

        Lesson differentLessonTimeLesson = new LessonBuilder()
                .withLessonTime(new LessonTime(DayOfWeek.MONDAY, LocalTime.NOON)).build();
        assertNotEquals(defaultLesson, differentLessonTimeLesson);

        Lesson differentPriceLesson = new LessonBuilder().withPrice(13.9).build();
        assertNotEquals(defaultLesson, differentPriceLesson);

        Lesson lessonWithDifferentStudents = new LessonBuilder().build();
        lessonWithDifferentStudents.enrollStudent(
            new StudentBuilder().withGrade(defaultLesson.getGrade().value).build()
        );
        assertNotEquals(defaultLesson, lessonWithDifferentStudents);

        // same lesson but different instance
        Lesson sameLesson = LessonBuilder.getDefault();
        assertEquals(defaultLesson, sameLesson);
    }
}
