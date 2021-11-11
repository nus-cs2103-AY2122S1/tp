package seedu.address.model.residency.exceptions;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.residency.Residency;

public class ResidencyContainsKeywordsPredicate implements Predicate<Residency> {

    private final List<String> keywords;

    public ResidencyContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Residency residency) {
        return keywords.stream()
                .map(keyword -> keyword.toLowerCase())
                .allMatch(keyword -> residency.toString().toLowerCase().contains(keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ResidencyContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ResidencyContainsKeywordsPredicate) other).keywords)); // state check
    }
}
