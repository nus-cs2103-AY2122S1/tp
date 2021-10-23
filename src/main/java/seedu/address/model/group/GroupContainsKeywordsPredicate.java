package seedu.address.model.group;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Group}'s {@code Name} matches any of the keywords given.
 */
public class GroupContainsKeywordsPredicate implements Predicate<Group> {
    private final List<String> keywords;
    private List<String> keywordsClone = new ArrayList<>();

    public GroupContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Ignores some special characters in input keyword
     */
    private void removeSpecialCharacters() {
        for (String keyword : keywords) {
            String newKeyword = keyword.replaceAll("/^[\\w-]+$/", "");
            keywordsClone.add(newKeyword);
        }
    }

    @Override
    public boolean test(Group group) {
        removeSpecialCharacters();
        return keywordsClone.stream()
                .anyMatch(keyword -> StringUtil.containsSubstringIgnoreCase(group.getName().name, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((GroupContainsKeywordsPredicate) other).keywords)); // state check
    }

}
