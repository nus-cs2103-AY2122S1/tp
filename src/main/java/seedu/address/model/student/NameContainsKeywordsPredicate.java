package seedu.address.model.student;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Student> {
    private final List<String> keywords;
    private List<String> keywordsClone = new ArrayList<>();

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Ignores special characters in input keyword
     */
    private void removeSpecialCharacters() {
        for (String keyword : keywords) {
            String newKeyword = keyword.replaceAll("\\W", "");
            keywordsClone.add(newKeyword);
        }
    }

    @Override
    public boolean test(Student student) {
        removeSpecialCharacters();
        return keywordsClone.stream()
                .anyMatch(keyword -> StringUtil.containsSubstringIgnoreCase(student.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
