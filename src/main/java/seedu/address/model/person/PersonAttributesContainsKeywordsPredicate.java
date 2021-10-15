package seedu.address.model.person;

import static seedu.address.commons.util.StringUtil.containsPartialWordIgnoreCase;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.parser.ArgumentMultimap;

/**
 * Tests that a {@code Person}'s attributes matches any of the keywords given.
 */
public class PersonAttributesContainsKeywordsPredicate implements Predicate<Person> {
    private final ArgumentMultimap keywords;

    public PersonAttributesContainsKeywordsPredicate(ArgumentMultimap keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        String[] generalKeywords = keywords.getPreamble().split(" ");

        boolean isMatching = Arrays.stream(generalKeywords).allMatch(attribute -> {
            boolean isAnyAttributePresent = Stream.of(person.getName().fullName, person.getPhone().value,
                    person.getLanguage().value, person.getAddress().value, person.getVisit().get().value,
                    person.getLastVisit().get().value)
                    .anyMatch(matching -> containsPartialWordIgnoreCase(matching, attribute));
            boolean isAnyTagPresent = person.getTags().stream()
                    .anyMatch(matching -> containsPartialWordIgnoreCase(matching.tagName, attribute));

            return isAnyAttributePresent || isAnyTagPresent;
        });

        return isMatching;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonAttributesContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonAttributesContainsKeywordsPredicate) other).keywords)); // state check
    }

}
