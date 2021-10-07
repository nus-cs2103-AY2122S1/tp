package seedu.address.model.tuition;

import java.util.ArrayList;
import java.util.Objects;

import seedu.address.model.person.Person;

/**
 * Represents a tuition class in the book
 */
public class TuitionClass {
    private final ClassName name;
    private final ClassLimit limit;
    private final Counter counter;
    private final Timeslot timeslot;
    private Student student;

    /**
     * Constructor for Tuition Class.
     *
     * @param name
     * @param limit
     * @param counter
     * @param timeslot
     * @param student
     */
    public TuitionClass(ClassName name, ClassLimit limit, Counter counter, Timeslot timeslot, Student student) {
        this.name = name;
        this.limit = limit;
        this.counter = counter;
        this.timeslot = timeslot;
        this.student = student;
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

    public Student getStudent() {
        return student;
    }

    /**
     * Returns true if both Tuition have the same time slot.
     * This defines a weaker notion of equality between two tuition classes.
     */
    public boolean isSameTuition(TuitionClass otherTuition) {
        if (otherTuition == this) {
            return true;
        }
        System.out.println(otherTuition.getName() + ": " + otherTuition.getTimeslot() + "  " + getName() + ": " + getTimeslot());
        System.out.println(otherTuition.getTimeslot().equals(timeslot));
//        return otherTuition.getTimeslot().toString().equals(getTimeslot().toString());
        return otherTuition.getTimeslot().equals(getTimeslot());
    }

    //addn/John Doe p/98765432 e/johnd@example.com a/John street, block 123
    //addclass n/cs2100 l/10 c/4 ts/Mon 13:00-14:00 s/John Doe

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        TuitionClass otherClass = (TuitionClass) other;
        return otherClass.getTimeslot().equals(getTimeslot());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, limit, counter, timeslot, student);
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
                .append(getStudent());
        return builder.toString();
    }

    /**
     * Changes the students in this class to the students in the input.
     * @param students a list of students names to be added.
     * @return this TuitionClass
     */
    public TuitionClass changeStudents(ArrayList<String> students) {
        this.student = new Student(students);
        return this;
    }

    /**
     * Adds a new student to an existing class if the student is not already in the class.
     * @param person student to be added
     * @return the tuition class after modification
     */
    public TuitionClass addStudent(Person person) {
        ArrayList<String> nowStudents = this.student.students;
        String name = person.getName().fullName;
        for (String s: nowStudents) {
            if (s.equals(name)) {
                return null;
            }
        }
        nowStudents.add(person.getName().fullName);
        return this;
    }
}
