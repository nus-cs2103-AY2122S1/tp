package seedu.address.commons.core.listroomargs;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class ListRoomArg {
    public static final String MESSAGE_CONSTRAINTS = "List room extra arguments should be 'vacant' or 'occupied'.";
    private static final String VACANT = "vacant";
    private static final String OCCUPIED = "occupied";

    private String arg;

    /**
     * Constructs a ListRoomArgType object with the given argument.
     *
     * @param arg
     */
    public ListRoomArg(String arg) {
        requireNonNull(arg);
        checkArgument(isValidListType(arg), MESSAGE_CONSTRAINTS);
        this.arg = arg;
    }

    /**
     * Returns true if a given string is a valid additional argument to list room.
     */
    public static boolean isValidListType(String type) {
        return type.equals(VACANT) || type.equals(OCCUPIED);
    }

    /**
     * Returns true if the ListRoomArg is "vacant".
     */
    public boolean isVacant() {
        return this.arg.equals(VACANT);
    }

    /**
     * Returns true if the ListRoomArg is "occupied".
     */
    public boolean isOccupied() {
        return this.arg.equals(OCCUPIED);
    }

    /**
     * Returns true if both refer to ListRoomArg with the same argument.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListRoomArg // instanceof handles nulls
                && arg.equals(((ListRoomArg) other).arg)); // state check
    }
}
