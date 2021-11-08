package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.reservation.DateTime;
import seedu.address.model.reservation.Id;
import seedu.address.model.reservation.Remark;
import seedu.address.model.reservation.Reservation;

/**
 * Jackson-friendly version of {@link Reservation}.
 */
class JsonAdaptedReservation {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Reservation's %s field is missing!";

    private final String id;
    private final String dateTime;
    private final String remark;

    /**
     * Constructs a {@code JsonAdaptedReservation} with the given reservation details.
     */
    @JsonCreator
    public JsonAdaptedReservation(@JsonProperty("id") String id, @JsonProperty("dateTime") String dateTime,
            @JsonProperty("remark") String remark) {
        this.id = id;
        this.dateTime = dateTime;
        this.remark = remark;
    }

    /**
     * Constructs a given {@code Reservation} into this class for Jackson use.
     */
    public JsonAdaptedReservation(Reservation source) {
        id = source.getId().value;
        dateTime = source.getDateTime().value;
        remark = source.getRemark().value;
    }

    /**
     * Converts this Jackson-friendly adapted Reservation object into the model's {@code Reservation} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted reservation.
     */
    public Reservation toModelType() throws IllegalValueException {
        if (id == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName()));
        }
        final Id modelId = new Id(id);

        if (dateTime == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, DateTime.class.getSimpleName()));
        }
        final DateTime modelDateTime = new DateTime(dateTime);

        if (remark == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        final Remark modelRemark = new Remark(remark);

        return new Reservation(modelId, modelDateTime, modelRemark);
    }

}
