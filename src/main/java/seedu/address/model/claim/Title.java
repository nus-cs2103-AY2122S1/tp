package seedu.address.model.claim;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Title implements Comparable<Title>{
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum}]*";
    public static final String MESSAGE_CONSTRAINTS = "Title should only contain alphanumeric letters";

    private final String title;

    public Title(String title) {
        requireNonNull(title);
        checkArgument(isValidTitle(title), MESSAGE_CONSTRAINTS);
        this.title = title;
    }

    public static boolean isValidTitle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.title;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Title // instanceof handles nulls
                && this.title.equals(((Title) other).title)); // state check
    }

    @Override
    public int compareTo(Title o) {
        return this.title.compareTo(o.title);
    }
}
