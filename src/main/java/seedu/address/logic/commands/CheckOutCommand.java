package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.residency.Residency;
import seedu.address.model.room.Room;

/**
 * Checks out all the persons in a room
 */
public class CheckOutCommand extends Command {
    public static final String COMMAND_WORD = "checkout";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Checks out all the guests in a room "
            + "using it's index number in the displayed list (not room number).\n"
            + "Parameters: INDEX_ROOM (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CHECKOUT_SUCCESS = "Room Checked Out: %1$s";
    public static final String MESSAGE_ROOM_IS_VACANT = "Room is already vacant.";

    private final Index roomIndex;

    /**
     * Creates a CheckOutCommand to check out the {@code Room} at the specified {@code Index}.
     */
    public CheckOutCommand(Index roomIndex) {
        this.roomIndex = roomIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Room> lastShownRoomList = model.getFilteredRoomList();

        if (roomIndex.getZeroBased() >= lastShownRoomList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);
        }

        Room roomToEdit = lastShownRoomList.get(roomIndex.getZeroBased());
        Optional<Residency> residency = model.getResidency(roomToEdit);
        if (residency.isEmpty()) {
            throw new CommandException(MESSAGE_ROOM_IS_VACANT);
        }

        //Resets room to default (vacant, no guests)
        Room editedRoom = new Room(roomToEdit.getRoomNumber(), roomToEdit.getTags());
        model.setRoom(roomToEdit, editedRoom);
        residency.ifPresent(model::record);
        residency.ifPresent(model::removeResidency);
        residency.ifPresent(Residency::checkOut);
        model.updateFilteredRoomList(Model.PREDICATE_SHOW_ALL_ROOMS);
        return new CommandResult(String.format(MESSAGE_CHECKOUT_SUCCESS, editedRoom));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CheckOutCommand // instanceof handles nulls
                && roomIndex.equals(((CheckOutCommand) other).roomIndex)); // state check
    }
}
