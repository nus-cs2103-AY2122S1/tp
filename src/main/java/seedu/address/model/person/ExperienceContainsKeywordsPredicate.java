package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class ExperienceContainsKeywordsPredicate implements Predicate<Person> {

    private final List<String> keywords;

    public ExperienceContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.isIntegerLargerOrEqualToValue(person.getExperience().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExperienceContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ExperienceContainsKeywordsPredicate) other).keywords)); // state check
    }
}
