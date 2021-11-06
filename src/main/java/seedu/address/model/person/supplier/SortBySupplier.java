package seedu.address.model.person.supplier;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Comparator;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents the types of sorting methods we can apply to suppliers in RHRH.
 */
public class SortBySupplier {

    private static final String NAME = "n";
    private static final String PHONE = "p";
    private static final String ADDRESS = "a";
    private static final String EMAIL = "e";
    private static final String SUPPLY_TYPE = "st";
    private static final String DELIVERY_DETAILS = "dd";

    private static final String NAME_DESC = "name";
    private static final String PHONE_DESC = "phone";
    private static final String ADDRESS_DESC = "address";
    private static final String EMAIL_DESC = "email";
    private static final String SUPPLY_TYPE_DESC = "supply type";
    private static final String DELIVERY_DETAILS_DESC = "delivery details";

    public static final String MESSAGE_CONSTRAINTS = "Sort by can only be 1 of the supplier field prefixes: "
            + NAME + "/" + PHONE + "/" + ADDRESS + "/" + EMAIL + "/" + SUPPLY_TYPE + "/"
            + DELIVERY_DETAILS + " and it should not be blank";

    private final String sortBy;

    /**
     * Constructs an {@code SortBySupplier}.
     *
     * @param sortBy A valid sorting type.
     */
    public SortBySupplier(String sortBy) {
        requireNonNull(sortBy);
        checkArgument(isValidSortBy(sortBy), MESSAGE_CONSTRAINTS);
        this.sortBy = sortBy;
    }

    /**
     * Returns true if a given string is a valid sorting type.
     */
    public static boolean isValidSortBy(String test) {
        switch (test) {
        case NAME:
        case ADDRESS:
        case EMAIL:
        case PHONE:
        case SUPPLY_TYPE:
        case DELIVERY_DETAILS:
            return true;
        default:
            return false;
        }
    }

    /**
     * Returns a comparator to sort the supplier list based on the sorting type and order given.
     * @param isAscending A boolean representing if sorting order is ascending.
     * @return A comparator to sort the supplier list.
     */
    public Comparator<Supplier> selectComparator(boolean isAscending) throws ParseException {
        switch (sortBy) {
        case NAME:
            return SupplierComparator.getNameComparator(isAscending);
        case ADDRESS:
            return SupplierComparator.getAddressComparator(isAscending);
        case EMAIL:
            return SupplierComparator.getEmailComparator(isAscending);
        case PHONE:
            return SupplierComparator.getPhoneComparator(isAscending);
        case SUPPLY_TYPE:
            return SupplierComparator.getSupplyTypeComparator(isAscending);
        case DELIVERY_DETAILS:
            return SupplierComparator.getDeliveryDetailsComparator(isAscending);
        default:
            throw new ParseException(MESSAGE_CONSTRAINTS);
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
        case SUPPLY_TYPE:
            return SUPPLY_TYPE_DESC;
        case DELIVERY_DETAILS:
            return DELIVERY_DETAILS_DESC;
        default:
            return NAME_DESC;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortBySupplier // instanceof handles nulls
                && sortBy.equals(((SortBySupplier) other).sortBy)); // state check
    }

    @Override
    public int hashCode() {
        return sortBy.hashCode();
    }
}
