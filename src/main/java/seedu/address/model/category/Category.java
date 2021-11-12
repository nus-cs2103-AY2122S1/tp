package seedu.address.model.category;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Locale;

/**
 * Represents a Category in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidCategory(String)}
 */
public class Category {

    public static final String MESSAGE_CONSTRAINTS = "Category can only be one of the person attributes";
    public static final String VALIDATION_REGEX = "NAME|PHONE|EMAIL|CALLED|ADDRESS|GENDER|AGE|INTERESTS";

    public final String category;

    /**
     * Constructs a {@code Category}.
     *
     * @param category A valid category.
     */
    public Category(String category) {
        requireNonNull(category);
        String categoryUpperCase = category.toUpperCase(Locale.ROOT);
        checkArgument(isValidCategory(categoryUpperCase), MESSAGE_CONSTRAINTS);
        this.category = categoryUpperCase;
    }

    /**
     * Returns true if a given string is a valid category.
     */
    public static boolean isValidCategory(String test) {
        String testUpperCase = test.toUpperCase(Locale.ROOT);
        return testUpperCase.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Category // instanceof handles nulls
                && category.equals(((Category) other).category)); // state check
    }

    @Override
    public int hashCode() {
        return category.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + category + ']';
    }

}
