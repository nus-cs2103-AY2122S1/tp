package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.person.Field.addToFieldSet;

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.person.exceptions.DuplicateShiftException;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Role role;
    private final Salary salary;
    private final Status status;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Field> fields = new HashSet<>();
    private final Set<Period> absentDates = new HashSet<>();

    private Schedule schedule;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address,
                  Role role, Salary salary, Status status, Set<Tag> tags, Set<Period> absentDates) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.role = role;
        this.salary = salary;
        this.status = status;
        this.tags.addAll(tags);
        this.schedule = new Schedule();
        this.fields.addAll(tags);
        this.absentDates.addAll(absentDates);
        addToFieldSet(fields, name, phone, email, address, salary, status, role);
    }




    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }


    public Address getAddress() {
        return address;
    }

    public Role getRole() {
        return role;
    }

    public Salary getSalary() {
        return salary;
    }

    public Status getStatus() {
        return status;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public boolean containsFields(List<Field> fields) {
        return this.fields.containsAll(fields);
    }

    /**
     * Marks this {@code period} when the {@code Person} was not working.
     */
    public Person mark(Period period) {
        Set<Period> periods = new HashSet<>();
        periods.addAll(this.getAbsentDates());
        periods.add(period);
        return new Person(name, phone, email, address,
                role, salary, status, tags, periods);

    }


    /**
     * Removes the marking of {@code period} to mark that the person was working in
     * this period. The input period must contain the period to remove.
     *
     * @return The resulting person from marking that the person was working.
     */
    public Person unMark(Period period) {
        requireNonNull(period);
        Set<Period> periods = new HashSet<>();
        List<Period> toRemove = getAbsentDates().stream()
                .filter(p -> period.contains(p))
                .collect(Collectors.toList());
        periods.addAll(getAbsentDates());
        periods.removeAll(toRemove);
        return new Person(name, phone, email, address,
                role, salary, status, tags, periods);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable period set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Period> getAbsentDates() {
        return Collections.unmodifiableSet(this.absentDates);
    }

    /**
     * Add a shift to the staff's schedule.
     *
     * @param dayOfWeek The day of the shift.
     * @param slot The time slot of the shift.
     * @throws DuplicateShiftException throws when there is already a shift in the target slot.
     */
    public void changeSchedule(DayOfWeek dayOfWeek, Slot slot) throws DuplicateShiftException {
        schedule.addShift(dayOfWeek, slot);
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherStaff) {
        if (otherStaff == this) {
            return true;
        }

        return otherStaff != null
                && otherStaff.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherStaff = (Person) other;
        //for some odd reason, the set equals method does not work, neither does the contains all
        List<Period> periods = getAbsentDates().stream().collect(Collectors.toList());
        List<Period> otherPeriods = otherStaff.getAbsentDates().stream().collect(Collectors.toList());

        return otherStaff.getName().equals(getName())
                && otherStaff.getPhone().equals(getPhone())
                && otherStaff.getEmail().equals(getEmail())
                && otherStaff.getAddress().equals(getAddress())
                && otherStaff.getRole().equals(getRole())
                && otherStaff.getSalary().equals(getSalary())
                && otherStaff.getStatus().equals(getStatus())
                && otherStaff.getTags().equals(getTags())
                && periods.containsAll(otherPeriods)
                && otherPeriods.containsAll(periods);

    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Role: ")
                .append(getRole())
                .append("; Salary: ")
                .append(getSalary())
                .append("; Status: ")
                .append(getStatus());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }



}
