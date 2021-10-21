package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private List<String> days;


    // Data fields
    private final Availability availability;
    private final TodayAttendance todayAttendance;
    private final TotalAttendance totalAttendance;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Availability availability) {
        requireAllNonNull(name, phone, availability);
        this.name = name;
        this.phone = phone;
        this.availability = availability;
        this.todayAttendance = new TodayAttendance(false);
        this.totalAttendance = new TotalAttendance(0);
    }

    /**
     * Constructor that creates person object with attendance.
     *
     * @param name Name of member
     * @param phone Phone number of member
     * @param availability availability of member
     * @param todayAttendance Today's attendance of member.
     * @param totalAttendance Total attendance of member.
     */
    public Person(Name name, Phone phone, Availability availability,
                  TodayAttendance todayAttendance, TotalAttendance totalAttendance) {
        requireAllNonNull(name, phone, availability, todayAttendance, totalAttendance);
        this.name = name;
        this.phone = phone;
        this.availability = availability;
        this.totalAttendance = totalAttendance;
        this.todayAttendance = todayAttendance;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Availability getAvailability() {
        return availability;
    }

    public TotalAttendance getTotalAttendance() {
        return totalAttendance;
    }

    public TodayAttendance getTodayAttendance() {
        return todayAttendance;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if person is available on specified day. Otherwise,
     * false is returned.
     *
     * @param day Day to be checked if person is available.
     * @return Boolean value if person is available on day.
     */
    public boolean isAvailableOnDay(String day) {
        return availability.contains(day);
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    /**
     * Sets the member as present today.
     */
    public void setPresent() {
        if (!isMarkedPresent()) {
            todayAttendance.setPresent();
            totalAttendance.incrementAttendance();
        }
    }

    /**
     * Sets member as not present today.
     */
    public void setNotPresent() {
        if (isMarkedPresent()) {
            todayAttendance.setNotPresent();
            totalAttendance.decrementAttendance();
        }
    }

    /**
     * Clears today's attendance.
     */
    public void clearTodayAttendance() {
        if (isMarkedPresent()) {
            todayAttendance.setNotPresent();
        }
    }

    /**
     * Returns true if person has been marked present. Otherwise,
     * false is returned.
     *
     * @return Boolean value if person is marked present.
     */
    public boolean isMarkedPresent() {
        return todayAttendance.isPresentToday();
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

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getAvailability().equals(getAvailability())
                && otherPerson.getTodayAttendance().equals(getTodayAttendance())
                && otherPerson.getTotalAttendance().equals(getTotalAttendance());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, availability);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append(": Availability: ")
                .append(getAvailability())
                .append("Today: ")
                .append(getTodayAttendance())
                .append("Total: ")
                .append(getTotalAttendance());
        return builder.toString();
    }
}
