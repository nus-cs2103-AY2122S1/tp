package seedu.address.model.room;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ROOM_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ROOM_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VACANCY_ROOM_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VACANCY_ROOM_TWO;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRooms.ROOM_ONE;
import static seedu.address.testutil.TypicalRooms.ROOM_TWO;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.RoomBuilder;


public class RoomTest {


    @Test
    public void invalidRoomName() {
        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_ROOM_TWO + " ";
        assertThrows(IllegalArgumentException.class, () ->
            new RoomBuilder(ROOM_TWO).withNumber(nameWithTrailingSpaces).build());
    }

    // TODO determine if this test is necessary
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Room room = new RoomBuilder().build();
        //assertThrows(UnsupportedOperationException.class, () -> room.getGuests().remove(0));
    }

    @Test
    public void isSameRoom() {
        // same object -> returns true
        assertTrue(ROOM_ONE.isSameRoom(ROOM_ONE));

        // null -> returns false
        assertFalse(ROOM_ONE.isSameRoom(null));

        // same room number, all other attributes different -> returns true
        Room editedRoom = new RoomBuilder(ROOM_ONE).withNumber(VALID_NAME_ROOM_ONE)
                .withVacancy(VALID_VACANCY_ROOM_TWO).build();

        assertTrue(ROOM_ONE.isSameRoom(editedRoom));


        // different room number, all other attributes same -> returns false
        editedRoom = new RoomBuilder(ROOM_ONE).withNumber(VALID_NAME_ROOM_TWO)
                .withVacancy(VALID_VACANCY_ROOM_ONE).build();

        assertFalse(ROOM_ONE.isSameRoom(editedRoom));


        // name differs in case, all other attributes same.
        // but since name is number eg "001", case should be indifferent -> returns true
        Room editedRoomTwo = new RoomBuilder(ROOM_TWO).withNumber(VALID_NAME_ROOM_TWO.toLowerCase()).build();
        assertTrue(ROOM_TWO.isSameRoom(editedRoomTwo));
    }


    @Test
    public void equals() {
        // same values -> returns true
        Room roomOneCopy = new RoomBuilder(ROOM_ONE).build();
        assertTrue(ROOM_ONE.equals(roomOneCopy));

        // same object -> returns true
        assertTrue(ROOM_ONE.equals(ROOM_ONE));

        // null -> returns false
        assertFalse(ROOM_ONE.equals(null));

        // different type -> returns false
        assertFalse(ROOM_ONE.equals(5));

        // different room -> returns false
        assertFalse(ROOM_ONE.equals(ROOM_TWO));

        // different room number -> returns false
        Room editedRoomOne = new RoomBuilder(ROOM_ONE).withNumber(VALID_NAME_ROOM_TWO).build();
        assertFalse(ROOM_ONE.equals(editedRoomOne));

        // different vacancy -> returns false
        editedRoomOne = new RoomBuilder(ROOM_ONE).withVacancy(VALID_VACANCY_ROOM_TWO).build();
        assertFalse(ROOM_ONE.equals(editedRoomOne));
    }

    @Test
    public void hashCodeCheck() {
        // same object -> returns true
        assertTrue(ROOM_ONE.hashCode() == ROOM_ONE.hashCode());

        // same values -> returns true
        Room roomOneCopy = new RoomBuilder(ROOM_ONE).build();
        assertTrue(ROOM_ONE.hashCode() == roomOneCopy.hashCode());

        // different room number -> returns false
        Room editedRoomOne = new RoomBuilder(ROOM_ONE).withNumber(VALID_NAME_ROOM_TWO).build();
        assertFalse(ROOM_ONE.hashCode() == editedRoomOne.hashCode());

        // different vacancy -> returns false
        editedRoomOne = new RoomBuilder(ROOM_ONE).withVacancy(VALID_VACANCY_ROOM_TWO).build();
        assertFalse(ROOM_ONE.hashCode() == editedRoomOne.hashCode());
    }
}
