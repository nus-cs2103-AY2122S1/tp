package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.particpant.Participant;

/**
 * Json friendly {@link Event}
 */
public class JsonAdaptedEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event's %s field is missing!";

    private final String name;
    private String isDone;
    private String date;
    private String time;
    private final List<JsonAdaptedParticipant> participants = new ArrayList<>();
    //Change to JsonAdaptedParticipants

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given Event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("name") String name, @JsonProperty("isDone") String isDone,
                             @JsonProperty("date") String date, @JsonProperty("time") String time,
                             @JsonProperty("participants") List<JsonAdaptedParticipant> participants) {
        this.name = name;
        this.isDone = isDone;
        this.date = date;
        this.time = time;
        if (participants != null) {
            this.participants.addAll(participants);
        }
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        name = source.getName().eventName;
        date = source.getDate().toString();
        time = source.getTime().toString();
        isDone = source.getIsDone() ? "Completed" : "Uncompleted";
        participants.addAll(source.getParticipants().stream()
                .map(JsonAdaptedParticipant::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Json-friendly adapted person object into the model's {@code Event} object.
     *
     * @return an Event instance representing the JsonAdaptedEvent.
     * @throws IllegalValueException if there were aby data constraints violated in the adapted event.
     */
    public Event toModelType() throws IllegalValueException {
        final List<Participant> participants = new ArrayList<>();
        for (JsonAdaptedParticipant p : this.participants) {
            participants.add(p.toModelType());
        }

        if (this.name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventName.class.getSimpleName()));
        }
        final EventName eventName = new EventName(this.name);

        // Implementation might need improvements
        if (!EventDate.isValidDate(this.date)) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventDate.class.getSimpleName()));
        }
        final EventDate eventDate = new EventDate(this.date);

        EventTime eventTime;
        if (EventTime.isValidTime(this.time)) {
            eventTime = new EventTime(this.time);
        } else if (this.time.equals("")) {
            eventTime = new EventTime(); //No time given
        } else {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventTime.class.getSimpleName()));
        }
        Event event = new Event(eventName, eventDate, eventTime);
        if (this.isDone.equals("Completed")) {
            event.markAsDone();
        }
        return event;
    }
}
