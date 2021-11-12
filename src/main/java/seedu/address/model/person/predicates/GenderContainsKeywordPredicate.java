package seedu.address.model.person.predicates;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Gender} matches the gender given.
 */
public class GenderContainsKeywordPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private final boolean isFindAll;

    /**
     * Constructor for the GenderContainsKeywordPredicate class
     * @param keywords The keywords to compare against the {@code Person}'s {@code Gender}
     * @param isFindAll True if all keywords need to match, false otherwise
     */
    public GenderContainsKeywordPredicate(List<String> keywords, boolean isFindAll) {
        requireNonNull(keywords);
        this.keywords = keywords;
        this.isFindAll = isFindAll;
    }

    @Override
    public boolean test(Person person) {
        if (isFindAll) {
            return keywords.stream()
                    .allMatch(keyword -> person.getGender().value.toLowerCase(Locale.ROOT).contains(
                            getGenderValueFromKeyword(keyword)
                    ));
        }
        return keywords.stream()
                .anyMatch(keyword -> person.getGender().value.toLowerCase(Locale.ROOT).contains(
                        getGenderValueFromKeyword(keyword)
                ));
    }

    /**
     * Allows more flexibility for user inputs
     * @param keyword entered by user
     * @return m if input is m or male
     *          f if its f or female
     */
    private String getGenderValueFromKeyword(String keyword) {
        String test = keyword.toLowerCase(Locale.ROOT);
        if (test.equals("m") || test.equals("male")) {
            return "m";
        } else if (test.equals("f") || test.equals("female")) {
            return "f";
        } else if (test.equals("n.a")) {
            return ("n.a");
        } else {
            // Basically return anything that is not 'm' / 'f' / 'N.A'
            // so that the predicate will return false
            return "THIS IS NOT A GENDER";
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GenderContainsKeywordPredicate // instanceof handles nulls
                && keywords.equals(((GenderContainsKeywordPredicate) other).keywords))
                && isFindAll == ((GenderContainsKeywordPredicate) other).isFindAll; // state check
    }
}
