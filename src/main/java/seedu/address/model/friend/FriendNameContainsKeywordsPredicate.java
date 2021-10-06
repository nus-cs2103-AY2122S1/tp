package seedu.address.model.friend;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Friend}'s {@code FriendName} matches any of the keywords given.
 */
public class FriendNameContainsKeywordsPredicate implements Predicate<Friend> {
    private final List<String> keywords;

    public FriendNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Friend friend) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(friend.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FriendNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((FriendNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
