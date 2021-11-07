package seedu.address.model.notes;

import java.util.function.Predicate;

import seedu.address.model.person.Person;


/**
 * Tests that a {@code Person}'s {@code Notes} matches the keyword given.
 */
public class NotesContainsKeywordsPredicate implements Predicate<Person> {

    private final String keyword;

    /**
     * Constructor for NotesContainsKeywordsPredicates.
     *
     * @param keyword List of words to match against the Notes of every applicant.
     */
    public NotesContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return person.getNotes().orElse(Notes.EMPTY_NOTES).toString().toLowerCase().contains(keyword.toLowerCase());
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NotesContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((NotesContainsKeywordsPredicate) other).keyword)); // state check
    }
}
