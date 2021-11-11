package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RECORDS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ROOMS;

import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.commons.core.listtype.ListType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.room.Room;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS_GUESTS = "Listed all guests";

    public static final String MESSAGE_SUCCESS_ROOMS = "Listed all rooms";

    public static final String MESSAGE_SUCCESS_RECORDS = "Listed all records";

    public static final String MESSAGE_SUCCESS_ROOMS_TYPE = "Listed all rooms of indicated type";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists guests, rooms, or records, based on given arguments.\n"
            + "Parameters: LISTTYPE ('guests', 'rooms' or 'records'), "
            + "(optional) LISTROOMARG ('vacant' or 'occupied') (only for listing rooms).\n"
            + "Examples: " + COMMAND_WORD + " guests, "
            + COMMAND_WORD + " records, "
            + COMMAND_WORD + " rooms, "
            + COMMAND_WORD + " rooms occupied, "
            + COMMAND_WORD + " rooms vacant";

    private ListType listType;

    private Predicate<Room> predicate;

    public ListCommand(ListType listType) {
        this.listType = listType;
    }

    /**
     * Constructs a ListCommand object with the ListType and predicate.
     *
     * @param listType The type to be listed.
     * @param predicate The predicate to filter the objects to be listed by.
     */
    public ListCommand(ListType listType, Predicate<Room> predicate) {
        this.listType = listType;
        this.predicate = predicate;
    }

    /**
     * Returns true if the ListCommand lists guests.
     */
    public boolean isGuests() {
        return this.listType.isGuestsType();
    }

    /**
     * Returns true if the ListCommand lists rooms.
     */
    public boolean isRooms() {
        return this.listType.isRoomsType();
    }

    /**
     * Returns true if the ListCommand lists records.
     */
    public boolean isRecords() {
        return this.listType.isRecordsType();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (this.isGuests()) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(MESSAGE_SUCCESS_GUESTS);
        } else if (this.isRooms()) {
            if (this.predicate == null) {
                model.updateFilteredRoomList(PREDICATE_SHOW_ALL_ROOMS);
                return new CommandResult(MESSAGE_SUCCESS_ROOMS);
            } else {
                model.updateFilteredRoomList(predicate);
                return new CommandResult(MESSAGE_SUCCESS_ROOMS_TYPE);
            }
        } else if (this.isRecords()) {
            model.updateFilteredRecordList(PREDICATE_SHOW_ALL_RECORDS);
            return new CommandResult(MESSAGE_SUCCESS_RECORDS);
        } else {
            throw new CommandException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    /**
     * Returns true if the both are ListCommands with the same arguments.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof ListCommand) {
            ListCommand otherListCommand = (ListCommand) other;
            return listType.equals(otherListCommand.listType)
                    && Objects.equals(predicate, otherListCommand.predicate);
        }
        return false;
    }
}
