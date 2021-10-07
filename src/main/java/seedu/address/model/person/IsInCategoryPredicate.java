package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code CategoryCode} matches the specified {@code CategoryCode}.
 */
public class IsInCategoryPredicate implements Predicate<Person> {
    private final CategoryCode category;

    public IsInCategoryPredicate(CategoryCode category) {
        this.category = category;
    }

    @Override
    public boolean test(Person person) {
        return person.getCategoryCode().equals(category);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsInCategoryPredicate// instanceof handles nulls
                && category.equals(((IsInCategoryPredicate) other).category)); // state check
    }

}
