package seedu.address.model.room;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ROOM_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VACANCY_ROOM_ONE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRooms.ROOM_ONE;
import static seedu.address.testutil.TypicalRooms.ROOM_TWO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.room.exceptions.DuplicateRoomException;
import seedu.address.model.room.exceptions.RoomNotFoundException;
import seedu.address.testutil.RoomBuilder;

public class RoomListTest {

    private static final Room ROOM_ONE_EDITED = new RoomBuilder()
            .withNumber(VALID_NAME_ROOM_ONE)
            .withVacancy(VALID_VACANCY_ROOM_ONE)
            .build();

    private final RoomList roomList = new RoomList();

    @Test
    public void contains_nullRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roomList.contains(null));
    }

    @Test
    public void contains_roomNotInList_returnsFalse() {
        assertFalse(roomList.contains(ROOM_ONE));
    }

    @Test
    public void contains_roomInList_returnsTrue() {
        roomList.add(ROOM_ONE);
        assertTrue(roomList.contains(ROOM_ONE));
    }

    @Test
    public void contains_roomWithSameIdentityFieldsInList_returnsTrue() {
        roomList.add(ROOM_ONE);
        Room editedRoom = ROOM_ONE_EDITED;
        assertTrue(roomList.contains(editedRoom));
    }

    @Test
    public void add_nullRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roomList.add(null));
    }

    @Test
    public void add_duplicateRoom_throwsDuplicatePersonException() {
        roomList.add(ROOM_ONE);
        assertThrows(DuplicateRoomException.class, () -> roomList.add(ROOM_ONE));
    }

    @Test
    public void setRoom_nullTargetRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roomList.setRoom(null, ROOM_ONE));
    }

    @Test
    public void setRoom_nullEditedRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roomList.setRoom(ROOM_ONE, null));
    }

    @Test
    public void setRoom_targetRoomNotInList_throwsRoomNotFoundException() {
        assertThrows(RoomNotFoundException.class, () -> roomList.setRoom(ROOM_ONE, ROOM_ONE));
    }

    @Test
    public void setRoom_editedRoomIsSameRoom_success() {
        roomList.add(ROOM_ONE);
        roomList.setRoom(ROOM_ONE, ROOM_ONE);
        RoomList expectedRoomList = new RoomList();
        expectedRoomList.add(ROOM_ONE);
        assertEquals(expectedRoomList, roomList);
    }

    @Test
    public void setRoom_editedRoomHasSameIdentity_success() {
        roomList.add(ROOM_ONE);
        Room editedRoom = ROOM_ONE_EDITED;
        roomList.setRoom(ROOM_ONE, ROOM_ONE_EDITED);
        RoomList expectedRoomList = new RoomList();
        expectedRoomList.add(editedRoom);
        assertEquals(expectedRoomList, roomList);
    }

    @Test
    public void setRoom_editedRoomHasDifferentIdentity_success() {
        roomList.add(ROOM_ONE);
        roomList.setRoom(ROOM_ONE, ROOM_TWO);
        RoomList expectedRoomList = new RoomList();
        expectedRoomList.add(ROOM_TWO);
        assertEquals(expectedRoomList, roomList);
    }

    @Test
    public void setRoom_editedRoomHasNonUniqueIdentity_throwsDuplicateRoomException() {
        roomList.add(ROOM_ONE);
        roomList.add(ROOM_TWO);
        assertThrows(DuplicateRoomException.class, () -> roomList.setRoom(ROOM_ONE, ROOM_TWO));
    }

    @Test
    public void remove_nullRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roomList.remove(null));
    }

    @Test
    public void remove_roomDoesNotExist_throwsRoomNotFoundException() {
        assertThrows(RoomNotFoundException.class, () -> roomList.remove(ROOM_ONE));
    }

    @Test
    public void remove_existingRoom_removesRoom() {
        roomList.add(ROOM_ONE);
        roomList.remove(ROOM_ONE);
        RoomList expectedRoomList = new RoomList();
        assertEquals(expectedRoomList, roomList);
    }

    @Test
    public void setRooms_nullRoomList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roomList.setRooms((RoomList) null));
    }

    @Test
    public void setRooms_roomList_replacesOwnListWithProvidedRoomList() {
        roomList.add(ROOM_ONE);
        RoomList expectedRoomList = new RoomList();
        expectedRoomList.add(ROOM_TWO);
        roomList.setRooms(expectedRoomList);
        assertEquals(expectedRoomList, roomList);
    }

    @Test
    public void setRooms_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roomList.setRooms((List<Room>) null));
    }

    @Test
    public void setRooms_list_replacesOwnListWithProvidedList() {
        roomList.add(ROOM_ONE);
        List<Room> localRoomList = Collections.singletonList(ROOM_TWO);
        roomList.setRooms(localRoomList);
        RoomList expectedRoomList = new RoomList();
        expectedRoomList.add(ROOM_TWO);
        assertEquals(expectedRoomList, roomList);
    }

    @Test
    public void setRooms_listWithDuplicateRooms_throwsDuplicateRoomException() {
        List<Room> listWithDuplicateRooms = Arrays.asList(ROOM_ONE, ROOM_ONE);
        assertThrows(DuplicateRoomException.class, () -> roomList.setRooms(listWithDuplicateRooms));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> roomList.asUnmodifiableObservableList().remove(0));
    }
}
