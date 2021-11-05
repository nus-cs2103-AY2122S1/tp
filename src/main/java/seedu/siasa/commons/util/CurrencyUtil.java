package seedu.siasa.commons.util;

public class CurrencyUtil {
    /**
     * Converts money in cents to dollars in string format.
     * @param moneyInCents Money in cents.
     * @return Dollars in string.
     */
    public static String centsToDollars(int moneyInCents) {
        int cents = moneyInCents % 100;
        int dollars = (moneyInCents - cents) / 100;

        String centsStr;
        if (cents <= 9) {
            centsStr = 0 + "" + cents;
        } else {
            centsStr = Integer.toString(cents);
        }

        return "$" + dollars + "." + centsStr;
    }
}
