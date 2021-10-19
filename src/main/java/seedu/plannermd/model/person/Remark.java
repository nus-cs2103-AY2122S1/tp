package seedu.plannermd.model.person;

import static java.util.Objects.requireNonNull;

public class Remark {

    public static final String MESSAGE_CONSTRAINTS = "Remarks can take any values, and it should not be blank";

    private static final Remark EMPTY_REMARK = new Remark("");

    public final String value;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    /**
     * Checks if value of {@code Remark} is an empty String
     *
     * @return true if value is an empty String
     */
    public Boolean isEmpty() {
        return this == EMPTY_REMARK || this.value.isEmpty();
    }

    /**
     * Returns an empty {@code Remark}
     *
     * @return A {@code Remark} which value is an empty String
     */
    public static Remark getEmptyRemark() {
        return EMPTY_REMARK;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
