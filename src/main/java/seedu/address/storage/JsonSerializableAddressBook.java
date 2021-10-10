package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Event;
import seedu.address.model.participant.Participant;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PARTICIPANT = "Participants list contains duplicate participant(s).";
    //Add on for Managera
    public static final String MESSAGE_DUPLICATE_EVENT = "Events list contains duplicate event(s).";

    private final List<JsonAdaptedParticipant> participants = new ArrayList<>();
    //Add on for Managera
    private final List<JsonAdaptedEvent> events = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given participants and events.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("participants") List<JsonAdaptedParticipant> participants,
                                       @JsonProperty("events") List<JsonAdaptedEvent> events) {
        this.participants.addAll(participants);
        //Add on for Managera
        this.events.addAll(events);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        participants.addAll(source.getParticipantList().stream()
                .map(JsonAdaptedParticipant::new).collect(Collectors.toList()));
        //Add on for Managera
        events.addAll(source.getEventList().stream().map(JsonAdaptedEvent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        //Add on for Managera
        for (JsonAdaptedEvent jsonAdaptedEvent : events) {
            Event event = jsonAdaptedEvent.toModelType(participants);
            if (addressBook.hasEvent(event)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EVENT);
            }
            addressBook.addEvent(event);
        }

        List<Event> eventList = addressBook.getEventList();

        // Changed for Managera
        for (JsonAdaptedParticipant jsonAdaptedParticipant : participants) {
            Participant participant = jsonAdaptedParticipant.toModelType();
            if (addressBook.hasParticipant(participant)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PARTICIPANT);
            }

            eventList.stream().filter(e -> e.hasParticipant(participant)).forEach(participant::addEvent);
            addressBook.addParticipant(participant);
        }

        return addressBook;
    }

}
