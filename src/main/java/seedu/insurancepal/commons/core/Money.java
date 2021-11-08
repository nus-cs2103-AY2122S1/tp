package seedu.insurancepal.commons.core;

import java.math.BigDecimal;

public class Money {
    private static final int CONVERT_BETWEEN_DOLLARS_AND_CENTS = 100;
    private BigDecimal moneyValue;

    public Money(String revenueString) {
        this.moneyValue = new BigDecimal(revenueString);
    }

    public Money(BigDecimal moneyValue) {
        this.moneyValue = moneyValue;
    }

    public boolean isNegative() {
        return moneyValue.signum() == -1;
    }

    public boolean isMoreThan(int lowerBoundary) {
        return moneyValue.compareTo(new BigDecimal(lowerBoundary)) == 1;
    }

    public Money addValue(Money otherMoney) {
        return new Money(this.moneyValue.add(otherMoney.moneyValue));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Money // instanceof handles nulls
                && moneyValue.equals(((Money) other).moneyValue));
    }

    @Override
    public String toString() {
        return "S$" + this.moneyValue.setScale(2).toString();
    }

    public static boolean isPlusSignPresent(String test) {
        return test.startsWith("+");
    }

    /**
     * Returns true if the given string is a valid input for Money. Ensure that it has up to 2 decimal places.
     *
     * @param test Given String to be checked.
     * @return A boolean that is true if the given string is a valid input for Money.
     */
    public static boolean isValidMoney(String test) {
        try {
            BigDecimal value = new BigDecimal(test);
            return Math.max(0, value.stripTrailingZeros().scale()) <= 2;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns the user input for revenue.
     *
     * @return String input by user for revenue field.
     */
    public String stringInputByUser() {
        return moneyValue.toString();
    }
}
