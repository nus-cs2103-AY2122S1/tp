package seedu.address.model.person.predicates;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Email} matches the keywords given.
 */
public class EmailContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private final boolean isFindAll;

    /**
     * Constructor for the EmailContainsKeywordsPredicate class
     * @param keywords The keywords to compare against the {@code Person}'s {@code Email}
     * @param isFindAll True if all keywords need to match, false otherwise
     */
    public EmailContainsKeywordsPredicate(List<String> keywords, boolean isFindAll) {
        requireNonNull(keywords);
        this.keywords = keywords;
        this.isFindAll = isFindAll;
    }

    @Override
    public boolean test(Person person) {
        if (isFindAll) {
            return keywords.stream()
                    .allMatch(keyword -> person.getEmail().value.toLowerCase(Locale.ROOT)
                            .contains(keyword.toLowerCase(Locale.ROOT)));
        }
        return keywords.stream()
                .anyMatch(keyword -> person.getEmail().value.toLowerCase(Locale.ROOT)
                        .contains(keyword.toLowerCase(Locale.ROOT)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((EmailContainsKeywordsPredicate) other).keywords))
                && isFindAll == ((EmailContainsKeywordsPredicate) other).isFindAll; // state check
    }
}
