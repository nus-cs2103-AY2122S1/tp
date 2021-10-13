package seedu.address.model.person.comparators;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class SortDirection {
    public static final String MESSAGE_CONSTRAINTS = "Sort direction should either be asc or dsc";

    private String direction;

    /**
     * @param direction to sort by. Either asc or dsc.
     */
    public SortDirection(String direction) {
        requireNonNull(direction);
        checkArgument(isValidDirection(direction), MESSAGE_CONSTRAINTS);
        this.direction = direction;
    }

    /**
     * Returns true if a given string is a valid sorting direction.
     */
    public static boolean isValidDirection(String test) {
        return test.toLowerCase().equals("asc") || test.toLowerCase().equals("dsc");
    }

    public boolean isAscending() {
        return direction.toLowerCase().equals("asc");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortDirection // instanceof handles nulls
                && direction.equals(((SortDirection) other).direction)); // state check
    }


}
