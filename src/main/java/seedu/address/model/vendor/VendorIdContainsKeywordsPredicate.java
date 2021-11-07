package seedu.address.model.vendor;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Vendor}'s {@code VendorId} matches any of the keywords given.
 */
public class VendorIdContainsKeywordsPredicate implements Predicate<Vendor> {

    private final List<String> keywords;

    public VendorIdContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Vendor vendor) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(vendor.getVendorId().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VendorIdContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((VendorIdContainsKeywordsPredicate) other).keywords)); // state check
    }


}
