package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ROOM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ROOM;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.residency.Residency;
import seedu.address.model.room.Room;
import seedu.address.model.room.Vacancy;

public class CheckOutCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validRoomIndex_success() {
        CheckOutCommand checkOutCommand = new CheckOutCommand(INDEX_FIRST_ROOM);
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        Room originalRoom = model.getFilteredRoomList().get(INDEX_FIRST_ROOM.getZeroBased());

        Set<Person> guests = new HashSet<>();
        guests.add(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()));
        Room roomToEdit = new Room(originalRoom.getRoomNumber(), Vacancy.OCCUPIED, originalRoom.getTags());

        model.setRoom(originalRoom, roomToEdit);
        model.register(roomToEdit, guests);
        Optional<Residency> toBeRecorded = model.getResidency(roomToEdit);

        // Checking out a room should return it to its default state
        Room editedRoom = originalRoom;

        String expectedMessage = String.format(CheckOutCommand.MESSAGE_CHECKOUT_SUCCESS, editedRoom);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.record(toBeRecorded.get());

        assertCommandSuccess(checkOutCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidRoomIndex_throwsCommandException() {
        Index invalidRoomIndex = Index.fromZeroBased(model.getFilteredRoomList().size());
        CheckOutCommand checkOutCommand = new CheckOutCommand(invalidRoomIndex);

        assertCommandFailure(checkOutCommand, model, Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);
    }

    @Test
    public void execute_vacantRoom_throwsCommandException() {
        CheckOutCommand checkOutCommand = new CheckOutCommand(INDEX_FIRST_ROOM);

        assertCommandFailure(checkOutCommand, model, CheckOutCommand.MESSAGE_ROOM_IS_VACANT);
    }

    @Test
    public void equals() {
        CheckOutCommand checkOutRoomOneCommand = new CheckOutCommand(INDEX_FIRST_ROOM);
        CheckOutCommand checkOutRoomTwoCommand = new CheckOutCommand(INDEX_SECOND_ROOM);

        // same object -> returns true
        assertTrue(checkOutRoomOneCommand.equals(checkOutRoomOneCommand));

        // same values -> returns true
        CheckOutCommand checkOutRoomOneCommandCopy = new CheckOutCommand(INDEX_FIRST_ROOM);
        assertTrue(checkOutRoomOneCommand.equals(checkOutRoomOneCommandCopy));

        // different types -> returns false
        assertFalse(checkOutRoomOneCommand.equals(1));

        // null -> returns false
        assertFalse(checkOutRoomOneCommand.equals(null));

        // different CheckInCommand -> returns false
        assertFalse(checkOutRoomOneCommand.equals(checkOutRoomTwoCommand));
    }
}
