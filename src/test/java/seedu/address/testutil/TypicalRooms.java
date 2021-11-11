package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ROOM_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ROOM_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VACANCY_ROOM_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VACANCY_ROOM_TWO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.room.Room;

public class TypicalRooms {
    public static final Room ROOM_ONE = new RoomBuilder().withNumber(VALID_NAME_ROOM_ONE)
            .withVacancy(VALID_VACANCY_ROOM_ONE).build();

    public static final Room ROOM_TWO = new RoomBuilder().withNumber(VALID_NAME_ROOM_TWO)
            .withVacancy(VALID_VACANCY_ROOM_TWO).build();

    private TypicalRooms() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical rooms.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Room room : getTypicalRooms()) {
            ab.addRoom(room);
        }
        return ab;
    }

    public static List<Room> getTypicalRooms() {
        return new ArrayList<>(Arrays.asList(ROOM_ONE, ROOM_TWO));
    }
}
