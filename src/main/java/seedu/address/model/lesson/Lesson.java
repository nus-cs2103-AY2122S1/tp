package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import seedu.address.model.person.Grade;
import seedu.address.model.person.Name;
import seedu.address.model.person.Student;

/**
 * Represents a Lesson in the tuitiONE book.
 * A lesson spans for an hour.
 * Guarantees: immutable; subject is valid as declared in {@link #isValidSubject(String)},
 * start and end times are valid as declared in {@link #isValidTime(LocalTime)}
 * and {@link #isValidPrice(double)}
 */
public class Lesson {

    public static final String SUBJECT_VALIDATION_REGEX = "\\p{Alnum}+";
    public static final String SUBJECT_MESSAGE_CONSTRAINTS = "Subject names should be alphanumeric";
    public static final String TIME_MESSAGE_CONSTRAINTS = "Lesson can only start be between 9 am to 8 pm";
    public static final String PRICE_MESSAGE_CONSTRAINT = "Price cannot be 0 or negative";
    public static final String CODE_MESSAGE_CONSTRAINT = "Lesson code should be of correct format";

    public static final LocalTime BOUNDED_START_TIME = LocalTime.of(9, 0);
    public static final LocalTime BOUNDED_END_TIME = LocalTime.of(20, 0);

    private final String subject;
    private final Grade grade;
    private final DayOfWeek day;
    private final LocalTime startTime;
    private final double price;
    private final List<Student> students;

    /**
     * Constructs a {@code Lesson}.
     *
     * @param subject A valid lesson name.
     * @param grade Grade of lesson.
     * @param day A day of the week.
     * @param startTime A valid start time.
     * @param price Price of lesson.
     */
    public Lesson(String subject, Grade grade, DayOfWeek day, LocalTime startTime, double price) {
        requireAllNonNull(subject, grade, day, startTime, price);

        checkArgument(isValidSubject(subject), SUBJECT_MESSAGE_CONSTRAINTS);
        checkArgument(isValidTime(startTime), TIME_MESSAGE_CONSTRAINTS);
        checkArgument(isValidPrice(price), PRICE_MESSAGE_CONSTRAINT);

        this.subject = subject;
        this.grade = grade;
        this.day = day;
        this.startTime = startTime;
        this.price = price;
        this.students = new ArrayList<>();
    }

    /**
     * Constructs a {@code Lesson} using a lesson code.
     * Acts as an overloaded constructor for easy loading from Storage.
     *
     * @param lessonCode A valid lesson code.
     * @param price Price of lesson.
     */
    public static Lesson createFromCodeAndPrice(String lessonCode, double price) {
        requireAllNonNull(lessonCode, price);

        checkArgument(isValidLessonCode(lessonCode), CODE_MESSAGE_CONSTRAINT);

        String[] parameters = lessonCode.split("-");
        String subject = parameters[0];
        Grade grade = new Grade(parameters[1]);
        DayOfWeek day = parseDayOfWeek(parameters[2]);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        LocalTime startTime = LocalTime.parse(parameters[3], timeFormatter);

        return new Lesson(subject, grade, day, startTime, price);
    }

    /**
     * Returns the subject of lesson.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Returns the grade of lesson.
     */
    public Grade getGrade() {
        return grade;
    }

    /**
     * Returns the day of lesson.
     */
    public DayOfWeek getDayOfWeek() {
        return day;
    }

    /**
     * Returns the start time of lesson.
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Returns the price of lesson.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns formatted lesson code string, e.g. Science-P5-Wed-1430.
     */
    public String getLessonCode() {
        return String.format(
                "%s-%s-%s-%s",
                subject,
                grade.value,
                parseDayToString(day),
                startTime.toString().replace(":", ""));
    }

    /**
     * Returns true if both lessons have the same lesson code.
     * This defines a weaker notion of equality between two lessons.
     */
    public boolean isSameLesson(Lesson otherLesson) {
        if (otherLesson == this) {
            return true;
        }
        return otherLesson != null
                && otherLesson.getLessonCode().equals(getLessonCode());
    }

    /**
     * Returns an immutable student set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    /**
     * Returns an unmodifiable set of student names for equality checks.
     */
    public List<Name> getStudentNames() {
        return students.stream().map(Student::getName).collect(Collectors.toUnmodifiableList());
    }

