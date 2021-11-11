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
import seedu.address.model.person.Person;
import seedu.address.model.residency.Residency;
import seedu.address.model.residency.ResidencyBook;
import seedu.address.model.room.Room;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    public static final String MESSAGE_DUPLICATE_ROOM = "Rooms list contains duplicate room(s).";

    public static final String MESSAGE_ROOM_ORDER_ERROR = "Rooms list contains room numbers "
            + "which are not in consecutive order.";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    private final List<JsonAdaptedRoom> rooms = new ArrayList<>();

    private final JsonAdaptedResidencyBook residencyBook;


    private final JsonAdaptedResidencyBook recordsBook;

    //private final int idCounter;

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons, rooms, residency book and id counter.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("rooms") List<JsonAdaptedRoom> rooms,
                                       @JsonProperty("residencyBook") JsonAdaptedResidencyBook residencyBook,
                                       @JsonProperty("recordsBook") JsonAdaptedResidencyBook recordsBook,
                                       @JsonProperty("id counter") int idCounter) {
        this.persons.addAll(persons);
        this.rooms.addAll(rooms);
        this.residencyBook = residencyBook;
        this.recordsBook = recordsBook;
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        rooms.addAll(source.getRoomList().stream().map(JsonAdaptedRoom::new).collect(Collectors.toList()));
        residencyBook = new JsonAdaptedResidencyBook(source.getResidencyBook());
        recordsBook = new JsonAdaptedResidencyBook(source.getRecordsBook());
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();

        addPersonsAndRooms(addressBook);
        addResidencies(addressBook);

        if (!addressBook.isValid()) {
            throw new IllegalValueException(AddressBook.MESSAGE_INVALID_ADDRESS_BOOK);
        }

        return addressBook;
    }

    private void addPersonsAndRooms(AddressBook addressBook) throws IllegalValueException {
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i).toModelType();
            if (addressBook.hasRoom(room)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ROOM);
            }
            if (i < rooms.size() - 1) {
                Room nextRoom = rooms.get(i + 1).toModelType();
                int roomNum = Integer.parseInt(room.toString());
                int nextRoomNum = Integer.parseInt(nextRoom.toString());
                if (nextRoomNum - roomNum != 1) {
                    throw new IllegalValueException(MESSAGE_ROOM_ORDER_ERROR);
                }
            }
            addressBook.addRoom(room);
        }
    }

    private void addResidencies(AddressBook addressBook) throws IllegalValueException {
        ResidencyBook tempResidencyBook =
                residencyBook.toModelType(addressBook.getPersonList(), addressBook.getRoomList(), false);
        ResidencyBook tempRecords =
                recordsBook.toModelType(addressBook.getPersonList(), addressBook.getRoomList(), true);
        for (Residency residency : tempResidencyBook.asUnmodifiableObservableList()) {
            addressBook.register(residency);
        }
        for (Residency residency: tempRecords.asUnmodifiableObservableList()) {
            addressBook.record(residency);
        }
    }
}
