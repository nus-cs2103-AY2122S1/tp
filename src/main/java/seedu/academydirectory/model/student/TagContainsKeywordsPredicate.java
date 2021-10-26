package seedu.academydirectory.model.student;

import java.util.List;

import seedu.academydirectory.commons.util.StringUtil;

/**
 * Tests that a {@code Student}'s {@code Tag} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate extends InformationContainsKeywordsPredicate {

    public TagContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Student student) {
        return this.getKeywords().stream()
                .anyMatch(keyword -> student.getTags().stream()
                        .anyMatch(tag -> StringUtil.containsWordIgnoreCase(tag.tagName, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && this.getKeywords().equals(((TagContainsKeywordsPredicate) other).getKeywords())); // state check
    }
}
