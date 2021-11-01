package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.healthcondition.HealthCondition;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Language language;

    // Data fields
    private final Address address;
    private final Optional<Frequency> frequency;
    private final Optional<LastVisit> lastVisit;
    private final Optional<Occurrence> occurrence;
    private final Optional<Visit> visit;
    private final Set<HealthCondition> healthConditions = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Language language, Address address, Optional<LastVisit> lastVisit,
                  Optional<Visit> visit, Optional<Frequency> frequency, Optional<Occurrence> occurrence,
                  Set<HealthCondition> healthConditions) {
        requireAllNonNull(name, phone, language, address, healthConditions);
        this.name = name;
        this.phone = phone;
        this.language = language;
        this.address = address;
        this.lastVisit = lastVisit;
        this.visit = visit;
        this.frequency = frequency;
        this.occurrence = occurrence;
        this.healthConditions.addAll(healthConditions);
    }


    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Language getLanguage() {
        return language;
    }

    public Address getAddress() {
        return address;
    }

    public Optional<LastVisit> getLastVisit() {
        return lastVisit;
    }

    public Optional<Visit> getVisit() {
        return visit;
    }

    public Optional<Frequency> getFrequency() {
        return frequency;
    }

    public Optional<Occurrence> getOccurrence() {
        return occurrence;
    }

    /**
     * Returns an immutable healthCondition set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<HealthCondition> getHealthConditions() {
        return Collections.unmodifiableSet(healthConditions);
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
     * Returns true if the person has a scheduled visit.
     */
    public boolean hasVisit() {
        return (this.visit.get().hasVisit());
    }

    /**
     * Returns true if the person has a scheduled last visit.
     */
    public boolean hasLastVisit() {
        return (this.lastVisit.get().hasLastVisit());
    }

    /**
     * Returns if the person has an existing visit that is overdue.
     * Only the immediate visit will be examined, not the recurring ones that follow.
     */
    public boolean isVisitOverdue() {
        if (this.visit.isEmpty()) {
            return false;
        }

        return this.visit.get().isOverdue();
    }

    /**
     * Returns true if visit is empty with nonempty frequency or occurrence more than 1.
     */
    public boolean hasInvalidFrequencyOccurrence() {
        boolean isEitherTrue = this.occurrence.get().isMoreThan(1)
                || !(this.frequency.get().equals(Frequency.EMPTY));
        return !this.hasVisit() && isEitherTrue;
    }

    /**
     * Returns true if visit is empty with nonempty frequency or occurrence more than 1.
     */
    public boolean hasInvalidFrequency() {
        return occurrence.get().isMoreThan(1) && frequency.get().equals(Frequency.EMPTY);
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
                && otherPerson.getLanguage().equals(getLanguage())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getLastVisit().equals(getLastVisit())
                && otherPerson.getVisit().equals(getVisit())
                && otherPerson.getHealthConditions().equals(getHealthConditions());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, language, address, visit, lastVisit, healthConditions);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Language: ")
                .append(getLanguage())
                .append("; Address: ")
                .append(getAddress())
                .append("; Last Visit: ")
                .append(getLastVisit().orElse(new LastVisit("")))
                .append("; Visit: ")
                .append(getVisit().get());

        Set<HealthCondition> healthConditions = getHealthConditions();
        if (!healthConditions.isEmpty()) {
            builder.append("; Health Conditions: ");
            healthConditions.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Returns the visit formatted with frequency as well as occurrence.
     */
    public String getFormattedVisit() {
        if (this.getVisit().isEmpty()) {
            return new Visit("").getFormatted();
        }

        String visit = this.getVisit().get().getFormatted();
        if (this.getFrequency().isEmpty() || this.getOccurrence().get().value == 1) {
            return visit;
        }

        String frequency = this.getFrequency().get().toString();
        int occurrence = this.getOccurrence().get().getNext().value;
        String formattedVisit = visit + " (repeats " + frequency + ", for " + occurrence + " more time(s))";
        return formattedVisit;
    }

    /**
     * Returns if the person has a visit and the visit is in this week.
     */
    public boolean hasVisitThisWeek() {
        if (!hasVisit()) {
            return false;
        }

        return this.visit.get().isNextSevenDays();
    }

    /**
     * Returns if the person has a visit and the visit is in this month.
     */
    public boolean hasVisitThisMonth() {
        if (!hasVisit()) {
            return false;
        }

        return this.visit.get().isNextThirtyDays();
    }
}
