package safeforhall.model.person;

import static safeforhall.commons.util.AppUtil.checkArgument;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Room} matches either fully, by block, by level, or both.
 */
public class RoomValidCheckPredicate implements Predicate<Person> {

    private final String input;

    /**
     * Validates the string input for room and returns the RoomValidCheckPredicate instance
     * or throws an exception
     * @param input the user input
     */
    public RoomValidCheckPredicate(String input) {
        checkArgument(Room.isValidRoomForFind(input), Room.MESSAGE_CONSTRAINTS_FOR_FIND);
        this.input = input;
    }

    @Override
    public boolean test(Person person) {
        String room = person.getRoom().room;
        String block = room.substring(0, 1);
        String level = room.substring(1, 2);
        if (input.length() == 1) {
            try {
                Integer.parseInt(input);
                return input.equals(level);
            } catch (NumberFormatException e) {
                return input.equalsIgnoreCase(block);
            }
        } else if (input.length() == 2) {
            return input.equalsIgnoreCase(block + level);
        } else {
            return input.equalsIgnoreCase(room);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoomValidCheckPredicate // instanceof handles nulls
                && input.equals(((RoomValidCheckPredicate) other).input)); // state check
    }
}
