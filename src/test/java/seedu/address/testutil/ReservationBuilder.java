package seedu.address.testutil;

import seedu.address.model.reservation.DateTime;
import seedu.address.model.reservation.Id;
import seedu.address.model.reservation.Remark;
import seedu.address.model.reservation.Reservation;

/**
 * A utility class to help with building Reservation objects.
 */
public class ReservationBuilder {

    public static final String DEFAULT_ID = "000001";
    public static final String DEFAULT_DATE_TIME = "2099-12-31 10:00";
    public static final String DEFAULT_REMARK = "3 people";

    private Id id;
    private DateTime dateTime;
    private Remark remark;

    /**
     * Creates a {@code ReservationBuilder} with the default details.
     */
    public ReservationBuilder() {
        id = new Id(DEFAULT_ID);
        dateTime = new DateTime(DEFAULT_DATE_TIME);
        remark = new Remark(DEFAULT_REMARK);
    }

    /**
     * Initializes the ReservationBuilder with the data of {@code reservationToCopy}.
     */
    public ReservationBuilder(Reservation reservationToCopy) {
        id = reservationToCopy.getId();
        dateTime = reservationToCopy.getDateTime();
        remark = reservationToCopy.getRemark();
    }

    /**
     * Sets the {@code ReservationId} of the {@code Reservation} that we are building.
     */
    public ReservationBuilder withId(String id) {
        this.id = new Id(id);
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code Reservation} that we are building.
     */
    public ReservationBuilder withDateTime(String dateTime) {
        this.dateTime = new DateTime(dateTime);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Reservation} that we are building.
     */
    public ReservationBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    public Reservation build() {
        return new Reservation(id, dateTime, remark);
    }

}
