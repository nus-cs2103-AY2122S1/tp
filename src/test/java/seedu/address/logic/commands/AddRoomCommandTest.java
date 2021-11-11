package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.util.SampleDataUtil.getTagSet;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.residency.Residency;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomNumber;
import seedu.address.model.tag.Tag;

public class AddRoomCommandTest {

    @Test
    public void constructor_nullTags_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddRoomCommand(1, null));
    }

    @Test
    public void execute_roomAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingRoomsAdded modelStub = new ModelStubAcceptingRoomsAdded();

        Set<Tag> tags = getTagSet("a");
        CommandResult commandResult = new AddRoomCommand(10,
                tags).execute(modelStub);

        assertEquals(String.format(AddRoomCommand.MESSAGE_SUCCESS, 10, tags),
                commandResult.getFeedbackToUser());
        assertTrue(modelStub.hasRoom(new Room(new RoomNumber("005"), tags)));
        assertEquals(modelStub.getNumberOfRooms(), 10);

        modelStub = new ModelStubAcceptingRoomsAdded();
        commandResult = new AddRoomCommand(3, tags).execute(modelStub);
        assertEquals(String.format(AddRoomCommand.MESSAGE_SUCCESS, 3, tags),
                commandResult.getFeedbackToUser());
        assertEquals(modelStub.roomsAdded, Arrays.asList(
                new Room(new RoomNumber("001"), tags),
                new Room(new RoomNumber("002"), tags),
                new Room(new RoomNumber("003"), tags)));
        assertEquals(modelStub.getNumberOfRooms(), 3);
    }

    @Test
    public void exceedingMaxAllowedRooms_throwsCommandException() {
        Set<Tag> tags = getTagSet("normal");
        AddRoomCommand addRoomCommand1 = new AddRoomCommand(1, tags);
        AddRoomCommand addRoomCommand10 = new AddRoomCommand(10, tags);
        ModelStub modelStub1 = new ModelStubWithMaxRooms();

        assertThrows(CommandException.class, String.format(AddRoomCommand.MESSAGE_EXCEEDED_MAX_NUMBER_OF_ROOMS, 1), ()
            -> addRoomCommand1.execute(modelStub1));
        assertThrows(CommandException.class, String.format(AddRoomCommand.MESSAGE_EXCEEDED_MAX_NUMBER_OF_ROOMS, 10), ()
            -> addRoomCommand10.execute(modelStub1));
    }

    @Test
    public void addingNumberOfRooms_cornerCases() throws Exception {
        Set<Tag> tags = getTagSet("normal");
        ModelStub modelStub = new ModelStubWith990Rooms();
        CommandResult commandResult = new AddRoomCommand(9, tags).execute(modelStub);

        // successfully added 9 rooms
        assertEquals(String.format(AddRoomCommand.MESSAGE_SUCCESS, 9, tags),
                commandResult.getFeedbackToUser());

        ModelStub modelStub2 = new ModelStubWith990Rooms();
        // command exception thrown when adding 10 rooms
        AddRoomCommand addRoomCommand10 = new AddRoomCommand(10, tags);
        assertThrows(CommandException.class, String.format(AddRoomCommand.MESSAGE_EXCEEDED_MAX_NUMBER_OF_ROOMS, 10), ()
            -> addRoomCommand10.execute(modelStub2));
    }


    @Test
    public void equals() {
        Set<Tag> tags = getTagSet("normal");
        Set<Tag> tags1 = getTagSet("aaaaa");
        AddRoomCommand addOneNormalRoomCommand = new AddRoomCommand(1, tags);
        AddRoomCommand addThreeNormalRoomsCommand = new AddRoomCommand(3, tags);
        AddRoomCommand addOneAaaaaRoomCommand = new AddRoomCommand(1, tags1);

        // same object -> returns true
        assertTrue(addOneNormalRoomCommand.equals(addOneNormalRoomCommand));

        // same values -> returns true
        AddRoomCommand addOneNormalRoomCommandCopy = new AddRoomCommand(1, tags);
        assertTrue(addOneNormalRoomCommand.equals(addOneNormalRoomCommandCopy));

        // different types -> returns false
        assertFalse(addOneNormalRoomCommand.equals(1));

        // null -> returns false
        assertFalse(addOneNormalRoomCommand.equals(null));

        // different person -> returns false
        assertFalse(addOneNormalRoomCommand.equals(addThreeNormalRoomsCommand));

        // different tags -> returns false
        assertFalse(addOneNormalRoomCommand.equals(addOneAaaaaRoomCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addRoom(Room room) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasRoom(Room room) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRoom(Room target, Room editedRoom) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void register(Room room, Set<Person> guests) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void record(Residency residency) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeResidency(Residency residency) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Residency> getResidency(Room room) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Residency> getResidency(Person guest) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Room> getFilteredRoomList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Integer getNumberOfRooms() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Residency> getFilteredRecordList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredRoomList(Predicate<Room> predicate) {
            requireNonNull(predicate);
        }

        @Override
        public void updateFilteredRecordList(Predicate<Residency> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that already contains the maximum number of rooms.
     */
    private class ModelStubWithMaxRooms extends ModelStub {
        @Override
        public Integer getNumberOfRooms() {
            return 999;
        }

    }

    /**
     * A Model stub that already contains 990 rooms.
     */
    private class ModelStubWith990Rooms extends ModelStub {
        final ArrayList<Room> roomsAdded = new ArrayList<>();

        @Override
        public Integer getNumberOfRooms() {
            return 990;
        }

        @Override
        public void addRoom(Room room) {
            requireNonNull(room);
            roomsAdded.add(room);
        }

    }

    /**
     * A Model stub that always accepts the rooms being added.
     */
    private class ModelStubAcceptingRoomsAdded extends ModelStub {
        final ArrayList<Room> roomsAdded = new ArrayList<>();

        @Override
        public Integer getNumberOfRooms() {
            return roomsAdded.size();
        }

        @Override
        public boolean hasRoom(Room room) {
            requireNonNull(room);
            return roomsAdded.stream().anyMatch(room::isSameRoom);
        }

        @Override
        public void addRoom(Room room) {
            requireNonNull(room);
            roomsAdded.add(room);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
