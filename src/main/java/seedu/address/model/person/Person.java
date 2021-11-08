package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.interview.Interview;
import seedu.address.model.position.Position;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the HR Manager.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Remark remark;
    private Status status;
    private Set<Position> positions = new HashSet<>();
    private Set<Interview> interviews = new HashSet<>();

    /**
     * Every field must be present and not null. If status is null, defaults to applied
     */
    public Person(Name name, Phone phone, Email email, Address address, Remark remark, Set<Tag> tags, Status status,
                  Set<Position> positions) {
        requireAllNonNull(name, phone, email, address, tags, status, positions);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.remark = remark;
        this.status = status;
        this.positions.addAll(positions);
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

    public Remark getRemark() {
        return remark;
    }

    public Status getStatus() {
        return status;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Return a String representation of Tags without '[', ']' and ','
     */
    public String getTagsString() {
        return tags.toString().replace("[", "")
                .replace("]", "").replace(",", "");
    }

    /**
     * Returns an immutable position set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Position> getPositions() {
        return Collections.unmodifiableSet(positions);
    }

    /**
     * Return a String representation of Positions without '[', ']' and ','
     */
    public String getPositionsString() {
        return positions.toString().replace("[", "")
                .replace("]", "").replace(",", "");
    }

    public Set<Interview> getInterviews() {
        return Collections.unmodifiableSet(interviews);
    }

    public boolean appliedForPosition(Position p) {
        return positions.stream().anyMatch(p::isSamePosition);
    }

    public boolean hasInterview(Interview i) {
        return interviews.stream().anyMatch(i::isSameInterview);
    }

    public void addPosition(Position p) {
        positions.add(p);
    }

    public void deletePosition(Position p) {
        positions.remove(p);
    }

    /**
     * Adds an interview into a person's list of interviews, and since the person has the upcoming added interview,
     * this method always sets the status as scheduled.
     *
     * @param i interview object to be added to person
     */
    public void addInterview(Interview i) {
        interviews.add(i);
        if (status == Status.APPLIED) {
            status = Status.SCHEDULED;
        }
    }

    /**
     * Deletes an interview from a person's list of interviews. If the person does not have any upcoming interview
     * this method sets the status as applied.
     *
     * @param i
     */
    public void deleteInterview(Interview i) {
        interviews.remove(i);
        if (interviews.isEmpty()) {
            status = Status.APPLIED;
        }
    }

    /**
     * Checks if a person is vacant by checking against interviews from a person's list of interviews.
     * If the person has interviews with overlapping time, return false.
     *
     * @param toSchedule interview to be scheduled for person.
     */
    public boolean isVacantFor(Interview toSchedule) {
        for (Interview interview : interviews) {
            if (interview.hasOverLapWith(toSchedule)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null && otherPerson.getEmail().equals(getEmail());
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
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getRemark().equals(getRemark())
                && otherPerson.getStatus().equals(getStatus())
                && otherPerson.getPositions().equals(getPositions());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        // hashing with all fields does not work properly when loading from data
        //return Objects.hash(name, phone, email, address, tags, remark, status, positions);
        return Objects.hash(name, phone, email, address, tags, remark);
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
                .append("; Status: ")
                .append(getStatus());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        if (!remark.toString().equals("")) {
            builder.append("; Remark: ").append(remark);
        }

        Set<Position> positions = getPositions();
        if (!positions.isEmpty()) {
            builder.append("; Positions: ");
            positions.forEach(builder::append);
        }

        Set<Interview> interviews = getInterviews();
        if (!interviews.isEmpty()) {
            builder.append("; Interviews: ");
            interviews.forEach(interview -> builder.append(interview.getDisplayString()));
        }

        return builder.toString();
    }

    public void setInterviews(Set<Interview> personInterviews) {
        interviews.addAll(personInterviews);
    }

    public void setPositions(Set<Position> personPositions) {
        positions = personPositions;
    }
}
