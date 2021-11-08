package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Name;
import seedu.address.model.module.event.Event;
import seedu.address.model.module.event.EventDate;
import seedu.address.model.module.member.Member;

/**
 * Jackson-friendly version of {@link Event}.
 */
public class JsonAdaptedEvent {
    public static final String MISSING_FIELD_EVENT_FORMAT = "Event's %s field is missing!";

    private final String name;
    private final String date;
    private final List<JsonAdaptedMember> participants;
    private final List<Boolean> attendanceList;

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("name") String name, @JsonProperty("date") String date,
                            @JsonProperty("participants") List<JsonAdaptedMember> participants,
                            @JsonProperty("attendanceList") List<Boolean> attendanceList) {
        this.name = name;
        this.date = date;
        this.participants = participants;
        this.attendanceList = attendanceList;
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        name = source.getName().fullName;
        date = source.getDate().toString();
        participants = new ArrayList<>();
        attendanceList = new ArrayList<>();
        for (Member m : source.getParticipants()) {
            participants.add(new JsonAdaptedMember(m));
            attendanceList.add(source.hasAttended(m));
        }
    }

    /**
     * Converts this Jackson-friendly adapted member object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event.
     */

    public Event toModelType() throws IllegalValueException {
        final Map<Member, Boolean> map = new HashMap<>();
        for (int i = 0; i < participants.size(); i++) {
            map.put(participants.get(i).toModelType(), attendanceList.get(i));
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_EVENT_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelEventName = new Name(name);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_EVENT_FORMAT, "date"));
        }
        if (!EventDate.isValidEventDate(date)) {
            throw new IllegalValueException(EventDate.MESSAGE_CONSTRAINTS);
        }
        final EventDate modelEventDate = new EventDate(date);

        return new Event(modelEventName, modelEventDate, map);
    }

}
