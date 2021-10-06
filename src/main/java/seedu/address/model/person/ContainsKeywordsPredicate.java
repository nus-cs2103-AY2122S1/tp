package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s field matches any of the keywords given.
 */
public class ContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private final PersonField field;

    public enum PersonField {
        NAME, PHONE, EMAIL, ADDRESS
    }

    public ContainsKeywordsPredicate(List<String> keywords, PersonField field) {
        this.keywords = keywords;
        this.field = field;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(getPersonField(person), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContainsKeywordsPredicate // instanceof handles nulls
                        && keywords.equals(((ContainsKeywordsPredicate) other).keywords)); // state check
    }

    private String getPersonField(Person person) {
        switch (field) {
        case NAME:
            return person.getName().fullName;
        case PHONE:
            return person.getPhone().value;
        case EMAIL:
            return person.getEmail().value;
        case ADDRESS:
            return person.getAddress().value;
        default:
            return null;
        }
    }
}
