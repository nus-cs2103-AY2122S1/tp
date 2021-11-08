package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Level of Education} matches any of the keywords given.
 */
public class LevelOfEducationContainsKeywordsPredicate implements Predicate<Person> {

    private final List<String> keywords;

    /**
     * Constructs a {@code LevelOfEducationContainsKeywordsPredicate}.
     *
     * @param keywords {@code List} of keywords to match against a {@code LevelOfEducation}.
     */
    public LevelOfEducationContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getLevelOfEducation().levelOfEducation.toLowerCase()
                        .startsWith(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LevelOfEducationContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((LevelOfEducationContainsKeywordsPredicate) other).keywords)); // state check
    }
}
