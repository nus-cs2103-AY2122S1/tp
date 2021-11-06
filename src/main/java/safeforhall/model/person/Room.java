package safeforhall.model.person;

import static java.util.Objects.requireNonNull;
import static safeforhall.commons.util.AppUtil.checkArgument;

import safeforhall.logic.parser.CliSyntax;

/**
 * Represents a Person's room in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRoom(String)}
 */
public class Room implements Comparable<Room> {

    public static final String MESSAGE_CONSTRAINTS = "Rooms should not contain spaces and be of the format AXXX: \n"
            + "Character 1: The block, A-E\n"
            + "Character 2: The level, 1-4\n"
            + "Character 3: The room, 0-2\n"
            + "Character 4: The room, 0-9\n";

    public static final String MESSAGE_CONSTRAINTS_FOR_FIND = "Rooms can be searched by block, level, block-level: \n"
            + "Example: " + CliSyntax.PREFIX_ROOM + "A , "
            + CliSyntax.PREFIX_ROOM + "1 , "
            + CliSyntax.PREFIX_ROOM + "A1 , "
            + CliSyntax.PREFIX_ROOM + "A100 ";

    /*
     * The room must be 4 characters
     * The first character of the room must be A-E to represent the block
     * The second character of the room must be 1-4 to represent the level
     * The third character of the room must be 0-2
     * The fourth character of the room must be 0-9
     * This check assumes 5 blocks, 4 levels and 30 rooms a level
     */
    public static final String VALIDATION_REGEX = "^[a-eA-E][1-4][0-2][0-9]$";

    public static final String VALIDATION_FOR_FIND_REGEX = "[a-eA-E]|[1-4]|[a-eA-E][1-4]";

    public static final String DESC = "Room: ";
    public static final String FIELD = "r";


    public final String room;

    /**
     * Constructs a {@code Room}.
     *
     * @param room A valid room.
     */
    public Room(String room) {
        requireNonNull(room);
        checkArgument(isValidRoom(room), MESSAGE_CONSTRAINTS);
        // Converts the first character to upper case
        this.room = room.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid room.
     */
    public static boolean isValidRoom(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid block, level, level-block or room.
     */
    public static boolean isValidRoomForFind(String test) {
        return test.matches(VALIDATION_FOR_FIND_REGEX) || isValidRoom(test);
    }

    @Override
    public String toString() {
        return room;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Room // instanceof handles nulls
                && room.equalsIgnoreCase(((Room) other).room)); // state check
    }

    @Override
    public int hashCode() {
        return room.hashCode();
    }

    @Override
    public int compareTo(Room r) {
        return this.room.compareToIgnoreCase(r.room);
    }
}

