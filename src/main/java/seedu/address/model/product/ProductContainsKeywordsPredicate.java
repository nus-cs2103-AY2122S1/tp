package seedu.address.model.product;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Product}'s {@code Name} matches any of the keywords given.
 */
public class ProductContainsKeywordsPredicate implements Predicate<Product> {
    private final List<String> keywords;

    public ProductContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Product product) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(product.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProductContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ProductContainsKeywordsPredicate) other).keywords)); // state check
    }

}
