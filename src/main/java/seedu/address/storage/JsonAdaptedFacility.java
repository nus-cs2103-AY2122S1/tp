package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.facility.Capacity;
import seedu.address.model.facility.Facility;
import seedu.address.model.facility.FacilityName;
import seedu.address.model.facility.Location;
import seedu.address.model.facility.Time;

/**
 * Jackson-friendly version of {@link Facility}.
 */
public class JsonAdaptedFacility {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String location;
    private final String time;
    private final String capacity;

    /**
     * Constructs a {@code JsonAdaptedFacility} with the given facility details.
     */
    @JsonCreator
    public JsonAdaptedFacility(@JsonProperty("name") String name, @JsonProperty("location") String location,
                             @JsonProperty("time") String time, @JsonProperty("capacity") String capacity) {
        this.name = name;
        this.location = location;
        this.time = time;
        this.capacity = capacity;
    }

    /**
     * Converts a given {@code Facility} into this class for Jackson use.
     */
    public JsonAdaptedFacility(Facility source) {
        name = source.getName().facilityName;
        location = source.getLocation().location;
        time = source.getTime().time;
        capacity = source.getCapacity().capacity;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Facility toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    FacilityName.class.getSimpleName()));
        }
        if (!FacilityName.isValidFacilityName(name)) {
            throw new IllegalValueException(FacilityName.MESSAGE_CONSTRAINTS);
        }

        final FacilityName modelFacilityName = new FacilityName(name);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }
        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }

        final Location modelLocation = new Location(location);

        if (time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Time.class.getSimpleName()));
        }
        if (!Time.isValidTime(time)) {
            throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
        }

        final Time modelTime = new Time(time);

        if (capacity == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Capacity.class.getSimpleName()));
        }
        if (!Capacity.isValidCapacity(capacity)) {
            throw new IllegalValueException(Capacity.MESSAGE_CONSTRAINTS);
        }

        final Capacity modelCapacity = new Capacity(capacity);

        return new Facility(modelFacilityName, modelLocation, modelTime, modelCapacity);
    }

}
