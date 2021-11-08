package seedu.address.model.member;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.Timestamp;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;

/**
 * Represents a Member in the ezFoodie.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Member {

    // Identity fields
    private final Id id;
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Timestamp timestamp;
    private final Credit credit;
    private final Point point;
    private final Set<Tag> tags = new HashSet<>();

    private final List<Transaction> transactions = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();


    /**
     * Constructs {@code Member} with follow parameters.
     * Every field must be present and not null.
     *
     * @param id the member id
     * @param name the member name
     * @param phone the member phone
     * @param email the member email
     * @param address the member address
     * @param timestamp the member timestamp
     * @param credit the member credits
     * @param point the member point
     * @param transactions the member transactions
     * @param reservations the member reservations
     * @param tags the member tag
     */

    public Member(Id id, Name name, Phone phone, Email email, Address address,
                  Timestamp timestamp, Credit credit, Point point, List<Transaction> transactions,
                  List<Reservation> reservations, Set<Tag> tags) {
        requireAllNonNull(id, name, phone, email, address, timestamp, credit, point, transactions, reservations, tags);
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.timestamp = timestamp;
        this.credit = credit;
        this.point = point;
        this.tags.addAll(tags);
        this.transactions.addAll(transactions);
        this.reservations.addAll(reservations);
    }

    /**
     * Gets Id.
     *
     * @return Id the valid member id.
     */
    public Id getId() {
        return id;
    }

    /**
     * Gets name.
     *
     * @return Name the valid member name.
     */
    public Name getName() {
        return name;
    }

    /**
     * Gets phone.
     *
     * @return Phone the valid member phone.
     */
    public Phone getPhone() {
        return phone;
    }

    /**
     * Gets email.
     *
     * @return Email the valid member email.
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Gets address.
     *
     * @return Address the valid member address.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Gets timestamp.
     *
     * @return Timestamp the valid member timestamp.
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * Gets credit.
     *
     * @return Credit the valid member credit.
     */
    public Credit getCredit() {
        return credit;
    }

    /**
     * Gets point.
     *
     * @return Point the valid member point.
     */
    public Point getPoint() {
        return this.point;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return Set a series of tags.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable transaction set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    /**
     * Returns an immutable transaction list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Reservation> getReservations() {
        return Collections.unmodifiableList(reservations);
    }

    /**
     * Returns true if both members have the same id, phone or email.
     * This defines a weaker notion of equality between two members.
     */
    public boolean isSameMember(Member otherMember) {
        if (otherMember == this) {
            return true;
        }

        return otherMember != null
                && (isSameId(otherMember) || isSameEmail(otherMember) || isSamePhone(otherMember));
    }

    /**
     * Returns true if both members have the same id.
     * This defines a weaker notion of equality between two members.
     */
    public boolean isSameId(Member otherMember) {
        if (otherMember == this) {
            return true;
        }

        return otherMember != null
                && otherMember.getId().equals(getId());
    }

    /**
     * Returns true if both members have the same phone.
     * This defines a weaker notion of equality between two members.
     */
    public boolean isSamePhone(Member otherMember) {
        if (otherMember == this) {
            return true;
        }

        return otherMember != null
                && otherMember.getPhone().equals(getPhone());
    }

    /**
     * Returns true if both members have the same email.
     * This defines a weaker notion of equality between two members.
     */
    public boolean isSameEmail(Member otherMember) {
        if (otherMember == this) {
            return true;
        }

        return otherMember != null
                && otherMember.getEmail().equals(getEmail());
    }

    /**
     * Returns true if both members have the same identity and data fields.
     * This defines a stronger notion of equality between two members.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Member)) {
            return false;
        }

        Member otherMember = (Member) other;
        return otherMember.getId().equals(getId())
                && otherMember.getName().equals(getName())
                && otherMember.getPhone().equals(getPhone())
                && otherMember.getEmail().equals(getEmail())
                && otherMember.getAddress().equals(getAddress())
                && otherMember.getTimestamp().equals(getTimestamp())
                && otherMember.getCredit().equals(getCredit())
                && otherMember.getPoint().equals(getPoint())
                && otherMember.getTransactions().equals(getTransactions())
                && otherMember.getReservations().equals(getReservations())
                && otherMember.getTags().equals(getTags());
    }

    /**
     * Overrides the hashCode method.
     */
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(id, name, phone, email, address, timestamp, credit, point,
                transactions, reservations, tags);
    }

    /**
     * Overrides the toString method.
     *
     * @return String of including all id, name, phone, email, address
     * timestamp, credit, point, tag, transaction and reservation.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Id: ")
                .append(getId())
                .append("; Name: ")
                .append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Timestamp: ")
                .append(getTimestamp())
                .append("; Credit: ")
                .append(getCredit())
                .append("; Point: ")
                .append(getPoint());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        List<Transaction> transactions = getTransactions();
        if (!transactions.isEmpty()) {
            builder.append("; Transactions: ");
            transactions.forEach(builder::append);
        }

        List<Reservation> reservations = getReservations();
        if (!reservations.isEmpty()) {
            builder.append("; Reservations: ");
            reservations.forEach(builder::append);
        }

        return builder.toString();
    }
}
