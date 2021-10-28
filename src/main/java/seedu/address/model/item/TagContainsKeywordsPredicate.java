package seedu.address.model.item;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Item}'s {@code tag} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Item> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Item item) {
        int lengthOfItemTag = item.getTags().toString().length();
        String itemTag = item.getTags().toString().substring(2, lengthOfItemTag - 2);
        boolean multipleWord = keywords.stream()
                .anyMatch(keyword -> StringUtil.containsMultipleWord(itemTag, keyword));
        return multipleWord;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }

}
