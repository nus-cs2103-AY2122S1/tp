package seedu.address.model.game;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Game}'s {@code GameId} matches any of the keywords given.
 */
public class GameIdContainsKeywordPredicate implements Predicate<Game> {
    private final String keyword;

    public GameIdContainsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    @Override
    public boolean test(Game game) {
        // if no keyword is specified, all games will be listed
        if (keyword.isEmpty()) {
            return true;
        }
        return StringUtil.containsPartialWordIgnoreCase(game.getGameId().value, keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GameIdContainsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((GameIdContainsKeywordPredicate) other).keyword)); // state check
    }

}
