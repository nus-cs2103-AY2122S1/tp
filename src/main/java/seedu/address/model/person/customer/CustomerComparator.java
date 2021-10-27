package seedu.address.model.person.customer;

import java.util.Comparator;

/**
 * Represents a class to that generates customer comparators
 */
public class CustomerComparator {
    /**
     * Generates a default name comparator in ascending order.
     * @return A name comparator that sorts in ascending order.
     */
    public static Comparator<Customer> getDefaultComparator() {
        return (c1, c2) -> c1.getName().fullName.toLowerCase().compareToIgnoreCase(c2.getName().fullName);
    }

    /**
     * Generates a name comparator based on the provided order of sorting.
     * @param isAscending True if sorting is to be done in ascending order.
     * @return A name comparator with type customer.
     */
    public static Comparator<Customer> getNameComparator(boolean isAscending) {
        return (c1, c2) -> {
            if (isAscending) {
                return c1.getName().fullName.toLowerCase().compareToIgnoreCase(c2.getName().fullName);
            } else {
                return c2.getName().fullName.toLowerCase().compareToIgnoreCase(c1.getName().fullName);
            }
        };
    }

    /**
     * Generates an address comparator based on the provided order of sorting.
     * @param isAscending True if sorting is to be done in ascending order.
     * @return An address comparator with type customer.
     */
    public static Comparator<Customer> getAddressComparator(boolean isAscending) {
        return (c1, c2) -> {
            if (isAscending) {
                return c1.getAddress().value.toLowerCase().compareToIgnoreCase(c2.getAddress().value);
            } else {
                return c2.getAddress().value.toLowerCase().compareToIgnoreCase(c1.getAddress().value);
            }
        };
    }

    /**
     * Generates an email comparator based on the provided order of sorting.
     * @param isAscending True if sorting is to be done in ascending order.
     * @return An email comparator with type customer.
     */
    public static Comparator<Customer> getEmailComparator(boolean isAscending) {
        return (c1, c2) -> {
            if (isAscending) {
                return c1.getEmail().value.toLowerCase().compareToIgnoreCase(c2.getEmail().value);
            } else {
                return c2.getEmail().value.toLowerCase().compareToIgnoreCase(c1.getEmail().value);
            }
        };
    }

    /**
     * Generates a phone comparator based on the provided order of sorting.
     * @param isAscending True if sorting is to be done in ascending order.
     * @return A phone comparator with type customer.
     */
    public static Comparator<Customer> getPhoneComparator(boolean isAscending) {
        return (s1, s2) -> {
            if (isAscending) {
                return s1.getPhone().value.compareToIgnoreCase(s2.getPhone().value);
            } else {
                return s2.getPhone().value.compareToIgnoreCase(s1.getPhone().value);
            }
        };
    }

    /**
     * Generates a loyalty points comparator based on the provided order of sorting.
     * @param isAscending True if sorting is to be done in ascending order.
     * @return A loyalty point comparator with type customer.
     */
    public static Comparator<Customer> getLoyaltyPointsComparator(boolean isAscending) {
        return (c1, c2) -> {
            int c1Lp = Integer.parseInt(c1.getLoyaltyPoints().value);
            int c2Lp = Integer.parseInt(c2.getLoyaltyPoints().value);
            if (isAscending) {
                return c1Lp - c2Lp;
            } else {
                return c2Lp - c1Lp;
            }
        };
    }
}
