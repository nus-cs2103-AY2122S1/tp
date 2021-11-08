package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.Timestamp;
import seedu.address.model.member.Address;
import seedu.address.model.member.Credit;
import seedu.address.model.member.Email;
import seedu.address.model.member.Id;
import seedu.address.model.member.Member;
import seedu.address.model.member.Name;
import seedu.address.model.member.Phone;
import seedu.address.model.member.Point;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Member objects.
 */
public class MemberBuilder {

    public static final String DEFAULT_ID = "00101";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_TIMESTAMP = "1610236800000";
    public static final String DEFAULT_CREDIT = "200";
    public static final String DEFAULT_POINT = "200";

    private Id id;
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Timestamp timestamp;
    private Credit credit;
    private Point point;
    private Set<Tag> tags;
    private List<Transaction> transactions;
    private List<Reservation> reservations;

    /**
     * Creates a {@code MemberBuilder} with the default details.
     */
    public MemberBuilder() {
        id = new Id(DEFAULT_ID);
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        timestamp = new Timestamp(DEFAULT_TIMESTAMP);
        credit = new Credit(DEFAULT_CREDIT);
        point = new Point(DEFAULT_POINT);
        tags = new HashSet<>();
        transactions = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    /**
     * Initializes the MemberBuilder with the data of {@code memberToCopy}.
     */
    public MemberBuilder(Member memberToCopy) {
        id = memberToCopy.getId();
        name = memberToCopy.getName();
        phone = memberToCopy.getPhone();
        email = memberToCopy.getEmail();
        address = memberToCopy.getAddress();
        timestamp = memberToCopy.getTimestamp();
        credit = memberToCopy.getCredit();
        point = memberToCopy.getPoint();
        tags = new HashSet<>(memberToCopy.getTags());
        transactions = new ArrayList<>(memberToCopy.getTransactions());
        reservations = new ArrayList<>(memberToCopy.getReservations());
    }

    /**
     * Sets the {@code Id} of the {@code Member} that we are building.
     */
    public MemberBuilder withId(String id) {
        this.id = new Id(id);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Member} that we are building.
     */
    public MemberBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Member} that we are building.
     */
    public MemberBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Member} that we are building.
     */
    public MemberBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Member} that we are building.
     */
    public MemberBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Timestamp} of the {@code Member} that we are building.
     */
    public MemberBuilder withTimestamp(String timestamp) {
        this.timestamp = new Timestamp(timestamp);
        return this;
    }

    /**
     * Sets the {@code Credit} of the {@code Member} that we are building.
     */
    public MemberBuilder withCredit(String credit) {
        this.credit = new Credit(credit);
        return this;
    }

    /**
     * Sets the {@code Point} of the {@code Member} that we are building.
     */
    public MemberBuilder withPoint(String point) {
        this.point = new Point(point);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Member} that we are building.
     */
    public MemberBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code transactions} into a {@code List<Transaction>} and set it to the {@code Member}
     * that we are building.
     */
    public MemberBuilder withTransactions(Transaction ... transactions) {
        this.transactions = SampleDataUtil.getTransactionList(transactions);
        return this;
    }

    /**
     * Parses the {@code reservations} into a {@code List<Reservation>} and set it to the {@code Member}
     * that we are building.
     */
    public MemberBuilder withReservations(Reservation ... reservations) {
        this.reservations = SampleDataUtil.getReservationList(reservations);
        return this;
    }

    public Member build() {
        return new Member(id, name, phone, email, address, timestamp, credit, point, transactions, reservations, tags);
    }
}
