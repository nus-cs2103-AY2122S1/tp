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
import seedu.address.model.participant.Participant;
import seedu.address.model.participant.ParticipantId;

/**
 * Json friendly {@link Event}
 */
public class JsonAdaptedEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event's %s field is missing!";

    private final String name;
    private String isDone;
    private String date;
    private String time;
    private final List<String> participantIds = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given Event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("name") String name, @JsonProperty("isDone") String isDone,
                             @JsonProperty("date") String date, @JsonProperty("time") String time,
                             @JsonProperty("participants") List<String> participantIds) {
        this.name = name;
        this.isDone = isDone;
        this.date = date;
        this.time = time;
        if (participantIds != null) {
            this.participantIds.addAll(participantIds);
        }
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        name = source.getNameString();
        date = source.getDateString();
        time = source.getTimeString();
        isDone = source.getIsDone() ? Event.COMPLETED : Event.UNCOMPLETED;
        participantIds.addAll(source.getParticipants().stream()
                .map(Participant::getParticipantId).map(ParticipantId::toString)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Json-friendly adapted person object into the model's {@code Event} object.
     *
     * @return an Event instance representing the JsonAdaptedEvent.
     * @throws IllegalValueException if there were any data constraints violated in the adapted event.
     */
    public Event toModelType(List<Participant> allParticipants) throws IllegalValueException {

        if (this.name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventName.class.getSimpleName()));
        }
        if (!EventName.isValidEventName(this.name)) {
            throw new IllegalValueException(EventName.MESSAGE_CONSTRAINTS);
        }
        final EventName eventName = new EventName(this.name);

        // Implementation might need improvements
        if (this.date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventDate.class.getSimpleName()));
        }
        if (!EventDate.isValidDate(this.date)) {
            throw new IllegalValueException(EventDate.MESSAGE_CONSTRAINTS);
        }
        final EventDate eventDate = new EventDate(this.date);

        EventTime eventTime;
        if (this.time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventTime.class.getSimpleName()));
        }
        if (EventTime.isValidTime(this.time)) {
            eventTime = new EventTime(this.time);
        } else if (this.time.equals("")) {
            eventTime = new EventTime(); //No time given
        } else {
            throw new IllegalValueException(EventTime.MESSAGE_CONSTRAINTS);
        }

        boolean isDone = this.isDone.equals(Event.COMPLETED);

        final Event eventModel = new Event(eventName, eventDate, eventTime, isDone, new ArrayList<>());

        // TODO: Optimise querying by using different data structures and algorithm in future updates
        for (String participantId : participantIds) {
            allParticipants.stream().filter(p -> p.getIdValue().equals(participantId))
                    .findFirst().ifPresent(eventModel::addParticipant);
        }
        return eventModel;
    }
}
