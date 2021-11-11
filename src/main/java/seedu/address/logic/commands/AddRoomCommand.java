package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ROOMS;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomNumber;
import seedu.address.model.tag.Tag;

public class AddRoomCommand extends Command {

    public static final String COMMAND_WORD = "addroom";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the specified number of rooms with the specified type. "
            + "Maximum number of rooms allowed is 999.\n"
            + "Parameters: NUMBER_OF_ROOMS (must be a positive integer) "
            + PREFIX_TAG + "TAG\n"
            + "Example: " + COMMAND_WORD + " 50 "
            + PREFIX_TAG + "normal";

    public static final String MESSAGE_SUCCESS = "%1$s new rooms of type %2$s added.";
    public static final String MESSAGE_EXCEEDED_MAX_NUMBER_OF_ROOMS =
            "Adding %1$s more room(s) would exceed the maximum 999 rooms allowed.";
    public static final String MESSAGE_INVALID_INTEGER = "Invalid number of rooms entered.";

    private final int number;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Creates an AddRoomCommand to add the specified number of {@code Rooms} with the specified {@code Tags}
     *
     * @param number The number of rooms to be added.
     * @param tags The tags to be set for the rooms.
     */
    public AddRoomCommand(int number, Set<Tag> tags) {
        requireAllNonNull(number, tags);
        this.number = number;
        this.tags.addAll(tags);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredRoomList(PREDICATE_SHOW_ALL_ROOMS);
        int numRooms = model.getNumberOfRooms();
        if (this.number + numRooms > 999) {
            throw new CommandException(String.format(MESSAGE_EXCEEDED_MAX_NUMBER_OF_ROOMS, number));
        }

        int totalNumOfRooms = numRooms + number;
        for (int i = numRooms + 1; i <= totalNumOfRooms; i++) {
            String roomNumber = String.format("%03d", i);
            Room room = new Room(new RoomNumber(roomNumber), tags);
            model.addRoom(room);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, number, tags.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddRoomCommand // instanceof handles nulls
                && number == ((AddRoomCommand) other).number
                && tags.equals(((AddRoomCommand) other).tags));
    }

}
