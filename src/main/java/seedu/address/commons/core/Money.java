package seedu.address.commons.core;

public class Money {
    private static final float CONVERT_BETWEEN_CURRENCY = 100f;
    private int cents;

    public Money(float dollars) {
        this.cents = (int) (dollars * CONVERT_BETWEEN_CURRENCY);
    }

    public int getCents() {
        return this.cents;
    }

    public float getInDollars() {
        return (this.cents / CONVERT_BETWEEN_CURRENCY);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Money // instanceof handles nulls
                && (cents == ((Money) other).getCents())); // state check
    }

    @Override
    public String toString() {
        return "S$" + String.format("%.02f", this.getInDollars());
    }
}
