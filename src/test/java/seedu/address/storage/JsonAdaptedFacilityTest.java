package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedMember.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFacilities.FIELD;
import static seedu.address.testutil.TypicalFacilities.KENT_RIDGE_SPORT_HALL_5_COURT_1;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.facility.Capacity;
import seedu.address.model.facility.FacilityName;
import seedu.address.model.facility.Location;
import seedu.address.model.facility.Time;

public class JsonAdaptedFacilityTest {
    private static final String INVALID_FACILITY_NAME = "Court 1!";
    private static final String INVALID_LOCATION = "University Town Sports Hall @";
    private static final String INVALID_TIME = "200";
    private static final String INVALID_CAPACITY = "5a";

    private static final String VALID_FACILITY_NAME = KENT_RIDGE_SPORT_HALL_5_COURT_1.getName().toString();
    private static final String VALID_LOCATION = KENT_RIDGE_SPORT_HALL_5_COURT_1.getLocation().toString();
    private static final String VALID_TIME = KENT_RIDGE_SPORT_HALL_5_COURT_1.getTime().time;
    private static final String VALID_CAPACITY = KENT_RIDGE_SPORT_HALL_5_COURT_1.getCapacity().toString();
    private static final Map<DayOfWeek, List<JsonAdaptedMember>> EMPTY_ALLOCATION_MAP = new EnumMap<>(
            Map.ofEntries(Map.entry(DayOfWeek.MONDAY, new ArrayList<>()),
                    Map.entry(DayOfWeek.TUESDAY, new ArrayList<>()),
                    Map.entry(DayOfWeek.WEDNESDAY, new ArrayList<>()),
                    Map.entry(DayOfWeek.THURSDAY, new ArrayList<>()),
                    Map.entry(DayOfWeek.FRIDAY, new ArrayList<>()),
                    Map.entry(DayOfWeek.SATURDAY, new ArrayList<>()),
                    Map.entry(DayOfWeek.SUNDAY, new ArrayList<>())));

    @Test
    public void toModelType_validFacilityDetails_returnsFacility() throws Exception {
        JsonAdaptedFacility facility = new JsonAdaptedFacility(FIELD);
        assertEquals(FIELD, facility.toModelType());
    }

    @Test
    public void toModelType_invalidFacilityName_throwsIllegalValueException() {
        JsonAdaptedFacility facility =
                new JsonAdaptedFacility(INVALID_FACILITY_NAME, VALID_LOCATION, VALID_TIME,
                        VALID_CAPACITY, EMPTY_ALLOCATION_MAP);
        String expectedMessage = FacilityName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, facility::toModelType);
    }

    @Test
    public void toModelType_nullFacilityName_throwsIllegalValueException() {
        JsonAdaptedFacility facility = new JsonAdaptedFacility(null, VALID_LOCATION, VALID_TIME,
                VALID_CAPACITY, EMPTY_ALLOCATION_MAP);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, FacilityName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, facility::toModelType);
    }

    @Test
    public void toModelType_invalidLocation_throwsIllegalValueException() {
        JsonAdaptedFacility facility =
                new JsonAdaptedFacility(VALID_FACILITY_NAME, INVALID_LOCATION, VALID_TIME,
                        VALID_CAPACITY, EMPTY_ALLOCATION_MAP);
        String expectedMessage = Location.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, facility::toModelType);
    }

    @Test
    public void toModelType_nullLocation_throwsIllegalValueException() {
        JsonAdaptedFacility facility = new JsonAdaptedFacility(VALID_FACILITY_NAME, null,
                VALID_TIME, VALID_CAPACITY, EMPTY_ALLOCATION_MAP);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, facility::toModelType);
    }

    @Test
    public void toModelType_invalidTime_throwsIllegalValueException() {
        JsonAdaptedFacility facility =
                new JsonAdaptedFacility(VALID_FACILITY_NAME, VALID_LOCATION, INVALID_TIME,
                        VALID_CAPACITY, EMPTY_ALLOCATION_MAP);
        String expectedMessage = Time.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, facility::toModelType);
    }

    @Test
    public void toModelType_nullTime_throwsIllegalValueException() {
        JsonAdaptedFacility facility = new JsonAdaptedFacility(VALID_FACILITY_NAME, VALID_LOCATION,
                null, VALID_CAPACITY, EMPTY_ALLOCATION_MAP);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, facility::toModelType);
    }

    @Test
    public void toModelType_invalidCapacity_throwsIllegalValueException() {
        JsonAdaptedFacility facility =
                new JsonAdaptedFacility(VALID_FACILITY_NAME, VALID_LOCATION, VALID_TIME,
                        INVALID_CAPACITY, EMPTY_ALLOCATION_MAP);
        String expectedMessage = Capacity.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, facility::toModelType);
    }

    @Test
    public void toModelType_nullCapacity_throwsIllegalValueException() {
        JsonAdaptedFacility facility = new JsonAdaptedFacility(VALID_FACILITY_NAME, VALID_LOCATION,
                VALID_TIME, null, EMPTY_ALLOCATION_MAP);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Capacity.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, facility::toModelType);
    }
}
