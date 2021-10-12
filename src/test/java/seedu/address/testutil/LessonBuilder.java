package seedu.address.testutil;

import static seedu.address.model.lesson.Price.isValidPrice;
import static seedu.address.model.lesson.Subject.isValidSubject;
import static seedu.address.model.person.Grade.isValidGrade;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonCode;
import seedu.address.model.lesson.LessonTime;
import seedu.address.model.lesson.Price;
import seedu.address.model.lesson.Subject;
import seedu.address.model.person.Grade;
import seedu.address.model.person.Student;

/**
 * A utility class to help with building Lesson objects.
 */
public class LessonBuilder {

    // default constants for test lessons
    public static final Subject DEFAULT_SUBJECT = new Subject("Science");
    public static final Grade DEFAULT_GRADE = new Grade("S1");
    public static final LessonTime DEFAULT_LESSON_TIME = new LessonTime(DayOfWeek.WEDNESDAY, LocalTime.of(15, 0));
    public static final Price DEFAULT_PRICE = new Price(9.9);
    public static final LessonCode DEFAULT_LESSON_CODE = new LessonCode("Science-S1-Wed-1500");

    private Subject subject;
    private Grade grade;
    private LessonTime lessonTime;
    private Price price;
    private final List<Student> students;

    /**
     * Creates a {@code LessonBuilder} with the default details.
     */
    public LessonBuilder() {
        this.subject = DEFAULT_SUBJECT;
        this.grade = DEFAULT_GRADE;
        this.lessonTime = DEFAULT_LESSON_TIME;
        this.price = DEFAULT_PRICE;
        this.students = new ArrayList<>();
    }

    /**
     * Initializes the LessonBuilder with the data of {@code lessonToCopy}.
     */
    public LessonBuilder(Lesson lessonToCopy) {
        this.subject = lessonToCopy.getSubject();
        this.grade = lessonToCopy.getGrade();
        this.lessonTime = lessonToCopy.getLessonTime();
        this.price = lessonToCopy.getPrice();
        this.students = lessonToCopy.getStudents();
    }

    /**
     * Sets the {@code subject} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    /**
     * Sets the {@code subject} of the {@code Lesson} that we are building.
     * An overloaded method that takes the string subject value instead as parameter.
     */
    public LessonBuilder withSubject(String subject) {
        assert (isValidSubject(subject)) : "Subject to mock is invalid";
        this.subject = new Subject(subject);
        return this;
    }

    /**
     * Sets the {@code grade} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withGrade(Grade grade) {
        this.grade = grade;
        return this;
    }

    /**
     * Sets the {@code grade} of the {@code Lesson} that we are building.
     * An overloaded method that takes the string grade value instead as parameter.
     */
    public LessonBuilder withGrade(String grade) {
        assert (isValidGrade(grade)) : "Grade to mock is invalid";
        this.grade = new Grade(grade);
        return this;
    }

    /**
     * Sets the {@code dayOfWeek} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withLessonTime(LessonTime lessonTime) {
        this.lessonTime = lessonTime;
        return this;
    }

    /**
     * Sets the {@code price} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withPrice(Price price) {
        this.price = price;
        return this;
    }

    /**
     * Sets the {@code price} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withPrice(double price) {
        assert (isValidPrice(price)) : "Price to mock is invalid";
        this.price = new Price(price);
        return this;
    }

    /**
     * Sets the {@code students} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withStudents(List<Student> students) {
        this.students.clear();
        this.students.addAll(students);
        return this;
    }

    /**
     * Creates a {@code Lesson} with the specified details.
     */
    public Lesson build() {
        Lesson lesson = new Lesson(subject, grade, lessonTime, price);
        for (Student student : students) {
            lesson.addStudent(student);
        }
        return lesson;
    }

    /**
     * Creates a {@code Lesson} with the default details.
     */
    public static Lesson getDefault() {
        return new LessonBuilder().build();
    }
}
