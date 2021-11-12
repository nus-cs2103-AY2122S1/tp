package seedu.address.model.person.predicates;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private final boolean isFindAll;

    /**
     * Constructor for the NameContainsKeywordsPredicate class
     * @param keywords The keywords to compare against the {@code Person}'s {@code name}
     * @param isFindAll True if all keywords need to match, false otherwise
     */
    public NameContainsKeywordsPredicate(List<String> keywords, boolean isFindAll) {
        requireNonNull(keywords);
        this.keywords = keywords;
        this.isFindAll = isFindAll;
    }

    @Override
    public boolean test(Person person) {
        if (isFindAll) {
            return keywords.stream()
                    .allMatch(keyword -> person.getName().fullName.toLowerCase(Locale.ROOT)
                            .contains(keyword.toLowerCase(Locale.ROOT))
                    );
        }
        return keywords.stream()
                .anyMatch(keyword -> person.getName().fullName.toLowerCase(Locale.ROOT)
                        .contains(keyword.toLowerCase(Locale.ROOT))
                );
    }

    @Override
    public boolean equals(Object other) {
        System.out.println("HELLO");
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords))
                && isFindAll == ((NameContainsKeywordsPredicate) other).isFindAll; // state check
    }

}
