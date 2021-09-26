package seedu.address.model.Tuition;

import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

import java.util.Objects;

/**
 * Represents a tuition class in the book
 */
public class TuitionClass {
    private final ClassName name;
    private final ClassLimit limit;
    private final Counter counter;
    private final Timeslot timeslot;
    private final Student student;

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
                && otherClass.getLimit().equals(getLimit())
                && otherClass.getCounter().equals(getCounter())
                && otherClass.getTimeslot().equals(getTimeslot())
                && otherClass.getStudent().equals(getStudent());
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
}
