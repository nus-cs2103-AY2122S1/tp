package seedu.address.model.room;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Room}'s {@code RoomNumber} matches any of the keywords given.
 */
public class RoomNumberContainsKeywordsPredicate implements Predicate<Room> {
    private final List<String> keywords;

    public RoomNumberContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Room room) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(room.getRoomNumber().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoomNumberContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((RoomNumberContainsKeywordsPredicate) other).keywords)); // state check
    }
}
