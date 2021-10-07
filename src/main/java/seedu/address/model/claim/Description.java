package seedu.address.model.claim;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Description {
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum}\\p{Punct}]*";
    public static final String MESSAGE_CONSTRAINTS = "Description should only contain alphanumeric letters";

    private final String description;

    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && this.description.equals(((Description) other).description)); // state check
    }
}
