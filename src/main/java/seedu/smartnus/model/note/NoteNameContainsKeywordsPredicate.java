package seedu.smartnus.model.note;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import seedu.smartnus.commons.util.StringUtil;

/**
 * Tests that a {@code Question}'s {@code Name} matches any of the keywords given.
 */
public class NoteNameContainsKeywordsPredicate implements Predicate<Note> {
    private final List<String> keywords;

    /**
     * Constructs the NameContainsKeywordsPredicate.
     * @param keywords The keywords for the question name to be matched against.
     */
    public NoteNameContainsKeywordsPredicate(List<String> keywords) {
        requireNonNull(keywords);
        this.keywords = keywords;
    }

    @Override
    public boolean test(Note note) {
        if (keywords.isEmpty()) {
            return false;
        }
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(note.getTitle(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NoteNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NoteNameContainsKeywordsPredicate) other).keywords)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(keywords);
    }
}

