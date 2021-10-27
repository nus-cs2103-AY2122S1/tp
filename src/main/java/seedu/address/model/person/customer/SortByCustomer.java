package seedu.address.model.person.customer;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Comparator;

/**
 * Represents the types of sorting methods we can apply to suppliers in the address book.
 */
public class SortByCustomer {

    private static final String NAME = "n";
    private static final String PHONE = "p";
    private static final String ADDRESS = "a";
    private static final String EMAIL = "e";
    private static final String LOYALTY_POINTS = "lp";

    private static final String NAME_DESC = "name";
    private static final String PHONE_DESC = "phone";
    private static final String ADDRESS_DESC = "address";
    private static final String EMAIL_DESC = "email";
    private static final String LOYALTY_POINTS_DESC = "loyalty points";

    public static final String MESSAGE_CONSTRAINTS = "Sort by can only be 1 of the customer fields: "
            + NAME_DESC + "/" + PHONE_DESC + "/" + ADDRESS_DESC + "/" + EMAIL_DESC + "/" + LOYALTY_POINTS_DESC + "/"
            + " and it should not be blank";

    private final String sortBy;

    /**
     * Constructs an {@code SortByCustomer}.
     *
     * @param sortBy A valid sorting type.
     */
    public SortByCustomer(String sortBy) {
        requireNonNull(sortBy);
        checkArgument(isValidSortingOrder(sortBy), MESSAGE_CONSTRAINTS);
        this.sortBy = sortBy;
    }

    /**
     * Returns true if a given string is a valid sorting type.
     */
    public static boolean isValidSortingOrder(String test) {
        switch (test) {
        case NAME:
        case ADDRESS:
        case EMAIL:
        case PHONE:
        case LOYALTY_POINTS:
            return true;
        default:
            return false;
        }
    }

    /**
     * Returns a comparator to sort the supplier list based on the sorting type and order given.
     * @param isAscending A boolean representing if sorting order is ascending.
     * @return A comparator to sort the customer list.
     */
    public Comparator<Customer> selectComparator(boolean isAscending) {
        switch (sortBy) {
        case ADDRESS:
            return CustomerComparator.getAddressComparator(isAscending);
        case EMAIL:
            return CustomerComparator.getEmailComparator(isAscending);
        case PHONE:
            return CustomerComparator.getPhoneComparator(isAscending);
        case LOYALTY_POINTS:
            return CustomerComparator.getLoyaltyPointsComparator(isAscending);
        default:
            return CustomerComparator.getNameComparator(isAscending);
        }
    }

    @Override
    public String toString() {
        switch (sortBy) {
        case ADDRESS:
            return ADDRESS_DESC;
        case EMAIL:
            return EMAIL_DESC;
        case PHONE:
            return PHONE_DESC;
        case LOYALTY_POINTS:
            return LOYALTY_POINTS_DESC;
        default:
            return NAME_DESC;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortByCustomer // instanceof handles nulls
                && sortBy.equals(((SortByCustomer) other).sortBy)); // state check
    }

    @Override
    public int hashCode() {
        return sortBy.hashCode();
    }
}
