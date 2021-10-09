package seedu.address.model.person;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class RiskAppetite {

    public static final String MESSAGE_CONSTRAINTS =
    "Risk Appetite number should be a single digit integer between 1 and 5";
    public static final String VALIDATION_REGEX = "\\d{1,1}";
    public final String value;

    /**
     * Constructs a {@code Risk Appetite}.
     *
     * @param riskAppetite A valid risk appetite number.
     */
    public RiskAppetite(String riskAppetite) {
        requireNonNull(riskAppetite);
        checkArgument(isValidRiskAppetite(riskAppetite), MESSAGE_CONSTRAINTS);
        value = riskAppetite;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidRiskAppetite(String test) {

        if (test.matches(VALIDATION_REGEX)) {
            try {
                int validInt = parseInt(test);
                return validInt > 0 && validInt < 6;


            } catch (NumberFormatException e) {
                return false;
            }

        } else {
            return false;
        }

    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RiskAppetite // instanceof handles nulls
                && value.equals(((RiskAppetite) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
