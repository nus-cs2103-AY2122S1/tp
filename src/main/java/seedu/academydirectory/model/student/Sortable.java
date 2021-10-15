package seedu.academydirectory.model.student;

import java.util.Arrays;
import java.util.List;

import static java.util.Objects.requireNonNull;

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

    public static boolean isValidSortable(String test) {
        requireNonNull(test);
        String uppercaseTest = test.toUpperCase();
        return VALID_SORTABLE.contains(uppercaseTest);
    }

    public static class Order {

        public static final String MESSAGE_CONSTRAINTS = "Please use a valid order as follows: \n"
                + "'asc'"
                + "'desc";

        public static final List<String> VALID_ORDER = Arrays.asList("ASC", "DESC");

        public static boolean isValidOrder(String test) {
            requireNonNull(test);
            String uppercaseTest = test.toUpperCase();
            return VALID_ORDER.contains(uppercaseTest);
        }
    }
}
