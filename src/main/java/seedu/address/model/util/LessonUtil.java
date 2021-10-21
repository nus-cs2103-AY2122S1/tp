package seedu.address.model.util;

public class LessonUtil {
    /**
     * This is taken exactly from implementation of {@code Fee#formateFee(String fee)}
     * Removes leading zeroes and postfixes decimal places.
     *
     * @param value A valid value that represents Money.
     * @return The formatted value.
     */
    public static String formattedValue(String value) {
        String formattedValue = value;
        if (formattedValue.startsWith("0")) { // remove all leading zeroes
            formattedValue = formattedValue.replaceFirst("^0+", "");
        }
        if (formattedValue.startsWith(".")) { // prefix missing zero that was removed
            formattedValue = "0" + formattedValue;
        }
        if (!formattedValue.isEmpty() && !formattedValue.contains(".")) { // postfix missing decimal places
            formattedValue = formattedValue + ".00";
        }
        int length = value.length();
        if (length >= 2 && value.charAt(length - 2) == '.') { // postfix missing zero
            formattedValue = formattedValue + "0";
        }
        return formattedValue;
    }

}
