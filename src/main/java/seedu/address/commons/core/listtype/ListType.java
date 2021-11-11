package seedu.address.commons.core.listtype;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class ListType {
    public static final String MESSAGE_CONSTRAINTS = "List types should be 'rooms', 'guests' or 'records'.";
    private static final String ROOMS = "rooms";
    private static final String GUESTS = "guests";
    private static final String RECORDS = "records";

    private String type;

    /**
     * Constructs a ListType object with given type.
     *
     * @param type
     */
    public ListType(String type) {
        requireNonNull(type);
        checkArgument(isValidListType(type), MESSAGE_CONSTRAINTS);
        this.type = type;
    }

    /**
     * Returns true if a given string is a valid list type.
     */
    public static boolean isValidListType(String type) {
        return type.equals(ROOMS) || type.equals(GUESTS) || type.equals(RECORDS);
    }

    /**
     * Returns true if the ListType is a GUESTS type.
     */
    public boolean isGuestsType() {
        return this.type.equals(GUESTS);
    }

    /**
     * Returns true if the ListType is a ROOMS type.
     */
    public boolean isRoomsType() {
        return this.type.equals(ROOMS);
    }

    /**
     * Returns true if the ListType is a RECORDS type.
     */
    public boolean isRecordsType() {
        return this.type.equals(RECORDS);
    }

    /**
     * Returns true if both refer to ListType with the same argument.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListType // instanceof handles nulls
                && type.equals(((ListType) other).type)); // state check
    }
}