    /**
     * Checks if Student is enrolled in this Lesson
     */
    public boolean containsStudent(Student student) {
        return getStudentNames().contains(student.getName());
    }

    /**
     * Returns the number of students attending the lesson.
     */
    public int getLessonSize() {
        return students.size();
    }

    /**
     * Add student to the lesson instance.
     */
    public void addStudent(Student student) {
        requireNonNull(student);
        students.add(student);
        student.enrollForLesson(this);
    }

    /**
     * Removes student from the lesson instance.
     */
    public void removeStudent(Student student) {
        requireNonNull(student);
        student.unenrollFromLesson(this);

        // this step is needed to break out of the equality checks before deletion
        Student toRemove = null;
        for (Student s : students) {
            if (s.isSamePerson(student)) {
                toRemove = s;
                break;
            }
        }
        if (toRemove != null) {
            students.remove(toRemove);
        }
    }

    /**
     * Returns true if a given string is a valid subject name for a lesson.
     */
    public static boolean isValidSubject(String test) {
        return test.matches(SUBJECT_VALIDATION_REGEX);
    }

    /**
     * Returns true if a given timings are within bounded limits.
     */
    public static boolean isValidTime(LocalTime testStart) {
        return testStart.equals(BOUNDED_START_TIME)
                || testStart.equals(BOUNDED_END_TIME)
                || (testStart.isAfter(BOUNDED_START_TIME) && testStart.isBefore(BOUNDED_END_TIME));
    }

    /**
     * Returns true if a given price is at least 0.
     */
    public static boolean isValidPrice(double testPrice) {
        return (testPrice >= 0.0);
    }

    /**
     * Returns true if a given lesson code is follows the correct format.
     */
    public static boolean isValidLessonCode(String testCode) {
        // check number of parameters in lesson code
        String[] testLessonParams = testCode.split("-");
        if (testLessonParams.length != 4) {
            return false;
        }
        if (!isValidSubject(testLessonParams[0])
                || !Grade.isValidGrade(testLessonParams[1])) {
            return false;
        }
        try {
            // attempt to parse
            parseDayOfWeek(testLessonParams[2]);
            LocalTime.parse(testLessonParams[3], DateTimeFormatter.ofPattern("HHmm"));
        } catch (IllegalArgumentException | DateTimeParseException e) {
            return false;
        }
        return true; // check subject
    }

    /**
     * Parses a {@code String day} into {@code DayOfWeek}.
     */
    public static DayOfWeek parseDayOfWeek(String day) {
        requireNonNull(day);
        String prefix = day.trim();

        switch (prefix) {
        case "Mon":
            return DayOfWeek.MONDAY;
        case "Tue":
            return DayOfWeek.TUESDAY;
        case "Wed":
            return DayOfWeek.WEDNESDAY;
        case "Thu":
            return DayOfWeek.THURSDAY;
        case "Fri":
            return DayOfWeek.FRIDAY;
        case "Sat":
            return DayOfWeek.SATURDAY;
        case "Sun":
            return DayOfWeek.SUNDAY;
        default:
            throw new IllegalArgumentException("Something went wrong with your DAY");
        }
    }

    /**
     * Returns String with corresponding to DayOfWeek.
     */
    public static String parseDayToString(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
        case MONDAY:
            return "Mon";
        case TUESDAY:
            return "Tue";
        case WEDNESDAY:
            return "Wed";
        case THURSDAY:
            return "Thu";
        case FRIDAY:
            return "Fri";
        case SATURDAY:
            return "Sat";
        case SUNDAY:
            return "Sun";
        default:
            throw new NullPointerException("DayOfWeek is null in Lesson");
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof Lesson)) {
            return false;
        }
        // state check
        Lesson otherLesson = (Lesson) other;
        return subject.equals(otherLesson.subject)
                && grade.equals(otherLesson.grade)
                && day.equals(otherLesson.day)
                && startTime.equals(otherLesson.startTime)
                && (price == otherLesson.price)
                && getStudentNames().equals(otherLesson.getStudentNames());
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, grade, day, startTime, price, getStudentNames());
    }

    @Override
    public String toString() {
        return '[' + getLessonCode() + ']';
    }
}
