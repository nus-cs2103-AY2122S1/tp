package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
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

/**
 * Jackson-friendly version of {@link Member}.
 */
class JsonAdaptedMember {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Member's %s field is missing!";

    private final String id;
    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String timestamp;
    private final String credit;
    private final String point;
    private final List<JsonAdaptedTransaction> transactions = new ArrayList<>();
    private final List<JsonAdaptedReservation> reservations = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedMember} with the given member details.
     */
    @JsonCreator
    public JsonAdaptedMember(@JsonProperty("id") String id, @JsonProperty("name") String name,
            @JsonProperty("phone") String phone, @JsonProperty("email") String email,
            @JsonProperty("address") String address,
            @JsonProperty("timestamp") String timestamp,
            @JsonProperty("credit") String credit,
            @JsonProperty("point") String point,
            @JsonProperty("transactions") List<JsonAdaptedTransaction> transactions,
            @JsonProperty("reservations") List<JsonAdaptedReservation> reservations,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {

        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.timestamp = timestamp;
        this.credit = credit;
        this.point = point;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (transactions != null) {
            this.transactions.addAll(transactions);
        }
        if (reservations != null) {
            this.reservations.addAll(reservations);
        }
    }

    /**
     * Converts a given {@code Member} into this class for Jackson use.
     */
    public JsonAdaptedMember(Member source) {
        id = source.getId().value;
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        timestamp = source.getTimestamp().value;
        credit = source.getCredit().value;
        point = source.getPoint().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        transactions.addAll(source.getTransactions().stream()
                .map(JsonAdaptedTransaction::new)
                .collect(Collectors.toList()));
        reservations.addAll(source.getReservations().stream()
                .map(JsonAdaptedReservation::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted member object into the model's {@code Member} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted member.
     */
    public Member toModelType() throws IllegalValueException {
        final List<Tag> memberTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            memberTags.add(tag.toModelType());
        }

        final List<Transaction> memberTransactions = new ArrayList<>();
        for (JsonAdaptedTransaction transaction : transactions) {
            memberTransactions.add(transaction.toModelType());
        }

        final List<Reservation> memberReservations = new ArrayList<>();
        for (JsonAdaptedReservation reservation : reservations) {
            memberReservations.add(reservation.toModelType());
        }

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName()));
        }
        if (!Id.isValidId(id)) {
            throw new IllegalValueException(Id.MESSAGE_CONSTRAINTS);
        }
        final Id modelId = new Id(id);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (timestamp == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Timestamp.class.getSimpleName()));
        }
        if (!Timestamp.isValidTimestamp(timestamp)) {
            throw new IllegalValueException(Timestamp.MESSAGE_CONSTRAINTS);
        }
        final Timestamp modelTimestamp = new Timestamp(timestamp);

        if (credit == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Credit.class.getSimpleName()));
        }
        if (!Credit.isValidCredit(credit)) {
            throw new IllegalValueException(Credit.MESSAGE_CONSTRAINTS);
        }
        final Credit modelCredit = new Credit(credit);

        if (point == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Point.class.getSimpleName()));
        }
        if (!Point.isValidPoint(point)) {
            throw new IllegalValueException(Point.MESSAGE_CONSTRAINTS);
        }
        final Point modelPoint = new Point(point);

        final Set<Tag> modelTags = new HashSet<>(memberTags);

        final List<Transaction> modelTransactions = new ArrayList<>(memberTransactions);

        final List<Reservation> modelReservations = new ArrayList<>(memberReservations);

        return new Member(modelId, modelName, modelPhone, modelEmail, modelAddress, modelTimestamp, modelCredit,
                modelPoint, modelTransactions, modelReservations, modelTags);
    }
}
