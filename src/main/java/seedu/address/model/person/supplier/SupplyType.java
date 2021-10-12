package seedu.address.model.person.supplier;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class SupplyType {

    public static final String MESSAGE_CONSTRAINTS =
            "Supply types should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String supplyType;

    /**
     * Constructs a {@code SupplyType}.
     *
     * @param supplyType A valid supply type.
     */
    public SupplyType(String supplyType) {
        requireNonNull(supplyType);
        checkArgument(isValidSupplyType(supplyType), MESSAGE_CONSTRAINTS);
        this.supplyType = supplyType;
    }

    /**
     * Returns true if a given string is a valid supply type.
     */
    public static boolean isValidSupplyType(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return this.supplyType;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SupplyType // instanceof handles nulls
                && this.supplyType.equals(((SupplyType) other).supplyType)); // state check
    }

    @Override
    public int hashCode() {
        return this.supplyType.hashCode();
    }

}
