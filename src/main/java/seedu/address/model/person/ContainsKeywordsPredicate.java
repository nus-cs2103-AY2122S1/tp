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

    /**
     * Constructs a {@link ContainsKeywordsPredicate}.
     *
     * @param keywords for any-type matching
     * @param field field to test
     */
    public ContainsKeywordsPredicate(List<String> keywords, PersonField field) {
        this.keywords = keywords;
        this.field = field;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(field.ofPersonString(person), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContainsKeywordsPredicate // instanceof handles nulls
                        && keywords.equals(((ContainsKeywordsPredicate) other).keywords)); // state check
    }

    public enum PersonField {
        NAME, PHONE, EMAIL, ADDRESS, TAG;

        private String ofPersonString(Person person) {
            switch (this) {
            case NAME:
                return person.getName().fullName;
            case PHONE:
                return person.getPhone().value;
            case EMAIL:
                return person.getEmail().value;
            case ADDRESS:
                return person.getAddress().value;
            case TAG:
                return person.getTags().stream().map(t -> t.tagName).reduce("", (x, y) -> x + " " + y);
            default:
                return null;
            }
        }

        @Override
        public String toString() {
            return StringUtil.capitalizeFirstLetter(name().toLowerCase());
        }
    }

}
