package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Phone;
import seedu.address.model.reservation.Remark;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.table.Table;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link seedu.address.model.reservation.Reservation}
 */
public class JsonAdaptedReservation {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Reservation's %s field is missing";
    public static final String NUMBER_OF_PEOPLE_CONSTRAINT = "Number of people should be a non-zero unsigned integer";
    public static final String DATE_TIME_CONSTRAINT = "Wrong date time format";
    public static final String TIME_ON_THE_HOUR_CONSTRAINT = "Minutes of time is not 00";
    public static final String TABLE_ID_CONSTRAINT = "Table ID should be a non-zero unsigned integer";

    private final String phone;
    private final Integer numberOfPeople;
    private final String time;
    private final Integer tableId;
    private final String remark;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs {@code JsonAdaptedReservation with the given values}
     */
    @JsonCreator
    public JsonAdaptedReservation(
            @JsonProperty("phone") String phone, @JsonProperty("numberOfPeople") int numberOfPeople,
            @JsonProperty("time") String time, @JsonProperty("tableId") int tableId,
            @JsonProperty("remark") String remark, @JsonProperty("tagged") List<JsonAdaptedTag> tagged
    ) {
        this.phone = phone;
        this.numberOfPeople = numberOfPeople;
        this.time = time;
        this.tableId = tableId;
        this.remark = remark;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Reservation} into this class for Jackson use
     */
    public JsonAdaptedReservation(Reservation source) {
        phone = source.getPhone().value;
        numberOfPeople = source.getNumberOfPeople();
        time = source.getDateTime().toString();
        tableId = source.getTableId();
        remark = source.getRemark().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted reservation object into the model's {@code Reservation} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted reservation.
     */
    public Reservation toModelType() throws IllegalValueException {
        final List<Tag> reservationTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            reservationTags.add(tag.toModelType());
        }

        if (phone == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName())
            );
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (numberOfPeople == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "number of people")
            );
        }
        if (numberOfPeople <= 0) {
            throw new IllegalValueException(NUMBER_OF_PEOPLE_CONSTRAINT);
        }

        if (time == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "date time")
            );
        }
        try {
            LocalDateTime.parse(time);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(DATE_TIME_CONSTRAINT);
        }
        LocalDateTime modelTime = LocalDateTime.parse(time);

        if (modelTime.getMinute() != 0) {
            throw new IllegalValueException(TIME_ON_THE_HOUR_CONSTRAINT);
        }

        if (tableId == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "table")
            );
        }
        if (tableId <= 0) {
            throw new IllegalValueException(TABLE_ID_CONSTRAINT);
        }
        final Table modelTable = new Table(numberOfPeople, tableId);

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        final Remark modelRemark = new Remark(remark);

        final Set<Tag> modelTags = new HashSet<>(reservationTags);

        return new Reservation(modelPhone, numberOfPeople, modelTime, modelTable, modelRemark, modelTags);
    }
}
