package seedu.address.model.order;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class OrderContainsKeywordsPredicate implements Predicate<Order> {
    private final List<String> keywords;

    public OrderContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    //This searches the label, date, customer for matches to the keyword.
    @Override
    public boolean test(Order order) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(order.getLabel().checkedLabel, keyword))
                || keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(order.getDate().toString(), keyword))
                || keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(order.getCustomer().getName(), keyword))
                || keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase("SO" + order.getId(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((OrderContainsKeywordsPredicate) other).keywords)); // state check
    }

}

// works but the orderlist is not refreshed. Ask cheng yi for help.
