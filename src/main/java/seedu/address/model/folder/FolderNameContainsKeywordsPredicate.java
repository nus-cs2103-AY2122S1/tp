package seedu.address.model.folder;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Folder}'s {@code Name} matches any of the keywords given.
 */
public class FolderNameContainsKeywordsPredicate implements Predicate<Folder> {
    private final List<String> keywords;

    public FolderNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Folder folder) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsTextIgnoreCase(folder.getFolderName().folderName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FolderNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((FolderNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
