package socialite.model.handle;

import java.util.Optional;

import socialite.commons.util.AppUtil;

/**
 * Represents a Person's Telegram handle in SociaLite.
 * Guarantees: immutable; handle is valid as declared in {@link #isValidHandle(String)}
 */
public class Telegram extends Handle {

    public static final String MESSAGE_CONSTRAINTS =
            "Telegram handles should only contain alphanumeric characters and underscores.\n"
            + "They should also be at least 5 characters long.\n"
            + "Furthermore, the handle cannot start or end with an underscore.\n"
            + "Consecutive underscores cannot appear in the handle either.\n"
            + "https://telegram.org/faq#q-what-can-i-use-as-my-username";
    public static final String VALIDATION_REGEX = "^((?!.*^_)[a-zA-Z0-9_](?!.*__)(?!.*_$)){5,}$";

    /**
     * Constructs a {@code Telegram}.
     *
     * @param handle A valid Telegram handle.
     */
    public Telegram(String handle) {
        if (handle != null && !handle.equals("")) {
            AppUtil.checkArgument(isValidHandle(handle), MESSAGE_CONSTRAINTS);
        }
        value = Optional.ofNullable(handle);
    }

    /**
     * Returns if a given handle is a valid Telegram handle.
     */
    public static boolean isValidHandle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String getUrl() {
        return "https://www.t.me/" + this.get();
    }

    @Override
    public String toString() {
        return value.orElse("");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Telegram // instanceof handles nulls
                && value.equals(((Telegram) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
