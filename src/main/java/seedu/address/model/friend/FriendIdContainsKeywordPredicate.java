package seedu.address.model.friend;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Friend}'s {@code FriendId} matches any of the keywords given.
 */
public class FriendIdContainsKeywordPredicate implements Predicate<Friend> {
    private final String keyword;

    public FriendIdContainsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    @Override
    public boolean test(Friend friend) {
        // if no keyword is specified, all friends will be listed
        if (keyword.isEmpty()) {
            return true;
        }
        return StringUtil.containsPartialWordIgnoreCase(friend.getFriendId().value, keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FriendIdContainsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((FriendIdContainsKeywordPredicate) other).keyword)); // state check
    }

}
