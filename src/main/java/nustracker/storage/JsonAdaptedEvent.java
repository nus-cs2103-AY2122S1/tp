package nustracker.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import nustracker.commons.exceptions.IllegalValueException;
import nustracker.model.event.Event;
import nustracker.model.event.EventDate;
import nustracker.model.event.EventName;
import nustracker.model.event.EventTime;
import nustracker.model.event.Participant;

/**
 * Jackson-friendly version of {@link Event}.
 */
public class JsonAdaptedEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event's %s field is missing!";

    private final String name;
    private final String date;
    private final String time;
    private final List<JsonAdaptedParticipant> participants = new ArrayList<>();
    private final List<JsonAdaptedParticipant> blacklist = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("name") String name,
                            @JsonProperty("date") String date,
                            @JsonProperty("time") String time,
                            @JsonProperty("participants") List<JsonAdaptedParticipant> participants,
                            @JsonProperty("BlackList") List<JsonAdaptedParticipant> blacklist) {
        this.name = name;
        this.date = date;
        this.time = time;
        if (participants != null) {
            this.participants.addAll(participants);
        }
        if (blacklist != null) {
            this.blacklist.addAll(blacklist);
        }
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        name = source.getName().getEventName();
        date = source.getDate().getEventDate();
        time = source.getTime().getEventTime();
        participants.addAll(source.getParticipants().stream()
                .map(JsonAdaptedParticipant::new)
                .collect(Collectors.toList()));
        blacklist.addAll(source.getBlacklist().stream()
                .map(JsonAdaptedParticipant::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event.
     */
    public Event toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventName.class.getSimpleName()));
        }
        if (!EventName.isValidName(name)) {
            throw new IllegalValueException(EventName.MESSAGE_CONSTRAINTS);
        }
        final EventName modelName = new EventName(name);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventDate.class.getSimpleName()));
        }
        if (!EventDate.isValidDate(date)) {
            throw new IllegalValueException(EventDate.MESSAGE_CONSTRAINTS);
        }
        final EventDate modelDate = new EventDate(date);

        if (time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventTime.class.getSimpleName()));
        }
        if (!EventTime.isValidTime(time)) {
            throw new IllegalValueException(EventTime.MESSAGE_CONSTRAINTS);
        }
        final EventTime modelTime = new EventTime(time);

        final List<Participant> eventParticipants = new ArrayList<>();
        for (JsonAdaptedParticipant participant : participants) {
            eventParticipants.add(participant.toModelType());
        }
        final Set<Participant> modelParticipants = new HashSet<>(eventParticipants);

        final List<Participant> eventBlacklist = new ArrayList<>();
        for (JsonAdaptedParticipant blacklisted : blacklist) {
            eventBlacklist.add(blacklisted.toModelType());
        }
        final Set<Participant> modelBlacklist = new HashSet<>(eventBlacklist);

        return new Event(modelName, modelDate, modelTime, modelParticipants, modelBlacklist);
    }
}
