package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.person.Field.addToFieldSet;

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.exceptions.DuplicateShiftException;
import seedu.address.model.person.exceptions.NoShiftException;
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
    private final Set<Role> roles = new HashSet<>();
    private final Salary salary;
    private final Status status;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Field> fields = new HashSet<>();

    private Schedule schedule;
    private int totalWeeklyWorkingHour;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address,
                  Set<Role> roles, Salary salary, Status status, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags, roles);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (roles.isEmpty()) {
            this.roles.add(Role.NO_ROLE);
        } else {
            this.roles.addAll(roles);
        }
        this.salary = salary;
        this.status = status;
        this.tags.addAll(tags);
        this.schedule = new Schedule();
        this.totalWeeklyWorkingHour = schedule.getTotalWorkingHour();
      
        this.fields.addAll(tags);
        this.fields.addAll(roles);
        addToFieldSet(fields, name, phone, email, address, salary, status);
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

    public boolean containsFields(List<Field> fields) {
        return this.fields.containsAll(fields);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Add a shift to the staff's schedule.
     *
     * @param dayOfWeek The day of the shift.
     * @param slot The time slot of the shift.
     * @throws DuplicateShiftException throws when there is already a shift in the target slot.
     */
    public void addShift(DayOfWeek dayOfWeek, Slot slot) throws DuplicateShiftException {
        schedule.addShift(dayOfWeek, slot);
        totalWeeklyWorkingHour = schedule.getTotalWorkingHour();
    }

    /**
     * Removes a shift from the staff's schedule.
     *
     * @param dayOfWeek The day of the shift.
     * @param slot The time slot of the shift.
     * @throws NoShiftException throws when a user tries to delete a shift that does not exist.
     */
    public void removeShift(DayOfWeek dayOfWeek, Slot slot) throws NoShiftException {
        schedule.removeShift(dayOfWeek, slot);
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
        totalWeeklyWorkingHour = schedule.getTotalWorkingHour();
    }

    public int getTotalWeeklyWorkingHour() {
        return totalWeeklyWorkingHour;
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
        return otherStaff.getName().equals(getName())
                && otherStaff.getPhone().equals(getPhone())
                && otherStaff.getEmail().equals(getEmail())
                && otherStaff.getAddress().equals(getAddress())
                && otherStaff.getRoles().equals(getRoles())
                && otherStaff.getSalary().equals(getSalary())
                && otherStaff.getStatus().equals(getStatus())
                && otherStaff.getTags().equals(getTags())
                && otherStaff.totalWeeklyWorkingHour == totalWeeklyWorkingHour;
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
                .append("; Salary: ")
                .append(getSalary())
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
