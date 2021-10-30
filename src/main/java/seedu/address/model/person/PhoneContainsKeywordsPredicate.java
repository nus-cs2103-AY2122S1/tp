package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Phone} matches any of the phone keywords given.
 */
public class PhoneContainsKeywordsPredicate implements Predicate<Person> {
    private final List<Phone> phone;

    public PhoneContainsKeywordsPredicate(List<Phone> phone) {
        this.phone = phone;
    }

    @Override
    public boolean test(Person person) {
        return phone.stream()
                .anyMatch(phone -> person.getPhone().equals(phone));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneContainsKeywordsPredicate // instanceof handles nulls
                && phone.equals(((PhoneContainsKeywordsPredicate) other).phone)); // state check
    }
}
