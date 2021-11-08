package seedu.smartnus.model.statistic.predicates;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import seedu.smartnus.commons.util.StringUtil;
import seedu.smartnus.model.statistic.TagStatistic;

/**
 * Tests that any of a {@code Question}'s {@code Tag}s match any of the keywords given.
 */
public class StatContainsKeywordPredicate implements Predicate<TagStatistic> {
    private final Set<String> keywords = new HashSet<>();

    /**
     * Constructs a predicate that tests if a TagStatistic contains at least one of the tags in {@code keywords}.
     * @param keywords The tag names that the user is searching for.
     */
    public StatContainsKeywordPredicate(List<String> keywords) {
        requireNonNull(keywords);
        this.keywords.addAll(keywords);
    }

    @Override
    public boolean test(TagStatistic tagStatistic) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tagStatistic.getTitle(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StatContainsKeywordPredicate // instanceof handles nulls
                && keywords.equals(((StatContainsKeywordPredicate) other).keywords)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(keywords);
    }
}

