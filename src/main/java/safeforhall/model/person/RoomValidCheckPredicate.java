package safeforhall.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Room} matches either fully, by block, by level, or both.
 */
public class RoomValidCheckPredicate implements Predicate<Person> {

    private final String input;

    public RoomValidCheckPredicate(String input) {
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
                return input.equals(block);
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
