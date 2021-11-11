package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.residency.ReadOnlyResidencyBook;
import seedu.address.model.residency.Residency;
import seedu.address.model.residency.ResidencyBook;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomNumber;

/**
 * Jackson-friendly version of {@link ResidencyBook}.
 */
public class JsonAdaptedResidencyBook {

    public static final String MESSAGE_INVALID_CHECKOUT_TIME = "Residency has invalid checkout time";

    private final List<JsonAdaptedResidency> residencies = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedResidencyBook} with the given residencies.
     */
    @JsonCreator
    public JsonAdaptedResidencyBook(@JsonProperty("residencies") List<JsonAdaptedResidency> residencies) {
        this.residencies.addAll(residencies);
    }

    /**
     * Converts a given {@code ResidencyBook} into this class for Jackson use.
     */
    public JsonAdaptedResidencyBook(ReadOnlyResidencyBook residencyBook) {
        this.residencies.addAll(residencyBook.asUnmodifiableObservableList()
                                             .stream()
                                             .map(JsonAdaptedResidency::new)
                                             .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted residency book object into the model's {@code ResidencyBook} object.
     */
    public ResidencyBook toModelType(List<Person> persons,
                                     List<Room> rooms,
                                     boolean allowDuplicates) throws IllegalValueException {

        ResidencyBook residencyBook = new ResidencyBook(allowDuplicates);

        // TODO throw correct error for duplicate residency (check for bugs)
        Map<Nric, Person> nricPersonMap = new HashMap<>();
        Map<RoomNumber, Room> roomNumberRoomMap = new HashMap<>();

        for (Person person : persons) {
            nricPersonMap.put(person.getNric(), person);
        }

        for (Room room : rooms) {
            roomNumberRoomMap.put(room.getRoomNumber(), room);
        }

        for (JsonAdaptedResidency jsonAdaptedResidency : residencies) {
            Residency residency = jsonAdaptedResidency.toModelType(nricPersonMap, roomNumberRoomMap);
            // If residency is checked in and this is the records book,
            // or is checked out and this is the residency book
            if (residency.isCheckedIn() == allowDuplicates) {
                throw new IllegalValueException(MESSAGE_INVALID_CHECKOUT_TIME);
            }
            residencyBook.register(residency);
        }

        return residencyBook;
    }

}
