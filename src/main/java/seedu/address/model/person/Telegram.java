package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Locale;

/**
 * Represents a Person's telegram in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegram(String)}
 */
public class Telegram {

    public static final String MESSAGE_CONSTRAINTS =
            "Telegram should only contain alphabets,numbers or underscore,"
                    + " and it should be between 5 to 32 characters long ";
    public static final String VALIDATION_REGEX = "\\w{5,32}";
    public final String value;

    /**
     * Constructs a {@code Telegram}.
     *
     * @param telegram A valid telegram handle.
     */
    public Telegram(String telegram) {
        requireNonNull(telegram);
        checkArgument(isValidTelegram(telegram), MESSAGE_CONSTRAINTS);
        value = telegram;
    }

    /**
     * Returns true if a given string is a valid telegram handle.
     *
     * @param test String of telegram to be tested against the validation regex.
     * @return Boolean representation of validity of String of telegram.
     */
    public static boolean isValidTelegram(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the String representation of Telegram.
     *
     * @return String representation of Telegram.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Method to compare two Telegram objects.
     *
     * @param other is the object that is going to be compared
     *              to the Telegram object that called this method.
     * @return boolean representation of whether the Telegram
     * object is equal to the other object passed as parameter.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Telegram // instanceof handles nulls
                && value.toLowerCase(Locale.ROOT).equals((
                        (Telegram) other).value.toLowerCase(Locale.ROOT))); // state check
    }

    /**
     * Returns the {@code hashCode} of Telegram.
     *
     * @return hashCode of Telegram.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
