package safeforhall.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import safeforhall.commons.exceptions.IllegalValueException;
import safeforhall.model.event.Capacity;
import safeforhall.model.event.Event;
import safeforhall.model.event.EventDate;
import safeforhall.model.event.EventName;
import safeforhall.model.event.EventTime;
import safeforhall.model.event.ResidentList;
import safeforhall.model.event.Venue;

/**
 * Jackson-friendly version of {@link Event}.
 */
class JsonAdaptedEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event's %s field is missing!";

    private final String eventName;
    private final String eventDate;
    private final String eventTime;
    private final String venue;
    private final String capacity;
    private final String residents;

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("eventName") String eventName,
                             @JsonProperty("eventDate") String eventDate,
                             @JsonProperty("eventTime") String eventTime,
                             @JsonProperty("venue") String venue,
                             @JsonProperty("capacity") String capacity,
                            @JsonProperty("residents") String residents) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.venue = venue;
        this.capacity = capacity;
        this.residents = residents;
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        eventName = source.getEventName().eventName;
        eventDate = source.getEventDate().eventDate;
        eventTime = source.getEventTime().eventTime;
        venue = source.getVenue().venue;
        capacity = source.getCapacity().inputCapacity;
        residents = source.getResidentList().getResidentsStorage();
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event.
     */
    public Event toModelType() throws IllegalValueException {
        // EventName
        if (eventName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventName.class.getSimpleName()));
        }
        if (!EventName.isValidEventName(eventName)) {
            throw new IllegalValueException(EventName.MESSAGE_CONSTRAINTS);
        }
        final EventName modelEventName = new EventName(eventName);

        // EventDate

        if (eventDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventDate.class.getSimpleName()));
        }
        if (!EventDate.isValidEventDate(eventDate)) {
            throw new IllegalValueException(EventDate.MESSAGE_CONSTRAINTS);
        }
        final EventDate modelEventDate = new EventDate(eventDate);

        // EventTime

        if (eventTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventTime.class.getSimpleName()));
        }
        if (!EventTime.isValidEventTime(eventTime)) {
            throw new IllegalValueException(EventTime.MESSAGE_CONSTRAINTS);
        }
        final EventTime modelEventTime = new EventTime(eventTime);

        // Venue

        if (venue == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Venue.class.getSimpleName()));
        }
        if (!Venue.isValidVenue(venue)) {
            throw new IllegalValueException(Venue.MESSAGE_CONSTRAINTS);
        }
        final Venue modelVenue = new Venue(venue);

        // Capacity

        if (capacity == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Capacity.class.getSimpleName()));
        }
        if (!Capacity.isValidCapacity(capacity)) {
            throw new IllegalValueException(Capacity.MESSAGE_CONSTRAINTS);
        }
        final Capacity modelCapacity = new Capacity(capacity);

        // Residents
        if (residents == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ResidentList.class.getSimpleName()));
        }

        String[] persons = residents.split("\\s*,\\s*");
        StringBuilder stringBuilder = new StringBuilder("");
        int count = 0;
        for (String person : persons) {
            String[] information = person.split("\\s*;\\s*");
            if (count == 0) {
                stringBuilder.append(information[0]);
            } else {
                stringBuilder.append(", ").append(information[0]);
            }
            count++;
        }
        if (!ResidentList.isValidResidentStorage(residents)) {
            throw new IllegalValueException(ResidentList.MESSAGE_CONSTRAINTS);
        }
        final ResidentList modelResidentList = new ResidentList(stringBuilder.toString(), residents);

        return new Event(modelEventName, modelEventDate, modelEventTime,
                modelVenue, modelCapacity, modelResidentList);
    }

}
