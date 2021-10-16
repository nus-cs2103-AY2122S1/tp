package seedu.academydirectory.model.student;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

public abstract class Sortable {

    public static final String MESSAGE_CONSTRAINTS = "Please use a valid sort attribute as follows: \n"
            + "RA1, "
            + "MIDTERM, "
            + "RA2, "
            + "FINAL, "
            + "AVERAGE, "
            + "PE, "
            + "name, "
            + "participation";

    public static final List<String> VALID_SORTABLE = Arrays.asList(
            "RA1",
            "MIDTERM",
            "RA2",
            "PE",
            "FINAL",
            "AVERAGE",
            "PARTICIPATION",
            "NAME"
    );

    /**
     * Returns true if a given string is a valid Sortable by checking if it is contained in
     * the VALID_SORTABLE list.
     */
    public static boolean isValidSortable(String test) {
        requireNonNull(test);
        String uppercaseTest = test.toUpperCase();
        return VALID_SORTABLE.contains(uppercaseTest);
    }

    /**
     * This nested class is to be used for the Sortable class to determine the order in which to sort.
     */
    public static class Order {

        public static final String MESSAGE_CONSTRAINTS = "Please use a valid order as follows: \n"
                + "'asc'"
                + "'desc";

        public static final List<String> VALID_ORDER = Arrays.asList("ASC", "DESC");

        /**
         * Returns true if a given string is a valid Order by checking if it is contained in
         * the VALID_ORDER list.
         */
        public static boolean isValidOrder(String test) {
            requireNonNull(test);
            String uppercaseTest = test.toUpperCase();
            return VALID_ORDER.contains(uppercaseTest);
        }
    }
}
