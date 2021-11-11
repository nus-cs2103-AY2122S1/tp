package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.room.Room;
import seedu.address.model.room.Vacancy;

/**
 * Checks a group of persons into a room
 */
public class CheckInCommand extends Command {
    public static final String COMMAND_WORD = "checkin";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Checks in guests to a room "
            + "using their index numbers used in the displayed guest list.\n"
            + "Parameters: INDEX_ROOM (must be a positive integer) "
            + "g/ [GUEST_INDEX] (can be used multiple times)\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "g/ 43 g/ 22";

    public static final String MESSAGE_CHECKIN_SUCCESS = "Room Checked In: %1$s";
    public static final String MESSAGE_NO_GUESTS = "At least one person must be checked into the room.";
    public static final String MESSAGE_ROOM_IS_OCCUPIED = "Room is currently occupied.";
    public static final String MESSAGE_PERSON_ALREADY_CHECKED_IN =
            "One or more guests have already been checked into another room.";
    public static final String MESSAGE_PERSON_INVALID_INDEX = "Invalid guest number for one or more guests.";
    public static final String MESSAGE_ROOM_INVALID_INDEX = "Invalid room number.";

    private final Index roomIndex;
    private final List<Index> guestIndexes;

    /**
     * @param roomIndex The index of the room to be checked into
     * @param guestIndexes A list of the indexes of guests to check into the room
     */
    public CheckInCommand(Index roomIndex, List<Index> guestIndexes) {
        this.roomIndex = roomIndex;
        this.guestIndexes = guestIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();
        List<Room> lastShownRoomList = model.getFilteredRoomList();

        if (roomIndex.getZeroBased() >= lastShownRoomList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);
        }
        for (Index guestIndex : guestIndexes) {
            if (guestIndex.getZeroBased() >= lastShownPersonList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
        }

        Room roomToEdit = lastShownRoomList.get(roomIndex.getZeroBased());
        if (model.getResidency(roomToEdit).isPresent()) {
            throw new CommandException(MESSAGE_ROOM_IS_OCCUPIED);
        }

        Set<Person> guests = new HashSet<>();
        for (Index guestIndex : guestIndexes) {
            Person guestToAdd = lastShownPersonList.get(guestIndex.getZeroBased());

            if (model.getResidency(guestToAdd).isPresent()) {
                throw new CommandException(
                        String.format(MESSAGE_PERSON_ALREADY_CHECKED_IN, guestIndex.getOneBased()));
            }

            guests.add(guestToAdd);
        }
        if (guests.isEmpty()) {
            throw new CommandException(MESSAGE_NO_GUESTS);
        }

        Room editedRoom = new Room(roomToEdit.getRoomNumber(), Vacancy.OCCUPIED, roomToEdit.getTags());

        model.setRoom(roomToEdit, editedRoom);
        model.register(editedRoom, guests);
        model.updateFilteredRoomList(Model.PREDICATE_SHOW_ALL_ROOMS);
        model.updateFilteredRecordList(Model.PREDICATE_SHOW_ALL_RECORDS);
        return new CommandResult(String.format(MESSAGE_CHECKIN_SUCCESS, editedRoom));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CheckInCommand)) {
            return false;
        }

        // state check
        CheckInCommand c = (CheckInCommand) other;

        return roomIndex.equals(c.roomIndex)
                && guestIndexes.equals(c.guestIndexes);
    }

}
