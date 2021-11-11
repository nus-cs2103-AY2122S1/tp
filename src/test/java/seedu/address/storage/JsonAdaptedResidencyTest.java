package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedResidency.MESSAGE_DATETIME_CONSTRAINTS_FORMAT;
import static seedu.address.storage.JsonAdaptedResidency.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalRooms.ROOM_ONE;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.residency.Residency;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomNumber;

public class JsonAdaptedResidencyTest {

    @Test
    public void toModelType_validResidency_returnsResidency() throws Exception {
        Set<Person> guests = new HashSet<>();
        guests.add(ALICE);
        guests.add(BENSON);

        Residency residency = new Residency(ROOM_ONE, guests);
        JsonAdaptedResidency jsonResidency = new JsonAdaptedResidency(residency);

        Map<Nric, Person> idPersonMap = new HashMap<>();
        Map<RoomNumber, Room> roomNumberRoomMap = new HashMap<>();
        idPersonMap.put(ALICE.getNric(), ALICE);
        idPersonMap.put(BENSON.getNric(), BENSON);
        roomNumberRoomMap.put(ROOM_ONE.getRoomNumber(), ROOM_ONE);

        assertEquals(residency, jsonResidency.toModelType(idPersonMap, roomNumberRoomMap));

        residency.checkOut();
        jsonResidency = new JsonAdaptedResidency(residency);
        assertEquals(residency, jsonResidency.toModelType(idPersonMap, roomNumberRoomMap));
    }

    @Test
    public void toModelType_invalidRoom_throwsIllegalValueException() {
        Set<Person> guests = new HashSet<>();
        guests.add(ALICE);
        guests.add(BENSON);

        Residency residency = new Residency(ROOM_ONE, guests);
        JsonAdaptedResidency jsonResidency = new JsonAdaptedResidency(residency);
        Map<Nric, Person> idPersonMap = new HashMap<>();
        Map<RoomNumber, Room> roomNumberRoomMap = new HashMap<>();
        idPersonMap.put(ALICE.getNric(), ALICE);
        idPersonMap.put(BENSON.getNric(), BENSON);

        String expectedMessage = RoomNumber.MESSAGE_CONSTRAINTS;

        assertThrows(IllegalValueException.class, expectedMessage, ()
            -> jsonResidency.toModelType(idPersonMap, roomNumberRoomMap));
    }

    @Test
    public void toModelType_invalidCheckInTime_throwsIllegalValueException() {
        JsonAdaptedResidency jsonResidency =
                new JsonAdaptedResidency(ROOM_ONE.getRoomNumber().toString(), new String[0], "beedle", null);

        Map<Nric, Person> nricPersonMap = new HashMap<>();
        Map<RoomNumber, Room> roomNumberRoomMap = new HashMap<>();
        roomNumberRoomMap.put(ROOM_ONE.getRoomNumber(), ROOM_ONE);

        String expectedMessage = String.format(MESSAGE_DATETIME_CONSTRAINTS_FORMAT, "check in time");

        assertThrows(IllegalValueException.class, expectedMessage, ()
            -> jsonResidency.toModelType(nricPersonMap, roomNumberRoomMap));
    }

    @Test
    public void toModelType_nullCheckInTime_throwsIllegalValueException() {
        JsonAdaptedResidency jsonResidency =
                new JsonAdaptedResidency(ROOM_ONE.getRoomNumber().toString(), new String[0], null, null);

        Map<Nric, Person> nricPersonMap = new HashMap<>();
        Map<RoomNumber, Room> roomNumberRoomMap = new HashMap<>();
        roomNumberRoomMap.put(ROOM_ONE.getRoomNumber(), ROOM_ONE);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "check in time");

        assertThrows(IllegalValueException.class, expectedMessage, ()
            -> jsonResidency.toModelType(nricPersonMap, roomNumberRoomMap));
    }

    @Test
    public void toModelType_invalidCheckOutTime_throwsIllegalValueException() {
        JsonAdaptedResidency jsonResidency =
                new JsonAdaptedResidency(ROOM_ONE.getRoomNumber().toString(),
                        new String[0],
                        "2021-10-22T11:15:57.563274400",
                        "beedle");

        Map<Nric, Person> nricPersonMap = new HashMap<>();
        Map<RoomNumber, Room> roomNumberRoomMap = new HashMap<>();
        roomNumberRoomMap.put(ROOM_ONE.getRoomNumber(), ROOM_ONE);

        String expectedMessage = String.format(MESSAGE_DATETIME_CONSTRAINTS_FORMAT, "check out time");

        assertThrows(IllegalValueException.class, expectedMessage, ()
            -> jsonResidency.toModelType(nricPersonMap, roomNumberRoomMap));
    }
}
