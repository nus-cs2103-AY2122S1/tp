package tutoraid.model.lesson;

import static tutoraid.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import tutoraid.model.lesson.exceptions.LessonExceedCapacityException;
import tutoraid.model.student.Student;

/**
 * Represents a Lesson in the TutorAid.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Lesson {

    // Identity Fields
    private LessonName lessonName;

    // Data Fields
    private Students students;
    private Capacity capacity;
    private Price price;
    private Timing timing;

    /**
     * Constructor for a Lesson when the Students are not yet initialised
     */
    public Lesson(LessonName lessonName, Capacity capacity, Price price, Timing timing) {
        requireAllNonNull(lessonName, capacity, price, timing);
        this.lessonName = lessonName;
        this.capacity = capacity;
        this.price = price;
        this.students = new Students();
        this.timing = timing;
    }

    /**
     * Every field must be present and not null.
     */
    public Lesson(LessonName lessonName, Capacity capacity, Price price, Students students, Timing timing) {
        requireAllNonNull(lessonName, capacity, price, timing);
        this.lessonName = lessonName;
        this.capacity = capacity;
        this.price = price;
        this.students = students;
        this.timing = timing;
    }

    public LessonName getLessonName() {
        return lessonName;
    }

    public Capacity getCapacity() {
        return capacity;
    }

    public int getCapacityValue() {
        return capacity.getCapacity();
    }

    public Price getPrice() {
        return price;
    }

    public Students getStudents() {
        return students;
    }

    public Timing getTiming() {
        return timing;
    }

    /**
     * Checks if a student is in this lesson.
     *
     * @param student student to be checked
     * @return true if the student is in this lesson, false otherwise
     */
    public boolean hasStudent(Student student) {
        return getStudents().hasStudent(student);
    }

    /**
     * Adds a student to this lesson.
     *
     * @param student student to be added
     */
    public void addStudent(Student student) {
        students.addStudent(student);
    }

    /**
     * Removes a student from this lesson.
     *
     * @param student student to be removed
     */
    public void removeStudent(Student student) {
        students.removeStudent(student);
    }

    /**
     * Removes all students from this lesson.
     */
    public void removeAllStudents() {
        students = new Students();
    }

    /**
     * Replaces the fields of this lesson with those of a different lesson to edit it
     *
     * @param lesson The lesson whose fields should replace this lesson
     */
    public void replace(Lesson lesson) {
        lessonName = lesson.getLessonName();
        timing = lesson.getTiming();
        capacity = lesson.getCapacity();
        price = lesson.getPrice();
    }

    /**
     * Checks if the lesson is full.
     *
     * @throws LessonExceedCapacityException if the lesson exceeds its capacity
     */
    public boolean isFull() throws LessonExceedCapacityException {
        if (getStudents().numberOfStudents() > capacity.getCapacity()) {
            throw new LessonExceedCapacityException();
        }
        return getStudents().numberOfStudents() == capacity.getCapacity();
    }

    /**
     * Returns true if both lessons have the same name.
     * This defines a weaker notion of equality between two lessons.
     */
    public boolean isSameLesson(Lesson otherLesson) {
        if (otherLesson == this) {
            return true;
        }

        return otherLesson != null
                && otherLesson.getLessonName().equals(this.getLessonName());
    }

    /**
     * Returns the name of the lesson in a string form
     *
     * @return The name of the lesson in a String
     */
    public String nameAsString() {
        return this.getLessonName().toString();
    }

    /**
     * Returns true if both lessons have the same identity and data fields.
     * This defines a stronger notion of equality between two lessons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Lesson)) {
            return false;
        }

        Lesson otherLesson = (Lesson) other;
        return otherLesson.getLessonName().equals(getLessonName())
                && otherLesson.getCapacity().equals(getCapacity())
                && otherLesson.getPrice().equals(getPrice())
                && otherLesson.getTiming().equals(getTiming());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(lessonName, timing, capacity, price, students);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("\nLesson's name: ")
                .append(getLessonName());
        builder.append("\nLesson's timing: ")
                .append(getTiming());
        builder.append("\nLesson's capacity: ")
                .append(getCapacity());
        builder.append("\nLesson's price: ")
                .append(getPrice());
        builder.append("\nLesson's students: ")
                .append(getStudents());
        return builder.toString();
    }

    /**
     * Returns the name of the lesson as a string.
     *
     * @return Name of lesson
     */
    public String toNameString() {
        return lessonName.toString();
    }

    /**
     * Returns a copy of the current lesson object by creating a new object with the same fields.
     *
     * @return Copy of this lesson object
     */
    public Lesson copy() {
        return new Lesson(
                new LessonName(getLessonName().toString()),
                new Capacity(getCapacity().toString()),
                new Price(getPrice().toString()),
                new Timing(getTiming().toString())
        );
    }
}
