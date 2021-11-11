package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomNumber;
import seedu.address.model.room.Vacancy;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Room}.
 */
class JsonAdaptedRoom {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    public final String roomNumber;
    private final boolean isVacant;
    private final Set<JsonAdaptedTag> tags = new HashSet<>();

    /**
     * Constructs a {@code JsonAdaptedRoom} with the given room details.
     */
    @JsonCreator
    public JsonAdaptedRoom(@JsonProperty("roomNumber") String roomNumber, @JsonProperty("isVacant") boolean isVacant,
                           @JsonProperty("tags") Set<JsonAdaptedTag> tags) {
        this.roomNumber = roomNumber;
        this.isVacant = isVacant;
        this.tags.addAll(tags);
    }

    /**
     * Converts a given {@code Room} into this class for Jackson use.
     */
    public JsonAdaptedRoom(Room source) {
        roomNumber = source.getRoomNumber().value;
        isVacant = source.getVacancy().isVacant();
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted room object into the model's {@code Room} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted room.
     */
    public Room toModelType() throws IllegalValueException {
        final List<Tag> roomTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            roomTags.add(tag.toModelType());
        }

        if (roomNumber == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, RoomNumber.class.getSimpleName()));
        }
        if (!RoomNumber.isValidRoomNumber(roomNumber)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final RoomNumber modelRoomNumber = new RoomNumber(roomNumber);
        final Vacancy modelVacancy = Vacancy.of(isVacant);
        final Set<Tag> modelTags = new HashSet<>(roomTags);

        return new Room(modelRoomNumber, modelVacancy, modelTags);
    }
}
