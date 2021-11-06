package seedu.siasa.commons.util;

import java.text.DecimalFormat;

public class CurrencyUtil {
    /**
     * Converts money in cents to dollars in string format.
     * @param moneyInCents Money in cents.
     * @return Dollars in string.
     */
    public static String centsToDollars(double moneyInCents) {
        DecimalFormat integerFormat = new DecimalFormat("#");
        double cents = moneyInCents % 100;
        double dollars = (moneyInCents - cents) / 100;

        String centsStr;
        if (cents <= 9) {
            centsStr = 0 + "" + integerFormat.format(cents);
        } else {
            centsStr = integerFormat.format(cents);
        }

        return "$" + integerFormat.format(dollars) + "." + centsStr;
    }
}
