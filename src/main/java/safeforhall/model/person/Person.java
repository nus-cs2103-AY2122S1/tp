package safeforhall.model.person;

import static safeforhall.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Room room;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final VaccStatus vaccStatus;
    private final Faculty faculty;
    private final LastDate lastFetDate;
    private final LastDate lastCollectionDate;

    /**
     * Every field must be present.
     */
    public Person(Name name, Room room, Phone phone, Email email, VaccStatus vaccStatus,
                    Faculty faculty, LastDate lastFetDate, LastDate lastCollectionDate) {
        requireAllNonNull(name, room, phone, email, vaccStatus, faculty, lastFetDate, lastCollectionDate);
        this.name = name;
        this.room = room;
        this.phone = phone;
        this.email = email;
        this.vaccStatus = vaccStatus;
        this.faculty = faculty;
        this.lastFetDate = lastFetDate;
        this.lastCollectionDate = lastCollectionDate;
    }

    public Name getName() {
        return name;
    }

    public Room getRoom() {
        return room;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public VaccStatus getVaccStatus() {
        return vaccStatus;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public LastDate getLastFetDate() {
        return lastFetDate;
    }

    public LastDate getLastCollectionDate() {
        return lastCollectionDate;
    }

    /**
     * Returns true if both persons have the same name and room.
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
                && otherPerson.getRoom().equals(getRoom())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getVaccStatus().equals(getVaccStatus())
                && otherPerson.getFaculty().equals(getFaculty());
                //&& otherPerson.getLastFetDate().equals(getLastFetDate())
                //&& otherPerson.getLastCollectionDate().equals(getLastCollectionDate());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, room, phone, email, vaccStatus);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Room: ")
                .append(getRoom())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Vaccinated: ")
                .append(getVaccStatus())
                .append("; Faculty: ")
                .append(getFaculty())
                .append("; Last Fet Date: ")
                .append(getLastFetDate())
                .append("; Last Collection Date: ")
                .append(getLastCollectionDate());

        return builder.toString();
    }

    /**
     * Returns true the person has missed any of his fet dates.
     */
    public boolean hasMissedDeadline() {
        LastDate currentDate = new LastDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        NameMissedDeadlinePredicate checkFet = new NameMissedDeadlinePredicate("f", currentDate);
        return checkFet.test(this);
    }

    /**
     * Returns the number of days the person has missed any of his fet dates.
     */
    public int getMissedDates() {
        LastDate currentDate = new LastDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        NameMissedDeadlinePredicate checkFet = new NameMissedDeadlinePredicate("f", currentDate);

        if (this.hasMissedDeadline()) {
            return (int) Math.abs(checkFet.getDeadlinePeriod(this));
        } else {
            return -1;
        }
    }
}
