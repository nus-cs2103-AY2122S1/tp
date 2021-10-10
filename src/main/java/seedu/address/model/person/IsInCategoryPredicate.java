package seedu.address.model.person;

import java.util.Set;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code CategoryCode} matches the specified {@code CategoryCode}.
 */
public class IsInCategoryPredicate implements Predicate<Person> {
    private final Set<CategoryCode> categoryCodes;

    public IsInCategoryPredicate(Set<CategoryCode> categoryCodes) {
        this.categoryCodes = categoryCodes;
    }

    @Override
    public boolean test(Person person) {
        CategoryCode category = person.getCategoryCode();
        return categoryCodes.contains(category);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsInCategoryPredicate// instanceof handles nulls
                && categoryCodes.equals(((IsInCategoryPredicate) other).categoryCodes)); // state check
    }

}
