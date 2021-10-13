package safeforhall.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import safeforhall.commons.exceptions.IllegalValueException;
import safeforhall.model.event.Event;
import safeforhall.model.event.Capacity;
import safeforhall.model.event.Venue;
import safeforhall.model.event.EventDate;
import safeforhall.model.event.EventName;

/**
 * Jackson-friendly version of {@link Event}.
 */
class JsonAdaptedEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String eventName;
    private final String eventDate;
    private final String venue;
    private final String capacity;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("eventName") String eventName,
                             @JsonProperty("eventDate") String eventDate,
                             @JsonProperty("venue") String venue,
                             @JsonProperty("capacity") String capacity) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.venue = venue;
        this.capacity = capacity;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        eventName = source.getEventName().eventName;
        eventDate = source.getEventDate().eventDate;
        venue = source.getVenue().venue;
        capacity = source.getCapacity().capacity;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Event toModelType() throws IllegalValueException {
        // EventName
        if (eventName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, EventName.class.getSimpleName()));
        }
        if (!EventName.isValidEventName(eventName)) {
            throw new IllegalValueException(EventName.MESSAGE_CONSTRAINTS);
        }
        final EventName modelEventName = new EventName(eventName);

        // EventDate

        if (eventDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, EventDate.class.getSimpleName()));
        }
        if (!EventDate.isValidEventDate(eventDate)) {
            throw new IllegalValueException(EventDate.MESSAGE_CONSTRAINTS);
        }
        final EventDate modelEventDate = new EventDate(eventDate);

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
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Capacity.class.getSimpleName()));
        }
        if (!Capacity.isValidCapacity(capacity)) {
            throw new IllegalValueException(EventName.MESSAGE_CONSTRAINTS);
        }
        final Capacity modelCapacity = new Capacity(capacity);

        return new Event(modelEventName, modelEventDate, modelVenue, modelCapacity);
    }

}
