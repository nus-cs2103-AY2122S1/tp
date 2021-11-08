package seedu.insurancepal.commons.core;

public class Money {
    private static final int CONVERT_BETWEEN_DOLLARS_AND_CENTS = 100;
    private int dollars;
    private int cents;
    private boolean isNegative;

    public Money(int dollars, int cents, boolean isNegative) {
        this.dollars = dollars;
        this.cents = cents;
        this.isNegative = isNegative;
    }

    public boolean isNegative() {
        return this.isNegative;
    }

    public int getDollars() {
        return this.dollars;
    }

    public int getCents() {
        return this.cents;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Money // instanceof handles nulls
                && (cents == ((Money) other).cents)
                && (dollars == ((Money) other).dollars)); // state check
    }

    @Override
    public String toString() {
        assert this.cents < 100 : "Final cents display should be less than 100.";
        return "S$" + this.dollars + "." + String.format("%02d", this.cents);
    }

    /**
     * Returns the user input for revenue.
     *
     * @return String input by user for revenue field.
     */
    public String stringInputByUser() {
        if (this.isNegative) {
            return "-" + this.dollars + "." + this.cents;
        } else {
            return this.dollars + "." + this.cents;
        }
    }
}
