package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.residency.Residency;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomNumber;

/**
 * Jackson-friendly version of {@link Residency}.
 */
class JsonAdaptedResidency {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Residency's %s field is missing!";
    public static final String MESSAGE_DATETIME_CONSTRAINTS_FORMAT = "Residency's %s format is incorrect.";
    public static final String MESSAGE_CHECKOUT_BEFORE_CHECKIN =
            "Residency's checkout time is before its checkin time!";

    private final String roomNumber;
    private final String[] guestNrics;
    private final String checkInTime;
    private final String checkOutTime;

    /**
     * Constructs a {@code JsonAdaptedResidency} with the given residency details.
     */
    @JsonCreator
    public JsonAdaptedResidency(@JsonProperty("roomNumber") String roomNumber,
                                @JsonProperty("guestNRICs") String[] guestNrics,
                                @JsonProperty("checkInTime") String checkInTime,
                                @JsonProperty("checkOutTime") String checkOutTime) {
        this.roomNumber = roomNumber;
        this.guestNrics = guestNrics;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }

    /**
     * Converts a given {@code Residency} into this class for Jackson use.
     */
    public JsonAdaptedResidency(Residency source) {
        roomNumber = source.getRoom().getRoomNumber().value;

        List<String> nrics = source.getGuests()
                                  .stream()
                                  .map(person -> person.getNric().value)
                                  .collect(Collectors.toList());
        guestNrics = new String[nrics.size()];
        for (int i = 0; i < nrics.size(); i++) {
            guestNrics[i] = nrics.get(i);
        }

        checkInTime = source.getCheckInTime().toString();
        checkOutTime = source.getCheckOutTime().map(LocalDateTime::toString).orElseGet(() -> null);
    }

    /**
     * Converts this Jackson-friendly adapted residency object into the model's {@code Residency} object.
     */
    public Residency toModelType(Map<Nric, Person> nricPersonMap,
                                 Map<RoomNumber, Room> roomNumberRoomMap) throws IllegalValueException {
        // TODO: Check if any data constraints are violated
        Set<Person> guests = new HashSet<>();
        for (String nric : guestNrics) {
            Nric something = Nric.of(nric);
            Person x = nricPersonMap.get(something);
            guests.add(x);
        }

        Room room = roomNumberRoomMap.get(new RoomNumber(roomNumber));
        if (room == null) {
            throw new IllegalValueException(RoomNumber.MESSAGE_CONSTRAINTS);
        }

        if (checkInTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "check in time"));
        }
        LocalDateTime checkIn;
        try {
            checkIn = LocalDateTime.parse(checkInTime);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(String.format(MESSAGE_DATETIME_CONSTRAINTS_FORMAT, "check in time"));
        }

        // TODO: Check if check out time is before check in time
        LocalDateTime checkOut;
        try {
            checkOut = checkOutTime != null ? LocalDateTime.parse(checkOutTime) : null;
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(String.format(MESSAGE_DATETIME_CONSTRAINTS_FORMAT, "check out time"));
        }

        if (checkOut != null && checkOut.isBefore(checkIn)) {
            throw new IllegalValueException(MESSAGE_CHECKOUT_BEFORE_CHECKIN);
        }

        return new Residency(room, guests, checkIn, checkOut);
    }
}
