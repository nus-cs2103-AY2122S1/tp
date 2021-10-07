package seedu.address.model.tuition;

import java.util.ArrayList;
import java.util.Objects;

import seedu.address.model.person.Person;

/**
 * Represents a tuition class in the book
 */
public class TuitionClass {
    /** Most recently viewed tuition class */
    private static TuitionClass MOST_RECENT;

    private final ClassName name;
    private final ClassLimit limit;
    private final Counter counter;
    private final Timeslot timeslot;
    private StudentList studentList;

    /**
     * Constructor for Tuition Class.
     *
     * @param name
     * @param limit
     * @param counter
     * @param timeslot
     * @param studentList
     */
    public TuitionClass(ClassName name, ClassLimit limit, Counter counter, Timeslot timeslot, StudentList studentList) {
        this.name = name;
        this.limit = limit;
        this.counter = counter;
        this.timeslot = timeslot;
        this.studentList = studentList;
        MOST_RECENT = this;
    }

    public ClassName getName() {
        return name;
    }

    public ClassLimit getLimit() {
        return limit;
    }

    public Counter getCounter() {
        return counter;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public StudentList getStudentList() {
        return studentList;
    }

    public int getStudentCount() {
        return studentList.getStudents().size();
    }

    public boolean isFull() {
        return this.getStudentCount() == this.limit.getLimit();
    }

    /**
     * Returns true if both Tuition have the same time slot.
     * This defines a weaker notion of equality between two tuition classes.
     */
    public boolean isSameTuition(TuitionClass otherTuition) {
        if (otherTuition == this) {
            return true;
        }

        return otherTuition != null
                && otherTuition.getTimeslot().equals(getTimeslot());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        TuitionClass otherClass = (TuitionClass) other;
        return otherClass.getName().equals(getName())
                && otherClass.limit.equals(this.limit)
                && otherClass.counter.equals(this.counter)
                && otherClass.timeslot.equals(this.timeslot)
                && otherClass.studentList.equals(this.studentList);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, limit, counter, timeslot, studentList);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Class: ")
                .append(getName())
                .append("; Limit: ")
                .append(getLimit())
                .append("; Counter: ")
                .append(getCounter())
                .append(" Timeslot: ")
                .append(getTimeslot())
                .append("; Students: ")
                .append(getStudentList());
        return builder.toString();
    }

    /**
     * Changes the students in this class to the students in the input.
     * @param students a list of students names to be added.
     * @return this TuitionClass
     */
    public TuitionClass changeStudents(ArrayList<String> students) {
        this.studentList = new StudentList(students);
        return this;
    }

    /**
     * Adds a new student to an existing class if the student is not already in the class.
     * @param person student to be added
     * @return the tuition class after modification
     */
    public TuitionClass addStudent(Person person) {
        ArrayList<String> nowStudents = this.studentList.getStudents();
        String name = person.getName().fullName;
        for (String s: nowStudents) {
            if (s.equals(name)) {
                return null;
            }
        }
        nowStudents.add(person.getName().fullName);
        return this;
    }

    /**
     * Convert students from an arraylist to a string to be displayed in UI
     * @return a string of all the student names combined into a list.
     */
    public String listStudents() {
        String studentString = "";
        if (this.studentList.isEmpty()) {
            studentString = "No student yet.";
            return studentString;
        }
        String lastStudent = studentList.getStudents().get(studentList.getStudents().size() - 1);
        for (String name: studentList.getStudents()) {
            studentString += name;
            if (!name.equals(lastStudent)) {
                studentString += ", ";
            }
        }
        return studentString;
    }

    /**
     * Sets most recently viewed tuition class to a given TuitionClass.
     * @param tuitionClass Tuition Class to set as most recently looked at.
     */
    public static void setMostRecentTo(TuitionClass tuitionClass) {
        MOST_RECENT = tuitionClass;
    }

    /**
     * Returns the most recently viewed tuition class
     * @return most recently viewed tuition class.
     */
    public static TuitionClass getMostRecent() {
        return MOST_RECENT;
    }
}
