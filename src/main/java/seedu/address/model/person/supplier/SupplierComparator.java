package seedu.address.model.person.supplier;

import java.util.Comparator;

/**
 * Represents a class to that generates supplier comparators
 */
public class SupplierComparator {

    /**
     * Generates a default name comparator in ascending order.
     * @return A name comparator that sorts in ascending order.
     */
    public static Comparator<Supplier> getDefaultComparator() {
        return (s1, s2) -> s1.getName().fullName.toLowerCase().compareToIgnoreCase(s2.getName().fullName);
    }

    /**
     * Generates a name comparator based on the provided order of sorting.
     * @param isAscending True if sorting is to be done in ascending order.
     * @return A name comparator with type supplier.
     */
    public static Comparator<Supplier> getNameComparator(boolean isAscending) {
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
    public static Comparator<Supplier> getAddressComparator(boolean isAscending) {
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
    public static Comparator<Supplier> getEmailComparator(boolean isAscending) {
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
    public static Comparator<Supplier> getPhoneComparator(boolean isAscending) {
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
    public static Comparator<Supplier> getSupplyTypeComparator(boolean isAscending) {
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
    public static Comparator<Supplier> getDeliveryDetailsComparator(boolean isAscending) {
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
