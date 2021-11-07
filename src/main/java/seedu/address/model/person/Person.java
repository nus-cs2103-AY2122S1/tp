package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.person.Field.addToFieldSet;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.exceptions.InvalidShiftTimeException;
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
    private final Set<Role> roles = new HashSet<>();
    private final Salary salary;
    private final Status status;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Field> fields = new HashSet<>();
    private final Set<Period> absentDates = new HashSet<>();

    private Schedule schedule;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Set<Role> roles,
                   Salary salary, Status status, Set<Tag> tags, Set<Period> absentDates) {
        requireAllNonNull(name, phone, email, tags, roles);

        this.name = name;
        this.phone = phone;
        this.email = email;
        if (roles.isEmpty()) {
            this.roles.add(Role.NO_ROLE);
        } else {
            this.roles.addAll(roles);
        }
        this.salary = salary;
        this.status = status;
        this.tags.addAll(tags);
        this.schedule = new Schedule();
        this.fields.addAll(tags);
        this.absentDates.addAll(absentDates);
        this.fields.addAll(roles);
        addToFieldSet(fields, name, phone, email, salary, status);
    }

    /**
     * Returns a copy of the provided Person object.
     *
     * @param p Person to be copied.
     * @return Person copy of p.
     */
    public static Person copy(Person p) {
        if (p == null) {
            return null;
        }
        return new Person(p.getName(), p.getPhone(), p.getEmail(), p.getRoles(), p.getSalary(),
                p.getStatus(), p.getTags(), p.getAbsentDates());
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

    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
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

    public boolean isWorking(DayOfWeek dayOfWeek, int slotNum, Period period) {
        return schedule.isWorking(dayOfWeek, slotNum, period);
    }

    public boolean isWorking(DayOfWeek dayOfWeek, LocalTime time, Period period) {
        return schedule.isWorking(dayOfWeek, time, period);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public boolean containsFields(List<Field> fields) {
        return this.fields.containsAll(fields);
    }

    /**
     * Marks this {@code period} when the {@code Person} was not working.
     */
    public Person mark(Period period) {
        Set<Period> periods = period.union(this.getAbsentDates())
                .stream()
                .collect(Collectors.toUnmodifiableSet());
        Person person = new Person(name, phone, email, roles, salary, status, tags, periods);
        person.setSchedule(getSchedule());
        return person;

    }

    /**
     * Set time for a shift from the staff's schedule.
     *
     * @param dayOfWeek of the shift.
     * @param slot of the shift.
     * @param startTime of the shift.
     * @param endTime of the shift.
     * @throws InvalidShiftTimeException throws when the timings of Shift are invalid.
     */
    public void setShiftTime(DayOfWeek dayOfWeek, Slot slot, LocalTime startTime, LocalTime endTime,
                             LocalDate startDate, LocalDate endDate)
            throws InvalidShiftTimeException {
        schedule.setTime(dayOfWeek, slot, startTime, endTime, startDate, endDate);
    }


    /**
     * Removes the marking of {@code period} to mark that the person was working in
     * this period. The input period must contain the period to remove.
     *
     * @return The resulting person from marking that the person was working.
     */
    public Person unMark(Period period) {
        requireNonNull(period);
        Set<Period> result = getAbsentDates().stream()
                .flatMap(p -> p.complement(period).stream())
                .collect(Collectors.toSet());
        Person person = new Person(name, phone, email, roles, salary, status, tags, result);
        person.setSchedule(getSchedule());
        return person;
    }


    /**
     * Returns an immutable period set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Period> getAbsentDates() {
        return Collections.unmodifiableSet(this.absentDates);
    }

    /**
     * Checks if this staff was absent on the date provided.
     *
     * @param checkDate The date of the shift to be checked.
     *
     */
    public boolean wasAbsent(LocalDate checkDate) {
        for (Period period : absentDates) {
            if (period.contains(checkDate)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add a shift to the staff's schedule.
     *
     * @param dayOfWeek The day of the shift.
     * @param slot The time slot of the shift.
     * @param startDate The date the shift starts at.
     * @throws DuplicateShiftException throws when there is already a shift in the target slot.
     */
    public void addShift(DayOfWeek dayOfWeek, Slot slot,
                         LocalDate startDate, LocalDate endDate) throws DuplicateShiftException {
        schedule.addShift(dayOfWeek, slot, startDate, endDate);
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }


    /**
     * A method to get the working hours of {@code this} during {@code Period period}.
     *
     * @param period The period to get the working hours over.
     * @return The total working hours over this period.
     */
    public long getTotalWorkingHour(Period period) {
        return this.schedule.getTotalWorkingHour(period, getAbsentDates());
    }

    /**
     * Gets the total salary that this staff has earned over {@code Period period}.
     *
     * @return The salary to be paid in dollars.
     */
    public double getSalaryToBePaid(Period period) {
        return getTotalWorkingHour(period) * salary.value / 100;
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
                && otherStaff.getRoles().equals(getRoles())
                && otherStaff.getSalary().equals(getSalary())
                && otherStaff.getStatus().equals(getStatus())
                && otherStaff.getTags().equals(getTags())
                && periods.containsAll(otherPeriods)
                && otherPeriods.containsAll(periods);

    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Salary: ")
                .append(getSalary().convertToDollars())
                .append("; Status: ")
                .append(getStatus());

        Set<Role> roles = getRoles();
        if (!roles.isEmpty()) {
            builder.append("; Roles: ");
            roles.forEach(builder::append);
        }
        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
