package seedu.address.model.person.comparators;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class SortDirection {

    private String direction;

    public static final String MESSAGE_CONSTRAINTS = "Sort direction should either be asc or dsc";

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

}
