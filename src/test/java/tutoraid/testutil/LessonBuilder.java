package tutoraid.testutil;

import java.util.ArrayList;

import tutoraid.model.lesson.Capacity;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.lesson.LessonName;
import tutoraid.model.lesson.Price;
import tutoraid.model.lesson.Students;
import tutoraid.model.lesson.Timing;
import tutoraid.model.student.Student;

/**
 * A utility class to help with building Lesson objects.
 */
public class LessonBuilder {

    public static final String DEFAULT_LESSON_NAME = "Maths 3";
    public static final String DEFAULT_LESSON_CAPACITY = "50";
    public static final String DEFAULT_LESSON_PRICE = "100";
    public static final ArrayList<Student> DEFAULT_LESSON_STUDENTS = new ArrayList<Student>();
    public static final String DEFAULT_LESSON_TIMING = "1000-1200";

    private LessonName lessonName;
    private Capacity capacity;
    private Price price;
    private Students students;
    private Timing timing;

    /**
     * Creates a {@code LessonBuilder} with the default details.
     */
    public LessonBuilder() {
        lessonName = new LessonName(DEFAULT_LESSON_NAME);
        capacity = new Capacity(DEFAULT_LESSON_CAPACITY);
        price = new Price(DEFAULT_LESSON_PRICE);
        students = new Students(DEFAULT_LESSON_STUDENTS);
        timing = new Timing(DEFAULT_LESSON_TIMING);
    }

    /**
     * Initializes the LessonBuilder with the data of {@code lessonToCopy}.
     */
    public LessonBuilder(Lesson lessonToCopy) {
        lessonName = lessonToCopy.getLessonName();
        capacity = lessonToCopy.getCapacity();
        price = lessonToCopy.getPrice();
        students = lessonToCopy.getStudents();
        timing = lessonToCopy.getTiming();
    }

    /**
     * Sets the {@code LessonName} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withLessonName(String lessonName) {
        this.lessonName = new LessonName(lessonName);
        return this;
    }

    /**
     * Sets the {@code Capacity} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withCapacity(String capacity) {
        this.capacity = new Capacity(capacity);
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withPrice(String price) {
        this.price = new Price(price);
        return this;
    }

    /**
     * Sets the {@code Students} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withStudents(ArrayList<Student> students) {
        this.students = new Students(students);
        return this;
    }

    /**
     * Sets the {@code Timing} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withTiming(String timing) {
        this.timing = new Timing(timing);
        return this;
    }

    public Lesson build() {
        return new Lesson(lessonName, capacity, price, students, timing);
    }
}
