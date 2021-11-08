package seedu.smartnus.model.question.predicates;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import seedu.smartnus.commons.util.StringUtil;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.tag.Tag;

/**
 * Tests that any of a {@code Question}'s {@code Tag}s match any of the keywords given.
 */
public class TagsContainKeywordsPredicate implements Predicate<Question> {
    private final Set<String> keywords = new HashSet<>();

    /**
     * Constructs a predicate that tests if a question contains at least one of the tags in {@code keywords}.
     * @param keywords The tag names that the user is searching for.
     */
    public TagsContainKeywordsPredicate(List<String> keywords) {
        requireNonNull(keywords);
        this.keywords.addAll(keywords);
    }

    @Override
    public boolean test(Question question) {
        Set<Tag> tags = question.getTags();
        return keywords.stream()
                .anyMatch(keyword -> matchTagsIgnoreCase(tags, keyword));
    }

    // checks if any tag matches the given keyword
    private boolean matchTagsIgnoreCase(Set<Tag> tags, String keyword) {
        return tags.stream()
                .anyMatch(tag -> StringUtil.containsWordIgnoreCase(tag.getTagName(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagsContainKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagsContainKeywordsPredicate) other).keywords)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(keywords);
    }
}
