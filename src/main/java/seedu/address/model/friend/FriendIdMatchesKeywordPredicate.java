package seedu.address.model.friend;

import java.util.function.Predicate;

/**
 * Tests that a {@code Friend}'s {@code FriendId} matches the keywords given.
 */
public class FriendIdMatchesKeywordPredicate implements Predicate<Friend> {
    private final String keyword;

    public FriendIdMatchesKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    @Override
    public boolean test(Friend friend) {
        return keyword.equalsIgnoreCase(friend.getFriendId().value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FriendIdMatchesKeywordPredicate // instanceof handles nulls
                && keyword.equalsIgnoreCase(((FriendIdMatchesKeywordPredicate) other).keyword)); // state check
    }

}
