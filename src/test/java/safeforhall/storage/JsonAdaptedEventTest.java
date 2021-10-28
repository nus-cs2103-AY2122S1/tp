package safeforhall.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static safeforhall.storage.JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT;
import static safeforhall.testutil.Assert.assertThrows;
import static safeforhall.testutil.TypicalEvents.BAND;

import org.junit.jupiter.api.Test;

import safeforhall.commons.exceptions.IllegalValueException;
import safeforhall.model.event.Capacity;
import safeforhall.model.event.EventDate;
import safeforhall.model.event.EventName;
import safeforhall.model.event.EventTime;
import safeforhall.model.event.ResidentList;
import safeforhall.model.event.Venue;


public class JsonAdaptedEventTest {
    private static final String INVALID_EVENTNAME = " ";
    private static final String INVALID_EVENTDATE = "231-31-20322";
    private static final String INVALID_EVENTTIME = "03";
    private static final String INVALID_VENUE = " ";
    private static final String INVALID_CAPACITY = "capacity";
    private static final String INVALID_RESIDENTS = "fakename1, ABCD324948";

    private static final String VALID_EVENTNAME = BAND.getEventName().eventName;
    private static final String VALID_EVENTDATE = BAND.getEventDate().eventDate;
    private static final String VALID_EVENTTIME = BAND.getEventTime().eventTime;
    private static final String VALID_VENUE = BAND.getVenue().venue;
    private static final String VALID_CAPACITY = BAND.getCapacity().inputCapacity;
    private static final String VALID_RESIDENTS = BAND.getResidentList().getResidentsStorage();

    @Test
    public void toModelType_validEventDetails_returnsEvent() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(BAND);
        assertEquals(BAND, event.toModelType());
    }

    @Test
    public void toModelType_invalidEventName_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(INVALID_EVENTNAME, VALID_EVENTDATE, VALID_EVENTTIME,
                        VALID_VENUE, VALID_CAPACITY, VALID_RESIDENTS);
        String expectedMessage = EventName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullEventName_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(null, VALID_EVENTDATE, VALID_EVENTTIME,
                VALID_VENUE, VALID_CAPACITY, VALID_RESIDENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidEventDate_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_EVENTNAME, INVALID_EVENTDATE, VALID_EVENTTIME,
                        VALID_VENUE, VALID_CAPACITY, VALID_RESIDENTS);
        String expectedMessage = EventDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullEventDate_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_EVENTNAME,
                null, VALID_EVENTTIME, VALID_VENUE, VALID_CAPACITY, VALID_RESIDENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidEventTime_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_EVENTNAME, VALID_EVENTDATE, INVALID_EVENTTIME,
                        VALID_VENUE, VALID_CAPACITY, VALID_RESIDENTS);
        String expectedMessage = EventTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullEventTime_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_EVENTNAME,
                VALID_EVENTDATE, null, VALID_VENUE, VALID_CAPACITY, VALID_RESIDENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidVenue_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_EVENTNAME, VALID_EVENTDATE,
                        VALID_EVENTTIME, INVALID_VENUE, VALID_CAPACITY, VALID_RESIDENTS);
        String expectedMessage = Venue.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullVenue_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_EVENTNAME, VALID_EVENTDATE, VALID_EVENTTIME,
                null, VALID_CAPACITY, VALID_RESIDENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Venue.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidCapacity_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_EVENTNAME, VALID_EVENTDATE, VALID_EVENTTIME,
                        VALID_VENUE, INVALID_CAPACITY, VALID_RESIDENTS);
        String expectedMessage = Capacity.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullCapacity_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_EVENTNAME, VALID_EVENTDATE, VALID_EVENTTIME,
                VALID_VENUE, null, VALID_RESIDENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Capacity.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidResidents_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_EVENTNAME, VALID_EVENTDATE, VALID_EVENTTIME,
                        VALID_VENUE, VALID_CAPACITY, INVALID_RESIDENTS);
        String expectedMessage = ResidentList.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullResidents_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_EVENTNAME, VALID_EVENTDATE, VALID_EVENTTIME,
                VALID_VENUE, VALID_CAPACITY, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ResidentList.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

}
