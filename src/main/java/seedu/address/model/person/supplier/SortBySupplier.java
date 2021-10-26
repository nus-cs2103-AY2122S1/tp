package seedu.address.model.person.supplier;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Comparator;

/**
 * Represents the types of sorting methods we can apply to suppliers in the address book.
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

    public static final String MESSAGE_CONSTRAINTS = "Sort by can only be 1 of the supplier fields: "
            + NAME_DESC + "/" + PHONE_DESC + "/" + ADDRESS_DESC + "/" + EMAIL_DESC + "/" + SUPPLY_TYPE_DESC + "/"
            + DELIVERY_DETAILS_DESC + " and it should not be blank";

    private final String sortBy;

    /**
     * Constructs an {@code Address}.
     *
     * @param sortBy A valid sorting type.
     */
    public SortBySupplier(String sortBy) {
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
    public Comparator<Supplier> selectComparator(boolean isAscending) {
        switch (sortBy) {
        case ADDRESS:
            return getAddressComparator(isAscending);
        case EMAIL:
            return getEmailComparator(isAscending);
        case PHONE:
            return getPhoneComparator(isAscending);
        case SUPPLY_TYPE:
            return getSupplyTypeComparator(isAscending);
        case DELIVERY_DETAILS:
            return getDeliveryDetailsComparator(isAscending);
        default:
            return getNameComparator(isAscending);
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

    /**
     * Generates a name comparator based on the provided order of sorting.
     * @param isAscending True if sorting is to be done in ascending order.
     * @return A name comparator with type supplier.
     */
    private Comparator<Supplier> getNameComparator(boolean isAscending) {
        return (s1, s2) -> {
            if (isAscending) {
                return s1.getName().fullName.toLowerCase().compareToIgnoreCase(s2.getName().fullName);
            } else {
                return s2.getName().fullName.toLowerCase().compareToIgnoreCase(s1.getName().fullName);
            }
        };
    }

    /**
     * Generates an address comparator based on the provided order of sorting.
     * @param isAscending True if sorting is to be done in ascending order.
     * @return An address comparator with type supplier.
     */
    private Comparator<Supplier> getAddressComparator(boolean isAscending) {
        return (s1, s2) -> {
            if (isAscending) {
                return s1.getAddress().value.toLowerCase().compareToIgnoreCase(s2.getAddress().value);
            } else {
                return s2.getAddress().value.toLowerCase().compareToIgnoreCase(s1.getAddress().value);
            }
        };
    }

    /**
     * Generates an email comparator based on the provided order of sorting.
     * @param isAscending True if sorting is to be done in ascending order.
     * @return An email comparator with type supplier.
     */
    private Comparator<Supplier> getEmailComparator(boolean isAscending) {
        return (s1, s2) -> {
            if (isAscending) {
                return s1.getEmail().value.toLowerCase().compareToIgnoreCase(s2.getEmail().value);
            } else {
                return s2.getEmail().value.toLowerCase().compareToIgnoreCase(s1.getEmail().value);
            }
        };
    }

    /**
     * Generates a phone comparator based on the provided order of sorting.
     * @param isAscending True if sorting is to be done in ascending order.
     * @return A phone comparator with type supplier.
     */
    private Comparator<Supplier> getPhoneComparator(boolean isAscending) {
        return (s1, s2) -> {
            if (isAscending) {
                return s1.getPhone().value.compareToIgnoreCase(s2.getPhone().value);
            } else {
                return s2.getPhone().value.compareToIgnoreCase(s1.getPhone().value);
            }
        };
    }

    /**
     * Generates a supplyType comparator based on the provided order of sorting.
     * @param isAscending True if sorting is to be done in ascending order.
     * @return A supplyType comparator with type supplier.
     */
    private Comparator<Supplier> getSupplyTypeComparator(boolean isAscending) {
        return (s1, s2) -> {
            if (isAscending) {
                return s1.getSupplyType().supplyType.toLowerCase()
                        .compareToIgnoreCase(s2.getSupplyType().supplyType);
            } else {
                return s2.getSupplyType().supplyType.toLowerCase()
                        .compareToIgnoreCase(s1.getSupplyType().supplyType);
            }
        };
    }

    /**
     * Generates a deliveryDetail comparator based on the provided order of sorting.
     * @param isAscending True if sorting is to be done in ascending order.
     * @return A deliveryDetail comparator with type supplier.
     */
    private Comparator<Supplier> getDeliveryDetailsComparator(boolean isAscending) {
        return (s1, s2) -> {
            if (isAscending) {
                return s1.getDeliveryDetails().deliveryDetails
                        .compareTo(s2.getDeliveryDetails().deliveryDetails);
            } else {
                return s2.getDeliveryDetails().deliveryDetails
                        .compareTo(s1.getDeliveryDetails().deliveryDetails);
            }
        };
    }

}
